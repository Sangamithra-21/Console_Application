package FoodDeliverySystem.DTO;

public class Admin extends User{

    static int i=1;
    private int adminId;
    public Admin(String username,String password)
    {
        super(username,password);
        this.adminId = i++;
    }
}
