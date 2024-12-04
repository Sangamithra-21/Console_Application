package BankApplicationUsingFiles.DTO;

import java.util.ArrayList;
import java.util.List;

public class Customer {

    private int custId;
    private int accId;
    private String name;
    private int balance;
    private String Password;
    private int transcount ;

    public Customer(int custId,int accId,String name,int balance,String password)
    {
        this.custId = custId;
        this.accId = accId;
        this.name = name;
        this.balance = balance;
        this.Password = password;
        this.transcount = 1;
    }

    public int getTranscount() {
        return transcount;
    }

    public void setTranscount(int transcount) {
        this.transcount = transcount;
    }
    //    public List<Transaction> getTransactionList() {
//        return transactionList;
//    }
//
//    public void setTransactionList(List<Transaction> transactionList) {
//        this.transactionList = transactionList;
//    }

    public int getCustId() {
        return custId;
    }

    public void setCustId(int custId) {
        this.custId = custId;
    }

    public int getAccId() {
        return accId;
    }

    public void setAccId(int accId) {
        this.accId = accId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
