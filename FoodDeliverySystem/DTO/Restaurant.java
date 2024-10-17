package FoodDeliverySystem.DTO;

import java.util.List;

public class Restaurant {

    static int rid=1;
    private int restaurantid;
    private String restaurantName;
    private int[] location;
    public List<Items> menuList;

    public Restaurant(String name,int x,int y,List<Items> list)
    {
        this.restaurantid =rid++;
        this.restaurantName = name;
        this.location = new int[]{x,y};
        this.menuList = list;
    }

    public int getRestaurantid() {
        return restaurantid;
    }

    public void setRestaurantid(int restaurantid) {
        this.restaurantid = restaurantid;
    }


    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public int[] getLocation() {
        return location;
    }

    public void setLocation(int[] location) {
        this.location = location;
    }


}
