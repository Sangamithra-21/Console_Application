package StockManagementSystem.Repository;

import StockManagementSystem.DTO.Company;
import StockManagementSystem.DTO.Customer;
import StockManagementSystem.DTO.Vendor;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Repository {

    private static Repository repository;

    public List<Customer> customerList;
    public List<Vendor> vendorList;
    public List<Company> companyList;
    public Scanner sc;

    private Repository()
    {
        this.customerList = new ArrayList<>();
        this.vendorList = new ArrayList<>();
        this.companyList = new ArrayList<>();
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
