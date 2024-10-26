package RailwayReservationSystem1.DTO;

public class Passenger {

    static int pid=1;
    private int passengerId;
    private int seatNo;
    private String passengerName;
    private int passengerAge;
    private String gender;
    private String allocatedBerth;

    public Passenger(String name,int age,String gender)
    {
        this.passengerName = name;
        this.gender = gender;
        this.passengerId = pid++;
        this.passengerAge = age;
        this.seatNo = -1;

    }

    public int getSeatNo() {
        return seatNo;
    }

    public void setSeatNo(int seatNo) {
        this.seatNo = seatNo;
    }

    public int getPassengerId() {
        return passengerId;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public int getPassengerAge() {
        return passengerAge;
    }

    public void setPassengerAge(int passengerAge) {
        this.passengerAge = passengerAge;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAllocatedBerth() {
        return allocatedBerth;
    }

    public void setAllocatedBerth(String allocatedBerth) {
        this.allocatedBerth = allocatedBerth;
    }
}
