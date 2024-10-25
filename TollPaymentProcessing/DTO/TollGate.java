package TollPaymentProcessing.DTO;

import java.util.ArrayList;
import java.util.List;

public class TollGate {

    static int tolId=1;
    private int tollNo;
    private String tollName;
    static double cost=100;
    private double tollCost;
    public List<Vehicle> vehicleList;
    public List<TollPay> tollPayList;
    private double totalCostEarned;

    public TollGate(String tollName)
    {
        this.tollNo = tolId++;
        this.tollName = tollName;
        this.tollCost = cost;
        this.vehicleList = new ArrayList<>();
        this.tollPayList = new ArrayList<>();
        cost += 15;
        this.totalCostEarned=0;
    }

    public double getTotalCostEarned() {
        return totalCostEarned;
    }

    public void setTotalCostEarned(double totalCostEarned) {
        this.totalCostEarned = totalCostEarned;
    }

    public int getTollNo() {
        return tollNo;
    }

    public void setTollNo(int tollNo) {
        this.tollNo = tollNo;
    }

    public String getTollName() {
        return tollName;
    }

    public void setTollName(String tollName) {
        this.tollName = tollName;
    }

    public double getTollCost() {
        return tollCost;
    }

    public void setTollCost(double tollCost) {
        this.tollCost = tollCost;
    }
}
