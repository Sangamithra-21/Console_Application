package InvoiceManagementSystem.DTO;

public class Items {
    static int itid = 1;
    private int itemId;
    private String itemName;
    private double price;

    public Items(String name,double price)
    {
        this.itemId = itid++;
        this.itemName = name;
        this.price = price;
    }

    public int getItemId() {
        return itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public double getPrice() {
        return price;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
