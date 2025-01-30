package StockManagementSystem.DTO;

public class CProduct {
    private int productId;
    private String productName;
    private double productCost;
    private int quantity;

    public CProduct(int id,String name,double cost,int quantity)
    {
        this.productId = id;
        this.productName = name;
        this.productCost = cost;
        this.quantity = quantity;
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
