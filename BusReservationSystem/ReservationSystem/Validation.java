package BusReservationSystem.ReservationSystem;

import BusReservationSystem.DTO.Customer;
import BusReservationSystem.Repository.Repository;

import java.util.Scanner;

public class Validation {

    public static int customerId=0;

    Repository repository = Repository.getInstance();

    Scanner sc = new Scanner(System.in);

    public Customer getCustomerById(int customerId) {

        for(Customer customer : repository.customerList)
        {
            if(customer.getCustomerId()==customerId)
            {
                return customer;
            }
        }
        return null;
    }

    public void signUp() {

        System.out.println("Enter the Name : ");
        String name = sc.nextLine();

        System.out.println("Enter Password  : ");
        String password = sc.nextLine();

        System.out.println("Enter the phone No : ");
        String phoneNo = sc.nextLine();

        Customer customer = new Customer(name,password,phoneNo);

        repository.customerList.add(customer);

        System.out.println("Registered Successfully...!");

    }

    public boolean login() {

        System.out.println("Enter the Username : ");
        String userName = sc.nextLine();

        System.out.println("Enter the password : ");
        String password = sc.nextLine();

        for(Customer customer : repository.customerList)
        {
            if(customer.getCustomerName().equals(userName) && customer.getPassword().equals(password))
            {
                System.out.println("Welcome "+userName);
                customerId = customer.getCustomerId();
                return true;
            }
        }
        return false;
    }


}
