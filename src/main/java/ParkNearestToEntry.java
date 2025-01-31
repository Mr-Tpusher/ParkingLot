import java.util.Set;
import java.util.TreeSet;

public class ParkNearestToEntry implements IParkingStrategy {
 /*   @Override
    public Ticket park(VehicleDetails vehicleDetails) {
        return new Ticket(vehicleDetails);
    }*/

    @Override
    public Ticket park(Vehicle vehicle, Set<ParkingSpot> parkingSpots) {
        Ticket ticket = new Ticket(vehicle);
        TreeSet<ParkingSpot> parkingSpots2 = (TreeSet<ParkingSpot>) parkingSpots;
        ticket.setParkingSpot(parkingSpots2.first());
        parkingSpots.remove(parkingSpots2.first());
        return ticket;
    }


    public Ticket park(Vehicle vehicle, ParkingGarage parkingGarage, EntryGate entryGate) {

        synchronized (parkingGarage) {
        if (parkingGarage.isSpotAvailable()) {
            TreeSet<ParkingSpot> gateParkingSpots = entryGate.getParkingSpots();

            ParkingSpot p = gateParkingSpots.first();
            while (!p.isAvailable()) {
                gateParkingSpots.remove(gateParkingSpots.first());
                p = gateParkingSpots.first();
            }
            if (p.book(vehicle)) {
                for (EntryGate gate : parkingGarage.getEntryGates().values()) {
                    gate.getParkingSpots().remove(p);
                }
                Ticket ticket = new Ticket(vehicle);
                ticket.setParkingSpot(p);

                System.out.println(Thread.currentThread().getName()
                        + " : vehicle:" + vehicle.getRegNumber() + "  spot:" + ticket.getParkingSpot().getId());

                parkingGarage.setAvailableParkingSpots(parkingGarage.getAvailableParkingSpots() - 1);
                parkingGarage.getTickets().put(ticket.getId(), ticket);
                return ticket;
            }
        }
        return null;
    }
    }
}
