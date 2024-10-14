package FlightReservationSystem.Reservation;

import FlightReservationSystem.DTO.Flight;
import FlightReservationSystem.DTO.Passenger;

import java.util.List;
import java.util.ArrayList;


import java.util.Scanner;

public class DataHandling {

    ReservationSystem system;

    public DataHandling(ReservationSystem system) {
        this.system = system;
    }

    Scanner sc =  new Scanner(System.in);


    public void addFlightDetails() {

        System.out.println("Enter the Flight Name ; ");
        String flightName = sc.next();

        Flight flight = new Flight(flightName,50,5000);

        system.flightList.add(flight);

        System.out.println("Flight Added successfully..!");
        
    }

    public void getFlightDetails() {

        system.displayFlightDetails();


    }

    public void bookTicket() {

        System.out.println("Enter the Flight Name  : ");
        String flightName = sc.next();

        System.out.println("Enter the Number of seats : ");
        int seats = sc.nextInt();

        system.bookFlight(flightName,seats);
    }

    public void cancelTicket() {

        System.out.println("Enter the Flight Name : ");
        String flightName = sc.next();

        System.out.println("How many seats to be cancelled ?");
        int cancelCount = sc.nextInt();

        List<Integer> cancelTicketList = new ArrayList<>();

        System.out.println("Seat Number you want to cancel :");

        for(int i=0;i<cancelCount;i++)
        {
            int a = sc.nextInt();
            cancelTicketList.add(a);
        }

        system.cancelTicket(flightName,cancelTicketList);
    }

    public Passenger getPassengerDetails(Flight flight) {

        System.out.println("Enter the passenger Name ; ");
        String name = sc.next();

        System.out.println("Enter seat No : ");
        int seatNo = sc.nextInt();

        Passenger passenger = new Passenger(name,flight.flightName,seatNo,0);
        return passenger;
    }

    public void printPassengerDetails() {

        System.out.println("Enter the flight Name : ");
        String flightName = sc.next();

        system.printDetails(flightName);
    }
}
