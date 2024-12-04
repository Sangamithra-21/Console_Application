package BankApplicationUsingFiles.DTO;

public class Transaction {
    private int transId;
    private String type;
    private int amt;
    private int balance;

    public Transaction(int id,String type,int amt,int bal)
    {
        this.transId = id;
        this.type = type;
        this.amt = amt;
        this.balance = bal;
    }

    public int getTransId() {
        return transId;
    }

    public void setTransId(int transId) {
        this.transId = transId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAmount() {
        return amt;
    }

    public void setAmount(int amt) {
        this.amt = amt;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }


}

