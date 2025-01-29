import lombok.Data;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class Ticket {
    private UUID id;
    private VehicleDetails vehicleDetails;
    private ParkingSpot parkingSpot;
    private LocalDateTime inTime;
    private LocalDateTime outTime;
    private double charge;

    public Ticket(VehicleDetails vehicleDetails) {
        this.id = UUID.randomUUID();
        this.inTime = LocalDateTime.now();
        this.vehicleDetails = vehicleDetails;
    }

    @Override
    public String toString() {
        return "Ticket \n" +
                "{" +
                "\n id = " + id +
                "\n vehicle = " + vehicleDetails.getRegNumber() +
                "\n parkingSpot = " + parkingSpot.getId() +
                "\n inTime = " + inTime +
                "\n outTime = " + outTime +
                "\n charge = " + charge +
                "\n}";
    }
}
