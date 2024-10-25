package TollPaymentProcessing.DTO;

public class TollPay {

    static int tid=1;
    private int tollPayId;
    private int vehicleId;
    private double amt;
    private String tollGateName;

    public TollPay(int vehicleId,double amt,String name)
    {
        this.tollPayId = tid++;
        this.vehicleId=vehicleId;
        this.amt = amt;
        this.tollGateName = name;
    }

    public String getTollGateName() {
        return tollGateName;
    }

    public void setTollGateName(String tollGateName) {
        this.tollGateName = tollGateName;
    }

    public int getTollPayId() {
        return tollPayId;
    }


    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public double getAmt() {
        return amt;
    }

    public void setAmt(double amt) {
        this.amt = amt;
    }
}
