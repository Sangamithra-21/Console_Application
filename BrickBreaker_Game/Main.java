package BrickBreaker_Game;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {

         Brickbreaker brickBreaker = new Brickbreaker(7,7);

         brickBreaker.placeBricks(2,2,2);
         brickBreaker.placeBricks(2,3,2);
         brickBreaker.placeBricks(2,4,2);
         brickBreaker.placeBricks(3,2,2);
         brickBreaker.placeBricks(3,3,2);
         brickBreaker.placeBricks(3,4,2);

         while(true)
         {
             brickBreaker.printGameBoard();

             if(brickBreaker.getBallLife()<=0)
             {
                 System.out.println("Ball Life Over...!");
                 System.exit(0);
             }

             System.out.println("Enter the Direction [L | R | S] : ");
             String direction = new Scanner(System.in).next();

             switch(direction)
             {
                 case "L" :
                     int[] ballPos1 = brickBreaker.getBallPos();
                     brickBreaker.initiateBall(ballPos1[0],ballPos1[1],-1,-1);
                     break;
                 case "R" :
                     int[] ballPos2 = brickBreaker.getBallPos();
                     brickBreaker.initiateBall(ballPos2[0],ballPos2[1],-1,1);
                     break;
                 case "S" :
                     int[] ballPos3 = brickBreaker.getBallPos();
                     brickBreaker.initiateBall(ballPos3[0],ballPos3[1],-1,0);
                     break;
                 default :
                     System.out.println("Choose Valid Position");


             }
         }


    }
}
