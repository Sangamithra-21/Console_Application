package StockManagementSystem.DTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Vendor {

    static int id=1;
    private int vendorId;
    private String vendorName;
    private Map<Integer,VendorProduct> productList;
    private Map<Integer,PurchaseBill> deliverable;

    public Vendor(String vendorName)
    {
        this.vendorId = id++;
        this.vendorName = vendorName;
        this.deliverable = new HashMap<>();
        this.productList = new HashMap<>();
    }

    public int getVendorId() {
        return vendorId;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public Map<Integer, VendorProduct> getProductList() {
        return productList;
    }

    public void setProductList(Map<Integer, VendorProduct> productList) {
        this.productList = productList;
    }

    public Map<Integer, PurchaseBill> getDeliverable() {
        return deliverable;
    }

    public void setDeliverable(Map<Integer, PurchaseBill> deliverable) {
        this.deliverable = deliverable;
    }
}
