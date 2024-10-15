package BusReservationSystem.DTO;

import java.util.ArrayList;
import java.util.List;

public class Customer {

    static int cid=1;
    private int customerId;
    private String customerName;
    private String password;
    private String phoneNo;
    List<Ticket> BookingList;

    public Customer(String customerName, String password,String phoneNo) {
        this.customerId = cid++;
        this.customerName = customerName;
        this.password = password;
        this.phoneNo = phoneNo;
        BookingList = new ArrayList<>();
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }


    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Ticket> getBookingList() {
        return BookingList;
    }

    public void setBookingList(List<Ticket> bookingList) {
        BookingList = bookingList;
    }


}
