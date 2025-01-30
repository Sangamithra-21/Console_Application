package StockManagementSystem.DTO;

public class Product {
    static int pid=1;
    private int productId;
    private String productName;
    private double productCost;
    private int quantity;

    public Product(int id,String name,double cost,int quantity)
    {
        this.productId = pid++;
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
