package InvoiceManagementSystem.DTO;

import java.util.ArrayList;
import java.util.List;

public class Customer {

    static int cid = 1;
    private int customerId;
    private String customerName;
    private String phoneNo;
    public List<Invoices> invoiceList;

    public Customer(String name,String phoneno)
    {
        this.customerId = cid++;
        this.customerName = name;
        this.invoiceList = new ArrayList<>();
        this.phoneNo = phoneno;
    }

    public void setPhoneNo(String phoneNo)
    {
        this.phoneNo = phoneNo;
    }

    public String getPhoneNo()
    {
        return phoneNo;
    }

    public int getCustomerId() {
        return customerId;
    }
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public List<Invoices> getInvoiceList() {
        return invoiceList;
    }

    public void setInvoiceList(List<Invoices> invoiceList) {
        this.invoiceList = invoiceList;
    }

}
