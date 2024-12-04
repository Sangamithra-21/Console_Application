package BankApplicationUsingFiles;

import BankApplicationUsingFiles.BankSystem.SystemView;
import BankApplicationUsingFiles.BankSystem.SystemViewModel;
import BankApplicationUsingFiles.Repository.Repository;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Currency;

public class Main {
    public static void main(String[] args) throws IOException {

        SystemView view = new SystemView();
        SystemViewModel model = new SystemViewModel(view);

        model.initialize();

        view.run();

    }
}
