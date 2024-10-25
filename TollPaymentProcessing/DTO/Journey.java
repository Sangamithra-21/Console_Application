package TollPaymentProcessing.DTO;

import java.util.ArrayList;
import java.util.List;

public class Journey {

    static int jid=1;
    private int journeyId;
    private String start;
    private String end;
    private double journeyCost;
    private int vehicleId;
    public List<TollPay> tollPayList;

    public Journey(String start,String end,int vehicleId)
    {
        this.journeyId = jid++;
        this.start = start;
        this.end = end;
        this.journeyCost =0;
        this.vehicleId = vehicleId;
        this.tollPayList = new ArrayList<>();
    }

    public int getJourneyId() {
        return journeyId;
    }

    public void setJourneyId(int journeyId) {
        this.journeyId = journeyId;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public double getJourneyCost() {
        return journeyCost;
    }

    public void setJourneyCost(double journeyCost) {
        this.journeyCost = journeyCost;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }
}
