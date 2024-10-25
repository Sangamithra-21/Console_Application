package TollPaymentProcessing.TollSystem;

import TollPaymentProcessing.DTO.Journey;
import TollPaymentProcessing.DTO.TollGate;
import TollPaymentProcessing.DTO.TollPay;
import TollPaymentProcessing.DTO.Vehicle;
import TollPaymentProcessing.Repository.Repository;

import java.util.Scanner;

public class TollView {

    private TollViewModel model;

    public TollView()
    {
        this.model = new TollViewModel(this);
    }

    Repository repository = Repository.getInstance();

    Scanner sc = repository.sc;

    public void addTolls() {

        System.out.println("Enter the Number of Tolls : ");
        int n = sc.nextInt();

        repository.tollGates = new TollGate[n];

        for(int i=0;i<n;i++)
        {
            TollGate gate = model.addTollGates((char)(i+65));
            repository.tollGates[i] = gate;
        }

        System.out.println("Toll Gates Added Successfully...!");
    }


    public void run() {

        boolean flag = true;
        while(flag)
        {
            System.out.println("Toll Payment System");
            System.out.println("1)Add Vehicle");
            System.out.println("2)Display Journey Cost");
            System.out.println("3)Display Toll Details");
            System.out.println("4)Display Vehicle Details");
            System.out.println("5)Exit");

            System.out.println("Enter the option : ");
            int choice = sc.nextInt();

            switch(choice)
            {
                case 1 :
                    addVehicle();
                    break;
                case 2 :
                    displayJourneyCost();
                    break;
                case 3 :
                    displayTollDetails();
                    break;
                case 4 :
                    displayVehicleDetails();
                    break;
                case 5 :
                    flag=false;
                    break;

            }
        }


    }


    private void addVehicle() {

        System.out.println("Enter the type of User [1 for VIP | 0 to Normal user]:");
        int user = sc.nextInt();

        Vehicle vehicle;
        if(user==1)
        {
            vehicle = new Vehicle(true);
        }
        else {
            vehicle = new Vehicle(false);
        }



        repository.vehicleList.add(vehicle);

        System.out.println("Vehicle Id : "+vehicle.getVehicleId());

        System.out.println("Vehicle added successfully...!");

    }

    private void displayJourneyCost() {

        System.out.println("Enter the Vehicle Id : ");
        int vehicleId = sc.nextInt();

        Vehicle vehicle = model.getVehicleById(vehicleId);

        if(vehicle==null)
        {
            System.out.println("No Such Vehicle Found..>!");
            return;
        }

        System.out.println("Enter the source : ");
        String source = sc.next();

        System.out.println("Enter the destination : ");
        String destination = sc.next();

        String shorteshPath = model.findShortestPath(source,destination);

        System.out.println("Shortest Path : "+shorteshPath);

        Journey journey = new Journey(source,destination,vehicleId);

        double cost = model.findCostForPath(shorteshPath,vehicle,journey);

        journey.setJourneyCost(cost);

        vehicle.getJourneyList().add(journey);

        vehicle.setTotCost(vehicle.getTotCost()+cost);

        displayJourney(journey);


    }

    private void displayJourney(Journey journey) {

        System.out.println("Journey Details...!");
        System.out.println("Journey Id   : "+journey.getJourneyId());
        System.out.println("Source       : "+journey.getStart());
        System.out.println("Destination  : "+journey.getEnd());
        System.out.println("Journey Cost : "+journey.getJourneyCost());
    }


    private void displayTollDetails() {

        for(TollGate tollGate : repository.tollGates)
        {
            System.out.println("Toll Gate Id     : "+tollGate.getTollNo());
            System.out.println("Toll Gate Name   : "+tollGate.getTollName());
            if(!tollGate.vehicleList.isEmpty()) {
                System.out.println("Vechicles Passed : " + tollGate.vehicleList.size());
                for (TollPay tollpay : tollGate.tollPayList) {
                    System.out.println("Vehicle Id   : " + tollpay.getVehicleId());
                    System.out.println("Vechile Paid : " + tollpay.getAmt());
                }
                System.out.println("Total Earning    : " + tollGate.getTotalCostEarned());
            }
            else {
                System.out.println("No Vehicle crossed this TollGate");
            }
            System.out.println("----------------------------------------------------");
        }
    }

    private void displayVehicleDetails() {

        for(Vehicle vehicle : repository.vehicleList)
        {
            System.out.println("Vehicle Id : "+vehicle.getVehicleId());
            for(Journey journey : vehicle.getJourneyList())
            {
                displayJourney(journey);
                for(TollPay tollPay : journey.tollPayList)
                {
                    System.out.print("TollGate Name : "+tollPay.getTollGateName()+"    ");
                    System.out.println("Amount Paid   : "+tollPay.getAmt());
                }
                System.out.println("Total Amount spend for the Journey : "+journey.getJourneyCost());
                System.out.println("---------------------------------------------");
            }
            System.out.println("Total Amount spend by the vehicle : "+vehicle.getTotCost());
            System.out.println("---------------------------------------------");
        }
    }


}
