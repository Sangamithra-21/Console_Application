package BankManagementSystem.DTO;

import java.util.ArrayList;
import java.util.List;

public class Customer {

    private int customerId ;
    private static int cid=1;
    private static int aid=10001;
    private int accountId;
    private String customerName;
    private int balance;
    private int transactionCount;
    private String password;
    public List<Transaction> transactionList;

    public Customer(String name,int amt,String password)
    {
        this.password = password;
        this.customerId = cid++;
        this.accountId = aid++;
        this.customerName = name;
        this.balance = amt;
        this.transactionCount = 1;
        this.transactionList = new ArrayList<>();
    }

    public int getTransactionCount() {
        return transactionCount;
    }

    public void setTransactionCount(int transactionCount) {
        this.transactionCount = transactionCount;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getCustomerId() {
        return customerId;
    }


    public int getAccountId() {
        return accountId;
    }


    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public List<Transaction> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }
}
