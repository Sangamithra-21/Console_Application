package InvoiceManagementSystem;

import InvoiceManagementSystem.InvoiceSystem.InvoiceView;
import InvoiceManagementSystem.InvoiceSystem.InvoiceViewModel;

public class Main {
    public static void main(String[] args) {

        InvoiceView view = new InvoiceView();
        InvoiceViewModel model = new InvoiceViewModel(view);

        view.run();
    }
}
