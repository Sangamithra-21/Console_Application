package FoodDeliverySystem.DTO;

import java.util.List;

public class Order {
    static int oid = 1;
    private int orderId;


    private int customerId;
    public List<Items> orderItems;

    private int orderCost;

    public Order(int customerId,int price,List<Items> list)
    {
        this.orderId  = oid++;
        this.customerId = customerId;
        this.orderCost = price;
        this.orderItems = list;
    }

    public int getOrderCost() {
        return orderCost;
    }

    public void setOrderCost(int orderCost) {
        this.orderCost = orderCost;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

}

