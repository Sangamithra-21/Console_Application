package ParkingSlot.DTO;

public class Ticket {

    private String ticketId;

    public Ticket(String ticket)
    {
        this.ticketId = ticket;
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

}
