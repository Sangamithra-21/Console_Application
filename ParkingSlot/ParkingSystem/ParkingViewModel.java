package ParkingSlot.ParkingSystem;

import ParkingSlot.DTO.Plot;
import ParkingSlot.DTO.Slot;
import ParkingSlot.DTO.Ticket;
import ParkingSlot.DTO.Vehicle;
import ParkingSlot.Repository.Repository;

import java.util.ArrayList;
import java.util.List;

public class ParkingViewModel {

    private ParkingView view;

    public ParkingViewModel(ParkingView view) {

        this.view = view;
    }

    Repository repository = Repository.getInstance();

    public Slot[][] addPlots(Plot plot,int floor,int slots) {

        Slot[][] plots = plot.getParkingLot();

        for(int i=1;i<=floor;i++)
        {
            for(int j=1;j<=slots;j++)
            {
                if(j==1)
                {
                    plots[i-1][j-1] = new Slot(j,"TRUCK",true,i);
                }
                else if(j==2 || j==3)
                {
                    plots[i-1][j-1] = new Slot(j,"BIKE",true,i);
                }
                else {
                    plots[i-1][j-1] = new Slot(j,"CAR",true,i);
                }
            }
        }

        return plots;

    }

    public Slot allocateSlot(Vehicle vehicle, Plot plot) {

        String vehicleType = vehicle.getVehicleType();

        Slot slot = null;

        if(vehicleType.equals("TRUCK"))
        {
            slot = checkForTruck(plot);
            if(slot==null)
            {
                System.out.println("Parking Lot Full");
                return slot;
            }
        }
        else if(vehicleType.equals("BIKE"))
        {
            slot = checkForBike(plot);
            if(slot==null)
            {
                System.out.println("Parking lot Full");
                return slot;
            }
        }
        else if(vehicleType.equals("CAR"))
        {
            slot = checkForCar(plot);
            if(slot==null)
            {
                System.out.println("Parking lot Full");
                return slot;
            }
        }

        slot.setVehicle(vehicle);

        return slot;
    }

    private Slot checkForCar(Plot plot) {

        Slot[][] slots = plot.getParkingLot();

        int floors = slots.length;
        int slotSize = slots[0].length;

        for(int i=1;i<=floors;i++)
        {
            for(int j=3;j<slotSize;j++)
            {
                if(slots[i-1][j].isFree())
                {
                    return slots[i-1][j];
                }
            }
        }
        return null;
    }

    private Slot checkForBike(Plot plot) {

        Slot[][] slots = plot.getParkingLot();

        int floors = slots.length;

        for(int i=1;i<=floors;i++)
        {
            if(slots[i-1][1].isFree())
            {
                return slots[i-1][1];
            }
            else if(slots[i-1][2].isFree())
            {
                return slots[i-1][2];
            }
        }
        return null;
    }

    private Slot checkForTruck(Plot plot) {

        Slot[][] slots = plot.getParkingLot();

        int floors = slots.length;

        for(int i=1;i<=floors;i++)
        {
            if(slots[i-1][0].isFree())
            {
                return slots[i-1][0];
            }
        }
        return null;

    }

    public Plot getPlot(String parkingLot) {

        for(Plot plot :repository.plotList)
        {
            if(plot.getPlotName().equals(parkingLot))
            {
                return plot;
            }
        }
        return null;
    }


    public void getRemoveTicket(Plot plot, String ticketId) {

        for(Ticket ticket : plot.getTicketList())
        {
            if(ticket.getTicketId().equals(ticketId))
            {
                plot.getTicketList().remove(ticket);
                return;
            }
        }
        return;
    }

    public List<Integer> getFreeSlotForVehicle(Plot plot, int floor, String vehicleName) {

        List<Integer> freeSlots = new ArrayList<>();

        Slot[][] slot = plot.getParkingLot();
        int slots = slot[0].length;

        for(int i=0;i<slots;i++)
        {
            if(slot[floor-1][i].isFree() && slot[floor-1][i].getVehicleType().equals(vehicleName))
            {
                freeSlots.add(i+1);

            }
        }

        return freeSlots;
    }

    public Ticket getTicket(String ticketId) {

        for(Ticket ticket : repository.ticketList)
        {
            if(ticket.getTicketId().equals(ticketId))
            {
                return ticket;
            }
        }
        return null;
    }
}
