package PharmacyManagementSystem.DTO;

import java.util.ArrayList;
import java.util.List;

public class Branch {

    static int bid=1;
    private int branchId;
    private String branchLocation;
    private String phoneNo;
    private List<Medicine> medicineList;

    public Branch(String branchLoc,String phoneNo)
    {
        this.branchId = bid++;
        this.branchLocation = branchLoc;
        this.phoneNo = phoneNo;
        this.medicineList = new ArrayList<>();
    }

    public int getBranchId() {
        return branchId;
    }

    public String getBranchLocation() {
        return branchLocation;
    }

    public void setBranchLocation(String branchLocation) {
        this.branchLocation = branchLocation;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public List<Medicine> getMedicineList() {
        return medicineList;
    }

    public void setMedicineList(List<Medicine> medicineList) {
        this.medicineList = medicineList;
    }
}
