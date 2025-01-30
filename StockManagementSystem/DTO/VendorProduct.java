package StockManagementSystem.DTO;

public class VendorProduct {

    static int vpid=1;
    private int productId;
    private String productName;
    private double productCost;
    private int quantity;

    public VendorProduct(String name,double cost)
    {
        this.productId = vpid++;
        this.productName = name;
        this.productCost = cost;
    }

    public int getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getProductCost() {
        return productCost;
    }

    public void setProductCost(double productCost) {
        this.productCost = productCost;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
