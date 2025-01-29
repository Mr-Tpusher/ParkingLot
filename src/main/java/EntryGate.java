import lombok.Data;

@Data
public class EntryGate {
    private String name;
    private IParkingStrategy parkingStrategy;

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

}
