package RailwayReservation;

import java.util.Scanner;

import static RailwayReservation.Ticket.ticketList;
import static RailwayReservation.Train.train;
import RailwayReservation.Booking;

public class Cancel {

    Scanner sc = new Scanner(System.in);
    public void cancelTicket()
    {
        System.out.println("Enter the PNR number : ");
        int pnr = sc.nextInt();

        System.out.println("Enter the Number of tickets to cancel : ");
        int cancelCount = sc.nextInt();

        cancellingLogic(pnr,cancelCount,train);

        modifyTicket(pnr,cancelCount);

        moveWaitingToConfrim();


    }

    private void moveWaitingToConfrim() {

         int[] pnr1=findWaitingList(8);
         int[] pnr2=findWaitingList(9);

         if(pnr1[0]!=0)
         {
             int p1 = findAvailability(pnr1[1],pnr1[2]);
             if(p1>=0)
             {
                 allocateTicket(8,p1,pnr1[0],pnr1[1],pnr1[2]);
             }
         }
         if(pnr2[0]!=0)
         {
             int p2 = findAvailability(pnr2[1],pnr2[2]);
             if(p2>=0)
             {
                 allocateTicket(9,p2,pnr1[0],pnr1[1],pnr1[2]);
             }
         }

    }

    private int findAvailability(int source,int destination) {

        for(int i=0;i<8;i++)
        {
            boolean flag = true;
            for(int j=source;j<=destination;j++)
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

    private void allocateTicket(int del,int row, int pnr , int source, int destination) {

        for(int i=source;i<destination;i++)
        {
            train[row][i]=pnr;
            train[del][i]=0;
        }
    }

    private int[] findWaitingList(int row) {
        int pnr = 0;
        int source = -1;
        int end = -1;
        boolean flag=true;
        for(int i=0;i<5;i++)
        {
            if(flag && train[row][i]!=0)
            {
                source = i;
                pnr = train[row][i];
                flag = false;
            }
            else if(train[row][i]==0)
            {
                end = i;
                return new int[]{pnr,source,end};
            }
        }
        return new int[]{pnr,source,end};

    }



    private void modifyTicket(int pnr,int cancelCount) {

        for(Ticket ticket : ticketList)
        {
            if(ticket.PNR==pnr)
            {
                System.out.println("Ticket Cancelled Successfully..!");
                ticket.noOfSeats = ticket.noOfSeats-cancelCount;
                Booking.displayBookedTicket(ticket);
                break;
            }
        }
    }


    private void cancellingLogic(int pnr, int cancelCount,int[][] train) {
        int count=0;
        for(int i=9;i>=0;i--)
        {
            boolean flag=true;
            for(int j=0;j<5;j++)
            {
                if(flag && train[i][j]==pnr)
                {
                    count++;
                    train[i][j]=0;
                    flag=false;
                }
                else if(train[i][j]==pnr) {
                    train[i][j]=0;
                }

            }
            if(count==cancelCount)
            {
                return;
            }
        }

    }
}
