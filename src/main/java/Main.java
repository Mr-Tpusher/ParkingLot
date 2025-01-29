public class Main {
    public static void main(String[] args) {

        ParkingGarage garage = new ParkingGarage("A" , 10);

        VehicleDetails vehicle1 = new VehicleDetails("1234", Size.SMALL);
        Ticket ticket1 = garage.park(vehicle1);

        Ticket ticket2 = garage.park(new VehicleDetails("A001", Size.MEDIUM));
        Ticket ticket3 = garage.park(new VehicleDetails("A002", Size.LARGE));

        double cost = garage.exit(ticket2);
        System.out.println(ticket2);
        System.out.println("Please pay rupees " + cost);


        garage.park(new VehicleDetails("A003", Size.LARGE));

    }
}
