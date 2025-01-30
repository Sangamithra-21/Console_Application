package StockManagementSystem.DTO;
import java.util.ArrayList;
import java.util.List;

public class Customer {

    static int cid=1;
    private int customerId;
    private String customerName;
    private List<CProduct> purchaseProducts;
    public int orderId = 1;

    public Customer(String name)
    {
        this.customerId = cid++;
        this.customerName = name;
        this.purchaseProducts = new ArrayList<>();
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

    public List<CProduct> getPurchaseProducts() {
        return purchaseProducts;
    }

    public void setPurchaseProducts(List<CProduct> purchaseProducts) {
        this.purchaseProducts = purchaseProducts;
    }
}
