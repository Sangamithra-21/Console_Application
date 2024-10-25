package ParkingSlot.DTO;

public class Slot {

    private int slotNo;
    private String vehicleType;
    private boolean isFree;
    private Vehicle vehicle;
    private int floorNo;

    public Slot(int slotNo,String vehicleType,boolean isFree,int floorNo)
    {
        this.slotNo = slotNo;
        this.vehicleType = vehicleType;
        this.isFree = isFree;
        this.vehicle = null;
        this.floorNo = floorNo;
    }

    public int getFloorNo() {
        return floorNo;
    }

    public void setFloorNo(int floorNo) {
        this.floorNo = floorNo;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public int getSlotNo() {
        return slotNo;
    }

    public void setSlotNo(int slotNo) {
        this.slotNo = slotNo;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public boolean isFree() {
        return isFree;
    }

    public void setFree(boolean free) {
        isFree = free;
    }
}
