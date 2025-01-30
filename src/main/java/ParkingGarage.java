import lombok.Data;

import java.time.LocalDateTime;
import java.util.*;

@Data
public class ParkingGarage {
    String name;
    String company;
    String address;
    int totalParkingSpots;
    int availableParkingSpots;
    //private List<ParkingLevel> parkingLevels;
    private final Set<ParkingSpot> parkingSpots;
    List<EntryGate> entryGates;
    List<ExitGate> exitGates;

    IParkingStrategy parkingStrategy;
    IChargingStrategy chargingStrategy;


    public ParkingGarage(String name, int totalParkingSpots) {
        this.name = name;
        this.totalParkingSpots = totalParkingSpots;
        this.availableParkingSpots = totalParkingSpots;
        this.parkingSpots = new TreeSet<>(Comparator.comparing(ParkingSpot::getId));

        for (int i = 1; i <= totalParkingSpots; i++) {
            parkingSpots.add(new ParkingSpot(i));
        }

        // Since initially parking and charging strategy is same across the garage
        parkingStrategy = new ParkNearestToEntry();
        chargingStrategy = new BaseHourlyCharge(100);
    }

    public void setEntryGate(EntryGate entryGate) {
        if (entryGates == null) {
            entryGates = new ArrayList<>();
        }
        entryGates.add(entryGate);
    }

    public void setExitGate(ExitGate exitGate) {
        if (exitGates == null) {
            exitGates = new ArrayList<>();
        }
        exitGates.add(exitGate);
    }


    public Ticket park(VehicleDetails vehicleDetails) {
        Ticket ticket = parkingStrategy.park(vehicleDetails, this.parkingSpots);
        System.out.println("vehicle:" + vehicleDetails.getRegNumber() + "  spot:" + ticket.getParkingSpot().getId());
        return ticket;
    }

    public Ticket park_v1(VehicleDetails vehicleDetails) {
        return null;
    }

    public double exit(Ticket ticket) {
        double charge =  chargingStrategy.charge();
        ticket.setCharge(charge);
        ticket.setOutTime(LocalDateTime.now());
        parkingSpots.add(ticket.getParkingSpot());
        return charge;
    }

    boolean isSpotAvailable() {
        // check if a slot is available
        return true;
    }
}
