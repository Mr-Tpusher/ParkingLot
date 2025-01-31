import lombok.Data;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class Ticket {
    private UUID id;
    private Vehicle vehicle;
    private ParkingSpot parkingSpot;
    private LocalDateTime inTime;
    private LocalDateTime outTime;
    private double charge;

    public Ticket(Vehicle vehicle) {
        this.id = UUID.randomUUID();
        this.inTime = LocalDateTime.now();
        this.vehicle = vehicle;
    }

    @Override
    public String toString() {
        return "Ticket \n" +
                "{" +
                "\n id = " + id +
                "\n vehicle = " + vehicle.getRegNumber() +
                "\n parkingSpot = " + parkingSpot.getId() +
                "\n inTime = " + inTime +
                "\n outTime = " + outTime +
                "\n charge = " + charge +
                "\n}";
    }
}
