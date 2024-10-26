package RailwayReservationSystem1;

import RailwayReservationSystem1.RailwaySystem.SystemView;
import RailwayReservationSystem1.RailwaySystem.SystemViewModel;
import RailwayReservationSystem1.Repository.Repository;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

       SystemView view = new SystemView();
        SystemViewModel model = new SystemViewModel(view);

        view.run();
    }
}
