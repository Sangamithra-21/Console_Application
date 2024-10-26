package RailwayReservationSystem1.RailwaySystem;

import RailwayReservationSystem1.DTO.Passenger;
import RailwayReservationSystem1.DTO.Ticket;
import RailwayReservationSystem1.DTO.Train;
import RailwayReservationSystem1.Repository.Repository;

import java.util.List;
import java.util.Scanner;

public class SystemViewModel {

    private SystemView view;

    public SystemViewModel(SystemView view) {

        this.view = view;
    }

    Repository repository = Repository.getInstance();

    Scanner sc = repository.sc;

    public Train getTrainById(int trainId) {

        for(Train train : repository.trainList)
        {
            if(train.getTrainId()==trainId)
            {
                return train;
            }
        }
        return null;
    }

    public int getAvailableTicketCount(Train train) {

        int totalCount = train.getUpperBerth().length + train.getLowerBerth().length + train.getMiddleBerth().length+
                         train.getWaitingCount() + train.getRacCount();
        int bookedCount = train.getBookedCount();

        return totalCount-bookedCount;
    }

    public void allocateBerth(Passenger passenger,Train train,String prefer) {

        if(passenger.getPassengerAge()>60)
        {
            if(train.getLowerCount()>0 && !prefer.equals("L"))
            {
                System.out.println("Your age is greated than 60.. So Lower berth is allocated");
                allocateSeat(passenger,train.getLowerBerth(),"L");
                train.setLowerCount(train.getLowerCount()-1);
                return;
            }
        }
        if(passenger.getGender().equals("F"))
        {
            System.out.println("Do you have a child : [1 for yes | 0 for No]");
            int choice = sc.nextInt();
            if(choice==1 && !prefer.equals("L") && train.getLowerCount()>0)
            {
                System.out.println("Since you have a child..So Lowerberth is suggested");
                allocateSeat(passenger, train.getLowerBerth(),"L");
                train.setLowerCount(train.getLowerCount()-1);
                return;
            }

        }

        // Check for prefer Berth
        if(train.getLowerCount()>0 && prefer.equals("L")) {
            allocateSeat(passenger,train.getLowerBerth(),"L");
            train.setLowerCount(train.getLowerCount()-1);
            return;
        }
        if(train.getMiddleCount()>0 && prefer.equals("M")) {
            allocateSeat(passenger,train.getMiddleBerth(),"M");
            train.setMiddleCount(train.getMiddleCount()-1);
            return;
        }
        if(train.getUpperCount()>0 && prefer.equals("U")){
            allocateSeat(passenger,train.getUpperBerth(),"U");
            train.setUpperCount(train.getUpperCount()-1);
            return;
        }

        // Check for Other availability

        System.out.println("Perfered Berth Not Available..!");

        if(train.getLowerCount()>0) {
            allocateSeat(passenger,train.getLowerBerth(),"L");
            train.setLowerCount(train.getLowerCount()-1);

        }

        else if(train.getMiddleCount()>0) {
            allocateSeat(passenger,train.getMiddleBerth(),"M");
            train.setMiddleCount(train.getMiddleCount()-1);

        }

        else if(train.getUpperCount()>0){
            allocateSeat(passenger,train.getUpperBerth(),"U");
            train.setUpperCount(train.getUpperCount()-1);

        }

        else if(train.getRACList().size()<train.getRacCount())
        {
            System.out.println("Ticket are in RAC List");
            train.getRACList().add(passenger);
            passenger.setAllocatedBerth("RAC");
            train.setRacCount(train.getRacCount()-1);
        }

        else if(train.getWaitingList().size()<train.getWaitingCount())
        {
            System.out.println("Ticket are in Waiting List");
            train.getWaitingList().add(passenger);
            passenger.setAllocatedBerth("WAITING");
            train.setWaitingCount(train.getWaitingCount()-1);
        }
        else {
            System.out.println("No Tickets Available");
        }

    }

    private void allocateSeat(Passenger passenger, Passenger[] passengers, String berth) {

        int seatNo = checkForBerth(passengers);
        if(seatNo!=-1)
        {
            passengers[seatNo] = passenger;
            passenger.setAllocatedBerth(berth);
            passenger.setSeatNo(seatNo);
        }

    }

    private int checkForBerth(Passenger[] passengers) {

        for(int i=0;i<passengers.length;i++)
        {
            if(passengers[i]==null)
            {
                return i;
            }
        }

        return -1;
    }

    public Ticket getTicketById(Train train,int ticketId) {

        for(Ticket ticket : train.getTicketList())
        {
            if(ticket.getTicketId()==ticketId)
            {
                return ticket;
            }
        }
        return null;
    }

    public Passenger getPassengerById(List<Passenger> passengerList,int passId) {

        for(Passenger passenger : passengerList)
        {
            if(passenger.getPassengerId()==passId)
            {
                return passenger;
            }
        }
        return null;
    }

    public void makeBerthAsAvailable(Passenger passenger,Train train,String berth,Ticket ticket) {

        int freeSeat = passenger.getSeatNo();

        if(berth.equals("L"))
        {
            train.setLowerCount(train.getLowerCount()+1);
            if(!train.getRACList().isEmpty()) {
                movePassengerFromRacToConfirmList(train,passenger, train.getLowerBerth(), freeSeat, ticket, berth);
            }

        }
        else if(berth.equals("M"))
        {
            train.setMiddleCount(train.getMiddleCount()+1);
            if(!train.getRACList().isEmpty()) {
                movePassengerFromRacToConfirmList(train,passenger, train.getMiddleBerth(), freeSeat, ticket, berth);
            }


        }
        else if(berth.equals("U"))
        {
            train.setUpperCount(train.getUpperCount()+1);
            if(!train.getRACList().isEmpty()) {
                movePassengerFromRacToConfirmList(train,passenger, train.getUpperBerth(), freeSeat, ticket, berth);
            }
        }

    }

    private void movePassengerFromWaitToRac(Train train,int seatNo) {

        Passenger waitPass = train.getWaitingList().poll();

        if(waitPass!=null) {
            waitPass.setSeatNo(seatNo);
            waitPass.setAllocatedBerth("RAC");
            train.getRACList().add(waitPass);

        }



    }

    private void movePassengerFromRacToConfirmList(Train train,Passenger passenger, Passenger[] passengers, int freeSeat, Ticket ticket, String berth) {

        if (passenger != null) {
            ticket.getPassengerList().remove(passenger);
        }

        Passenger racPass = train.getRACList().poll();


        if(racPass!=null) {
            int seatNo = racPass.getSeatNo();
            racPass.setAllocatedBerth(berth);
            racPass.setSeatNo(freeSeat);

            passengers[freeSeat] = racPass;


            if(!train.getWaitingList().isEmpty()) {

                movePassengerFromWaitToRac(train,seatNo);
            }

        }


    }
}
