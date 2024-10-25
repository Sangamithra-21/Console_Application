package TollPaymentProcessing.TollSystem;

import TollPaymentProcessing.DTO.Journey;
import TollPaymentProcessing.DTO.TollGate;
import TollPaymentProcessing.DTO.TollPay;
import TollPaymentProcessing.DTO.Vehicle;
import TollPaymentProcessing.Repository.Repository;

public class TollViewModel {

    private TollView view;

    public TollViewModel(TollView view) {

        this.view = view;
    }

    Repository repository = Repository.getInstance();

    public TollGate addTollGates(char ch) {

        TollGate gate = new TollGate(Character.toString(ch));

        return gate;

    }

    public Vehicle getVehicleById(int vehicleId) {

        for(Vehicle vehicle : repository.vehicleList)
        {
            if(vehicle.getVehicleId()==vehicleId)
            {
                return vehicle;
            }
        }
        return null;
    }


    public String findShortestPath(String source, String destination) {

        StringBuilder path1 = new StringBuilder();

        int sourceId = (source.charAt(0)%65);
        int destId  =(destination.charAt(0)%65);

        int i=sourceId;
        while(i!=destId)
        {
            path1.append((char)(i+65));
            i++;
        }

        StringBuilder path2 = new StringBuilder();

        int n = repository.tollGates.length;

        int j=sourceId;
        while(j!=destId)
        {
            path2.append((char)(j+65));
            j = (j-1+n)%n;
        }

        if(path1.length()<path2.length())
        {
            return path1.toString();
        }
        return path2.toString();
    }

    public double findCostForPath(String shorteshPath, Vehicle vehicle, Journey journey) {

        double cost = 0;

        for(char ch : shorteshPath.toCharArray())
        {
            TollGate gate = repository.tollGates[ch%65];

            double tolCost = 0;
            if(vehicle.isVIP())
            {
                tolCost = gate.getTollCost() - (gate.getTollCost()*0.2);
            }
            else{
                tolCost = gate.getTollCost();
            }

            TollPay pay = new TollPay(vehicle.getVehicleId(),tolCost,Character.toString(ch));

            journey.tollPayList.add(pay);

            gate.tollPayList.add(pay);

            gate.vehicleList.add(vehicle);

            gate.setTotalCostEarned(gate.getTotalCostEarned()+tolCost);

            cost += tolCost;

        }
        return cost;
    }
}
