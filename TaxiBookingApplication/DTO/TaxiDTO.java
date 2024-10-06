package TaxiBookingApplication.DTO;

import java.util.ArrayList;

public class TaxiDTO {

    public static int[] TaxiEarning;

    public static char[][] taxi;
    public static boolean[][] isAvailable;
    public static TaxiDTO taxiDto ;
    public static  ArrayList<BookDTO>[] bookArray;


    private TaxiDTO()
    {
        bookArray = new ArrayList[5];

        for (int i = 0; i < bookArray.length; i++) {
            bookArray[i] = new ArrayList<>();
        }
        TaxiEarning = new int[5];
        taxi = new char[5][25];
        fillSource(taxi);
        isAvailable = new boolean[5][25];
        fillAvailability(isAvailable);

    }
    public static TaxiDTO getInstance(){
        if(taxiDto == null){
            taxiDto = new TaxiDTO();
        }
        return taxiDto;
    }

    private void fillAvailability(boolean[][] isAvailable) {

        for(int i=0;i<isAvailable.length;i++)
        {
            for(int j=0;j<isAvailable[0].length;j++)
            {
                isAvailable[i][j] = true;
            }
        }
    }

    private void fillSource(char[][] taxi) {

        for(int i=0;i<taxi.length;i++)
        {
            for(int j=0;j<25;j++)
            {
                taxi[i][j] = 'A';
            }
        }
    }

}
