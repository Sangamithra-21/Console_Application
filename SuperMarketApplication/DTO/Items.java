package SuperMarketApplication.DTO;

public class Items {

    static int iid = 1;
    private String itemName;
    private int itemPrice;
    private int itemId;
    public Items(String  itemName,int itemPrice)
    {
        this.itemId = iid++;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public static int getIid() {
        return iid;
    }

    public static void setIid(int iid) {
        Items.iid = iid;
    }


    public int getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(int itemPrice) {
        this.itemPrice = itemPrice;
    }



}
