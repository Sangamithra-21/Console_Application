package PharmacyManagementSystem.DTO;

import java.util.List;

public class Purchase {

    static int pid=1;
    private int purchaseId;
    private String branchName;

    private List<Medicine> medicineList;

    private int amount;

    public Purchase(int amount,String branch,List<Medicine> list)
    {
        this.purchaseId = pid++;
        this.amount = amount;
        this.branchName = branch;
        this.medicineList = list;
    }

    public int getPurchaseId() {
        return purchaseId;
    }


    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public List<Medicine> getMedicineList() {
        return medicineList;
    }

    public void setMedicineList(List<Medicine> medicineList) {
        this.medicineList = medicineList;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }
}
