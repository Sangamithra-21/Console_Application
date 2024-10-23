package PharmacyManagementSystem.DTO;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class Customer {

    static int cid=1;
    private int customerId;
    private String customerName;
    private String phoneNo;
    private List<Medicine> cartList;
    private List<Purchase> purchaseList;

    public Customer(String name,String phoneNo)
    {
        this.customerName=name;
        this.customerId = cid++;
        this.phoneNo = phoneNo;
        this.cartList = new ArrayList<>();
        this.purchaseList = new ArrayList<>();
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

    public List<Medicine> getCartList() {
        return cartList;
    }

    public void setCartList(List<Medicine> cartList) {
        this.cartList = cartList;
    }

    public List<Purchase> getPurchaseList() {
        return purchaseList;
    }

    public void setPurchaseList(List<Purchase> purchaseList) {
        this.purchaseList = purchaseList;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
}
