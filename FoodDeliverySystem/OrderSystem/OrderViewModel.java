package FoodDeliverySystem.OrderSystem;

import FoodDeliverySystem.DTO.*;
import FoodDeliverySystem.Repository.Repository;

import java.util.ArrayList;
import java.util.List;

public class OrderViewModel {

    Repository repository = Repository.getInstance();

    private OrderView handler;

    public OrderViewModel(OrderView view) {
        this.handler= view;
    }

    public void addRestaurant() {

        // Restaurant 1
        Items item1 = new Items("Idli",40);
        Items item2 = new Items("Dosa",80);
        Items item3 = new Items("Poori",70);

        List<Items> list1 = new ArrayList<>();
        list1.add(item1);
        list1.add(item2);
        list1.add(item3);

        Restaurant r1 = new Restaurant("A2B",1,10,list1);

        repository.restaurantList.add(r1);

        //Restaurant 2
        Items item4 = new Items("Mini Idli",30);
        Items item5 = new Items("Pongal",50);
        Items item6 = new Items("Vada",20);

        List<Items> list2 = new ArrayList<>();
        list2.add(item4);
        list2.add(item5);
        list2.add(item6);

        Restaurant r2 = new Restaurant("Sangeetha",-10,5,list2);

        repository.restaurantList.add(r2);

        //Restaurant 3
        Items item7 = new Items("Fried Rice",150);
        Items item8 = new Items("Briyani",300);
        Items item9 = new Items("Noodles",180);

        List<Items> list3 = new ArrayList<>();
        list3.add(item7);
        list3.add(item8);
        list3.add(item9);

        Restaurant r3 = new Restaurant("Buhari",-6,-10,list3);

        repository.restaurantList.add(r3);

    }

    public void addDrivingAgents() {

        Agent agent1 = new Agent("Kumar","kumar",5,6,true);
        repository.agentList.add(agent1);

        Agent agent2 = new Agent("Praveen","praveen",1,-8,false);
        repository.agentList.add(agent2);

        Agent agent3 = new Agent("Sathish","sathish",-5,-5,true);
        repository.agentList.add(agent3);

    }

    public Customer verifyCustomer(String username, String password) {

        for (Customer customer : repository.customerList) {
            if (customer.getUserName().equals(username) && customer.getPassword().equals(password)) {
                return customer;
            }
        }
        return null;
    }

    public boolean verifyAdmin(String userName, String password) {

        for(Admin admin : repository.adminList)
        {
            if(admin.getUserName().equals(userName) && admin.getPassword().equals(password))
            {
                return true;
            }
        }
        return false;
    }

    public Agent verifyAgent(String username, String password) {

        for(Agent agent : repository.agentList)
        {
            if(agent.getUserName().equals(username) && agent.getPassword().equals(password))
            {
                return agent;
            }
        }
        return null;
    }

    public void updateLocationOfAgent(Agent agent, int[] loc) {

        agent.setCurrLocation(loc);
        agent.setStatus(true);

    }

    public double addItemsToCart(Customer customer, List<Integer> itemIds,Restaurant restaurant) {

        int totalCost = 0;
        for(Items item : restaurant.menuList)
        {
            if(itemIds.contains(item.getItemId()))
            {
                totalCost += item.getPrice();
                customer.cartList.add(item);
            }
        }
        return totalCost;
    }



    public double allocateAgent(Restaurant restaurant) {

        double minDistance = Double.MAX_VALUE;
        int[] resLoc = restaurant.getLocation();

        Agent nearAgent = null;

        for(Agent agent : repository.agentList)
        {
            int[] agentLoc = agent.getCurrLocation();

            double dist = calculateDistance(agentLoc[0],agentLoc[1],resLoc[0],resLoc[1]);

            if(dist<minDistance && agent.isStatus())
            {
                nearAgent = agent;
                minDistance = dist;
            }
        }
        if(nearAgent==null)
        {
            System.out.println("No Agents Available : Booking is currently blocked...!");
            return 0;
        }
        markAgentNotAvailable(nearAgent);
        System.out.println("Agent : "+nearAgent.getUserName()+" is assigned...!");
        return minDistance;
    }

    private void markAgentNotAvailable(Agent nearAgent) {

        nearAgent.setStatus(false);
    }

    double calculateDistance(int x1, int y1, int x2, int y2) {

        return Math.sqrt(((x2-x1)*(x2-x1))+((y2-y1)*(y2-y1)));
    }

    public Restaurant getRestaurants(int restId) {

        for(Restaurant restaurant : repository.restaurantList)
        {
            if(restaurant.getRestaurantid()==restId)
            {
                return restaurant;
            }
        }
        return null;
    }

    public Restaurant getRestaurant(String resName) {
        for(Restaurant restaurant : repository.restaurantList)
        {
            if(restaurant.getRestaurantName().equals(resName))
            {
                return restaurant;
            }
        }
        return null;
    }
}
