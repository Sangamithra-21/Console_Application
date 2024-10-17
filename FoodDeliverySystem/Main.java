package FoodDeliverySystem;

import FoodDeliverySystem.OrderSystem.OrderView;
import FoodDeliverySystem.OrderSystem.OrderViewModel;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        OrderView handler = new OrderView();
        OrderViewModel order = new OrderViewModel(handler);

        order.addRestaurant();

        order.addDrivingAgents();

        Scanner sc = new Scanner(System.in);

        boolean f1 = true;

        while (f1) {
            System.out.println("1)Signup 2)Login 3)Exit");
            int choice1 = sc.nextInt();
            switch (choice1) {
                case 1: {
                    boolean f2 = true;
                    while (f2) {
                        System.out.println("1)Admin 2)Customer 3)Agent 4)Exit");
                        int choice2 = sc.nextInt();
                        switch (choice2) {
                            case 1: {
                                handler.addAdmin();
                                break;
                            }
                            case 2: {
                                handler.addCustomer();
                                break;
                            }
                            case 3: {
                                handler.addAgent();
                                break;
                            }
                            case 4: {
                                f2 = false;
                                break;
                            }


                        }
                    }

                }
                break;
                case 2: {
                    boolean f3 = true;
                    while (f3) {
                        System.out.println("1)Admin 2)Customer 3)Agent 4)Logout");
                        int choice3 = sc.nextInt();
                        switch (choice3) {
                            case 1: {
                                handler.verifyAdmin();
                                break;
                            }
                            case 2: {
                                handler.verifyCustomer();
                                break;
                            }
                            case 3: {
                                handler.verifyAgent();
                                break;
                            }
                            case 4: {
                                f3 = false;
                                break;
                            }
                        }
                    }
                }
                break;
                case 3 :
                {
                    f1=false;
                    break;
                }
            }


        }
    }
}
