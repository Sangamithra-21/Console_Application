package AssertManagementSystem.DTO;

import java.util.ArrayList;
import java.util.List;

public class Employee {
    static int eid=1;
    private int employeeId;
    private String employeeName;
    public List<Device> devices;

    public Employee(String employeeName)
    {
        this.employeeId = eid++;
        this.employeeName = employeeName;
        this.devices = new ArrayList<>();
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

}
