package BankApplicationUsingFiles.BankSystem;

import BankApplicationUsingFiles.DTO.Customer;
import BankApplicationUsingFiles.DTO.Transaction;
import BankApplicationUsingFiles.Repository.Repository;

import java.io.*;
import java.util.List;

public class SystemViewModel {

    private SystemView view;

    public SystemViewModel(SystemView systemView) {
        this.view = systemView;
    }

    Repository repository = Repository.getInstance();

    File file = new File("bank_db.txt");

    public void initialize() throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader(file));

        String currLine = reader.readLine();


        while(currLine!=null)
        {
            Customer customer = castToObject(currLine);
            repository.customerList.add(customer);
            currLine = reader.readLine();
        }

        reader.close();
    }

    private Customer castToObject(String currLine) {

        String[] cust = currLine.split("\\s+");

        return new Customer(Integer.parseInt(cust[0]),Integer.parseInt(cust[1]),cust[2],Integer.parseInt(cust[3]),cust[4]);
    }

    public void addCustomer(Customer customer) throws IOException {

        String cust = ObjectToString(customer);

        BufferedWriter writer = new BufferedWriter(new FileWriter(file,true));

        writer.write(cust);

        writer.newLine();

        writer.close();
    }

    public String ObjectToString(Customer customer)
    {
        return customer.getCustId()+" "+customer.getAccId()+" "+customer.getName()+" "+customer.getBalance()+" "+customer.getPassword();
    }

    public String encryptPassword(String password) {

        char[] pass = password.toCharArray();

        for(int i=0;i<password.length();i++)
        {
            if(pass[i]>='A' && pass[i]<='Z')
            {
                pass[i] = (char)((pass[i]-'A'+1)%26 + (int)'A');
            }
            else if(pass[i]>='a' && pass[i]<='z')
            {
                pass[i] = (char)((pass[i]-'a'+1)%26 + (int)'a');
            }
            else if(pass[i]>='0' && pass[i]<='9') {
                pass[i] = (char) ((pass[i]-'0'+1)%10 + (int)'0');
            }
        }

        return String.valueOf(pass);
    }

    public Customer getCustomerById(int id, String password) {

        for(Customer customer : repository.customerList)
        {
            if(customer.getCustId()==id && customer.getPassword().equals(password))
            {
                return customer;
            }
        }
        return null;
    }

    public void finalizee() throws IOException {

        BufferedWriter writer = new BufferedWriter(new FileWriter(file,false));

        for(Customer customer : repository.customerList)
        {
            String cust = ObjectToString(customer);

            writer.write(cust);

            writer.newLine();
        }

        writer.close();

    }

    public Customer getCustomerByCustId(int id) {

        for(Customer customer : repository.customerList)
        {
            if(customer.getCustId()==id)
            {
                return customer;
            }
        }
        return null;
    }

    public void addTransaction(String transaction,File file) throws IOException {

        BufferedWriter writer = new BufferedWriter(new FileWriter(file,true));

        writer.write(transaction);

        writer.newLine();

        writer.close();

    }


    public void sort() {

        List<Customer> customerList = repository.customerList;

        customerList.sort((a,b)->{
            int amt1 = a.getBalance();
            int amt2 = b.getBalance();
            return amt2-amt1;
        });
    }
}
