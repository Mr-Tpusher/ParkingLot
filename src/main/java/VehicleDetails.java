import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VehicleDetails {
    String regNumber;
    Size size;
}
