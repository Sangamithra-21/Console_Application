package RailwayReservationSystem1.DTO;

import java.util.ArrayList;
import java.util.List;

public class Ticket {

    static int tid=1;
    private int ticketId;
    private int trainId;
    private List<Passenger> passengerList;

    public Ticket(int trainId)
    {
        this.trainId = trainId;
        this.ticketId = tid++;
        this.passengerList = new ArrayList<>();
    }

    public int getTicketId() {
        return ticketId;
    }

    public int getTrainId() {
        return trainId;
    }

    public void setTrainId(int trainId) {
        this.trainId = trainId;
    }

    public List<Passenger> getPassengerList() {
        return passengerList;
    }

    public void setPassengerList(List<Passenger> passengerList) {
        this.passengerList = passengerList;
    }
}
