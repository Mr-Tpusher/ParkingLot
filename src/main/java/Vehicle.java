import constants.Sizes;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Vehicle {
    private String regNumber;
    private Sizes size;
}
