package BusReservationSystem.ReservationSystem;

import BusReservationSystem.DTO.Bus;
import BusReservationSystem.DTO.Customer;
import BusReservationSystem.DTO.Passenger;
import BusReservationSystem.DTO.Ticket;
import BusReservationSystem.Repository.Repository;



public class Summary {

    Repository respository = Repository.getInstance();

    public void viewTicket() {


        Customer customer = new Validation().getCustomerById(Validation.customerId);
        if(customer.getBookingList()==null)
        {
            System.out.println("No such data Found...!");
            return;
        }
        for(Ticket ticket : customer.getBookingList())
        {
            System.out.println("----------------------------------------------------------------------------------------");
            System.out.println("Ticket No : "+ticket.getTicketId());
            for(Passenger passenger : ticket.passengers)
            {
                System.out.println("-------------------------------------------");
                System.out.println("Passenger Id      : "+passenger.getPassengerId()+"\n"+
                                   "Passenger Name    : "+passenger.getPassengerName()+"\n"+
                                   "Passenger Seat No : "+passenger.getSeatNo());

            }
            System.out.println("----------------------------------------------------------------------------------------");
        }
    }

    public void fullSummary() {
        System.out.println("Bus Details...!");
        System.out.println("--------------------------------------------------------------------------------------------");
        for(Bus bus : respository.busList)
        {
            System.out.println("Bus Id   : "+bus.getBusId()+" "+
                               "Bus Type : "+bus.getBusType()+" "+
                               "Seat Type: "+bus.getSeatType()+" "+
                               "Capacity : "+bus.getCapacity());
        }
        System.out.println("--------------------------------------------------------------------------------------------");
    }

    public void addBus() {

        Bus bus1 = new Bus("AC","Seater",20,100);
        Bus bus2 = new Bus("AC","Sleeper",20,100);
        Bus bus3 = new Bus("NON-AC","Seater",20,100);
        Bus bus4 = new Bus("NON-AC","Sleeper",20,100);

        respository.busList.add(bus1);
        respository.busList.add(bus2);
        respository.busList.add(bus3);
        respository.busList.add(bus4);

    }
}
