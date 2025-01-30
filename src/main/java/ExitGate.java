import lombok.Data;

@Data
public class ExitGate {
    private String name;
    private IChargingStrategy chargingStrategy;

    ExitGate(String name) {
        this.name = name;
    }
}
