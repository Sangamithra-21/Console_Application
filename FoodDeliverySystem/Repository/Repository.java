package FoodDeliverySystem.Repository;

import FoodDeliverySystem.DTO.*;

import java.util.ArrayList;
import java.util.List;

public class Repository {

    public List<Agent> agentList;
    public List<Customer> customerList;
    public List<Restaurant> restaurantList;
    public List<Admin> adminList;
    public List<Order> orderList;

    private static Repository repository;

    private Repository()
    {
        agentList = new ArrayList<>();
        customerList = new ArrayList<>();
        restaurantList = new ArrayList<>();
        adminList = new ArrayList<>();
        orderList = new ArrayList<>();
    }

    public static Repository getInstance()
    {
        if(repository==null)
        {
            repository=new Repository();
        }
        return repository;
    }

}
