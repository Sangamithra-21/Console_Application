package Liftsystem;

public class Lift {

    public static int[] liftPosition;
    public static char[] liftDirection;
    public static int[] noOfPeoples;

    public static Lift lift;

    private Lift()
    {
        liftPosition = new int[6];
        liftDirection = new char[6];
        initializeDirection();
        noOfPeoples = new int[]{0, 5, 10, 5, 10, 10};


    }


    private void initializeDirection() {

        for(int i=0;i<liftDirection.length;i++)
        {
            liftDirection[i] = 'U';
        }
    }

    public static Lift getInstance()
    {
        if(lift==null)
        {
            lift = new Lift();
            return lift;
        }
        return lift;
    }

}
