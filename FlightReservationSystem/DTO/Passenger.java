package FlightReservationSystem.DTO;

public class Passenger {

    static int pid=1;
    public int passengerId;
    public String passengerName;
    public String flightName;
    public int seatNo;
    public int ticketCost;

    public Passenger(String name,String fname,int seatNo,int ticketCost)
    {
        this.passengerId = pid++;
        this.passengerName = name;
        this.flightName = fname;
        this.seatNo = seatNo;
    }

}
