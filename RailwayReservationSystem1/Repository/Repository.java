package RailwayReservationSystem1.Repository;

import RailwayReservationSystem1.DTO.Ticket;
import RailwayReservationSystem1.DTO.Train;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Repository {

    public List<Train> trainList;
    public List<Ticket> ticketList;
    public Scanner sc;

    private Repository()
    {
        this.trainList = new ArrayList<>();
        this.ticketList = new ArrayList<>();
        this.sc = new Scanner(System.in);
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
