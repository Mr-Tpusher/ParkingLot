import constants.Sizes;

public class Main {
    public static void main(String[] args) {

        ParkingGarage garage = new ParkingGarage("A" , 10);

        Vehicle vehicle1 = new Vehicle("A000", Sizes.SMALL);
        Ticket ticket1 = garage.park(vehicle1);

        Ticket ticket2 = garage.park(new Vehicle("A001", Sizes.MEDIUM));
        Ticket ticket3 = garage.park(new Vehicle("A002", Sizes.LARGE));

        //double cost = garage.exit(ticket2);
        System.out.println(ticket2);
        //System.out.println("Please pay rupees " + cost);


        garage.park(new Vehicle("A003", Sizes.LARGE));

    }
}
