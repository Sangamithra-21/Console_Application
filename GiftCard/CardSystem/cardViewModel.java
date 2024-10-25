package GiftCard.CardSystem;

import GiftCard.DTO.Customer;
import GiftCard.DTO.Giftcard;
import GiftCard.Repository.Repository;

import java.util.Arrays;
import java.util.List;

public class cardViewModel {

    private cardView view;

    public cardViewModel(cardView view) {

        this.view = view;
    }

    Repository repository = Repository.getInstance();

    public Customer addCustomers(String name,String password) {

        Customer customer  = new Customer(name,password);

        repository.customerList.add(customer);

        return customer;
    }

    public String encryptPassword(String pass) {

        char[] passArr = pass.toCharArray();

        for(int i=0;i<pass.length();i++)
        {
            if(passArr[i]=='Z')
            {
                passArr[i]='A';
            }
            else if(passArr[i]=='z')
            {
                passArr[i]='a';
            }
            else
            {
                passArr[i] += 1;
            }
        }

        return Arrays.toString(passArr);
    }

    public Customer getCustomer(String name, String password) {

        for(Customer customer : repository.customerList)
        {
            if(customer.getCustomerName().equals(name) && customer.getPassword().equals(password))
            {
                return customer;
            }
        }
        return null;
    }

    public Giftcard createGiftCard(Customer customer) {

        Giftcard card = new Giftcard(customer.getCustomerId());

        repository.giftcardList.add(card);

        customer.getGiftCardList().add(card);

        return card;

    }

    public Giftcard getGiftCard(List<Giftcard> cardList, int cardNo,int pinNo) {

        for(Giftcard card : cardList)
        {
            if(card.getGiftCardNo()==cardNo && card.getPinNo()==pinNo)
            {
                return card;
            }
        }
        return null;
    }

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
}
