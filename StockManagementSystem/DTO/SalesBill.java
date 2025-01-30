package StockManagementSystem.DTO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SalesBill {

    static int sid=1;
    private int salesId;
    private List<CProduct> productList;
    private double cost;
    private LocalDate date;
    private int customerId;
    private String status;

    public SalesBill(int customerId,double cost,String status,LocalDate date)
    {
        this.productList = new ArrayList<>();
       this.salesId = sid++;
        this.cost = cost;
        this.date = date;
        this.customerId = customerId;
        this.status = status;
    }

    public int getSalesId() {
        return salesId;
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
