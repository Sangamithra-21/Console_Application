package TaxiBookingApplication;

import TaxiBookingApplication.DTO.BookDTO;
import TaxiBookingApplication.DTO.TaxiDTO;

import java.util.ArrayList;
import java.util.Scanner;



public class Booking {


    char source;
    char destination;
    int time;

    Scanner sc = new Scanner(System.in);

    TaxiDTO t = TaxiDTO.getInstance();

    public void bookTaxi() {

        getDetailsFromUser();

        int taxiNo = checkAvailableTaxi();
        System.out.println("Taxi No :" + taxiNo + " is allocated...!");

        if(taxiNo==0)
        {
            System.out.println("No Taxi Avaliable...!");
            return;
        }

        int earnings = findEarnings();

        BookDTO book = new BookDTO(source,destination,earnings,time,time+(destination-source));

        if(taxiNo!=0){

             t.bookArray[taxiNo].add(book);
             t.TaxiEarning[taxiNo]+=earnings;
        }

    }

    private int findEarnings() {

        int dist = destination-source;
        dist = dist*15;
        int res;
        dist = dist-5;
        res = dist*10 + 100;
        return res;
    }

    private int checkAvailableTaxi() {
        int minDistance = Integer.MAX_VALUE;
        int taxiNo = 0;

        for (int i = 1; i < t.taxi.length; i++) {

            int val = Math.abs(t.taxi[i][time] - source);

            if (taxiAvailable(i)) {
                if (val < minDistance) {
                    minDistance = val;
                    taxiNo = i;

                } else if (val == minDistance) {
                    if (t.TaxiEarning[i] < t.TaxiEarning[taxiNo]) {
                        taxiNo = i;
                    }
                }
            }
        }

        if (taxiNo != 0) {
            makeNonAvailable(taxiNo);
            makeBusy(taxiNo);
        }

        return taxiNo;
    }


    private void makeBusy(int taxiNo) {

        for(int j=time;j<25;j++)
        {
            t.taxi[taxiNo][j] = destination;
        }
    }

    private void makeNonAvailable(int taxiNo) {
        int col = time + (destination-source);
        for(int j=time;j<col;j++)
        {
            t.isAvailable[taxiNo][j]=false;
        }

    }

    private boolean taxiAvailable(int i) {
        int col = time + (destination - source);
        for(int j=time;j<col;j++)
        {
            if(!t.isAvailable[i][j])
            {
                return false;
            }
        }
        return true;
    }



    private void getDetailsFromUser() {

        System.out.println("Enter the Source      : ");
        source = sc.next().charAt(0);
        System.out.println("Enter the Destination : ");
        destination = sc.next().charAt(0);
        System.out.println("Enter the pickUp Time : ");
        time = sc.nextInt();
    }




    public void displayBookingDetails() {

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the Taxi Number : ");
        int taxiNo = sc.nextInt();

        System.out.println("Taxi Number    : "+taxiNo);
        System.out.println("Total Earnings : "+t.TaxiEarning[taxiNo]);

         ArrayList<BookDTO> list = t.bookArray[taxiNo];

         if(list.size()==0)
         {
             System.out.println("Taxi Not Booked yet...!");
             return;
         }

         for(BookDTO cust : list)
         {
             System.out.println(convertToString(cust));
         }

    }
    public String convertToString(BookDTO book)
    {
        return "Booking ID  : "+book.bookingId+" "
             + "Customer ID : "+book.customerId+" "
             + "Source      : "+book.source+" "
             + "Destination : "+book.destination+" "
             + "PickUp Time : "+book.pickupTime+" "
             + "Drop Time   : "+book.dropTime+" "
             + "Amount      : "+book.amount;
    }

}

