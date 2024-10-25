package GiftCard.DTO;

import java.util.ArrayList;
import java.util.List;

public class Customer {

    static int cid= 1;
    private int customerId;
    private String customerName;
    private String password;
    private double accBalance;
    private int reedemPoints;
    private double purchaseAmt;
    private List<Giftcard> giftCardList;

    public Customer(String name,String password)
    {
        this.customerId = cid++;
        this.customerName = name;
        this.password = password;
        this.purchaseAmt = 0;
        this.reedemPoints = 0;
        this.accBalance = 1000;
        this.giftCardList = new ArrayList<>();
    }

    public double getPurchaseAmt() {
        return purchaseAmt;
    }

    public void setPurchaseAmt(double purchaseAmt) {
        this.purchaseAmt = purchaseAmt;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getAccBalance() {
        return accBalance;
    }

    public void setAccBalance(double accBalance) {
        this.accBalance = accBalance;
    }

    public int getReedemPoints() {
        return reedemPoints;
    }

    public void setReedemPoints(int reedemPoints) {
        this.reedemPoints = reedemPoints;
    }

    public List<Giftcard> getGiftCardList() {
        return giftCardList;
    }

    public void setGiftCardList(List<Giftcard> giftCardList) {
        this.giftCardList = giftCardList;
    }
}
