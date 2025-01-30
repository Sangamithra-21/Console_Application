package StockManagementSystem.System;

import StockManagementSystem.DTO.*;
import StockManagementSystem.Repository.Repository;

import java.time.LocalDate;
import java.util.*;

import java.time.LocalDate;


public class StockViewModel {
    private StockView view;

    public StockViewModel(StockView view)
    {
        this.view = view;
    }

    static Repository repository = Repository.getInstance();

    Scanner sc = repository.sc;


    public Vendor getVendorById(int vendorId) {

        for(Vendor vendor : repository.vendorList)
        {
            if(vendor.getVendorId()==vendorId)
            {
                return vendor;
            }
        }
        return null;
    }

    public Product getProductById(List<Product> productList, int pids) {

        for(Product product : productList)
        {
            if(product.getProductId()==pids)
            {
                return product;
            }
        }
        return null;
    }

    public Company getCompanyById(int companyId) {

        for(Company company : repository.companyList)
        {
            if(company.getCompanyId()==companyId)
            {
                return company;
            }
        }
        return null;
    }

    public Customer getCustomerById(int custId) {

        for(Customer customer : repository.customerList)
        {
            if(customer.getCustomerId()==custId)
            {
                return customer;
            }
        }
        return null;
    }


    public void purshaseProductsFromVendor(Vendor vendor,Company company) {

        int vendorId = vendor.getVendorId();

        Map<Integer,VendorProduct> productList = vendor.getProductList();

        double totalCost = 0;

        int totalQuantity = 0;

        List<CProduct> buyProducts = new ArrayList<>();

        while(true)
        {
            System.out.println("Enter the Product Id : ");
            int productId = sc.nextInt();

            if(productId==0)
            {
                break;
            }

            if(productList.containsKey(productId))
            {
               VendorProduct vendorProduct = productList.get(productId);

                System.out.println("Enter the Quantity : ");
                int quantity = sc.nextInt();

               CProduct product = new CProduct(productId,vendorProduct.getProductName(),vendorProduct.getProductCost(),quantity);

               buyProducts.add(product);

               totalQuantity += quantity;

               totalCost += (product.getProductCost() * quantity);

            }

        }

        PurchaseBill purchaseBill = new PurchaseBill(vendorId,totalCost,"Paid",LocalDate.now(),totalQuantity);

        purchaseBill.setProductList(buyProducts);

        company.getPurchaseBillList().add(purchaseBill);

        vendor.getDeliverable().put(company.getCompanyId(),purchaseBill);

    }

    public void deliverAllOrders(Vendor vendor) {

        for(Map.Entry<Integer,PurchaseBill> order : vendor.getDeliverable().entrySet())
        {
            int companyId = order.getKey();

            Company company = getCompanyById(companyId);

            if(company==null)
            {
                continue;
            }
            List<CProduct> products = order.getValue().getProductList();

            increaseStock(company,products);

            order.getValue().setStatus("Received");

            vendor.getDeliverable().remove(order.getKey());

        }

        System.out.println("All Stocks Delivered Successfully...!");

    }

    public void increaseStock(Company company,List<CProduct> products)
    {
        Map<Integer,Product> productList = company.getProductList();

        for(CProduct product : products)
        {
            int productId = product.getProductId();
            int productQuantity = product.getQuantity();

          if(productList.containsKey(productId))
          {
              Product product1 = productList.get(productId);
              int orgQuantity = product1.getQuantity();

              product1.setQuantity(orgQuantity+productQuantity);
          }
          else {
              Product product1 = new Product(productId,product.getProductName(),product.getProductCost(),productQuantity);

              productList.put(product1.getProductId(),product1);
          }

        }
    }

    public void purshaseProductsFromCompany(Company company, Customer customer) {

        Map<Integer,Product> productList = company.getProductList();

        double totalCost = 0;

        List<CProduct> buyProducts = new ArrayList<>();

        while(true) {
            System.out.println("Enter the Product Id : ");
            int productId = sc.nextInt();

            if (productId == 0) {
                break;
            }

            if(company.getProductList().containsKey(productId))
            {
                Product product = company.getProductList().get(productId);
                System.out.println("Enter the Quantity Required : ");
                int quantity = sc.nextInt();

               CProduct custProduct = new CProduct(customer.orderId++,product.getProductName(),(product.getProductCost()*quantity),quantity);

               buyProducts.add(custProduct);

               totalCost += (custProduct.getProductCost());
            }

        }

        SalesBill salesBill = new SalesBill(customer.getCustomerId(),totalCost,"Paid",LocalDate.now());

        salesBill.setProductList(buyProducts);

        company.getSalesBillList().add(salesBill);

        company.getDeliverable().put(customer.getCustomerId(),salesBill);


    }

    public void deliverAllOrdersToCustomers(Company company) {

        for(Map.Entry<Integer,SalesBill> orders : company.getDeliverable().entrySet())
        {
            int customerId = orders.getKey();

            List<CProduct> products = orders.getValue().getProductList();

            Customer customer = getCustomerById(customerId);

            decreaseStock(products,customer,company);

            orders.getValue().setStatus("Received");

            company.getDeliverable().remove(orders.getKey());

        }

        System.out.println("All Stocks Delivered Successfully...!");
    }

    private void decreaseStock(List<CProduct> products,Customer customer,Company company)
    {
        for(CProduct product : products)
        {
            customer.getPurchaseProducts().add(product);
           int productId = product.getProductId();
           int productStock = product.getQuantity();

           if(company.getProductList().containsKey(productId))
           {
               int availableStock = company.getProductList().get(productId).getQuantity();

               company.getProductList().get(productId).setQuantity(availableStock-productStock);

           }
        }

    }

}
