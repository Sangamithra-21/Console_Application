package BankManagementSystem;

import BankManagementSystem.System.SystemView;
import BankManagementSystem.System.SystemViewModel;

public class Main {
    public static void main(String[] args) {

        SystemView view = new SystemView();

        SystemViewModel model = new SystemViewModel(view);

        view.run();
    }
}
