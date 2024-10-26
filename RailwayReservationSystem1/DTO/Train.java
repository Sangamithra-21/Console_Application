package RailwayReservationSystem1.DTO;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Train {

    static int tid=1;
    private int trainId;
    private Passenger[] lowerBerth;
    private Passenger[] upperBerth;
    private Passenger[] middleBerth;

    private int lowerCount;
    private int upperCount;
    private int middleCount;

    private int waitingCount;
    private int racCount;

    private Queue<Passenger> waitingList;
    private Queue<Passenger> RACList;

    private List<Ticket> ticketList;

    private int bookedCount;

    public Train(int lowerBerth,int upperBerth,int middleBerth,int waiting,int rac){
        this.trainId = tid++;
        this.lowerBerth = new Passenger[lowerBerth];
        this.upperBerth = new Passenger[upperBerth];
        this.middleBerth = new Passenger[middleBerth];
        this.waitingList = new LinkedList<>();
        this.RACList = new LinkedList<>();
        this.waitingCount = waiting;
        this.racCount = rac;
        this.bookedCount = 0;
        this.lowerCount = lowerBerth;
        this.middleCount = middleBerth;
        this.upperCount = upperBerth;
        this.ticketList = new ArrayList<>();
    }

    public List<Ticket> getTicketList() {
        return ticketList;
    }

    public void setTicketList(List<Ticket> ticketList) {
        this.ticketList = ticketList;
    }

    public int getLowerCount() {
        return lowerCount;
    }

    public void setLowerCount(int lowerCount) {
        this.lowerCount = lowerCount;
    }

    public int getUpperCount() {
        return upperCount;
    }

    public void setUpperCount(int upperCount) {
        this.upperCount = upperCount;
    }

    public int getMiddleCount() {
        return middleCount;
    }

    public void setMiddleCount(int middleCount) {
        this.middleCount = middleCount;
    }

    public int getBookedCount() {
        return bookedCount;
    }

    public void setBookedCount(int bookedCount) {
        this.bookedCount = bookedCount;
    }

    public int getWaitingCount() {
        return waitingCount;
    }

    public void setWaitingCount(int waitingCount) {
        this.waitingCount = waitingCount;
    }

    public int getRacCount() {
        return racCount;
    }

    public void setRacCount(int racCount) {
        this.racCount = racCount;
    }

    public int getTrainId() {
        return trainId;
    }

    public Passenger[] getLowerBerth() {
        return lowerBerth;
    }

    public void setLowerBerth(Passenger[] lowerBerth) {
        this.lowerBerth = lowerBerth;
    }

    public Passenger[] getUpperBerth() {
        return upperBerth;
    }

    public void setUpperBerth(Passenger[] upperBerth) {
        this.upperBerth = upperBerth;
    }

    public Passenger[] getMiddleBerth() {
        return middleBerth;
    }

    public void setMiddleBerth(Passenger[] middleBerth) {
        this.middleBerth = middleBerth;
    }

    public Queue<Passenger> getWaitingList() {
        return waitingList;
    }

    public void setWaitingList(Queue<Passenger> waitingList) {
        this.waitingList = waitingList;
    }

    public Queue<Passenger> getRACList() {
        return RACList;
    }

    public void setRACList(Queue<Passenger> RACList) {
        this.RACList = RACList;
    }
}
