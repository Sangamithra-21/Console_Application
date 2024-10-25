package GiftCard.DTO;

import java.util.ArrayList;
import java.util.List;

public class Giftcard {

    static int card = 11111;
    private int giftCardNo;
    static int pin = 1234;
    private int pinNo ;
    private int customerId;
    private double amount;
    private boolean isActive;
    private List<Topup> history;


    public Giftcard(int custId)
    {
        this.giftCardNo = card++;
        this.pinNo = pin++;
        this.amount = 0;
        this.customerId = custId;
        this.isActive = true;
        this.history = new ArrayList<>();
    }

    public int getGiftCardNo() {
        return giftCardNo;
    }


    public int getPinNo() {
        return pinNo;
    }


    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public List<Topup> getHistory() {
        return history;
    }

    public void setHistory(List<Topup> history) {
        this.history = history;
    }
}
