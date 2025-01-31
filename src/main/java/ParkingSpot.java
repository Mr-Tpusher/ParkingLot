import constants.Sizes;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ParkingSpot implements Comparable<ParkingSpot> {
    private int id;
    private boolean isOccupied;
    private Sizes size;
    private Vehicle vehicle;

    public ParkingSpot(int id) {
        this.id = id;
        this.isOccupied = false;
        this.size = Sizes.LARGE;
    }

    public synchronized boolean book(Vehicle vehicle) {
        if (isAvailable()) {
            isOccupied = true;
            this.vehicle = vehicle;
            // System.out.println(vehicleDetails.getRegNumber() +
                    //" : " + this.getId());
            return true;
        }
        return false;
    }

    public synchronized boolean isAvailable() {
        return !isOccupied;
    }

    @Override
    public int compareTo(ParkingSpot otherSpot) {
        return Integer.compare(this.id, otherSpot.getId());
    }

    public boolean release() {
        this.isOccupied = false;
        return true;
    }
}
