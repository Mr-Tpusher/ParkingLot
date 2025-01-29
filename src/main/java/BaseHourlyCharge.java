import lombok.Data;

@Data
public class BaseHourlyCharge implements IChargingStrategy{
    double hourlyCharge;

    public BaseHourlyCharge(double hourlyCharge) {
        this.hourlyCharge = hourlyCharge;
    }


    double charge(Ticket ticket) {
        return hourlyCharge;
    }

    @Override
    public double charge() {
        return hourlyCharge;
    }
}
