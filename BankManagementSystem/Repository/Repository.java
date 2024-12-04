package BankManagementSystem.Repository;

import BankManagementSystem.DTO.Customer;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Repository {

    private static Repository repository;
    public Scanner scanner;

    public List<Customer> customerList;

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
