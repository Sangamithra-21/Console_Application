package RailwayReservation;

import java.util.ArrayList;
import java.util.List;

public class Ticket {
    int PNR;
    static int pnrNo = 1;

    @Override
    public String toString() {
        return "Ticket{" +
                "PNR=" + PNR +
                ", source=" + source +
                ", destination=" + destination +
                ", noOfSeats=" + noOfSeats +
                ", status='" + status + '\'' +
                ", Type='" + Type + '\'' +
                '}';
    }

    char source;
    char destination;
    int noOfSeats;
    String status;
    String Type;

    static List<Ticket> ticketList = new ArrayList<>();

    Ticket(int PNR,char source,char destination,int noOfSeats,String status,String ticketType)
    {
        this.PNR = pnrNo++;
        this.source = source;
        this.destination = destination;
        this.noOfSeats = noOfSeats;
        this.status = status;
    }
}
