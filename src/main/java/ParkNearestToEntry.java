import java.time.LocalDateTime;
import java.util.Set;
import java.util.TreeSet;

public class ParkNearestToEntry implements IParkingStrategy{
    @Override
    public Ticket park(VehicleDetails vehicleDetails) {
        return new Ticket(vehicleDetails);
    }

    @Override
    public Ticket park(VehicleDetails vehicleDetails, Set<ParkingSpot> parkingSpots) {
        Ticket ticket = new Ticket(vehicleDetails);
        TreeSet<ParkingSpot> parkingSpots2 = (TreeSet<ParkingSpot>) parkingSpots;
        ticket.setParkingSpot(parkingSpots2.first());
        parkingSpots.remove(parkingSpots2.first());
        return ticket;
    }
}
