package RailwayReservationSystem1.RailwaySystem;

import RailwayReservationSystem1.DTO.Passenger;
import RailwayReservationSystem1.DTO.Ticket;
import RailwayReservationSystem1.DTO.Train;
import RailwayReservationSystem1.Repository.Repository;

import java.util.Queue;
import java.util.Scanner;

public class SystemView {

    private SystemViewModel model;

    public SystemView()
    {
        this.model = new SystemViewModel(this);
    }

    Repository repository = Repository.getInstance();
    Scanner sc= repository.sc;

    public void run() {

        while(true)
        {
            System.out.println("Railway Reservation System");
            System.out.println("1)Add Train");
            System.out.println("2)Booking");
            System.out.println("3)Cancel");
            System.out.println("4)Print Booked Tickets");
            System.out.println("5)Print Available Tickets");
            System.out.println("6)Exit");

            System.out.println("Enter the option : ");
            int choice = sc.nextInt();

            switch(choice)
            {
                case 1 :
                    addNewTrain();
                    break;
                case 2 :
                    bookTicket();
                    break;
                case 3 :
                    cancelTicket();
                    break;
                case 4 :
                    printTickets();
                    break;
                case 5 :
                    printAvailableTickets();
                    break;
                case 6 :
                    return;

            }


        }

    }


    // 1) Add Train

    private void addNewTrain() {

        System.out.println("Enter the Number of Lower Berth : ");
        int lower = sc.nextInt();

        System.out.println("Enter the Number of Upper Berth : ");
        int upper = sc.nextInt();

        System.out.println("Enter the Number of Middle Berth : ");
        int middle = sc.nextInt();

        System.out.println("Number of waiting count : ");
        int wait = sc.nextInt();

        System.out.println("Number of RAC seats : ");
        int rac = sc.nextInt();

        Train train = new Train(lower,upper,middle,wait,rac);

        repository.trainList.add(train);

        System.out.println("Train Id : "+train.getTrainId());
        System.out.println("Train Added Successfully...!");

    }

    // 2)Book Ticket

    private void bookTicket() {

        System.out.println("Enter the train id : ");
        int trainId = sc.nextInt();

        Train train = model.getTrainById(trainId);

        if(train==null)
        {
            System.out.println("No Such train found...!");
            return;
        }

        System.out.println("Enter the Number of Seats want to Book : ");
        int tickets = sc.nextInt();

        int availableTicket = model.getAvailableTicketCount(train);

        if(availableTicket<tickets)
        {
            System.out.println("Required Ticket Not Available..! Available Tickets are : "+availableTicket);
            return;
        }

        Ticket ticket = new Ticket(trainId);

        while(tickets>0)
        {
            System.out.println("Enter the Passenger Name : ");
            String name = sc.next();

            System.out.println("Enter the passenger Age : ");
            int age = sc.nextInt();

            System.out.println("Enter the Passenger Gender [M | F] : ");
            String gender = sc.next();

            System.out.println("Enter the Preferred Berth [L | M | U ]  : ");
            String prefer = sc.next();

            Passenger passenger = null;
            if(age<5)
            {
                passenger = new Passenger(name,age,gender);
            }
            else {
                passenger = new Passenger(name,age,gender);
                model.allocateBerth(passenger,train,prefer);
                train.setBookedCount(train.getBookedCount()+1);
            }

            ticket.getPassengerList().add(passenger);

            tickets--;

            displayPassenger(passenger);
        }

        train.getTicketList().add(ticket);


        System.out.println("Ticket Booked Successfully...!");

    }


    // 4) Print All Tickets

    private void printTickets() {

        System.out.println("Enter the Train Id : ");
        int trainId = sc.nextInt();

        Train train = model.getTrainById(trainId);

        if(train==null)
        {
            System.out.println("No such train found..>!");
            return;
        }

        for(Ticket ticket : train.getTicketList())
        {
            System.out.println("Ticket Id : "+ticket.getTicketId());
            System.out.println("------------------------------------");
            displayPassengerDetails(ticket);
        }

        System.out.println("Total Ticket Booked : "+train.getBookedCount());
    }

    private void displayPassengerDetails(Ticket ticket) {

        for(Passenger passenger : ticket.getPassengerList())
        {
            displayPassenger(passenger);
            System.out.println("-------------------");
        }
    }

    private void displayPassenger(Passenger passenger) {
        System.out.println("Passenger Id : "+passenger.getPassengerId()+"\n"+
                "Passenger Name : "+passenger.getPassengerName()+"\n"+
                "Passenger Age : "+passenger.getPassengerAge()+"\n"+
                "Passenger Gender : "+passenger.getGender());
        if(passenger.getSeatNo()!=-1) {
            System.out.println("Passenger SeatNo : " + passenger.getSeatNo());
        }
        if(passenger.getAllocatedBerth()!=null) {
            System.out.println("Allocated Berth : " + passenger.getAllocatedBerth());
        }
    }

    // 3)cancel Ticket

    private void cancelTicket() {

        System.out.println("Enter the Train id : ");
        int trainId = sc.nextInt();

        Train train = model.getTrainById(trainId);

        if(train==null)
        {
            System.out.println("No such train found..!");
            return;
        }

        System.out.println("Enter the Ticket Id : ");
        int ticketId = sc.nextInt();

        Ticket ticket = model.getTicketById(train,ticketId);

        if(ticket==null)
        {
            System.out.println("No such ticket Booked yet...!");
            return;
        }

        displayPassengerDetails(ticket);

        System.out.println("Enter the passenger Id you want to cancel : ");
        int cancelId = sc.nextInt();

        Passenger passenger = model.getPassengerById(ticket.getPassengerList(),cancelId);

        if(passenger==null)
        {
            System.out.println("No such passenger Found..!");
            return;
        }

        String berth  = passenger.getAllocatedBerth();

        model.makeBerthAsAvailable(passenger,train,berth,ticket);

        System.out.println("Ticket Cancelled Successfully...!");

    }

    private void printAvailableTickets() {

        System.out.println("Enter the train id : ");
        int trainId = sc.nextInt();

        Train train = model.getTrainById(trainId);

        if(train==null)
        {
            System.out.println("No such Train Available..!");
            return;
        }
        System.out.println("Available seats in Lower Berth : "+availableInBerth(train.getLowerBerth()));


        System.out.println("Available seats in Middle Berth : "+availableInBerth(train.getMiddleBerth()));


        System.out.println("Available seats in Upper Berth : "+ availableInBerth(train.getUpperBerth()));



        System.out.println("Available seats in RAC List : "+(train.getRacCount()-train.getRACList().size()));


        System.out.println("Available seats in Waiting List : "+(train.getWaitingCount()-train.getWaitingList().size()));


    }


    private int availableInBerth(Passenger[] Berth) {

        int count=0;

        for(int i=0;i<Berth.length;i++)
        {
            if(Berth[i]==null)
            {
                count++;
            }
        }

        return count;
    }

}
