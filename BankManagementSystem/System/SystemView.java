package BankManagementSystem.System;

import BankManagementSystem.DTO.Customer;
import BankManagementSystem.DTO.Transaction;
import BankManagementSystem.Repository.Repository;

import javax.swing.*;
import java.util.Currency;
import java.util.Scanner;

public class SystemView {

    private SystemViewModel model;

    public SystemView()
    {
        this.model = new SystemViewModel(this);
    }

    Repository repository = Repository.getInstance();

    Scanner sc = repository.scanner;
    public void run() {

        while(true)
        {
            System.out.println("1)Signup 2)Login 3)Exit");
            int choice = sc.nextInt();

            switch(choice)
            {
                case 1 : {
                    signup();
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

    private void login() {

        System.out.println("Enter the Customer Id :");
        int custId = sc.nextInt();

        Customer customer = model.getCustomer(custId);

        if(customer==null) {
            System.out.println("No Such Customer Id Found");
            return;
        }

        System.out.println("Enter the password : ");
        String password = sc.next();

        if(customer.getPassword().equals(password))
        {
            System.out.println("login Successfully...!");
            operations(customer);
            return;
        }

        System.out.println("Invalid Credentials..!");

    }

    private void operations(Customer customer) {

        while(true)
        {
            System.out.println("1)Deposit 2)Withdraw 3)Transfer 4)History 5)Exit");
            int choice = sc.nextInt();

            switch(choice)
            {
                case 1 : {
                    model.depositMoney(customer);
                    break;
                }
                case 2 : {
                    model.withDrawMoney(customer);
                    break;
                }
                case 3 : {
                    model.transferMoney(customer);
                    break;
                }
                case 4 : {
                    model.history(customer);
                    break;
                }
                case 5 : {
                    return;
                }

            }

        }
    }

    private void signup() {

        System.out.println("Enter the Customer Name : ");
        String name = sc.next();

        System.out.println("Enter the password : ");
        String password = sc.next();

        System.out.println("Re-enter the password : ");
        String pass = sc.next();

        if(!password.equals(pass))
        {
            System.out.println("Password and Confirm password mismatch");
            return;
        }

        password = model.encrptyPassword(password);

        Customer customer  = new Customer(name,10000,password);

        repository.customerList.add(customer);

        Transaction transaction = new Transaction(customer.getTransactionCount(),"Opening",10000,customer.getBalance());

        customer.transactionList.add(transaction);

        customer.setTransactionCount(customer.getTransactionCount()+1);

        System.out.println("Account added Successfully...!");
    }


}
