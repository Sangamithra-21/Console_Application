package GiftCard.CardSystem;


import GiftCard.DTO.Customer;
import GiftCard.DTO.Giftcard;
import GiftCard.DTO.Topup;
import GiftCard.Repository.Repository;

import java.util.List;
import java.util.Scanner;

public class cardView {

    private cardViewModel model;

    public cardView()
    {
        this.model = new cardViewModel(this);
    }

    Repository repository = Repository.getInstance();

    Scanner sc = repository.sc;


    public void run() {


        boolean start=true;
        while (start) {
            System.out.println("1)Add Customer 2)Account Login 3)Purchase 4)Exit");
            System.out.println("Enter the option : ");
            int option = sc.nextInt();
            switch (option) {
                case 1:
                    addCustomers();
                    break;
                case 2:
                    accountLogin();
                    break;
                case 3:
                    purchase();
                    break;
                case 4:
                    start=false;
                    break;

            }
        }
    }



    public void addCustomers()
    {
        System.out.println("Enter the Customer Name");
        String custName = sc.next();

        System.out.println("Enter the password : ");
        String pass = sc.next();

        String encPass = model.encryptPassword(pass);

        Customer customer = model.addCustomers(custName,encPass);

       if(customer!=null)
       {
           System.out.println("Customer Added Successfully...!");
       }

    }

    private void accountLogin() {

        System.out.println("Enter the customer Name : ");
        String name = sc.next();

        System.out.println("Enter the password : ");
        String pass = sc.next();

        String password = model.encryptPassword(pass);

        Customer customer = model.getCustomer(name,password);

        if(customer!=null)
        {
            boolean flag=true;
            while(flag) {
                System.out.println("1)Create Gift Card");
                System.out.println("2)Top Up");
                System.out.println("3)Transaction History");
                System.out.println("4)Block");
                System.out.println("5)Logout");

                System.out.println("Enter the Option : ");
                int choice = sc.nextInt();

                switch(choice)
                {
                    case 1 :
                        createGiftCard(customer);
                        break;
                    case 2 :
                        topup(customer);
                        break;
                    case 3 :
                        transaction(customer);
                        break;
                    case 4 :
                        block(customer);
                        break;
                    case 5 :
                        flag=false;
                       break;
                }
            }


        }
        else {
            System.out.println("Invalid Credentials...!");
        }
    }



    private void createGiftCard(Customer customer) {

        Giftcard card = model.createGiftCard(customer);

        System.out.println("Your Gift Card Number : "+card.getGiftCardNo());
        System.out.println("Your pin Number : "+card.getPinNo());

        System.out.println("Gift Card Generated Successfully...!");

    }

    private void topup(Customer customer) {

        System.out.println("Enter the Gift card number : ");
        int cardNo = sc.nextInt();

        System.out.println("Enter the pin number : ");
        int pinNo = sc.nextInt();

        Giftcard card = model.getGiftCard(customer.getGiftCardList(),cardNo,pinNo);

        if(card==null)
        {
            System.out.println("No such Gift card created by customer..!");
            return;
        }

        if(!card.isActive())
        {
            System.out.println("Gift Card is Blocked..!");
            return;
        }

        System.out.println("Enter the amount to be topup : ");
        double amt = sc.nextDouble();

        if(customer.getAccBalance()<amt)
        {
            System.out.println("Required Amount Not available...!");
            return;
        }
         double totAmt = customer.getAccBalance() - amt;

         customer.setAccBalance(totAmt);

         double cardAmt = card.getAmount() + amt;

         card.setAmount(cardAmt);

         Topup topup = new Topup(amt,card.getGiftCardNo(),"Top Up");

         card.getHistory().add(topup);

        System.out.println("Main Account Balance : "+customer.getAccBalance());
        System.out.println("Card Amount  : "+card.getAmount());


        System.out.println("Top Up successfully...!");
    }

    private void transaction(Customer customer) {

        System.out.println("Enter the Gift card Number : ");
        int giftCardNo = sc.nextInt();

        System.out.println("Enter the Pin No : ");
        int pinNo =sc.nextInt();

        Giftcard card = model.getGiftCard(customer.getGiftCardList(),giftCardNo,pinNo);

        displayTransaction(card.getHistory());

    }

    private void displayTransaction(List<Topup> history) {

        System.out.println("Card Transation History...!");
        for(Topup topup : history)
        {
            System.out.println("Id : "+topup.getTopupId()+" Amount: "+topup.getTopupAmt()+" Activity : "+topup.getType());
        }

    }

    private void block(Customer customer) {

        System.out.println("Enter the Giftcard Number : ");
        int cardNo = sc.nextInt();

        System.out.println("Enter the Card Pin : ");
        int pinNo = sc.nextInt();

        Giftcard card = model.getGiftCard(customer.getGiftCardList(),cardNo,pinNo);

        card.setActive(false);

        double amt = customer.getAccBalance() + card.getAmount();

        customer.setAccBalance(amt);

        System.out.println("Card Block Successfully...!");
    }

    private void purchase() {

        System.out.println("Enter the card number : ");
        int cardNo = sc.nextInt();

        System.out.println("Enter the pin number : ");
        int pinNo = sc.nextInt();

        Giftcard card = model.getGiftCard(repository.giftcardList,cardNo,pinNo);

        if(card==null)
        {
            System.out.println("No such card created by Customer..>!");
            return;
        }

        if(!card.isActive())
        {
            System.out.println("Card is Blocked..!");
            return;
        }

        System.out.println("Amount in the Card : "+card.getAmount());

        System.out.println("Enter the Amount you want : ");
        double amt = sc.nextDouble();

        if(amt>card.getAmount())
        {
            System.out.println("Required amount not found...!");
            return;
        }

        Customer customer = model.getCustomerById(card.getCustomerId());

        int count = ((int)(customer.getPurchaseAmt()+amt)/100)+customer.getReedemPoints();

        double rem = (int)amt%100;

        customer.setPurchaseAmt(rem);

        customer.setReedemPoints(count);

        if(customer.getReedemPoints()==10)
        {
            double amts = customer.getAccBalance()+10;
            customer.setAccBalance(amts);
            customer.setReedemPoints(0);
        }

        double cardbal = card.getAmount()-amt;

        card.setAmount(cardbal);

        Topup topup = new Topup(amt,cardNo,"Purchase");

        card.getHistory().add(topup);

        System.out.println("Purchased Amount : "+amt);
        System.out.println("Available Card balance : "+card.getAmount());
        System.out.println("Account Balance : "+customer.getAccBalance());
        System.out.println("Amount purchased successfully..>!");
    }



}
