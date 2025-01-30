import lombok.Data;

import java.util.TreeSet;

@Data
public class EntryGate {
    private String name;
    private IParkingStrategy parkingStrategy;
    private TreeSet<ParkingSpot> parkingSpots;

    public EntryGate(String name) {
        this.name = name;
    }

    public EntryGate(String name, IParkingStrategy parkingStrategy) {
        this.name = name;
        this.parkingStrategy = parkingStrategy;
    }

    public Ticket park(VehicleDetails vehicleDetails) {
        //
        return null;
    }

    public void addParkingSpot(ParkingSpot parkingSpot) {
        if (this.parkingSpots == null) {
            this.parkingSpots = new TreeSet<>();
        }
        this.parkingSpots.add(parkingSpot);
    }

    public void addParkingSpots(TreeSet<ParkingSpot> parkingSpots) {
        if (this.parkingSpots == null) {
            this.parkingSpots = new TreeSet<>();
        }
        this.parkingSpots.addAll(parkingSpots);
    }

}
