package RailwayReservation;

import java.util.Scanner;

import RailwayReservation.Train;

import static RailwayReservation.Ticket.pnrNo;
import static RailwayReservation.Ticket.ticketList;
import static RailwayReservation.Train.train;
import RailwayReservation.Ticket;

public class Booking {
    char source;
    char destination;
    int noOfSeats;
    Scanner sc = new Scanner(System.in);


    public void bookTricket()
    {


        getUserDetails();
        int sourceData = (int)(source)%65;
        int destinationData = (int)(destination)%65;

         boolean isAvailable = checkTotalTicketAvailability(train,sourceData,destinationData,noOfSeats);

         Ticket ticket;

         if(isAvailable) {

             for (int i = 0; i < noOfSeats; i++) {

                 int seatRow = checkAvailability(train, sourceData, destinationData);
                 if (seatRow == -1) {
                    break;
                 }

                 allocateSeat(seatRow, pnrNo, sourceData, destinationData);


             }
             ticket = new Ticket(pnrNo,source,destination,noOfSeats,"Booked","");
             ticketList.add(ticket);


         }
         else {
             System.out.println("Required Ticket Not Avaliable..!");
             return;
         }
         if(ticket!=null) {
             System.out.println("Ticket Booked Successfully...!");
             displayBookedTicket(ticket);
         }


    }

    private boolean checkTotalTicketAvailability(int[][] train, int sourceData, int destinationData, int noOfSeats) {

        int count=0;
        for(int i=0;i<train.length;i++)
        {
            boolean flag=true;
            for(int j=sourceData;j<destinationData;j++)
            {
                if(train[i][j]!=0)
                {
                    flag=false;
                    break;
                }

            }
            if(flag) count++;

        }
        return count>=noOfSeats;
    }

    public static void displayBookedTicket(Ticket ticket) {

        System.out.println("PNR Number    : "+ticket.PNR);
        System.out.println("Source        : "+ticket.source);
        System.out.println("Destination   : "+ticket.destination);
        System.out.println("Ticket Count  : "+ticket.noOfSeats);
        System.out.println("Ticket Status : "+ticket.status);
    }

    private void allocateSeat(int seatRow, int pnrNo,int sourceData,int destinationData) {
        for(int j=sourceData;j<destinationData;j++)
        {
            train[seatRow][j]=pnrNo;
        }
    }

    private void getUserDetails() {

        System.out.println("Enter the source --> (A | B | C | D ): ");
        source = sc.next().charAt(0);
        System.out.println("Enter the destination --> (B | C | D | E ): ");
        destination = sc.next().charAt(0);
        System.out.println("Enter the no of seats : ");
        noOfSeats = sc.nextInt();
    }
    public static int checkAvailability(int[][] train, int source, int destination)
    {

        for(int i=0;i<train.length;i++)
        {
            boolean flag = true;
            for(int j=source;j<destination;j++)
            {
                if(train[i][j]!=0)
                {
                    flag=false;
                    break;
                }
            }
            if(flag) return i;
        }
        return -1;
    }
}
