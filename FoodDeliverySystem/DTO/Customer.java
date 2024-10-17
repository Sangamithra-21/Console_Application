package FoodDeliverySystem.DTO;

import java.util.ArrayList;
import java.util.List;

public class Customer extends User {

    static int cid=1;
    private int customerId;
    private int[] currLocation;
    public List<Items> cartList;
    public List<Order> customerOrder;

    public Customer(String username,String password,int x,int y)
    {
        super(username,password);
        this.customerId=cid++;
        currLocation = new int[]{x,y};
        this.cartList = new ArrayList<>();
        this.customerOrder = new ArrayList<>();
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int[] getCurrLocation() {
        return currLocation;
    }

    public void setCurrLocation(int[] currLocation) {
        this.currLocation = currLocation;
    }

}
