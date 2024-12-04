package BankApplicationUsingFiles.BankSystem;

import BankApplicationUsingFiles.DTO.Customer;
import BankApplicationUsingFiles.DTO.Transaction;
import BankApplicationUsingFiles.Repository.Repository;

import java.io.*;
import java.util.Scanner;

public class SystemView {

    private SystemViewModel model;

    public SystemView()
    {
        this.model = new SystemViewModel(this);
    }

    Repository repository = Repository.getInstance();

    Scanner sc = repository.scanner;

    public void run() throws IOException {

         while(true)
         {
             System.out.println("1)Add Customer 2)Login 3)Exit");
             int option = sc.nextInt();

             switch (option)
             {
                 case 1 : {
                     addCustomer();
                     break;
                 }
                 case 2 : {
                     login();
                     break;
                 }
                 case 3 : {
                     return;
                 }
             }
         }
    }

    private void addCustomer() throws IOException {

        System.out.println("Enter the Customer Name : ");
        String name = sc.next();

        System.out.println("Enter the password : ");
        String password = sc.next();

        System.out.println("Re-Enter the password : ");
        String pass = sc.next();

        if(!password.equals(pass)) {
            System.out.println("Password and Confirm Password Mismatch");
            return;
        }

        password = model.encryptPassword(password);

        int ind = repository.customerList.size()-1;

        int custId = repository.customerList.get(ind).getCustId()+1;
        int accId = repository.customerList.get(ind).getAccId()+1;

        Customer customer = new Customer(custId,accId,name,10000,password);

        String transaction = customer.getTranscount()+" "+"Opening "+"10000 "+"10000";

        customer.setTranscount(customer.getTranscount()+1);



        String fileName = customer.getName()+customer.getCustId()+".txt";

        File f = new File(fileName);

        if(!f.exists())
        {
           try
           {
               f.createNewFile();
               model.addTransaction(transaction,f);

           }
           catch(Exception e)
           {
               e.printStackTrace();
           }
        }


        repository.customerList.add(customer);

        model.addCustomer(customer);

        model.sort();

        model.finalizee();

        System.out.println("Customer Added Successfully...!");
        System.out.println("Your Customer Id : "+custId);
        System.out.println("Your Account Id  : "+accId);

    }

    private void login() throws IOException {
        System.out.println("Enter the customer id : ");
        int id = sc.nextInt();

        System.out.println("Enter the password : ");
        String password = sc.next();

        password = model.encryptPassword(password);

        Customer customer = model.getCustomerById(id,password);

        if(customer==null)
        {
            System.out.println("No such Customer Found..>!");
            return;
        }

        operations(customer);
    }

    private void operations(Customer customer) throws IOException {

        while(true)
        {
            System.out.println("1)Cash Deposit 2)Withdraw 3)Transfer 4)History 5)Exit");

            int choice = sc.nextInt();

            switch(choice)
            {
                case 1 : {
                    depositAmount(customer);
                    break;
                }
                case 2 : {
                    withdrawAmount(customer);
                    break;
                }
                case 3 : {
                    transferAmount(customer);
                    break;
                }
                case 4 :
                {
                    transactionHistory(customer);
                    break;
                }
                case 5 : {
                    return;
                }
            }
        }
    }


    private void depositAmount(Customer customer) throws IOException {

        System.out.println("Enter the Amount to Deposit : ");
        int amt = sc.nextInt();

        int accBalance = customer.getBalance();

        accBalance = accBalance + amt;

        customer.setBalance(accBalance);

         int ind = customer.getTranscount();

        String transaction = ind+" Deposit "+amt+" "+accBalance;

        customer.setTranscount(ind+1);

        File file = new File(customer.getName()+customer.getCustId()+".txt");

        model.addTransaction(transaction,file);

        model.sort();

        model.finalizee();

        System.out.println("Amount Deposited Successfully..!");
    }


    private void withdrawAmount(Customer customer) throws IOException {
        System.out.println("Enter the Amount to Withdraw : ");
        int amt = sc.nextInt();

        int accBalance = customer.getBalance();

        if(accBalance<=1000)
        {
            System.out.println("Minimum Balance must be Maintained..!");
            return;
        }

        if(amt>accBalance)
        {
            System.out.println("Insufficinet Balance..!");
            return;
        }

        accBalance = accBalance - amt;

        customer.setBalance(accBalance);

        int ind = customer.getTranscount();

        String transaction = ind+" Withdraw "+amt+" "+accBalance;

        customer.setTranscount(ind+1);

        File file = new File(customer.getName()+customer.getCustId()+".txt");

        model.addTransaction(transaction,file);

        model.sort();

        model.finalizee();

        System.out.println("Your Account Balance : "+accBalance);

    }

    private void transferAmount(Customer customer) throws IOException {
        System.out.println("Enter the Customer Id you want to Transfer : ");
        int id = sc.nextInt();

        Customer cust = model.getCustomerByCustId(id);

        if(cust==null)
        {
            System.out.println("No Such Customer Found...!");
            return;
        }

        System.out.println("Enter the Amount to transfer : ");
        int amt = sc.nextInt();

        int customerAccBal = customer.getBalance();

        if(customerAccBal<=1000)
        {
            System.out.println("Minimum Balance must be Maintained..!");
            return;
        }

        if(amt>customerAccBal)
        {
            System.out.println("insufficient Balance..!");
            return;
        }

        int receiverAccBal = cust.getBalance();

        customerAccBal = customerAccBal - amt;
        receiverAccBal = receiverAccBal + amt;

        customer.setBalance(customerAccBal);
        cust.setBalance(receiverAccBal);

        int ind = customer.getTranscount();

        String transaction = ind+" Transfer "+amt+" "+customerAccBal;

        customer.setTranscount(ind+1);

        File file = new File(customer.getName()+customer.getCustId()+".txt");

        model.addTransaction(transaction,file);

//         // Receiver transaction

        int ind1 = customer.getTranscount();

        String transaction1 = ind1+" Deposit "+amt+" "+receiverAccBal;

        cust.setTranscount(ind1+1);

        File file1 = new File(cust.getName()+cust.getCustId()+".txt");

        model.addTransaction(transaction1,file1);

        model.sort();

        model.finalizee();

        System.out.println("Amount transferred Successfully...!");

    }

    private void transactionHistory(Customer customer) throws IOException {

        File file = new File(customer.getName()+customer.getCustId()+".txt");

        BufferedReader reader = new BufferedReader(new FileReader(file));

        String currLine = reader.readLine();

        while(currLine!=null)
        {
            System.out.println(currLine);
            currLine = reader.readLine();
        }

    }


}
