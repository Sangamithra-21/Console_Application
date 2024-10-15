package BusReservationSystem;

import BusReservationSystem.ReservationSystem.Booking;
import BusReservationSystem.ReservationSystem.Cancellation;
import BusReservationSystem.ReservationSystem.Summary;
import BusReservationSystem.ReservationSystem.Validation;
import RailwayReservation.Cancel;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {


        Scanner sc = new Scanner(System.in);

        boolean f1 = true;

        Validation validation = new Validation();

        Summary summary = new Summary();

        summary.addBus();

        while(f1)
        {
            System.out.println("1)SignUp 2)Login 3)Exit");
            int choice1 = sc.nextInt();
            switch(choice1)
            {
                case 1 : {
                    validation.signUp();
                    break;
                }
                case 2 :{
                    boolean user = validation.login();
                    if(user)
                    {
                        boolean f2=true;
                        while(f2)
                        {
                            System.out.println("1)Booking 2)Cancellation 3)View Tickets 4)Summary 5)Logout");
                            int choice2 = sc.nextInt();
                            switch(choice2)
                            {
                                case 1 : {
                                    Booking booking = new Booking();
                                    booking.bookTikets();
                                    break;
                                }
                                case 2 : {
                                    Cancellation cancel = new Cancellation();
                                    cancel.cancelTicket();
                                    break;
                                }
                                case 3 : {
                                    summary.viewTicket();
                                    break;
                                }
                                case 4 : {
                                    summary.fullSummary();
                                    break;
                                }
                                case 5 : {
                                    f2=false;
                                    break;
                                }
                            }
                        }

                    }
                    else {
                        System.out.println("Invalid User...!");
                    }
                    break;
                }
                case 3 : {
                    f1 = false;
                    break;
                }

            }
        }

    }
}
