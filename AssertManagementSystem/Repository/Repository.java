package AssertManagementSystem.Repository;

import AssertManagementSystem.DTO.*;

import java.util.ArrayList;
import java.util.List;

public class Repository {

    public List<Vendor> vendorList;
    public List<Employee> employeeList;
    public List<Software> softwareList;
    public List<Device> deviceList;
    public List<Installation> installList;


    public Repository()
    {
        this.vendorList = new ArrayList<>();
        this.employeeList = new ArrayList<>();
        this.deviceList = new ArrayList<>();
        this.installList = new ArrayList<>();
        this.softwareList = new ArrayList<>();
    }

    private static Repository repository;

    public static Repository getInstance()
    {
        if(repository==null)
        {
            repository = new Repository();
        }
        return repository;
    }

}
