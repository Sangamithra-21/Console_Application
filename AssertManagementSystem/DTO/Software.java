package AssertManagementSystem.DTO;

import java.util.Date;

public class Software {

    static int sid = 1;
    private int softwareId;
    private String softwareName;
    private Vendor vendor;
    private int cost;
    private Date expiryDate;

    public Software(String name,Vendor vendor,int cost,Date date)
    {
        this.softwareName = name;
        this.vendor = vendor;
        this.softwareName = softwareName;
        this.cost = cost;
        this.expiryDate = date;
    }

    public int getSoftwareId() {
        return softwareId;
    }

    public void setSoftwareId(int softwareId) {
        this.softwareId = softwareId;
    }

    public String getSoftwareName() {
        return softwareName;
    }

    public void setSoftwareName(String softwareName) {
        this.softwareName = softwareName;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }


}
