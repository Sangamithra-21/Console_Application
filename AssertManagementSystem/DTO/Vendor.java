package AssertManagementSystem.DTO;

public class Vendor {

    static int id=1;
    private int vendorId;
    private String vendorName;

    public Vendor(String name)
    {
        this.vendorId = id++;
        this.vendorName = name;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public int getVendorId() {
        return vendorId;
    }

    public void setVendorId(int vendorId) {
        this.vendorId = vendorId;
    }
}
