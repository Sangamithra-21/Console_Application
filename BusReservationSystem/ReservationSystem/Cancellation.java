package BusReservationSystem.ReservationSystem;

import BusReservationSystem.DTO.Bus;
import BusReservationSystem.DTO.Passenger;
import BusReservationSystem.DTO.Ticket;
import BusReservationSystem.Repository.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Cancellation {

    Scanner sc = new Scanner(System.in);

    Repository repository = Repository.getInstance();

    Booking book = new Booking();

    public void cancelTicket() {


        System.out.println("Enter the Bus Type [ 1 for AC -- 2 for NON-AC ] : ");
        int bust = sc.nextInt();

        String busType = (bust==1) ? "AC" : "NON-AC";

        System.out.println("Enter the Seat Type [ 1 for Seater -- 2 for Sleeper ] : ");
        int seat = sc.nextInt();

        String seatType = (seat==1) ? "Seater" : "Sleeper";

        Bus bus = book.getBus(busType,seatType);

        System.out.println("Enter the no of seats to cancel : ");
        int seats = sc.nextInt();

        System.out.println("Enter the seats Nos : ");

        List<Integer> seatNos = new ArrayList<>();


        for(int i=0;i<seats;i++)
        {
            int seatNo = sc.nextInt();
            seatNos.add(seatNo);
        }

        int refundCost = 0;

        for(int seatNo : seatNos)
        {
            cancelAndRefund(seatNo,bus);
            int capacity = bus.getCapacity()+1;
            bus.setCapacity(capacity);
            refundCost +=100;
        }

        book.displayAvailableSeats(bus);

        System.out.println();


        System.out.println("Ticket Cancelled Successfully..!");

        System.out.println("Refunded Amount : "+refundCost);

    }

    private void cancelAndRefund(int seatNo,Bus bus) {

        for(Passenger passenger : bus.getPassengerList())
        {
            if(passenger.getSeatNo()==seatNo)
            {
                bus.getPassengerList().remove(passenger);
                bus.seats[seatNo-1] = null;
                deleteFromTicket(passenger);
                return;
            }
        }

    }

    private void deleteFromTicket(Passenger passenger) {

        for(Ticket ticket : repository.ticketList)
        {
            if(ticket.passengers.contains(passenger))
            {
                ticket.passengers.remove(passenger);
                if(ticket.passengers.size()==0)
                {
                    repository.ticketList.remove(ticket);
                }
                return;
            }
        }
    }


}
