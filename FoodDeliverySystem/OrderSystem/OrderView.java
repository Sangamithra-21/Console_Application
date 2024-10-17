package FoodDeliverySystem.OrderSystem;

import FoodDeliverySystem.DTO.*;
import FoodDeliverySystem.Repository.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class OrderView {

    Scanner sc = new Scanner(System.in);

    Repository repository = Repository.getInstance();

    private OrderViewModel order;

    public OrderView() {
        this.order = new OrderViewModel(this);
    }

    public void addAdmin() {

        System.out.println("Enter the User Name : ");
        String username = sc.next();

        System.out.println("Enter the password  : ");
        String password = sc.next();

        Admin admin = new Admin(username,password);

        repository.adminList.add(admin);

        System.out.println("Admin added successfully...!");

    }


    public void addCustomer() {

        System.out.println("Enter the Username : ");
        String username = sc.next();

        System.out.println("Enter the password : ");
        String password = sc.next();

        System.out.println("Enter the Location [ x and y ] : ");
        int x = sc.nextInt();
        int y = sc.nextInt();

        Customer customer = new Customer(username,password,x,y);

        repository.customerList.add(customer);

        System.out.println("Customer added successfully..!");

    }

    public void addAgent() {

        System.out.println("Enter the username :" );
        String username = sc.next();

        System.out.println("Enter the password : ");
        String password = sc.next();

        System.out.println("Enter the Location : ");
        int x = sc.nextInt();
        int y = sc.nextInt();

        Agent agent = new Agent(username,password,x,y,true);

        repository.agentList.add(agent);

        System.out.println("Agent Added successfully..!");

    }

    public void verifyAdmin() {

        System.out.println("Enter the username : ");
        String userName = sc.next();

        System.out.println("Enter the password : ");
        String password = sc.next();

        if(order.verifyAdmin(userName,password)) {
            System.out.println("Login successfully...!");
            boolean flag = true;
            while (flag) {
                System.out.println("1)Add Restaurant 2)Add Items 3)Delievery agents " +
                                   "4)Show Restaurant 5)Show Orders 6)Show Agents 7)Exit");
                int choice = sc.nextInt();

                switch(choice){
                    case 1 : {
                        System.out.println("Enter number of restaurants to be added : ");
                        int count = sc.nextInt();

                        for (int i = 0; i < count; i++) {
                            addRestaurantByAdmin();
                        }
                    }
                    break;
                    case 2 : {
                        System.out.println("Enter the Number of Items to be added : ");
                        int count = sc.nextInt();

                        for(int i=0;i<count;i++)
                        {
                            addItem();
                        }
                    }
                    break;
                    case 3 : {
                        System.out.println("Enter the agents to be added : ");
                        int count = sc.nextInt();

                        for(int i=0;i<count;i++)
                        {
                            addAgent();
                        }
                    }
                    break;
                    case 4 : {
                        showAllRestaurants();
                        break;
                    }
                    case 5 : {
                        showAllOrder();
                        break;
                    }
                    case 6 : {
                        showAgents();
                        break;
                    }
                    case 7 : {
                        flag=false;
                        break;
                    }
                }

            }
        }
    }

    private void showAgents() {
        System.out.println("Delievery Agents..!");
        System.out.println("--------------------------------------------------------------------------------------------");
        for(Agent agent : repository.agentList)
        {
            System.out.println("Agent Id       : "+agent.getAgentId()+"\n"+
                               "Agent Name     : "+agent.getUserName()+"\n"+
                               "Agent Location : "+ Arrays.toString(agent.getCurrLocation()));
            System.out.println("------------------------------------------------------");
        }
    }

    private void showAllOrder() {

        System.out.println("All Orders...!");
        System.out.println("--------------------------------------------------------------------------------------------");
        for(Order order : repository.orderList)
        {
            System.out.println("Order Id : "+order.getOrderId()+"\n"+
                               "Customer Id : "+order.getCustomerId()+"\n"+
                                "Items Order : ");
            displayItems(order.orderItems);
        }
    }

    private void displayItems(List<Items> orderItems) {

        System.out.println("Ordered Items..!");
        for(Items item : orderItems)
        {
            System.out.println("Item id    : "+item.getItemId()+"\n"+
                               "Item Name  : "+item.getItemName()+"\n"+
                               "Item price : "+item.getPrice());
            System.out.println("------------------------------------------------------");
        }

    }

    private void showAllRestaurants() {
        System.out.println("Restaurants...!");
        System.out.println("--------------------------------------------------------------------------------------------");
        for(Restaurant restaurant : repository.restaurantList)
        {
            System.out.println("Restaurant Id   : "+restaurant.getRestaurantid()+"\n"+
                               "Restaurant Name : "+restaurant.getRestaurantName()+"\n"+
                               "Location        : "+ Arrays.toString(restaurant.getLocation()));
            System.out.println("------------------------------------------------------");

        }
    }

    private void addItem() {

        System.out.println("Enter the restaurant Name you want to add : ");
        String resName = sc.next();

        System.out.println("Enter the Item Name : ");
        String itemName = sc.next();

        System.out.println("Enter the Item price : ");
        int price = sc.nextInt();

        Items item = new Items(itemName,price);

        Restaurant restaurant = order.getRestaurant(resName);

        if(restaurant==null)
        {
            System.out.println("No such restaurant found...!");
            return;
        }

        restaurant.menuList.add(item);

        System.out.println("Item added successfully..!");
    }


    private void addRestaurantByAdmin() {

        System.out.println("Enter the Restaurant Name : ");
        String name = sc.next();

        System.out.println("Enter the location [x and y] : ");
        int x = sc.nextInt();
        int y = sc.nextInt();

        System.out.println("Enter the Number of Items : ");
        int item = sc.nextInt();

        List<Items> itemList = new ArrayList<>();

        for(int i=0;i<item;i++){

            System.out.println("Enter the Item Name : ");
            String itemName = sc.next();


            System.out.println("Enter the item price : ");
            int price = sc.nextInt();

            Items items = new Items(itemName,price);

            itemList.add(items);

        }

        System.out.println("Restaurant Added successfully..>!");
        Restaurant restaurant = new Restaurant(name,x,y,itemList);

        repository.restaurantList.add(restaurant);
    }

    public void verifyCustomer() {

        System.out.println("Enter the username : ");
        String username = sc.next();

        System.out.println("Enter the password : ");
        String password = sc.next();

        Customer customer = order.verifyCustomer(username,password);

        if(customer==null)
        {
            System.out.println("Invalid Login..!");
        }
        else {
            System.out.println("Login Successfully..!");
            boolean flag = true;
            while(flag)
            {
                System.out.println("1)Place Order 2)Show Orders 3)Exit");
                int choice = sc.nextInt();
                switch (choice)
                {
                    case 1 : {
                        showAvailableRestaurants(customer);
                        break;
                    }
                    case 2 : {
                        showOrders(customer);
                        break;
                    }
                    case 3 : {
                        flag = false;
                        break;
                    }
                }
            }

        }

    }

    private void showOrders(Customer customer) {

        System.out.println("Orders");
        System.out.println("--------------------------------------------------------------------------------------------");
        for(Order order : customer.customerOrder)
        {
            System.out.println("Order Id : "+order.getOrderId());
            displayItems(order.orderItems);
            System.out.println("--------------------------------------------------------------------------------------------");
        }
    }

    private void showAvailableRestaurants(Customer customer) {

        List<Restaurant> resList = repository.restaurantList;

        int customerX = customer.getCurrLocation()[0];
        int customerY = customer.getCurrLocation()[1];

        resList.sort((r1, r2) -> {
            double dist1 = order.calculateDistance(customerX, customerY, r1.getLocation()[0], r1.getLocation()[1]);
            double dist2 = order.calculateDistance(customerX, customerY, r2.getLocation()[0], r2.getLocation()[1]);
            return Double.compare(dist1, dist2);
        });

        for(Restaurant restaurant : resList)
        {
            System.out.println("Restaurant Id : "+restaurant.getRestaurantid()+" "+"Restaurant Name : "+restaurant.getRestaurantName());
        }

        System.out.println("Enter the Restaurant Id : ");
        int restId = sc.nextInt();

        Restaurant restaurant = order.getRestaurants(restId);

        List<Items> itemList  = displayRestaurantMenu(restaurant);

        List<Integer> itemIds = new ArrayList<>();

        System.out.println("Enter the Item Ids [0 to exit]: ");

        while(true)
        {
           int id = sc.nextInt();
           if(id==0)
           {
               break;
           }
           itemIds.add(id);
        }

        double cost = order.addItemsToCart(customer,itemIds,restaurant);

        double dist = order.allocateAgent(restaurant);

        if(dist==0)
        {
            return;
        }

        cost = cost + (dist)*10;

        System.out.println("Food order Successfully..!");

        Order newOrder = new Order(customer.getCustomerId(),(int)cost,itemList);


        customer.customerOrder.add(newOrder);

        repository.orderList.add(newOrder);

        System.out.println("Total cost for your order : "+String.format("%.2f", cost));

        customer.cartList= new ArrayList<>();

        }

    private List<Items> displayRestaurantMenu(Restaurant restaurant) {

        List<Items> list = new ArrayList<>();
        for(Items items : restaurant.menuList)
        {
            list.add(items);
            System.out.println("Item id : "+items.getItemId()+" Item Name : "+items.getItemName()+" Item Price : "+items.getPrice());
        }
        return list;
    }


    public void verifyAgent() {

        System.out.println("Enter the username : ");
        String username  = sc.next();

        System.out.println("Enter the password : ");
        String password = sc.next();

        Agent agent = order.verifyAgent(username,password);
        if(agent!=null)
        {
            System.out.println("Login Successfull..!");

            System.out.println("Enter the current Location [ x and y ] : ");
            int x = sc.nextInt();
            int y = sc.nextInt();

            order.updateLocationOfAgent(agent,new int[]{x,y});

        }
        else {
            System.out.println("Invalid Login..!");
        }
    }
}
