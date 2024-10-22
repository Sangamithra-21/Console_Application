package SuperMarketApplication.DTO;

import SuperMarketApplication.Repository.Node;

import java.time.LocalTime;

public class Order {

    static int oid = 1;
    private int orderId;
    private int amount;
    public Node orderItems;

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    private LocalTime time;

    public Order(Node Object,int amount,LocalTime time)
    {
        this.orderId = oid++;
        orderItems = Object;
        this.amount = amount;
        this.time = time;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }


    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }


}
