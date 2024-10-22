package SuperMarketApplication.DTO;

import SuperMarketApplication.Repository.Node;

public class Product {

    static int pid = 1;
    private int productId = pid;
    private String productName;
    public Node Items;

    public Product(String productName)
    {
        this.productName = productName;
        Items = null;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
