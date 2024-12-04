package BankManagementSystem.DTO;

public class Transaction {

    private int transactionId;
    private String type;
    private int amount;
    private int balance;

    public Transaction(int tid,String type,int amt,int bal)
    {
        this.transactionId = tid;
        this.type = type;
        this.amount = amt;
        this.balance = bal;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
