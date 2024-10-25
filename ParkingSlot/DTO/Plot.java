package ParkingSlot.DTO;

import java.util.ArrayList;
import java.util.List;

public class Plot {

    private String plotName;
    private Slot[][] parkingLot;
    private List<Ticket> ticketList;

    public Plot(String name,int floors,int slots)
    {
        this.plotName = name;
        this.parkingLot = new Slot[floors][slots];
        this.ticketList = new ArrayList<>();
    }

    public String getPlotName() {
        return plotName;
    }

    public void setPlotName(String plotName) {
        this.plotName = plotName;
    }

    public Slot[][] getParkingLot() {
        return parkingLot;
    }

    public void setParkingLot(Slot[][] parkingLot) {
        this.parkingLot = parkingLot;
    }

    public List<Ticket> getTicketList() {
        return ticketList;
    }

    public void setTicketList(List<Ticket> ticketList) {
        this.ticketList = ticketList;
    }
}
