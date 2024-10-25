package GiftCard.DTO;

public class Topup {

    static int tid = 1;
    private int topupId;
    private double topupAmt;
    private int giftId;
    private String type;

    public Topup(double topupAmt,int giftId,String type)
    {
        this.topupAmt = topupAmt;
        this.topupId = tid++;
        this.giftId = giftId;
        this.type = type;
    }

    public int getTopupId() {
        return topupId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getTopupAmt() {
        return topupAmt;
    }

    public void setTopupAmt(double topupAmt) {
        this.topupAmt = topupAmt;
    }

    public int getGiftId() {
        return giftId;
    }

    public void setGiftId(int giftId) {
        this.giftId = giftId;
    }
}
