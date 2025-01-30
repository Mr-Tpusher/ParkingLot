import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ParkingSpot implements Comparable {
    private int id;

    @Override
    public int compareTo(Object o) {
        ParkingSpot otherSpot = (ParkingSpot) o;
        return Integer.compare(this.id, otherSpot.getId());
    }
}
