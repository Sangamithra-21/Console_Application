package SuperMarketApplication.SuperMarket;

import FoodDeliverySystem.DTO.Items;
import SuperMarketApplication.DTO.Order;
import SuperMarketApplication.DTO.Product;
import SuperMarketApplication.DTO.Seller;
import SuperMarketApplication.DTO.Buyer;
import SuperMarketApplication.Repository.Node;
import SuperMarketApplication.Repository.Repository;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MarketView {

    private MarketViewModel model;


    Scanner sc  = new Scanner(System.in);

    Repository repository = Repository.getInstance();

    public MarketView() {

        this.model = new MarketViewModel(this);
    }

    public void addSeller() {

        System.out.println("Enter the Seller Name : ");
        String sellerName = sc.next();

        System.out.println("Enter the password  :");
        String password = sc.next();

        model.addSeller(sellerName,password);

        System.out.println("Seller Added Successfully...!");

        displaySeller();

    }

    public void addBuyer() {

        System.out.println("Enter the Buyer Name : ");
        String buyerName = sc.next();

        System.out.println("Enter the password : ");
        String password = sc.next();

        model.addBuyer(buyerName,password);
    }

    public void displaySeller()
    {
        Node temp = repository.Seller;
        if (temp == null) {
            System.out.println("No sellers to display.");
            return;
        }
        while (temp != null) {
            System.out.println(temp.data);
            temp = temp.next;
        }

    }

    public void displayBuyer()
    {
        Node temp = repository.Buyer;
        if(temp==null)
        {
            System.out.println("No Buyer Available..!");
            return;
        }

        while(temp!=null)
        {
            System.out.println(temp.data);
            temp = temp.next;
        }
    }

    public void verifySeller() {

        System.out.println("Enter the Username : ");
        String name = sc.next();

        System.out.println("Enter the password : ");
        String password = sc.next();

        Seller seller = model.checkIsValidSupplier(name,password);

        if(seller==null)
        {
            System.out.println("Invalid Seller..!");
            return;
        }

        boolean flag = true;

        while(flag)
        {
            System.out.println("1)Add Item 2)Update Items 3)View All products 4)Logout");
            int choice = sc.nextInt();
            switch(choice)
            {
                case 1 : {
                    addItem(seller);
                    break;
                }
                case 2 : {
                    updateItems(seller);
                    break;
                }
                case 3 : {
                    viewProductsOfSeller(seller,null);
                    break;
                }
                case 4 : {
                    flag = false;
                    break;
                }
            }
        }
    }

    private void updateItems(Seller seller) {

        System.out.println("Enter the product Name : ");
        String prodName= sc.next();

        Product product = viewProductsOfSeller(seller,prodName);

        if(product==null)
        {
            System.out.println("No Such product exists with this Id");
            return;
        }

        Node itemList = product.Items;

        System.out.println("Items present In the " + prodName + "categories are...");
        getItems(itemList);

        System.out.println("Enter the Item number you want to modify...!");
        int itemId = sc.nextInt();

        System.out.println("Selected Item Details");

        Items item = getItemById(itemList,itemId);

        if(item==null)
        {
            System.out.println("No such item found");
            return;
        }

        displayItems(item);

        System.out.println("What field You want to update : [1--> Item Name | 2-->Item price]");
        int choice = sc.nextInt();

        switch(choice){
            case 1 : {
                System.out.println("Enter the Modified Item Name : ");
                String newName = sc.next();
                item.setItemName(newName);
                break;
            }
            case 2 : {
                System.out.println("Enter the Modified Item price : ");
                int newPrice = sc.nextInt();
                item.setPrice(newPrice);
                break;
            }
        }

        displayItems(item);
        System.out.println("Item Updated Successfully...!");
    }

    public Items getItemById(Node temp,int itemId) {

        while(temp!=null)
        {
            Items item = (Items)temp.data;
            if(item.getItemId()==itemId)
            {
                return item;
            }
            temp = temp.next;
        }
        return null;

    }

    private Product viewProductsOfSeller(Seller seller,String productName) {

        Node productList = seller.Products;
        Product temp = null;
        while(productList!=null)
        {
            Product product = (Product)productList.data;
            if(product.getProductName().equals(productName))
            {
                temp = product;
            }
            Node itemList = product.Items;
            getItems(itemList);
            productList = productList.next;
        }
        return temp;
    }

    private void addItem(Seller seller) {

        System.out.println("Enter the product Name : ");
        String productName = sc.next();

        Node productNode = model.getProductNode(seller,productName);

        if(productNode==null)
        {
            Product product =  new Product(productName);
            model.addproductToSeller(seller,product);
            productNode = model.getProductNode(seller,productName);
        }


        System.out.println("Enter the Number of Items to be Added : ");
        int itemCount = sc.nextInt();


        for(int i=0;i<itemCount;i++)
        {
            System.out.println("Enter the Item Name : ");
            String itemName = sc.next();

            System.out.println("Enter the Item Price : ");
            int itemPrice = sc.nextInt();

            Items item = new Items(itemName,itemPrice);

            model.additemsBySupplier(productNode,item);
        }

        System.out.println("Items added successfully....!");

    }


    public void verifyBuyer() {

        System.out.println("Enter the Buyer Name : ");
        String buyerName = sc.next();


        System.out.println("Enter the password : ");
        String password = sc.next();

        Buyer buyer = model.checkisValidBuyer(buyerName,password);

        if(buyer==null)
        {
            System.out.println("Not a Valid Buyer...!");
            return;
        }

        boolean flag = true;

        while(flag)
        {
            System.out.println("1)View Product 2)Add Item to cart 3)View cart 4)Remove item from cart 5)Make order 6)Cancel Order 7)Display Orders 8)Logout");
            int choice = sc.nextInt();

            switch(choice)
            {
                case 1 : {
                    viewProducts();
                    break;
                }
                case 2 : {
                    addItemsToCart(buyer);
                    break;
                }
                case 3 :{
                    displayCartItems(buyer.cart);
                    break;
                }
                case 4 : {
                    deleteItem(buyer);
                    break;
                }
                case 5 : {
                    makeOrder(buyer);
                    break;
                }
                case 6 : {
                   cancelOrder(buyer);
                   break;
                }
                case 7 : {
                    displayAllOrders(buyer);
                    break;
                }
                case 8 : {
                    flag = false;
                    break;
                }
            }
        }

    }

    private void displayAllOrders(Buyer buyer) {

        Node orders = buyer.orderList;

        while(orders!=null)
        {
            Order order = (Order)orders.data;
            System.out.println("Order Id : "+order.getOrderId()+" "+"\n"+"Items");
            Node items = order.orderItems;
            while(items!=null)
            {
                Items item = (Items)items.data;
                if(item!=null) {
                    displayItems(item);
                }
                items = items.next;
            }
            orders = orders.next;
        }
    }

    private void cancelOrder(Buyer buyer) {

        displayAllOrders(buyer);

        System.out.println("Enter the order id : ");
        int orderId = sc.nextInt();

        LocalTime currTime = LocalTime.now();

        buyer.orderList = model.cancelOrder(buyer.orderList,orderId,currTime);

    }

    private void deleteItem(Buyer buyer) {

        displayCartItems(buyer.cart);

        System.out.println("Enter the Item id : ");
        int id = sc.nextInt();

        buyer.cart = model.deleteItemById(buyer.cart,id);

    }

    private void displayCartItems(Node cart) {
        if(cart==null)
        {
            System.out.println("No items in the cart...!");
        }
        while(cart!=null)
        {
            Items items = (Items)cart.data;
            displayItems(items);
            cart = cart.next;
        }
    }

    private void makeOrder(Buyer buyer) {

        Node cartList = buyer.cart;
        Node cart = cartList;

        int totalCost = 0;
        System.out.println("Your Cart Items...!");
        System.out.println("---------------------------------");
        while(cartList!=null)
        {
            Items item = (Items)cartList.data;
            displayItems(item);
            totalCost += item.getPrice();
            cartList = cartList.next;
        }
        System.out.println("Press 1 to confirm order | 0 to quit");
        int choice = sc.nextInt();

        Order order=null;
        if(choice==1)
        {
            LocalTime currentTime = LocalTime.now();
            order = new Order(null,totalCost,currentTime);
            order.orderItems = cart;
            System.out.println("Total Purchase Amount : "+totalCost);
            System.out.println("This is your order Id : "+order.getOrderId());
            System.out.println("Thanks for Order..!");
        }
        else{
            return;
        }


        model.placeOrder(buyer,order);

        buyer.cart = null;


    }

    private void addItemsToCart(Buyer buyer) {

        List<Integer> itemIds = new ArrayList<>();

        System.out.println("Enter the Item ids : [0 to stop]");
        while(true)
        {
            int id = sc.nextInt();
            if(id==0)
            {
                break;
            }
            itemIds.add(id);
        }

        addItems(buyer,itemIds);

        System.out.println("Items added to the cart successfully...!");


    }

    private void addItems(Buyer buyer, List<Integer> itemIds) {

        Node temp = repository.Items;

        while(temp!=null)
        {
            Items item = (Items)temp.data;

            if(itemIds.contains(item.getItemId()))
            {
                model.addItemsToTheCart(buyer,item);
            }

            temp = temp.next;
        }
    }

    private void viewProducts() {

        Node temp = repository.Seller;

        while(temp!=null)
        {
            Seller seller = (Seller)temp.data;
            System.out.println("---------------------------------");
            System.out.println("Seller Id : "+seller.getSellerId());
            System.out.println("---------------------------------");

            Node product = seller.Products;

            if(product==null)
            {
                System.out.println("No Products found for seller with Id : "+seller.getSellerId());
            }
            while(product!=null)
            {
                Product prodList = (Product)product.data;

                Node itemList = prodList.Items;

                if(itemList==null)
                {
                    System.out.println("No Items found");
                }

                getItems(itemList);

                product = product.next;
            }
            temp = temp.next;
        }
    }

    private void getItems(Node itemList) {

        while(itemList!=null)
        {
            Items item = (Items)itemList.data;
            displayItems(item);
            itemList = itemList.next;
        }

    }

    private void displayItems(Items items) {

        System.out.println("Item Id   : "+items.getItemId()+"---- "+
                           "Item Name : "+items.getItemName()+"----"+
                           "Item price :"+items.getPrice());
    }

}
