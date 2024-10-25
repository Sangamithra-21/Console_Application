package GiftCard.Repository;

import GiftCard.DTO.Customer;
import GiftCard.DTO.Giftcard;
import GiftCard.DTO.Topup;

import java.util.ArrayList;
import java.util.Scanner;

import java.util.List;

public class Repository {

    public List<Customer> customerList;
    public List<Giftcard> giftcardList;
    public List<Topup> topup;
    public Scanner sc;

    private Repository()
    {
        this.giftcardList = new ArrayList<>();
        this.topup = new ArrayList<>();
        this.customerList = new ArrayList<>();
        this.sc = new Scanner(System.in);
    }

    private static Repository repository;

    public static Repository getInstance()
    {
        if(repository==null)
        {
            repository = new Repository();
        }
        return repository;
    }



}
