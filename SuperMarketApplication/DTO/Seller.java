package SuperMarketApplication.DTO;

import SuperMarketApplication.Repository.Node;

public class Seller extends User {

    static int sid=1;

    private int sellerId;

    public Node Products;

    public Seller(String username, String password) {
        super(username, password);
        this.sellerId = sid++;
        this.Products = null;
    }

    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    @Override
    public String toString() {
        return "Seller{" +
                "sellerId=" + sellerId +
                '}';
    }
}
