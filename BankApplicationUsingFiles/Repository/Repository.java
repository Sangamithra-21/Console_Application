package BankApplicationUsingFiles.Repository;

import BankApplicationUsingFiles.DTO.Customer;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Repository {

    private static Repository repository;

    public List<Customer> customerList;
    public Scanner scanner;

    private Repository()
    {
        this.customerList = new ArrayList<>();
        this.scanner = new Scanner(System.in);

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
