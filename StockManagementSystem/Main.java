package StockManagementSystem;

import StockManagementSystem.System.StockView;
import StockManagementSystem.System.StockViewModel;

public class Main {
    public static void main(String[] args) {

        StockView view = new StockView();
        StockViewModel model = new StockViewModel(view);

        view.run();
    }
}
