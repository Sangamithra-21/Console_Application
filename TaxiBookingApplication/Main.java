package TaxiBookingApplication;

import TaxiBookingApplication.DTO.BookDTO;
import TaxiBookingApplication.DTO.TaxiDTO;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        boolean flag=true;

        while(flag)
        {
            System.out.println("1)Booking\n2)Display");
            int choice = sc.nextInt();

            switch(choice)
            {
                case 1:
                    Booking booking = new Booking();
                    booking.bookTaxi();
                    break;
                case 2 :
                    new Booking().displayBookingDetails();
                    break;
            }
        }

    }


}
