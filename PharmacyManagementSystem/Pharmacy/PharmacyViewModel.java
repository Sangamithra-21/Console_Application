package PharmacyManagementSystem.Pharmacy;

import PharmacyManagementSystem.DTO.Branch;
import PharmacyManagementSystem.DTO.Customer;
import PharmacyManagementSystem.DTO.Medicine;
import PharmacyManagementSystem.Repository.Repository;

import java.util.List;

public class PharmacyViewModel {

    private PharmacyView view;

    public PharmacyViewModel(PharmacyView view) {
        this.view = view;
    }

    private Repository repository = Repository.getInstance();


    public void addBranch(String branchName, String phoneNo) {

        Branch branch = new Branch(branchName,phoneNo);

        repository.branches.add(branch);
    }

    public Branch getBranchById(String branchName) {

        for(Branch branch : repository.branches)
        {
            if(branch.getBranchLocation().equals(branchName))
            {
                return branch;
            }
        }
        return null;
    }

    public Medicine getMedicineByName(String medicineName, List<Medicine> medicineList) {

        for(Medicine medicine : medicineList)
        {
            if(medicine.getMedicineName().equals(medicineName))
            {
                return medicine;
            }
        }
        return null;
    }

    public void addStock(String name, int price, int quantity,Branch branch) {

        Medicine medicine = new Medicine(name,quantity,price);

        branch.getMedicineList().add(medicine);

        repository.medicineList.add(medicine);


    }

    public void addCustomer(String name, String phone) {

        Customer customer = new Customer(name,phone);

        repository.customerList.add(customer);
    }

    public Medicine getMedicineById(int medicineId, List<Medicine> medicineList) {

        for(Medicine medicine : medicineList)
        {
            if(medicine.getMedicineId()==medicineId)
            {
                return medicine;
            }
        }
        return null;
    }

    public Customer getCustomerByName(String customerName) {

        for(Customer customer : repository.customerList)
        {
            if(customer.getCustomerName().equals(customerName))
            {
                return customer;
            }
        }
        return null;
    }
}
