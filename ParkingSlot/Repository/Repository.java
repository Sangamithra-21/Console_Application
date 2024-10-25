package ParkingSlot.Repository;

import ParkingSlot.DTO.Plot;
import ParkingSlot.DTO.Ticket;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Repository {

    public List<Plot> plotList;
    public Scanner sc;
    public String parkingLotId ;
    public List<Ticket> ticketList;

    private Repository()
    {
        this.plotList = new ArrayList<>();
        this.sc = new Scanner(System.in);
        this.parkingLotId = "PR1234";
        this.ticketList  = new ArrayList<>();
    }

    private static Repository repository;

    public static Repository getInstance()
    {
        if(repository==null)
        {
            repository = new Repository();
        }
        return repository;
    }

}
