package AssertManagementSystem.DTO;

import java.util.ArrayList;
import java.util.List;

public class Device {

    static int did=1;
    private int deviceId;
    private Employee owner;
    public List<Installation> installations;

    public Device(Employee owner)
    {
        this.deviceId = did++;
        this.owner = owner;
        this.installations = new ArrayList<>();
    }

    public int getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    public Employee getOwner() {
        return owner;
    }

    public void setOwner(Employee owner) {
        this.owner = owner;
    }

}
