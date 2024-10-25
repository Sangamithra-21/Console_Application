package ParkingSlot.ParkingSystem;

import ParkingSlot.DTO.Plot;
import ParkingSlot.DTO.Slot;
import ParkingSlot.DTO.Ticket;
import ParkingSlot.DTO.Vehicle;
import ParkingSlot.Repository.Repository;

import java.util.List;
import java.util.Scanner;

public class ParkingView {

    private ParkingViewModel model;

    public ParkingView()
    {
        this.model = new ParkingViewModel(this);
    }


    Repository repository = Repository.getInstance();

    Scanner sc = repository.sc;

    public void run()
    {
        while(true)
        {
            System.out.println("Parking Lot System ");

            System.out.println("1)Create Parking Plot");
            System.out.println("2)Park Vehicle");
            System.out.println("3)Unpark Vehicle");
            System.out.println("4)Display Free count of slots for vehicle");
            System.out.println("5)Display Free slots for vehicle");
            System.out.println("6)Display Occupied slot");
            System.out.println("7)Exit");

            System.out.println("Enter the Option : ");
            int choice = sc.nextInt();

            switch(choice)
            {
                case 1 :
                    createPlot();
                    break;
                case 2 :
                    parkVehicle();
                    break;
                case 3 :
                    unparkVehicle();
                    break;
                case 4 :
                    displayFreeCount();
                    break;
                case 5 :
                    displayAllFreeList();
                    break;
                case 6 :
                    displayOccupiedSlots();
                    break;
                case 7 :
                    return;

            }
        }
    }



    // 1)Creating Plot
    private void createPlot() {

        System.out.println("Enter the parking lot Name : ");
        String parkingLot = sc.next();

        System.out.println("Enter the Number of Floors : ");
        int floors = sc.nextInt();

        System.out.println("Enter the Number of Slots : ");
        int slots = sc.nextInt();

        Plot plot = new Plot(parkingLot,floors,slots);

        repository.plotList.add(plot);

        plot.setParkingLot(model.addPlots(plot,floors,slots));

        System.out.println("Created Parking lot with "+floors+" floors and "+slots+" slots per floor");

    }

    // 2)Park Vehicle

    private void parkVehicle()
    {
        System.out.println("Enter the parking lot Name : ");
        String parkingLot = sc.next();

        Plot plot = model.getPlot(parkingLot);

        if(plot==null)
        {
            System.out.println("No such parking lot Available...!");
            return;
        }

        System.out.println("Enter the Vehicle Type [CAR | TRUCK | BIKE] : ");
        String vehicleName = sc.next();

        if(!(vehicleName.equals("CAR") || vehicleName.equals("TRUCK") || vehicleName.equals("BIKE")))
        {
            System.out.println("No Such vehicle is Allowed...!");
            return;
        }

        System.out.println("Enter the Registration Number : ");
        String regNo = sc.next();

        System.out.println("Enter the color of the vehicle : ");
        String color = sc.next();

        Vehicle vehicle = new Vehicle(vehicleName,regNo,color);

        Slot slot = model.allocateSlot(vehicle,plot);

        if(slot==null)
        {
            return;
        }

        slot.setFree(false);

        slot.setVehicle(vehicle);

        Ticket ticket = new Ticket(plot.getPlotName()+"_"+slot.getFloorNo()+"_"+(slot.getSlotNo()));

        repository.ticketList.add(ticket);

        plot.getTicketList().add(ticket);

        System.out.println("Parked Vehicle : "+ticket.getTicketId());

    }

    // 3) Unpark vehicle

    private void unparkVehicle()
    {


        System.out.println("Enter the Ticket Id : ");
        String ticketId = sc.next();

        Ticket ticket = model.getTicket(ticketId);

        if(ticket==null)
        {
            System.out.println("Invalid Ticket");
            return;
        }

        String[] arr = ticketId.split("_");

        Plot plot = model.getPlot(arr[0]);

        if(plot==null)
        {
            System.out.println("No such plot available");
            return;
        }


        int floorNo = Integer.parseInt(arr[1]);
        int slotNo = Integer.parseInt(arr[2]);


        Slot[][] parkLot = plot.getParkingLot();

        Slot slot = parkLot[floorNo-1][slotNo-1] ;

        slot.setFree(true);

        Vehicle vehicle = slot.getVehicle();

        slot.setVehicle(null);

        model.getRemoveTicket(plot,ticketId);

        repository.ticketList.remove(ticket);

        System.out.println("Unparked Vehicle With Registration Number : "+vehicle.getRegNo() + " and Color : "+vehicle.getColor());

    }

    // 4) Display free count

    private void displayFreeCount()
    {
        System.out.println("Enter the parking lot Id : ");
        String plotname = sc.next();

        Plot plot = model.getPlot(plotname);

        if(plot==null)
        {
            System.out.println("No such parking lot Available...!");
            return;
        }

        System.out.println("Enter the Vehicle Type : ");
        String vehicleName = sc.next();

        if(!(vehicleName.equals("CAR") || vehicleName.equals("TRUCK") || vehicleName.equals("BIKE")))
        {
            System.out.println("No Such vehicle is Allowed...!");
            return;
        }

        Slot[][] slots = plot.getParkingLot();

        for(int i=1;i<=slots.length;i++)
        {
            int freeSlots = model.getFreeSlotForVehicle(plot,i,vehicleName).size();
            System.out.println("Number of Free Slots Available for "+vehicleName+" on Floor "+i+" : "+freeSlots);
        }


    }

    // 5) Display free of all floors
    private void displayAllFreeList() {

        System.out.println("Enter the parking lot Id : ");
        String plotname = sc.next();

        Plot plot = model.getPlot(plotname);

        if(plot==null)
        {
            System.out.println("No such parking lot Available...!");
            return;
        }

        System.out.println("Enter the Vehicle Type : ");
        String vehicleName = sc.next();

        if(!(vehicleName.equals("CAR") || vehicleName.equals("TRUCK") || vehicleName.equals("BIKE")))
        {
            System.out.println("No Such vehicle is Allowed...!");
            return;
        }


        Slot[][] slots = plot.getParkingLot();

        for(int i=1;i<=slots.length;i++)
        {
            List<Integer> freeSlots = model.getFreeSlotForVehicle(plot,i,vehicleName);
            System.out.println("Number of Free Slots Available for Floor "+i+" : "+freeSlots);
        }

    }

    // 6) Display Free Slots

    private void displayOccupiedSlots()
    {
        System.out.println("Enter the parking lot Name : ");
        String parkingLot = sc.next();


        Plot plot = model.getPlot(parkingLot);

        if(plot==null)
        {
            System.out.println("No such parking lot Available...!");
            return;
        }

        System.out.println("Enter the Vehicle Type : ");
        String vehicleName = sc.next();

        if(!(vehicleName.equals("CAR") || vehicleName.equals("TRUCK") || vehicleName.equals("BIKE")))
        {
            System.out.println("No Such vehicle is Allowed...!");
            return;
        }



        displayOccupiedSlotList(plot,vehicleName);
    }

    private void displayOccupiedSlotList(Plot plot,String vehicleName) {

        Slot[][] slots = plot.getParkingLot();

        int floor = slots.length;
        int slot = slots[0].length;


        for(int i=1;i<=floor;i++)
        {
            System.out.print("Occupied Seats in floor "+i+" : ");
            for(int j=1;j<=slot;j++){

                if(!slots[i-1][j-1].isFree() && slots[i-1][j-1].getVehicleType().equals(vehicleName))
                {
                    System.out.print(j+" ");
                }
            }
            System.out.println();
        }
    }

}
