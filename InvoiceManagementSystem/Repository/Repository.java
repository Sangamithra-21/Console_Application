package InvoiceManagementSystem.Repository;
import InvoiceManagementSystem.DTO.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Repository {

    public List<Customer> customerList;
    public List<Invoices> invoiceList;
    public List<Items> itemList;
    public Scanner sc;

    private static Repository repository;

    private Repository()
    {
        this.customerList = new ArrayList<>();
        this.invoiceList = new ArrayList<>();
        this.itemList = new ArrayList<>();
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
