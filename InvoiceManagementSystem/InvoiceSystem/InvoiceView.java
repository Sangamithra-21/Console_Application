package InvoiceManagementSystem.InvoiceSystem;

import InvoiceManagementSystem.DTO.Customer;
import InvoiceManagementSystem.DTO.Invoices;
import InvoiceManagementSystem.DTO.Items;
import InvoiceManagementSystem.Repository.Repository;

import java.util.List;
import java.util.Scanner;

public class InvoiceView {

    private InvoiceViewModel model;

    public InvoiceView()
    {
        this.model = new InvoiceViewModel(this);
    }

    Repository repository = Repository.getInstance();

    Scanner sc = repository.sc;

    public void run() {

        while(true)
        {
            System.out.println("----------------------------------------------------------------------");
            System.out.println("Invoice Management");
            System.out.println("----------------------------------------------------------------------");
            System.out.println("1)Add Customer");
            System.out.println("2)Add Invoice");
            System.out.println("3)Add Item to Invoice");
            System.out.println("4)List all customers");
            System.out.println("5)List all Invoice");
            System.out.println("6)List all Invoices of a acustomer");
            System.out.println("7)Display an invoice");
            System.out.println("8)Exit");

            System.out.println("----------------------------------------------------------------------");

            System.out.println("Enter your option");
            int option = sc.nextInt();

            switch(option){
                case 1 :
                    addCustomer();
                    break;
                case 2 :
                    addInvoice();
                    break;
                case 3 :
                    addItemToInvoice();
                    break;
                case 4 :
                    listCustomers();
                    break;
                case 5 :
                    listInvoices(repository.invoiceList);
                    break;
                case 6 :
                    listInvoicesOfCustomer();
                    break;
                case 7 :
                    displayInvoice();
                    break;
                case 8 :
                    break;

            }
        }


    }

    // 1)Add Customer

    private void addCustomer() {

        System.out.println("Enter the Customer Name : ");
        String customerName = sc.next();

        System.out.println("Enter the phone Number : ");
        String phoneNo = sc.next();

        Customer customer = model.addCustomer(customerName,phoneNo);

        System.out.println("Your Customer Id : "+customer.getCustomerId());
        System.out.println("Customer Added Successfully..!");

    }

    // 2)Add Invoice

    private void addInvoice()
    {
        System.out.println("Enter the customer id : ");
        int customerId = sc.nextInt();

        Customer customer = model.getCustomerById(customerId);

        if(customer==null)
        {
            System.out.println("No Such Customer Exists..!");
            return;
        }

        Invoices invoice = model.addInvoice(customer);

        System.out.println("Invoice Id : "+invoice.getInvoiceId());

        System.out.println("Invoice Added Successfully..!");
    }

    // 3) Add Item to Invoice

    private void addItemToInvoice()
    {
        System.out.println("Enter the Customer Id : ");
        int customerId = sc.nextInt();

        Customer customer = model.getCustomerById(customerId);

        if(customer==null)
        {
            System.out.println("No such Customer Found");
            return;
        }


        System.out.print("Your Invoice Ids : ");
        displayInvoiceIds(customer.invoiceList);
        System.out.println();

        System.out.println("Enter the Invoice Id : ");
        int invoiceId = sc.nextInt();

        Invoices invoice = model.getInvoiceById(customer.invoiceList,invoiceId);

        if(invoice==null)
        {
            System.out.println("No such invoice found..!");
            return;
        }

        System.out.println("Enter the Item Name : ");
        String itemName = sc.next();

        System.out.println("Enter the Item Price ");
        double price = sc.nextDouble();

        model.addItemToInvoice(invoice,itemName,price);


        System.out.println("Item added to the invoice successfully...!");

    }

    private void displayInvoiceIds(List<Invoices> invoiceList) {

        for(Invoices invoice : invoiceList)
        {
            System.out.print(invoice.getInvoiceId()+" ");
        }
    }

    // 4) List all customers

    private void listCustomers()
    {
        System.out.println("Customer List");
        System.out.println("------------------------------------------------------");
        for(Customer customer : repository.customerList)
        {
            System.out.println("Customer Id   : "+customer.getCustomerId()+"\n"+
                               "Customer Name : "+customer.getCustomerName()+"\n"+
                               "Phone Number  : "+customer.getPhoneNo()+"\n"+
                               "Invoice Count : "+customer.getInvoiceList().size());
            System.out.println("------------------------------------------------------");
        }
    }

    // 5)List All Invoices

    private void listInvoices(List<Invoices> invoiceList)
    {
        System.out.println("Invoice Details");
        System.out.println("------------------------------------------------------");

        for(Invoices invoice : invoiceList)
        {
            System.out.println("Invoice Id     : "+invoice.getInvoiceId());
            displayInvoiceItems(invoice);
        }
    }

    private void displayInvoiceItems(Invoices invoice) {

        for(Items item : invoice.invoiceItems){

            System.out.print("Item Id    : "+item.getItemId()+" ");
            System.out.print("Item Name  : "+item.getItemName()+" ");
            System.out.print("Item price : "+item.getPrice()+" ");
            System.out.println();

        }
        System.out.println("Total Item Count     :"+invoice.getInvoiceItems().size());
        System.out.println("Total Invoice Amount : "+invoice.getTotalAmount());
        System.out.println("------------------------------------------------------");


    }

    // 6) List Invoices of the customer

    private void listInvoicesOfCustomer()
    {
        System.out.println("Enter customer id : ");
        int customerid = sc.nextInt();

        Customer customer = model.getCustomerById(customerid);

        if(customer==null)
        {
            System.out.println("No such Customer Found..!");
            return;
        }

        if(customer.invoiceList.isEmpty())
        {
            System.out.println("No Invoices found..!");
            return;
        }

        listInvoices(customer.invoiceList);

    }

    // 7) Display Invoice

    private void displayInvoice()
    {
        System.out.println("Enter Invoice Id : ");
        int invoiceId = sc.nextInt();

        Invoices invoice = model.getInvoiceById(repository.invoiceList,invoiceId);

        if(invoice==null)
        {
            System.out.println("No such Invoice Found..!");
            return;
        }

        displayInvoiceItems(invoice);


    }


}
