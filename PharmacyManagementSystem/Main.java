package PharmacyManagementSystem;

import PharmacyManagementSystem.Pharmacy.PharmacyView;
import PharmacyManagementSystem.Pharmacy.PharmacyViewModel;

public class Main {
    public static void main(String[] args) {

        PharmacyView view = new PharmacyView();
        PharmacyViewModel model = new PharmacyViewModel(view);

        view.run();

    }
}
