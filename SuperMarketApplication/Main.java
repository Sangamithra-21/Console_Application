package SuperMarketApplication;

import SuperMarketApplication.SuperMarket.MarketView;
import SuperMarketApplication.SuperMarket.MarketViewModel;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        MarketView view = new MarketView();
        MarketViewModel model = new MarketViewModel(view);

        Scanner sc = new Scanner(System.in);

        boolean f1=true;

        while(f1)
        {
            System.out.println("1)Signup 2)Login 3)Exit");
            int choice1 = sc.nextInt();

            switch (choice1) {
                case 1: {
                    boolean f2 = true;
                    while (f2) {
                        System.out.println("1)Seller 2)Buyer 3)Login");
                        int choice2 = sc.nextInt();
                        switch (choice2) {
                            case 1: {
                                view.addSeller();
                                break;
                            }
                            case 2: {
                                view.addBuyer();
                                break;
                            }
                            case 3: {
                                f2 = false;
                                break;
                            }

                        }
                    }
                }
                break;
                case 2 : {
                    boolean f3 = true;
                    while(f3) {
                        System.out.println("1)Seller 2)Buyer 3)Signup");
                        int choice3 = sc.nextInt();
                        switch(choice3) {
                            case 1 : {
                                view.verifySeller();
                                break;
                            }
                            case 2 : {
                                view.verifyBuyer();
                                break;
                            }
                            case 3 : {
                                f3 = false;
                                break;
                            }
                        }
                    }
                }
                break;
                case 3 : {
                    f1 = false;
                    break;
                }
            }
        }

    }
}
