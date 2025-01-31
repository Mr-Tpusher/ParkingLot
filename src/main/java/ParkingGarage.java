import constants.Gates;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.*;

@Data
public class ParkingGarage {
    private String name;
    private int totalParkingSpots;
    private int availableParkingSpots;
    private final Set<ParkingSpot> parkingSpots;
    private Map<Gates, EntryGate> entryGates;
    private Map<Gates, ExitGate> exitGates;
    private Map<UUID, Ticket> tickets;
    // For future use
    private List<ParkingLevel> parkingLevels;
    private IParkingStrategy parkingStrategy;
    private IChargingStrategy chargingStrategy;


    public ParkingGarage(String name, int totalParkingSpots) {
        this.name = name;
        this.totalParkingSpots = totalParkingSpots;
        this.availableParkingSpots = totalParkingSpots;
        this.parkingSpots = new TreeSet<>(Comparator.comparing(ParkingSpot::getId));
        this.tickets = new HashMap<>();

        for (int i = 1; i <= totalParkingSpots; i++) {
            parkingSpots.add(new ParkingSpot(i));
        }

        // Since initially parking and charging strategy is same across the garage
        parkingStrategy = new ParkNearestToEntry();
        chargingStrategy = new BaseHourlyCharge(100);
    }

    public void setEntryGate(Gates gate, EntryGate entryGate) {
        if (entryGates == null) {
            entryGates = new HashMap<>();
        }
        entryGates.put(gate, entryGate);
    }

    public void setExitGate(Gates gate, ExitGate exitGate) {
        if (exitGates == null) {
            exitGates = new HashMap<>();
        }
        exitGates.put(gate, exitGate);
    }


    public Ticket park(Vehicle vehicle) {
        Ticket ticket = parkingStrategy.park(vehicle, this.parkingSpots);
        System.out.println("vehicle:" + vehicle.getRegNumber() + "  spot:" + ticket.getParkingSpot().getId());
        return ticket;
    }


    public Ticket park(Vehicle vehicle, Gates gate) {

        // If the parking is full
      /*  if (!isSpotAvailable()) {
            System.out.println("No spots available.");
            return null;
        }*/
        String threadName = Thread.currentThread().getName();
        EntryGate entryGate = entryGates.get(gate);

        Ticket ticket = entryGate.park(vehicle, this);
        if (ticket != null) {
            return ticket;
        } else {
            System.out.println(threadName + " : fully occupied.");
            return null;
        }
    }

    public double exit(UUID ticketId) {
        double charge =  chargingStrategy.charge();
        Ticket ticket = tickets.get(ticketId);
        ticket.setCharge(charge);
        ticket.setOutTime(LocalDateTime.now());

        // Update the parking spot
        ParkingSpot p = ticket.getParkingSpot();
        p.setOccupied(false);
        p.setVehicle(null);
        // also add to the gates
        for (EntryGate gate : entryGates.values()) {
            gate.addParkingSpot(p);
        }

        // increment count
        setAvailableParkingSpots(getAvailableParkingSpots() + 1);

        return charge;

    }

    public synchronized boolean isSpotAvailable() {
        System.out.println(Thread.currentThread().getName() + " isSpotAvailable() : availableParkingSpots=" + availableParkingSpots);
        return availableParkingSpots > 0;
    }

    public synchronized int getAvailableParkingSpots() {
        System.out.println(Thread.currentThread().getName() +
                " : getAvailableParkingSpots() : availableParkingSpots=" + availableParkingSpots);
        return availableParkingSpots;
    }

    public synchronized void setAvailableParkingSpots(int availableParkingSpots) {
        System.out.println(Thread.currentThread().getName() +
                " : setAvailableParkingSpots() : availableParkingSpots=" + availableParkingSpots);
        this.availableParkingSpots = availableParkingSpots;
    }

    public void printParkingSpots() {
        System.out.println("id   : vehicle");
        for (ParkingSpot p : parkingSpots) {
            System.out.println(p.getId() + "   :  " +
                    (p.isOccupied() ? p.getVehicle().getRegNumber() : "     "));
        }
    }
}
