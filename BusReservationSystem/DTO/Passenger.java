package BusReservationSystem.DTO;

public class Passenger {

    static int pid=1;
    private int passengerId;
    private String passengerName;
    private String BusType;
    private String seatType;
    private int age;
    private String gender;
    private int seatNo;

    public Passenger(String passengerName, String busType, String seatType, int age, String gender, int seatNo) {
        this.passengerId = pid++;
        this.passengerName = passengerName;
        BusType = busType;
        this.seatType = seatType;
        this.age = age;
        this.gender = gender;
        this.seatNo = seatNo;
    }

    public int getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(int passengerId) {
        this.passengerId = passengerId;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public String getBusType() {
        return BusType;
    }

    public void setBusType(String busType) {
        BusType = busType;
    }

    public String getSeatType() {
        return seatType;
    }

    public void setSeatType(String seatType) {
        this.seatType = seatType;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getSeatNo() {
        return seatNo;
    }

    public void setSeatNo(int seatNo) {
        this.seatNo = seatNo;
    }

}
