package FoodDeliverySystem.DTO;

public class Items {

    static int id=1;
    private int itemId;

    private String itemName;
    private int price;

    public Items(String item, int price)
    {
        this.itemId = id++;
        this.itemName = item;
        this.price = price;
    }
    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }


    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }


    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

}
