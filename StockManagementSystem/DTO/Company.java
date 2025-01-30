package StockManagementSystem.DTO;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Company {

    static int cid=1;
    private int companyId;
    private String companyName;
    private List<PurchaseBill> purchaseBillList;
    private List<SalesBill> salesBillList;
    private Map<Integer,Product> productList;
    private Map<Integer,SalesBill> deliverable;


    public Company(String name)
    {
        this.companyId = cid++;
        this.companyName = name;
        this.purchaseBillList = new ArrayList<>();
        this.salesBillList = new ArrayList<>();
        this.productList = new HashMap<>();
        this.deliverable = new HashMap<>();
    }

    public int getCompanyId() {
        return companyId;
    }


    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public List<PurchaseBill> getPurchaseBillList() {
        return purchaseBillList;
    }

    public void setPurchaseBillList(List<PurchaseBill> purchaseBillList) {
        this.purchaseBillList = purchaseBillList;
    }

    public List<SalesBill> getSalesBillList() {
        return salesBillList;
    }

    public void setSalesBillList(List<SalesBill> salesBillList) {
        this.salesBillList = salesBillList;
    }

    public Map<Integer, Product> getProductList() {
        return productList;
    }

    public void setProductList(Map<Integer, Product> productList) {
        this.productList = productList;
    }

    public Map<Integer, SalesBill> getDeliverable() {
        return deliverable;
    }

    public void setDeliverable(Map<Integer, SalesBill> deliverable) {
        this.deliverable = deliverable;
    }
}
