package SuperMarketApplication.DTO;

import SuperMarketApplication.Repository.Node;

public class Buyer extends User{


    static int bid=1;

    private int buyerId;

    public Node cart;

    public Node orderList;


    public Buyer(String username, String password) {
        super(username, password);
        this.buyerId = bid++;
        this.cart = null;
        this.orderList = null;

    }

    public int getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(int buyerId) {
        this.buyerId = buyerId;
    }
}
