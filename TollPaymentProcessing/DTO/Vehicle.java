package TollPaymentProcessing.DTO;

import java.util.ArrayList;
import java.util.List;

public class Vehicle {

    static int id=1;
    private int vehicleId;
    private boolean isVIP;
    private List<Journey> journeyList;
    private double totCost;

    public Vehicle(boolean data)
    {
        this.vehicleId = id++;
        this.journeyList = new ArrayList<>();
        this.isVIP = data;
        this.totCost = 0;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public boolean isVIP() {
        return isVIP;
    }

    public void setVIP(boolean VIP) {
        isVIP = VIP;
    }

    public List<Journey> getJourneyList() {
        return journeyList;
    }

    public void setJourneyList(List<Journey> journeyList) {
        this.journeyList = journeyList;
    }

    public double getTotCost() {
        return totCost;
    }

    public void setTotCost(double totCost) {
        this.totCost = totCost;
    }
}
