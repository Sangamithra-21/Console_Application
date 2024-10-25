package TollPaymentProcessing;

import TollPaymentProcessing.TollSystem.TollView;
import TollPaymentProcessing.TollSystem.TollViewModel;

public class Main {
    public static void main(String[] args) {

        TollView view = new TollView();
        TollViewModel model = new TollViewModel(view);

        view.addTolls();

        view.run();


    }
}
