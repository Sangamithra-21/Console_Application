package FlightReservationSystem.DTO;

import java.util.ArrayList;
import java.util.List;

public class Flight {

    static int id=1;
    int flightNo;
    public String flightName;
    public int capacity;
    public int cost;
    public int[] seats;

    public List<Passenger> passengerList;

    public Flight(String flightName,int capacity,int cost)
    {
        this.flightNo = id++;
        this.flightName = flightName;
        this.cost = cost;
        this.capacity = capacity;
        this.passengerList = new ArrayList<>();
        this.seats = new int[capacity];
    }

    public String toString() {
        return "Flight[" +
                "flightNo=" + flightNo +
                ", flightName='" + flightName + '\'' +
                ", seatsAvailable=" + capacity +
                ", Current Ticket cost=" + cost +
                ", PassengerCount=" + passengerList.size() +
                ']';
    }


}
