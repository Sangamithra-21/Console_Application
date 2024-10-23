package PharmacyManagementSystem.Pharmacy;

import PharmacyManagementSystem.DTO.Branch;
import PharmacyManagementSystem.DTO.Purchase;
import PharmacyManagementSystem.Repository.Repository;
import PharmacyManagementSystem.DTO.Medicine;
import PharmacyManagementSystem.DTO.Customer;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PharmacyView {

    private PharmacyViewModel model;

    private Repository repository = Repository.getInstance();

    public PharmacyView()
    {
        this.model = new PharmacyViewModel(this);
    }


    Scanner sc = repository.sc;
    public void run() {

        while(true)
        {
            System.out.println("Pharmacy Management");
            System.out.println("1)Add Branch    2)Add Stock            3)Associate Alternate Medicine");
            System.out.println("4)Add Customer  5)Purchase Products    6)Print Summary    7)Display Branch Medicines   8)Exit");

            System.out.println("Enter the option : ");
            int option = sc.nextInt();

            switch(option)
            {
                case 1 :
                    addBranch();
                    break;
                case 2 :
                    addStock();
                    break;
                case 3 :
                    associateMedicine();
                    break;
                case 4 :
                    addCustomer();
                    break;
                case 5 :
                    purchaseMedicine();
                    break;
                case 6 :
                    printSummary();
                    break;
                case 7 :
                    displayMedicineBranch();
                    break;
                case 8 :
                    break;
            }

        }

    }


    // 1) Add Branch

    private void addBranch()
    {
        System.out.println("Enter the Branch Location : ");
        String branchName = sc.next();

        System.out.println("Enter the Phone Number : ");
        String phoneNo = sc.next();

        model.addBranch(branchName,phoneNo);

        System.out.println("Branch added Successfully..!");
    }

    // 2) Add Stock

    private void addStock()
    {
        System.out.println("Enter the Branch Name : ");
        String branchName = sc.next();

        Branch branch = model.getBranchById(branchName);

        if(branch==null)
        {
            System.out.println("No such branch found...!");
            return;
        }

        System.out.println("Enter the Number of stocks to be added : ");
        int stockCount = sc.nextInt();

        for(int i=0;i<stockCount;i++)
        {
            System.out.println("Enter the Medicine Name : ");
            String name = sc.next();

            Medicine medicine = model.getMedicineByName(name,branch.getMedicineList());


            if(medicine!=null)
            {
                System.out.println("Enter the quantity of stock added : ");
                int stock = sc.nextInt();

                int quantity = medicine.getQuantity()+stock;

                medicine.setQuantity(quantity);

                continue;
            }

            System.out.println("Enter the price of Medicine : ");
            int price = sc.nextInt();

            System.out.println("Enter the quantity of stock added :  ");
            int quantity = sc.nextInt();

            model.addStock(name,price,quantity,branch);

        }

        System.out.println("Medicines added successfully...!");


    }

    // 3) Associate Alternative

    private void associateMedicine()
    {
        System.out.println("Enter the Medicine Name :  ");
        String medicineName = sc.next();

        Medicine medicine1 = model.getMedicineByName(medicineName,repository.medicineList);

        System.out.println("Enter the Alternate Medicine Name : ");
        String altMedicine = sc.next();

        Medicine medicine2 = model.getMedicineByName(altMedicine,repository.medicineList);

        repository.alternateMedicineList.put(medicineName,medicine2);
        repository.alternateMedicineList.put(altMedicine,medicine1);

        System.out.println("Medicines Associated Successfully...!");

    }

    // 4)Add Customer

    private void addCustomer()
    {
        System.out.println("Enter the customer Name : ");
        String name = sc.next();

        System.out.println("Enter the phone Number : ");
        String phone = sc.next();

        model.addCustomer(name,phone);

        System.out.println("Customer added successfully..!");


    }

    // 5)Purchase Medicine

    private void purchaseMedicine()
    {

        System.out.println("Enter the customer Name : ");
        String customerName = sc.next();

        Customer customer = model.getCustomerByName(customerName);

        if(customer==null)
        {
            System.out.println("No Such Customer Found..!");
            return;
        }

        System.out.println("Enter the Branch Name : ");
        String branchName = sc.next();

        Branch branch = model.getBranchById(branchName);

        if(branch==null)
        {
            System.out.println("No such branch found..!");
            return;
        }


        int totalAmount = 0;

        while(true)
        {
            displayMedicinesOfBranch(branch.getMedicineList());

            System.out.println("Enter the Medicine Id : ");
            int medicineId = sc.nextInt();


            System.out.println("Enter the quantity you needed : ");
            int reqQuantity = sc.nextInt();

            Medicine medicine = model.getMedicineById(medicineId,branch.getMedicineList());

            if(medicine==null)
            {
                System.out.println("No such Medicine found..!");
                continue;
            }

            if(reqQuantity>medicine.getQuantity())
            {
                System.out.println("Required quantity Not found..!");
                Medicine altMedicine = repository.alternateMedicineList.get(medicine.getMedicineName());

                if(branch.getMedicineList().contains(altMedicine))
                {

                if(reqQuantity<altMedicine.getQuantity()) {
                    System.out.println("Suggested Medicine : " + altMedicine.getMedicineName());
                    System.out.println("1 to confirm | 0 to quit");
                    int choice = sc.nextInt();

                    if (choice == 1) {
                        customer.getCartList().add(altMedicine);

                        int newquantity = altMedicine.getQuantity() - reqQuantity;

                        totalAmount = totalAmount + (altMedicine.getPrice() * reqQuantity);

                        altMedicine.setQuantity(newquantity);
                        System.out.println("Medicine Added to cart Successfully...!");
                    } else {
                        continue;
                    }
                }

                }
                else {

                    Branch altBranch1 = model.findAnotherBranch(reqQuantity,medicine);
                    Branch altBranch2 = model.findAnotherBranch(reqQuantity,altMedicine);
                    Branch alter=null;
                    if(altBranch1!=null)
                    {
                        alter = altBranch1;
                    }
                    else if(altBranch2!=null)
                    {
                        alter = altBranch2;
                    }
                    if(alter==null)
                    {
                        System.out.println("Medicine Not Found Anywhere...!");
                    }
                    if(alter!=null){
                        System.out.println("Required Medicine Not Available In this branch ");
                        System.out.println("Suggested branch : "+branch.getBranchLocation());
                        System.out.println("press 1 to change branch | 0 to continue with same branch");

                        int choice = sc.nextInt();

                        if(choice==1)
                        {
                            branch = alter;
                        }
                        else if(choice==0)
                        {
                            continue;
                        }
                    }


                }
            }
            else {
                int newquantity = medicine.getQuantity() - reqQuantity;

                totalAmount = totalAmount + (medicine.getPrice()*reqQuantity);

                medicine.setQuantity(newquantity);

                customer.getCartList().add(medicine);

                System.out.println("Medicine Added to cart Successfully...!");
            }

            System.out.println("Press 1 to continue Purchase | 0 to Make Order ");

            int choice = sc.nextInt();

            if(choice==0)
            {
                Purchase purchase = new Purchase(totalAmount,branch.getBranchLocation(),customer.getCartList());
                customer.getPurchaseList().add(purchase);
                System.out.println("Amount For your Order : "+purchase.getAmount());
                customer.setCartList(new ArrayList<>());
                System.out.println("Ordered Successfully...!");
                break;
            }
        }


    }

    private void displayMedicinesOfBranch(List<Medicine> medicineList) {

        for(Medicine medicine : medicineList)
        {
            System.out.println("Medicine Id : "+medicine.getMedicineId()+" Medicine Name : "+medicine.getMedicineName()+ " Medicine Price : "+medicine.getPrice() +" Medicine Quantity : "+medicine.getQuantity());
        }
    }

    // 6) Purchase summary

    private void printSummary()
    {
        System.out.println("Enter the customer Name :");
        String name = sc.next();

        Customer customer = model.getCustomerByName(name);

        if(customer==null)
        {
            System.out.println("No such Customer Exist");
            return;
        }

        for(Purchase purchase : customer.getPurchaseList())
        {
            System.out.println("Purchase Id : "+purchase.getPurchaseId());
            displayMedicinesOfBranch(purchase.getMedicineList());
            System.out.println("Purchase Amount : "+purchase.getAmount());
        }
    }

    // 7)display Branch Medicines

    private void displayMedicineBranch()
    {
        System.out.println("Enter the branch Name : ");
        String name = sc.next();

        Branch branch = model.getBranchById(name);

        if(branch==null)
        {
            System.out.println("No such branch found...!");
            return;
        }

        displayMedicinesOfBranch(branch.getMedicineList());
    }

}
