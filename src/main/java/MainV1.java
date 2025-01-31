import constants.Gates;
import constants.Sizes;

import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;

public class MainV1 {
    public static void main(String[] args) throws InterruptedException {

        ParkingGarage parkingGarage = new ParkingGarage("ParkIt", 10);
        configureEntryGates(parkingGarage);
        configureExitGates(parkingGarage);
        //parkingGarage.printParkingSpots();


        Thread t0 = new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                parkingGarage.park(new Vehicle("T0-" + (i) , Sizes.MEDIUM), Gates.ONE);
            }
        });

        Thread t1 = new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                parkingGarage.park(new Vehicle("T1-" + (i) , Sizes.MEDIUM), Gates.TWO);
            }
        });

        t0.start();
        t1.start();

        t0.join();
        t1.join();

        System.out.println("----------before exit------------------------------");
        for (EntryGate g : parkingGarage.getEntryGates().values()) {
            System.out.println(g.getParkingSpots());
        }

        //System.out.println(parkingGarage);
        UUID ticketID = parkingGarage.getTickets().keySet().iterator().next();
        double charge = parkingGarage.exit(ticketID);
        System.out.println("Pay:" + charge);


        System.out.println("----------after exit------------------------------");
        for (EntryGate g : parkingGarage.getEntryGates().values()) {
            System.out.println(g.getParkingSpots());
        }
        //System.out.println(parkingGarage.getParkingSpots());

    }



    private static void configureEntryGates(ParkingGarage parkingGarage) {
        Set<ParkingSpot> parkingSpots = parkingGarage.getParkingSpots();
        // configure EntryGate1
        EntryGate entryGate1 = new EntryGate("EntryGate1");
        entryGate1.addParkingSpots(parkingSpots);
        entryGate1.setParkingStrategy(new ParkNearestToEntry());
        parkingGarage.setEntryGate(Gates.ONE, entryGate1);
        //System.out.println(entryGate1.getParkingSpots() + "\n");

        //configure EntryGate2
        EntryGate entryGate2 = new EntryGate("EntryGate2");
        TreeSet<ParkingSpot> ts = new TreeSet<>(Collections.reverseOrder());
        ts.addAll(parkingSpots);
        entryGate2.setParkingSpots(ts);
        entryGate2.setParkingStrategy(new ParkNearestToEntry());
        parkingGarage.setEntryGate(Gates.TWO, entryGate2);
        //System.out.println(entryGate1.getParkingSpots() + "\n");

    }


    private static void configureExitGates(ParkingGarage parkingGarage) {

        // Configure Exit gates
        ExitGate exitGate = new ExitGate("Exit1");
        exitGate.setChargingStrategy(new BaseHourlyCharge(1000));
        parkingGarage.setExitGate(Gates.THREE, exitGate);

        ExitGate exitGate2 = new ExitGate("Exit2");
        exitGate.setChargingStrategy(new BaseHourlyCharge(1000));
        parkingGarage.setExitGate(Gates.FOUR, exitGate2);
    }
}