package FlightReservationSystem;

import FlightReservationSystem.Reservation.DataHandling;
import FlightReservationSystem.Reservation.ReservationSystem;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

         Scanner sc = new Scanner(System.in);
         boolean flag=true;

        ReservationSystem reservationSystem = new ReservationSystem();
        DataHandling handler = new DataHandling(reservationSystem);


         while(flag)
         {
             System.out.println("1)Admin 2)User");
             int choice1 = sc.nextInt();
             switch(choice1) {
                 case 1:
                     boolean f1 = true;
                     while (f1) {
                         System.out.println("1)Add Flight 2)Print Flight Details 3)Print passenger Details 4)Exit");
                         int choice2 = sc.nextInt();
                         switch (choice2) {
                             case 1: {
                                 handler.addFlightDetails();
                                 break;
                             }
                             case 2: {
                                 handler.getFlightDetails();
                                 break;
                             }
                             case 3 :
                             {
                                 handler.printPassengerDetails();
                                 break;
                             }
                             case 4 : {
                                 f1 = false;
                                 break;
                             }
                         }

                     }
                     break;
                 case 2:
                     boolean f2 = true;
                     while(f2)
                     {
                         System.out.println("1)Booking 2)Cancel 3)Exit");
                         int choice3 = sc.nextInt();
                         switch(choice3)
                         {
                             case 1 : {
                                 handler.bookTicket();
                                 break;
                             }
                             case 2 : {
                                 handler.cancelTicket();
                                 break;
                             }
                             case 3 : {
                                 f2 = false;
                                 break;
                             }
                         }

                     }
                     break;
                 case 3 : {
                     flag = false;
                     break;
                 }


             }
         }

    }
}
