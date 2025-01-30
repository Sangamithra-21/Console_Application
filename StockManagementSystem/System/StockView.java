package StockManagementSystem.System;

import StockManagementSystem.DTO.*;
import StockManagementSystem.Repository.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class StockView {

    private StockViewModel model;

    public StockView()
    {
        this.model = new StockViewModel(this);
    }

    static Repository repository = Repository.getInstance();

    Scanner sc = repository.sc;



    public void run()
    {
        System.out.println("Stock Management System");
        while(true)
        {
            System.out.println("1)Add Vendor 2)Add Products to Vendor 3)Add Company \n" +
                               "4)Add Customer 5)Buy Products (Company -> Vendor) \n"+
                               "6)Deliver To Company 7)Buy Products (Customer -> Company) \n"+
                               "8)Deliver To Customer 9)Data Summary");

            System.out.println("Enter the Option : ");
            int option = sc.nextInt();

            switch(option)
            {
                case 1 :
                    addvendor();
                    break;
                case 2 :
                    addProducts();
                    break;
                case 3 :
                    addCompany();
                    break;
                case 4 :
                    addCustomer();
                    break;
                case 5 :
                    buyProductsFromVendor();
                    break;
                case 6 :
                    deliverProductToCompany();
                    break;
                case 7 :
                    buyProductsFromCompany();
                    break;
                case 8 :
                    deliverProductToCustomer();
                    break;
                case 9 :
                    dataSummary();
                    break;
                default :
                    System.out.println("Thank You...!");
                    return;
            }

        }
    }


    // Add Customer

    private void addCustomer() {

        System.out.println("Enter the Customer Name : ");
        String name = sc.next();

        Customer customer = new Customer(name);

        repository.customerList.add(customer);

        System.out.println("Customer Added Successfully..!");

        System.out.println("Customer Id : "+customer.getCustomerId());

    }

    // Add Vendor

    private void addvendor() {

        System.out.println("Enter the Vendor Name");
        String name = sc.next();

        Vendor vendor = new Vendor(name);

        repository.vendorList.add(vendor);

        System.out.println("Vendor Added Successfully...>!");

        System.out.println("Vendor Id : "+vendor.getVendorId());

    }

    // Add products to Vendor

    private void addProducts()
    {
        System.out.println("Enter the Vendor Id : ");
        int vid = sc.nextInt();

        Vendor vendor = model.getVendorById(vid);

        if(vendor==null)
        {
            System.out.println("No Such Vendor Found..>!");
            return;
        }

        System.out.println("Enter the Number of Products : ");
        int count = sc.nextInt();

        while(count>0)
        {
            System.out.println("Enter the Product Name : ");
            String productName = sc.next();

            System.out.println("Enter the Product Cost : ");
            int cost = sc.nextInt();

            VendorProduct product = new VendorProduct(productName,cost);

            vendor.getProductList().put(product.getProductId(),product);

            count--;
        }

        System.out.println("Products added successfully...>!");

    }

    // Add Company

    private void addCompany()
    {
        System.out.println("Enter the Company Name : ");
        String companyName = sc.next();

        Company company = new Company(companyName);

        repository.companyList.add(company);

        System.out.println("Company Added Successfully...!");

        System.out.println("Company Id : "+company.getCompanyId());
    }

    // Buy Products from Vendor

    private void buyProductsFromVendor() {

        System.out.println("Enter the Vendor Id : ");
        int vendorId = sc.nextInt();

        Vendor vendor = model.getVendorById(vendorId);

        if(vendor==null)
        {
            System.out.println("No such vendor Found");
            return;
        }

        System.out.println("Enter the company Id : ");
        int companyId = sc.nextInt();

       Company company = model.getCompanyById(companyId);

        if(company==null)
        {
            System.out.println("No Such Company Found..>!");
            return;
        }

        displayProductsOfVendor(vendor);

        model.purshaseProductsFromVendor(vendor,company);
    }

    private void displayProductsOfVendor(Vendor vendor) {

        for(VendorProduct product : vendor.getProductList().values())
        {
            System.out.println(product.getProductId()+" -- "+product.getProductName()+" -- "+product.getProductCost());
        }
    }


    private void deliverProductToCompany() {

        System.out.println("Enter the Vendor Id : ");
        int vendorId = sc.nextInt();

        Vendor vendor = model.getVendorById(vendorId);

        if(vendor==null)
        {
            System.out.println("No Such Vendor Found..>!");
            return;
        }

        model.deliverAllOrders(vendor);
    }

    private void buyProductsFromCompany() {

        System.out.println("Enter the Company Id : ");
        int companyId = sc.nextInt();

        Company company = model.getCompanyById(companyId);

        if(company==null)
        {
            System.out.println("No Such Company Found...>!");
            return;
        }

        System.out.println("Enter the customer Id : ");
        int custId = sc.nextInt();

        Customer customer = model.getCustomerById(custId);

        if(customer==null)
        {
            System.out.println("No Such customer Found..>!");
            return;
        }

        displayProductsOfCompany(company);

        model.purshaseProductsFromCompany(company,customer);



    }

    private void displayProductsOfCompany(Company company) {

        for(Product product : company.getProductList().values())
        {
            System.out.println(product.getProductId()+" -- "+product.getProductName()+" -- "+product.getProductCost() +" -- "+product.getQuantity());
        }
    }


    private void deliverProductToCustomer() {
        System.out.println("Enter the Company Id : ");
        int companyId = sc.nextInt();

        Company company = model.getCompanyById(companyId);

        if(company==null)
        {
            System.out.println("No Such Company Found...>!");
            return;
        }

        model.deliverAllOrdersToCustomers(company);

    }



    private void dataSummary() {

        System.out.println("1)Current Stock Availability of a product");
        System.out.println("2)Stock Value of a product");
        System.out.println("3)Quantity to be received");
        System.out.println("4)Quantity needed to deliever the paid SB");
        System.out.println("5)Overall stock value(in Rupeess) of org");
        System.out.println("6)Quantity Received for a product during a date range");
        System.out.println("7)Quantity delievered for a product during a date range");

        System.out.println("Enter the Option :");
        int option = sc.nextInt();

        System.out.println("Enter the company id : ");
        int companyId = sc.nextInt();

        Company company = model.getCompanyById(companyId);

        if(company==null)
        {
            System.out.println("No Such Company Found...!");
            return;
        }

        switch(option)
        {
            case 1 :
                currentStock(company);
                break;
            case 2 :
                stockValue(company);
                break;
            case 3 :
                pendingProductsFromVendor(company);
                break;
            case 4 :
                pendingProductsToCustomer(company);
                break;
            case 5 :
                overallStockValue(company);
                break;
            case 6 :
                quantityReceived(company);
                break;
            case 7 :
                quantityDelivered(company);
                break;
            default :
                return;


        }

    }



    private void currentStock(Company company)
    {
        System.out.println("Enter the Product Id : ");
        int productId = sc.nextInt();

        if(company.getProductList().containsKey(productId)) {

            Product product = company.getProductList().get(productId);

            System.out.println("Available Stock : "+(product.getQuantity()));
        }

    }

    private void stockValue(Company company)
    {

        System.out.println("Enter the Product Id : ");
        int productId = sc.nextInt();

        if(company.getProductList().containsKey(productId)) {

            Product product = company.getProductList().get(productId);

            System.out.println("Available Stock : "+(product.getQuantity()*product.getProductCost()));
        }
    }


    private void pendingProductsFromVendor(Company company)
    {
        int totalQuantity = 0;

        for(PurchaseBill bill : company.getPurchaseBillList())
        {
            if(bill.getStatus().equals("Paid"))
            {
                for(CProduct product : bill.getProductList())
                {
                    totalQuantity += product.getQuantity();
                }
            }
        }

        System.out.println("Quantity to be received : "+totalQuantity);

    }


    private void pendingProductsToCustomer(Company company) {

        int totalQuantity = 0;

        for(SalesBill bill : company.getSalesBillList())
        {
            if(bill.getStatus().equals("Paid"))
            {
                for(CProduct product : bill.getProductList())
                {
                    totalQuantity += product.getQuantity();
                }
            }
        }

        System.out.println("Quantity to be received : "+totalQuantity);

    }

    private void overallStockValue(Company company)
    {

        double totalValue = 0;

        for(Map.Entry<Integer,Product> products : company.getProductList().entrySet())
        {
            int quantity = products.getValue().getQuantity();
            double cost = products.getValue().getProductCost();

            totalValue += (quantity * cost);


        }
        System.out.println("Overall Stock Value : "+totalValue);
    }

    private void quantityReceived(Company company)
    {

        System.out.println("Enter start Date [yyyy-mm-dd]: ");
        String sDate = sc.next();

        System.out.println("Enter end Date");
        String eDate = sc.next();

        System.out.println("Enter the Product Name : ");
        String productName = sc.next();

        LocalDate start =  LocalDate.parse(sDate);

        LocalDate end = LocalDate.parse(eDate);

        int totalQuantity = 0;

        for(PurchaseBill bill : company.getPurchaseBillList())
        {
           if(!bill.getDate().isBefore(start) && !bill.getDate().isAfter(end))
           {
               List<CProduct> products = bill.getProductList();

               for(CProduct product : products)
               {
                   if(product.getProductName().equals(productName))
                   {
                       totalQuantity += product.getQuantity();
                   }
               }
           }
        }

        System.out.println("Quantity Received for a product during a date : "+totalQuantity);
    }


    private void quantityDelivered(Company company)
    {

        System.out.println("Enter start Date [yyyy-mm-dd]: ");
        String sDate = sc.next();

        System.out.println("Enter end Date");
        String eDate = sc.next();

        System.out.println("Enter the Product Name : ");
        String productName = sc.next();

        LocalDate start =  LocalDate.parse(sDate);

        LocalDate end = LocalDate.parse(eDate);

        int totalQuantity = 0;

        for(SalesBill bill : company.getSalesBillList())
        {
            if(!bill.getDate().isBefore(start) && !bill.getDate().isAfter(end))
            {
                List<CProduct> products = bill.getProductList();

                for(CProduct product : products)
                {
                    if(product.getProductName().equals(productName))
                    {
                        totalQuantity += product.getQuantity();
                    }
                }
            }
        }

        System.out.println("Quantity Received for a product during a date : "+totalQuantity);
    }


}
