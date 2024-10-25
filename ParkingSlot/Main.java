package ParkingSlot;

import ParkingSlot.ParkingSystem.ParkingView;
import ParkingSlot.ParkingSystem.ParkingViewModel;

public class Main {

    public static void main(String[] args) {

        ParkingView view = new ParkingView();

        ParkingViewModel model = new ParkingViewModel(view);

        view.run();


    }
}
