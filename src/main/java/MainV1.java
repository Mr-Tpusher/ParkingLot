import java.util.TreeSet;

public class MainV1 {
    public static void main(String[] args) {
        ParkingGarage parkingGarage = new ParkingGarage("ParkIt", 10);
        TreeSet<ParkingSpot> parkingSpots = (TreeSet<ParkingSpot>) parkingGarage.getParkingSpots();

        EntryGate entryGate1 = new EntryGate("EntryGate1");
        entryGate1.addParkingSpots(parkingSpots);
        parkingGarage.setEntryGate(entryGate1);

        EntryGate entryGate2 = new EntryGate("EntryGate2");
        entryGate2.addParkingSpots(new TreeSet<>(parkingSpots.descendingSet()));
        parkingGarage.setEntryGate(entryGate2);




    }
}
