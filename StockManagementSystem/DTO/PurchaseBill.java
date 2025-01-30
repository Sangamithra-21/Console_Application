package StockManagementSystem.DTO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PurchaseBill {

    static int psid=1;
    private int purchaseId;
    private int vendorId;
    private List<CProduct> productList;
    private double cost;
    private String status;
    private LocalDate date;
    private int quantity;

    public PurchaseBill(int vendorId,double cost,String status,LocalDate date,int quantity)
    {
        this.purchaseId = psid++;
        this.vendorId = vendorId;
        this.cost = cost;
        this.status = status;
        this.productList = new ArrayList<>();
        this.date = date;
        this.quantity = quantity;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getPurchaseId() {
        return purchaseId;
    }

    public int getVendorId() {
        return vendorId;
    }

    public void setVendorId(int vendorId) {
        this.vendorId = vendorId;
    }

    public List<CProduct> getProductList() {
        return productList;
    }

    public void setProductList(List<CProduct> productList) {
        this.productList = productList;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "PurchaseBill{" +
                "purchaseId=" + purchaseId +
                ", vendorId=" + vendorId +
                ", productList=" + productList +
                ", cost=" + cost +
                ", date=" + date +
                '}';
    }
}
