package TaxiBookingApplication.DTO;

public class BookDTO {

    public int bookingId;
    static int bookId =1;
    public int customerId;

    public char source;
    public char destination;

    public int amount;
    public int pickupTime;
    public int dropTime;


    public BookDTO(char source, char destination, int amount, int pickup,int drop)
    {
        this.bookingId = bookId;
        this.customerId = bookId++;
        this.source = source;
        this.destination = destination;
        this.amount = amount;
        this.pickupTime = pickup;
        this.dropTime = drop;
    }
}
