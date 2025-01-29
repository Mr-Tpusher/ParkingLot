import java.util.Set;

public interface IParkingStrategy {
    Ticket park(VehicleDetails vehicleDetails);
    Ticket park(VehicleDetails vehicleDetails, Set<ParkingSpot> parkingSpots);
}
