package ParkingSlot.DTO;

public class Vehicle {

    private String vehicleType;
    private String regNo;

    private String color;

    public Vehicle(String type,String regNo,String color)
    {
        this.vehicleType = type;
        this.regNo = regNo;
        this.color = color;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

}
