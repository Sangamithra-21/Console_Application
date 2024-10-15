package BusReservationSystem.DTO;

import java.util.ArrayList;
import java.util.List;

public class Ticket {

    static int tid = 1;
    private int ticketId;
    public List<Passenger> passengers;

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public Ticket(List<Passenger> passList)
    {
        this.ticketId = tid++;
        this.passengers = passList;
    }


}
