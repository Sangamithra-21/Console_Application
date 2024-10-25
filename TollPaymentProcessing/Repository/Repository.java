package TollPaymentProcessing.Repository;

import TollPaymentProcessing.DTO.TollGate;
import TollPaymentProcessing.DTO.Vehicle;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Repository {

    private static Repository repository;

    public TollGate[] tollGates;
    public List<Vehicle> vehicleList;
    public Scanner sc;

    private Repository()
    {
        this.vehicleList = new ArrayList<>();
        this.sc = new Scanner(System.in);
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
