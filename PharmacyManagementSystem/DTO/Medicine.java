package PharmacyManagementSystem.DTO;

public class Medicine {

    static int mid=1;
    private int medicineId;
    private String medicineName;
    private int quantity;
    private int price;

    public Medicine(String medicineName,int quantity,int price)
    {
        this.medicineId = mid++;
        this.medicineName = medicineName;
        this.quantity = quantity;
        this.price = price;
    }

    public int getMedicineId() {
        return medicineId;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
