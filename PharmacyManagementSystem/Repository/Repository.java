package PharmacyManagementSystem.Repository;

import PharmacyManagementSystem.DTO.Branch;
import PharmacyManagementSystem.DTO.Customer;
import PharmacyManagementSystem.DTO.Medicine;

import java.util.*;

public class Repository {

    private static Repository repository;

    public List<Branch> branches;
    public List<Customer> customerList;
    public List<Medicine> medicineList;
    public Map<String,Medicine> alternateMedicineList;
    public Scanner sc;

    private Repository()
    {
        this.branches = new ArrayList<>();
        this.customerList = new ArrayList<>();
        this.medicineList = new ArrayList<>();
        this.alternateMedicineList = new HashMap<>();
        this.sc = new Scanner(System.in);
    }

    public static Repository getInstance()
    {
        if(repository==null)
        {
            repository = new Repository();
        }
        return repository;
    }
}
