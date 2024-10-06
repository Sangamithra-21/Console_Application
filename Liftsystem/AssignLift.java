package Liftsystem;

import Liftsystem.Lift;

import java.util.Scanner;


public class AssignLift {

   Lift lift = Lift.getInstance();
   Scanner sc  = new Scanner(System.in);

   int source;
   int destination;

   int nooOfPeople;

   public void assignLift()
   {
       getInput();
       char direction = findDirection();
       int liftNo = findNearestLift(direction);

       if(liftNo==0)
       {
          System.out.println("No possible lift available...!");
          return;
       }

       lift.liftPosition[liftNo]=destination;
       lift.liftDirection[liftNo] = direction;

      System.out.println("lift : "+liftNo+" is assigned...!");
      display();

   }


   private int findNearestLift(char direction) {

      int n = lift.liftDirection.length;
      int stops = Integer.MAX_VALUE;
      int liftNo = 0;
      for(int i=1;i<n;i++)
      {
         if(!checkAllowedLift(i,source,destination))
         {
            continue;
         }
         if(lift.liftPosition[i]==-1)
         {
            continue;
         }
         if(lift.noOfPeoples[i]<nooOfPeople)
         {
            continue;
         }
         //int val = Math.abs(lift.liftPositio[i]-source);
         int val = calculateStops(i,source,destination);
         if(stops>val)
         {
            stops = val;
            liftNo = i;
         }
         else if(stops==val)
         {
            liftNo = checkDirection(direction,liftNo,i);
         }
      }
      return liftNo;
   }

   private int calculateStops(int liftNo, int source, int destination) {

      int liftPosition = lift.liftPosition[liftNo];
      char liftDirection = lift.liftDirection[liftNo];

      int stops ;

      if(liftDirection=='U' && source>liftPosition)
      {
         stops = Math.abs(destination-liftPosition);
      }
      else if(liftDirection=='D' && source<liftPosition)
      {
         stops = Math.abs(destination-liftPosition);
      }
      else {
         stops = Math.abs(destination-source)+Math.abs(source-liftPosition);
      }
      return stops;
   }

   private boolean checkAllowedLift(int lift, int source, int destination) {

      if(lift==1 || lift==2)
      {
         return source>=0 && source<=5 && destination>=0 && destination<=5;
      }
      else if(lift==3 || lift==4)
      {
         return source>=6 && source<=10 && destination>=6 && destination<=10;
      }
      else if(lift==5)
      {
         return source>=0 && source<=10 && destination>=10 && destination<=10;
      }
      return false;

   }

   private int checkDirection(char direction,int lift1,int lift2) {

      if(direction==lift.liftDirection[lift1])
      {
         return lift1;
      }
      return lift2;
   }

   private char findDirection() {

      int val = source-destination;
      if(val<0) return 'U';
      return 'D';
   }

   private void getInput() {

      System.out.println("Enter the source and Destination  : ");
      source = sc.nextInt();
      destination = sc.nextInt();
      System.out.println("Enter the Number of people : ");
      nooOfPeople = sc.nextInt();

   }


   public void display() {
      System.out.println("L1 L2 L3 L4 L5");
      for(int i=1;i<lift.liftPosition.length;i++)
      {
         System.out.print(lift.liftPosition[i]+"  ");
      }
      System.out.println();
   }

   public void markAsNotWorking() {
      System.out.println("Enter the Lift Number : ");
      int liftNo = sc.nextInt();
      lift.liftPosition[liftNo] = -1;
   }
}
