package BusReservationSystem.ReservationSystem;

import BusReservationSystem.DTO.Bus;
import BusReservationSystem.DTO.Customer;
import BusReservationSystem.DTO.Passenger;
import BusReservationSystem.DTO.Ticket;
import BusReservationSystem.Repository.Repository;

import java.util.Scanner;

import java.util.List;
import java.util.ArrayList;


public class Booking {
    
    
    Scanner sc = new Scanner(System.in);

    Repository repository = Repository.getInstance();

    public void bookTikets() {

        System.out.println("Enter the Bus Type [ 1 for AC -- 2 for NON-AC ] : ");
        int bust = sc.nextInt();
        
        String busType = (bust==1) ? "AC" : "NON-AC";

        System.out.println("Enter the Seat Type [ 1 for Seater -- 2 for Sleeper ] : ");
        int seat = sc.nextInt();

        String seatType = (seat==1) ? "Seater" : "Sleeper";

        System.out.println("Enter the number of seats : ");
        int seats = sc.nextInt();

        Bus bus = getBus(busType,seatType);

        if(bus.getCapacity()<seats)
        {
            System.out.println("Required Seats Not Available...!");
            return;
        }

        List<Passenger> passList = new ArrayList<>();

        int totalCost = 0;

        for(int i=0;i<seats;i++)
        {
            displayAvailableSeats(bus);

            System.out.println("Enter the passenger Name  : ");
            String name = sc.next();

            System.out.println("Enter the passenger age : ");
            int age = sc.nextInt();

            System.out.println("Enter the Gender [ F | M ]: ");
            String gender = sc.next();

            System.out.println("Enter the Seat No : ");
            int seatNo = sc.nextInt();

            Passenger passenger = new Passenger(name,bus.getBusType(),bus.getSeatType(),age,gender,seatNo);

            bus.seats[seatNo-1] = gender;

            int capacity = bus.getCapacity()-1;

            bus.setCapacity(capacity);

            bus.getPassengerList().add(passenger);

            passList.add(passenger);

            totalCost += bus.getCost();

        }

        System.out.println();

        displayAvailableSeats(bus);

        Ticket ticket = new Ticket(passList);

        Customer customer = new Validation().getCustomerById(Validation.customerId);

        customer.getBookingList().add(ticket);

        repository.ticketList.add(ticket);

        System.out.println("Tickets Booked Successfully...!");

        System.out.println("Total Cost : "+totalCost);

        printBookingDetails(ticket);
    }

    private void printBookingDetails(Ticket ticket) {

        System.out.println("Ticket Id : "+ticket.getTicketId());
        System.out.println("----------------------------------------------------------------------------------------------");
        for(Passenger passenger : ticket.passengers)
        {
            System.out.println("Passenger Id   : "+passenger.getPassengerId()+"\n"+
                               "Passenger Name : "+passenger.getPassengerName()+"\n"+
                               "Seat No        : "+passenger.getSeatNo());
            System.out.println("----------------------------------------------------------------------------------------------");

        }

    }

    public void displayAvailableSeats(Bus bus) {

        for(int i=0;i<bus.seats.length;i++)
        {
            if(bus.seats[i]==null)
            {
                System.out.print((i+1)+" ");
            }
            else {
                System.out.print(bus.seats[i]+" ");
            }
            if(i!=0 && i%5==0)
            {
                System.out.println();
            }

        }
    }

    Bus getBus(String busType, String seatType) {

        for(Bus bus : repository.busList)
        {
            if(bus.getBusType().equals(busType) && bus.getSeatType().equals(seatType))
            {
                return bus;
            }
        }
        return null;
    }
}
