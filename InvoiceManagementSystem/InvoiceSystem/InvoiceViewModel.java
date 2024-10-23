package InvoiceManagementSystem.InvoiceSystem;

import InvoiceManagementSystem.DTO.Customer;
import InvoiceManagementSystem.DTO.Invoices;
import InvoiceManagementSystem.DTO.Items;
import InvoiceManagementSystem.Repository.Repository;

import java.util.List;

public class InvoiceViewModel {

    private InvoiceView view;

    public InvoiceViewModel(InvoiceView view) {
        this.view = view;
    }

    Repository repository = Repository.getInstance();

    public Customer addCustomer(String customerName, String phoneNo) {

        Customer customer = new Customer(customerName,phoneNo);

        repository.customerList.add(customer);

        return customer;
    }


    public Customer getCustomerById(int customerId) {

        for(Customer customer : repository.customerList)
        {
            if(customer.getCustomerId()==customerId)
            {
                return customer;
            }
        }
        return null;
    }

    public Invoices addInvoice(Customer customer) {

        Invoices invoice = new Invoices(customer.getCustomerId(),0);

        repository.invoiceList.add(invoice);

        customer.invoiceList.add(invoice);

        return invoice;
    }

    public Invoices getInvoiceById(List<Invoices> invoiceList , int invoiceId) {

        for(Invoices invoices : invoiceList)
        {
            if(invoices.getInvoiceId()==invoiceId)
            {
                return invoices;
            }
        }

        return null;

    }

    public void addItemToInvoice(Invoices invoice,String itemName, double price) {

        Items items = new Items(itemName,price);

        invoice.invoiceItems.add(items);

        double amt = invoice.getTotalAmount()+price;

        invoice.setTotalAmount(amt);

        repository.itemList.add(items);

    }

}
