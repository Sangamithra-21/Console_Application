package InvoiceManagementSystem.DTO;

import java.util.ArrayList;
import java.util.List;

public class Invoices {
    static int iid = 1;
    private int invoiceId;
    private int customerId;
    public List<Items> invoiceItems;
    private double totalAmount;

    public Invoices(int customerId,double totalAmount)
    {
        this.invoiceId = iid++;
        this.customerId = customerId;
        this.invoiceItems = new ArrayList<>();
        this.totalAmount = totalAmount;

    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public List<Items> getInvoiceItems() {
        return invoiceItems;
    }

    public void setInvoiceItems(List<Items> invoiceItems) {
        this.invoiceItems = invoiceItems;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
}
