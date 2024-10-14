package FlightReservationSystem.Reservation;

import FlightReservationSystem.DTO.Flight;
import FlightReservationSystem.DTO.Passenger;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReservationSystem {


    public static List<Flight> flightList = new ArrayList<>();

    DataHandling handler;

    public ReservationSystem() {
        handler = new DataHandling(this);
    }


    public void displayFlightDetails() {

        for(Flight flight : flightList)
        {
            System.out.println(flight.toString());
        }

    }

    public void bookFlight(String flightName, int seats) {

        Flight flight = getFlight(flightName);

        if(flight==null)
        {
            System.out.println("No such flight exists");
            return;
        }

        if(seats>flight.capacity)
        {
            System.out.println("Required Ticket Not Available");
        }
        else {


            List<Integer> seatNos = new ArrayList<>();


            int ticketCost = 0;
            for(int i=0;i<seats;i++)
            {
                System.out.println("Available Seats");
                showAvailableSeats(flight);
                Passenger passenger = handler.getPassengerDetails(flight);
                passenger.ticketCost = flight.cost;
                flight.passengerList.add(passenger);
                flight.seats[passenger.seatNo-1]=1;
                seatNos.add(passenger.seatNo);
                flight.capacity--;
                ticketCost += flight.cost;
                flight.cost += 200;
            }

            System.out.println("Total Ticket Cost : "+ticketCost);
            System.out.println("Ticket Booked Successfully..!");

            showBookedTickets(seatNos);

        }

    }

    public void showAvailableSeats(Flight flight) {
        int[] seatList = flight.seats;
        int c =0;
        for(int i=0;i<seatList.length;i++)
        {
            if(seatList[i]==0)
            {
                System.out.print((i+1)+" ");
            }
            else {
                System.out.print("*"+" ");
            }
            c++;
            if(i!=0 && c%10==0)
            {
                System.out.println();
            }
        }

    }

    private void showBookedTickets(List<Integer> seatNos) {
        System.out.print("Booked Seats : ");
        for(int seat : seatNos)
        {
            System.out.print(seat+" ");
        }
        System.out.println();
    }

    private Flight getFlight(String flightName) {

        for(Flight f : flightList)
        {
            if(f.flightName.equals(flightName))
            {
                return f;
            }
        }
        return null;
    }

    public void printDetails(String flightName) {

        Flight flight = getFlight(flightName);

        List<Passenger> list  = flight.passengerList;

        for(Passenger passenger : list)
        {
            System.out.println("Passenger Id : "+passenger.passengerId+
                             "Passenger Name : "+passenger.passengerName+
                             "Seat No : "+passenger.seatNo);

        }
    }

    public void cancelTicket(String flightName,List<Integer> cancelTicketList) {

        Flight flight = getFlight(flightName);
        int refundAmount=0;
        for(int seat : cancelTicketList) {
            flight.capacity++;
            flight.cost -= 200;
            refundAmount += removePassengerAndSeat(flight, seat);

        }
        System.out.println("Refund Amount : "+refundAmount);
        System.out.println("Ticket Cancelled Successfully...!");

    }

    private int removePassengerAndSeat(Flight flight, int seat) {

        int ind;
        for(Passenger passenger : flight.passengerList)
        {
            if(passenger.seatNo==seat)
            {
                ind = passenger.passengerId-1;
                flight.passengerList.remove(ind);
                flight.seats[ind-1]=ind+1;
                return passenger.ticketCost;
            }
        }
        return 0;
    }


}
