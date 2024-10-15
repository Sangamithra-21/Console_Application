package BusReservationSystem.Repository;

import BusReservationSystem.DTO.Bus;
import BusReservationSystem.DTO.Customer;
import BusReservationSystem.DTO.Ticket;

import java.util.ArrayList;
import java.util.List;

public class Repository {

    private static Repository repository;

    public List<Bus> busList;
    public List<Ticket> ticketList;
    public List<Customer> customerList;

    private Repository(){

        busList = new ArrayList<>();
        ticketList = new ArrayList<>();
        customerList = new ArrayList<>();

    }

    public static Repository getInstance()
    {
        if(repository==null)
        {
            repository = new Repository();
        }
        return repository;
    }
}
