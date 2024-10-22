package AssertManagementSystem.AssertSystem;

import AssertManagementSystem.DTO.*;
import AssertManagementSystem.Repository.Repository;

import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class AssertView {

    private AssetViewModel model;

    private Repository repository = Repository.getInstance();

    public AssertView() {
        this.model = new AssetViewModel(this);
    }

    Scanner sc = new Scanner(System.in);

    public void run() {
        while (true) {
            System.out.println("\nAsset Management System : ");
            System.out.println("1. Add Vendor");
            System.out.println("2. Add Software");
            System.out.println("3. Add Employee");
            System.out.println("4. Add Device");
            System.out.println("5. Install Software on Device");
            System.out.println("6. Generate Report");
            System.out.println("7. Exit");

            System.out.println("Enter the Option : ");
            int option = sc.nextInt();

            switch (option) {
                case 1:
                    addVendor();
                    break;
                case 2:
                    addSoftware();
                    break;
                case 3:
                    addEmployee();
                    break;
                case 4:
                    addDevice();
                    break;
                case 5:
                    installSoftware();
                    break;
                case 6:
                    generateReport();
                    break;
                case 7:
                    break;
            }

        }
    }

    private void addVendor() {

        System.out.println("Enter the Vendor Name : ");
        String vendorName = sc.next();

        Vendor vendor = new Vendor(vendorName);

        repository.vendorList.add(vendor);

        System.out.println("Vendor Added Successfully...!");

    }

    private void addSoftware() {

        System.out.println("Enter the Software Name : ");
        String softwareName = sc.next();

        System.out.println("Enter the Vendor id : ");
        int id = sc.nextInt();

        Vendor vendor = model.getVendorById(id);

        if (vendor == null) {
            System.out.println("Vendor Not Found...!");
            return;
        }

        System.out.println("Enter the Cost per device  : ");
        int cost = sc.nextInt();

        System.out.println("Enter the expiry date [yyyy-mm-dd] : ");
        String date = sc.next();

        Date expiryDate = model.parseDate(date);

        if (expiryDate == null) {
            System.out.println("Invalid date Format");
            return;
        }

        Software software = new Software(softwareName, vendor, cost, expiryDate);

        repository.softwareList.add(software);


        System.out.println("Software Added successfully...!");


    }


    private void addEmployee() {

        System.out.println("Enter the Employee Name : ");
        String empName = sc.next();

        Employee employee = new Employee(empName);

        repository.employeeList.add(employee);

        System.out.println("Employee Added Successfully..!");

    }

    private void addDevice() {

        System.out.println("Enter the employee Id : ");
        int empId = sc.nextInt();

        Employee employee = model.getEmployeeById(empId);

        if (employee == null) {
            System.out.println("No such Employee Found...!");
            return;
        }

        Device device = new Device(employee);

        employee.devices.add(device);

        repository.deviceList.add(device);

        System.out.println("Device added successfully..!");

    }

    private void installSoftware() {

        System.out.println("Enter the device Id : ");
        int deviceId = sc.nextInt();

        Device device = model.getDeviceById(deviceId);

        if (device == null) {
            System.out.println("No such device found...!");
            return;
        }


        System.out.println("Enter the software Name : ");
        String softwareName = sc.next();

        Software software = model.getSoftwareByName(softwareName);

        if (software == null) {
            System.out.println("No such software found...!");
            return;
        }

        System.out.println("Enter the Installation Date [yyyy-mm-dd] : ");
        String date = sc.next();

        Date installDate = model.parseDate(date);

        if (installDate == null) {
            System.out.println("Invalid Date format...!");
            return;
        }

        Installation install = new Installation(software, installDate);

        device.installations.add(install);

        repository.installList.add(install);

        System.out.println("Software installed successfully...!");


    }


    private void generateReport() {

        System.out.println("\nReport Menu");
        System.out.println("1)Number of installations of a particular software");
        System.out.println("2)Number of software installed in a device");
        System.out.println("3)Number of software installed for an employee");
        System.out.println("4)Amount spent for a software");
        System.out.println("5)Amount spent for an employee");
        System.out.println("6)Amount spent on a vendor");
        System.out.println("7)Number of installations of software from a vendor");
        System.out.println("8)Devices that have an expired software");
        System.out.println("9)Exit");

        System.out.println("Enter the option : ");
        int option = sc.nextInt();

        switch (option) {
            case 1:
                noOfInstallationOfSoftware();
                break;
            case 2:
                noOfSoftwareInstalledOnDevice();
                break;
            case 3:
                noOfSoftwareInstalledByEmployee();
                break;
            case 4:
                amountForSoftware();
                break;
            case 5:
                amountForEmployee();
                break;
            case 6:
                amountSpentOnVendor();
                break;
            case 7:
                noOfInstallFromVendor();
                break;
            case 8:
                expiredDevices();
                break;
            case 9:
                break;

        }

    }


    // 1)Number of installations of a particular software

    private void noOfInstallationOfSoftware() {

        System.out.println("Enter the software : ");
        String softwareName = sc.next();

        Software software = model.getSoftwareByName(softwareName);

        if(software==null)
        {
            System.out.println("No such software found..!");
            return;
        }

        int installCount = model.getInstallCountOfSoftware(software);
        if(installCount==0)
        {
            System.out.println("This software is not yet Installed");
            return;
        }

        System.out.println("Installations done by the software "+software.getSoftwareName()+" : "+installCount);

    }

    // 2)Number of softwares installed in a device

    private void noOfSoftwareInstalledOnDevice()
    {
        System.out.println("Enter the Device Number : ");
        int deviceid = sc.nextInt();

        Device device = model.getDeviceById(deviceid);

        if(device==null)
        {
            System.out.println("No such device exists");
            return;
        }

        int installCount = model.getInstallCountOnDevice(device);

        if(installCount==0)
        {
            System.out.println("Device has no softwares");
            return;
        }

        System.out.println("Softwares installed in a device : "+installCount);


    }

    //3)Number of software installed by an employee

    private void noOfSoftwareInstalledByEmployee()
    {
        System.out.println("Enter the Employee Id : ");
        int empid = sc.nextInt();

        Employee employee = model.getEmployeeById(empid);

        if(employee==null)
        {
            System.out.println("No such Employee found..!");
            return;
        }

        int installCount = model.getInstallCountByEmployee(employee);

        System.out.println("Software installed by the Employee : "+installCount);

    }

    //4)Amount spent for a software

    private void amountForSoftware()
    {
        System.out.println("Enter the software name : ");
        String softwareName = sc.next();

        Software software = model.getSoftwareByName(softwareName);

        if(software==null)
        {
            System.out.println("No such software found...!");
            return;
        }

        int totalAmount = model.amountSpendForSoftware(software);

        if(totalAmount==0)
        {
            System.out.println("Software not yet installed..!");
            return;
        }

        System.out.println("Amount spent for a software : "+totalAmount);
    }

    //5)Amount spent for an Employee

    private void amountForEmployee() {

        System.out.println("Enter the Employee Id : ");
        int empId = sc.nextInt();

        Employee employee = model.getEmployeeById(empId);

        if(employee==null)
        {
            System.out.println("No such employee found...!");
            return;
        }

        int totalAmount = model.amountSpendByEmployee(employee);

        if(totalAmount==0)
        {
            System.out.println("Employee not installed any software..!");
            return;
        }

        System.out.println("Amount spent by an employee to install software : "+totalAmount);

    }

    //6)Amount spend on vendor

    private void amountSpentOnVendor()
    {
        System.out.println("Enter the Vendor Id : ");
        int vendorId = sc.nextInt();

        Vendor vendor = model.getVendorById(vendorId);

        if(vendor==null)
        {
            System.out.println("No such vendor found..!");
            return;
        }

        int amount = model.amountSpendByVendor(vendor);

        if(amount==0)
        {
            System.out.println("Vendor's software not yet installed...!");
            return;
        }

        System.out.println("Amount spend by the vendor : "+amount);
    }

    // 7)Number of Install from Vendor

    private void noOfInstallFromVendor()
    {
        System.out.println("Enter the Vendor Id : ");
        int vendorId = sc.nextInt();

        Vendor vendor = model.getVendorById(vendorId);

        if(vendor==null)
        {
            System.out.println("No such vendor found..!");
            return;
        }

        int installCount = model.getInstallCountFromVendor(vendor);

        if(installCount==0)
        {
            System.out.println("Vendor's software not yet installed...!");
            return;
        }

        System.out.println("Number of Installs from Vendor : "+installCount);
    }

    //8) Expiried Devices

    private void expiredDevices()
    {
        System.out.println("Enter the date [yyyy-mm-dd] : ");
        String date = sc.next();

        Date currDate = model.parseDate(date);

        List<Device> expiredDevices = model.getExpiriedDevice(currDate);

        System.out.println("Devices with expired Software ");
        for(Device device : expiredDevices)
        {
            System.out.print(device.getDeviceId()+" ");
        }

    }


}
