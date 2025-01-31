import java.util.Set;

public interface IParkingStrategy {
    //Ticket park(VehicleDetails vehicleDetails);
    Ticket park(Vehicle vehicle, Set<ParkingSpot> parkingSpots);
    Ticket park(Vehicle vehicle, ParkingGarage parkingGarage, EntryGate entryGate);
}
