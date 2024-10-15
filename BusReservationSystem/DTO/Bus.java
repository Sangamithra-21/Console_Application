package BusReservationSystem.DTO;

import java.util.ArrayList;
import java.util.List;

public class Bus {

    static int bid=1;
    private int busId;
    private String busType;
    private String seatType;

    private int capacity;
    public String[] seats;
    private int cost;
    List<Passenger> passengerList;


    public Bus(String busType, String seatType, int capacity, int cost) {
        this.busId = bid++;
        this.busType = busType;
        this.seatType = seatType;
        this.capacity = capacity;
        this.cost = cost;
        this.passengerList = new ArrayList<>();
        this.seats = new String[capacity];
    }


    public int getBusId() {
        return busId;
    }

    public void setBusId(int busId) {
        this.busId = busId;
    }

    public String getBusType() {
        return busType;
    }

    public void setBusType(String busType) {
        this.busType = busType;
    }

    public String getSeatType() {
        return seatType;
    }

    public void setSeatType(String seatType) {
        this.seatType = seatType;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public List<Passenger> getPassengerList() {
        return passengerList;
    }

    public void setPassengerList(List<Passenger> passengerList) {
        this.passengerList = passengerList;
    }




}
