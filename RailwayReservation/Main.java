package RailwayReservation;

import java.util.List;
import java.util.Scanner;

import static RailwayReservation.Train.train;
import static RailwayReservation.Ticket.ticketList;

public class Main {
    public static void main(String[] args) {

        Train train1 = new Train();

        Scanner sc = new Scanner(System.in);
        boolean flag=true;
        while(flag)
        {
            System.out.println("1)Book Ticket\n2)Cancel Ticket\n3)Chart\n4)Display Tickets\n5)Exit");
            int choice = sc.nextInt();
            switch(choice)
            {
                case 1 :
                    Booking booking = new Booking();
                    booking.bookTricket();
                    break;
                case 2 :
                    Cancel cancel = new Cancel();
                    cancel.cancelTicket();
                    break;
                case 3 :
                    display(train);
                    break;
                case 4 :
                    displayTicketList(ticketList);
                    break;
                case 5 :
                    flag=false;
                    break;
            }

        }




    }

    private static void displayTicketList(List<Ticket> ticketList) {

        for(Ticket ticket : ticketList)
        {
            System.out.println(ticket.toString());
        }

    }

    public static void display(int[][] train)
    {
        System.out.print("  ");
        for(int k=0;k<train[0].length;k++)
        {
            System.out.print((char)(k+65)+" ");
        }
        System.out.println();
        for(int i=0;i<train.length;i++)
        {
            System.out.print((i+1) + " ");
            for(int j=0;j<train[0].length;j++)
            {
                if(train[i][j]!=0)
                {
                    System.out.print(train[i][j]+" ");
                }
                else {
                    System.out.print("  ");
                }
            }
            System.out.println();
        }
    }
}
