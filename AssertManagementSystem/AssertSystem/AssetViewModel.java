package AssertManagementSystem.AssertSystem;

import AssertManagementSystem.DTO.*;
import AssertManagementSystem.Repository.Repository;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AssetViewModel {

    private AssertView view ;

    Repository repository = Repository.getInstance();

    public AssetViewModel(AssertView assertView) {

        this.view = assertView;
    }

    Vendor getVendorById(int id) {

        for(Vendor vendor : repository.vendorList)
        {
            if(vendor.getVendorId()==id)
            {
                return vendor;
            }
        }
        return null;
    }

    public Date parseDate(String date) {
        try{
            return new SimpleDateFormat("yyyy-MM-dd").parse(date);

        } catch (Exception e) {
           return null;
        }
    }

    public Employee getEmployeeById(int empId) {

        for(Employee employee : repository.employeeList)
        {
            if(employee.getEmployeeId()==empId)
            {
                return employee;
            }
        }
        return null;
    }

    public Device getDeviceById(int deviceId) {

        for(Device device : repository.deviceList)
        {
            if(device.getDeviceId()==deviceId)
            {
                return device;
            }
        }
        return null;
    }

    public Software getSoftwareByName(String softwareName) {

        for(Software software : repository.softwareList)
        {
            if(software.getSoftwareName().equals(softwareName))
            {
                return software;
            }
        }
        return null;
    }

    public int getInstallCountOfSoftware(Software software) {

        int installCount=0;

        for(Installation install : repository.installList)
        {
            if(install.getSoftware()==software)
            {
                installCount++;
            }
        }
        return installCount;
    }

    public int getInstallCountOnDevice(Device device) {

        return device.installations.size();
    }

    public int getInstallCountByEmployee(Employee employee) {

        int installCount = 0;
        for(Device device : employee.devices)
        {
            installCount += getInstallCountOnDevice(device);
        }
        return installCount;
    }

    public int amountSpendForSoftware(Software software) {

        int totalAmount = 0;
        for(Installation installation : repository.installList)
        {
            if(installation.getSoftware()==software)
            {
                totalAmount += software.getCost();
            }
        }
        return totalAmount;
    }


    public int amountSpendByEmployee(Employee employee) {

        int amount = 0;

        for(Device device : employee.devices)
        {
            for(Installation install : device.installations)
            {
                amount += install.getSoftware().getCost();
            }
        }
        return amount;
    }

    public int amountSpendByVendor(Vendor vendor) {

        int amount = 0;
        for(Installation install : repository.installList)
        {
            Software software = install.getSoftware();
            if(software.getVendor()==vendor)
            {
                amount += software.getCost();
            }
        }
        return amount;
    }

    public int getInstallCountFromVendor(Vendor vendor) {

        int installCount =0 ;

        for(Installation installation : repository.installList)
        {
            if(installation.getSoftware().getVendor()==vendor)
            {
                installCount++;
            }
        }
        return installCount;
    }

    public List<Device> getExpiriedDevice(Date currDate) {

        List<Device> devices = new ArrayList<>();

        for(Device device : repository.deviceList) {
            for(Installation install : device.installations)
            {
                long expiryTime = install.getSoftware().getExpiryDate().getTime();
                long currTime = currDate.getTime();
                if(currTime>expiryTime)
                {
                   devices.add(device);
                   break;
                }
            }
        }
        return devices;
    }

}
