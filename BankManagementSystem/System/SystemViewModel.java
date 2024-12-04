package BankManagementSystem.System;

import BankManagementSystem.DTO.Customer;
import BankManagementSystem.DTO.Transaction;
import BankManagementSystem.Repository.Repository;

import java.util.Scanner;

public class SystemViewModel {

    private SystemView view;

    public SystemViewModel(SystemView view)
    {
        this.view = view;
    }

    Repository repository = Repository.getInstance();

    Scanner sc = repository.scanner;

    public String encrptyPassword(String password) {

        StringBuilder pass = new StringBuilder();

        for(char ch : password.toCharArray())
        {
            if(ch=='Z') pass.append('A');
            else if(ch=='z') pass.append('z');
            else if(ch=='9') pass.append('0');
            else pass.append((char)(ch+1));
        }

        return pass.toString();
    }

    public Customer getCustomer(int custId) {

        for(Customer customer : repository.customerList)
        {
            if(customer.getCustomerId()==custId)
            {
                return customer;
            }
        }

        return null;
    }

    public void depositMoney(Customer customer) {

        System.out.println("Enter the Amount to Deposit : ");
        int depAmt = sc.nextInt();

        int accBal = customer.getBalance();

        accBal = depAmt + accBal;

        customer.setBalance(accBal);

        Transaction transaction = new Transaction(customer.getTransactionCount(),"Deposit",depAmt,accBal);

        customer.setTransactionCount(customer.getTransactionCount()+1);

        customer.getTransactionList().add(transaction);

        System.out.println("Amount Deposited Successfully...!");
    }

    public void withDrawMoney(Customer customer) {

        System.out.println("Enter the Amount to Deposit : ");
        int withDraw = sc.nextInt();

        int accBal = customer.getBalance();

        if(accBal<=1000) {
            System.out.println("Account Balance Must Be Maintained...!");
            return;
        }

        if(withDraw>accBal){
            System.out.println("Insufficinet Balance..!");
            return;
        }

        accBal = accBal - withDraw;

        customer.setBalance(accBal);

        Transaction transaction  = new Transaction(customer.getTransactionCount(),"Withdraw",withDraw,accBal);

        customer.setTransactionCount(customer.getTransactionCount()+1);

        customer.transactionList.add(transaction);

        System.out.println("Amount Withdrawed Successfully...!");
        System.out.println("Your Current balance : "+customer.getBalance());
    }

    public void history(Customer customer) {

        for(Transaction transact : customer.getTransactionList())
        {
            System.out.println(transact.getTransactionId()+" "+transact.getType()+" "+transact.getAmount()+" "+transact.getBalance());
        }
    }

    public void transferMoney(Customer customer) {

        System.out.println("Enter the Account Id you want to transfer : ");
        int accId = sc.nextInt();

        Customer customer1 = getCustomer(accId);

        if(customer1==null)
        {
            System.out.println("No Such Customer Exists..!");
            return;
        }

        System.out.println("Enter the Amount to transfer : ");
        int amt = sc.nextInt();

        int accBal = customer.getBalance();

        if(accBal<=1000)
        {
            System.out.println("Minimum Balance must be Maintained..!");
            return;
        }

        if(accBal<amt)
        {
            System.out.println("Insufficient Balance..!");
            return;
        }

        customer1.setBalance(customer1.getBalance()+amt);
        customer.setBalance(customer.getBalance()-amt);

        System.out.println("Money Transfered Successfully...!");
        Transaction t1 = new Transaction(customer.getTransactionCount(),"Transfer",amt,customer.getBalance());
        customer.setTransactionCount(customer.getTransactionCount()+1);


        Transaction t2 = new Transaction(customer1.getTransactionCount(),"Deposit",amt,customer1.getBalance());
        customer1.setTransactionCount(customer1.getTransactionCount()+1);

        customer.transactionList.add(t1);
        customer1.transactionList.add(t2);
    }
}
