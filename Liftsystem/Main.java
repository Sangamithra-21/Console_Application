package Liftsystem;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

          Scanner sc = new Scanner(System.in);

          boolean flag=true;
          while(flag)
          {
              System.out.println("1)Use Lift 2)Display 3)Make Construction 3)Exit");
              int choice = sc.nextInt();
              switch(choice)
              {
                  case 1:
                      AssignLift assign = new AssignLift();
                      assign.assignLift();
                      break;
                  case 2:
                      AssignLift display = new AssignLift();
                      display.display();
                      break;
                  case 3:
                      AssignLift construction = new AssignLift();
                      construction.markAsNotWorking();
                      break;
                  case 4:
                      flag=false;
                      break;
              }
          }
    }
}
