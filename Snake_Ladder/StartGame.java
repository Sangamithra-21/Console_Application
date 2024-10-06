package Snake_Ladder;

import java.util.*;

public class StartGame {
    static int[][] board = new int[5][5];
    static Map<Integer,Integer> map = new HashMap<>();
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        initiateBoard();

        putSnakeAndLadders(map);

        System.out.println("Enter the Number of Players : ");
        int playersCount = sc.nextInt();

        List<Character> playersId = new ArrayList<>();
        List<String> playerName = new ArrayList<>();

        for(int i=1;i<=playersCount;i++)
        {
            System.out.println("Enter the Player "+i+" Name : ");
            String name = sc.next();
            playersId.add(name.charAt(0));
            playerName.add(name);
        }

        int[] position = new int[playersId.size()];
        System.out.println("Let's start a Game...!");

        playGame(playersId,playerName,position);


    }

    private static void playGame(List<Character> playersId,List<String> playerName,int[] position) {

        int n = position.length;

        String currentPlayer = playerName.get(0);


        int i=0;
        while(true)
        {
            System.out.println(currentPlayer+"'s Turn : (Enter--> r) ");
            Scanner sc = new Scanner(System.in);
            String command = sc.next();
            if(command.equals("r")) {
                int getDiceValue = getRandomValue();

                System.out.println(currentPlayer + " rolled Value : " + getDiceValue);
                int pos = i % n;

                int rolledValue = position[pos] + getDiceValue;
                if (position[pos] > 1 && rolledValue <= 25) {
                    position[pos] = rolledValue;
                }
                if (getDiceValue == 1 || getDiceValue == 6) {
                    System.out.println(currentPlayer + "roll again");
                    continue;
                }

                if (map.containsKey(rolledValue)) {
                    position[pos] = map.get(rolledValue);
                }
                if (position[pos] == 25) {
                    System.out.println(playerName.get(pos) + " won the Game...!");
                    break;
                }
                i++;

                System.out.println("Current Position :" + rolledValue);
                currentPlayer = playerName.get(i%n);
            }else {
                System.out.println("Try Valid Option..!");
            }
        }

    }

    private static int getRandomValue() {

        Random r = new Random();
        int value=r.nextInt(6);
        return value;
    }

    private static void putSnakeAndLadders(Map<Integer, Integer> map) {
        map.put(24,18);
        map.put(21,2);
        map.put(18,23);
        map.put(14,5);
        map.put(4,12);
        map.put(6,15);

    }

    private static void initiateBoard() {

        int val = 1;

        for(int i=board.length-1;i>=0;i--)
        {
            if(i%2!=0)
            {
                for(int j=0;j<board[0].length;j++)
                {
                    board[i][j]=val++;
                }
            }
            else {
                for(int j=board[0].length-1;j>=0;j--)
                {
                    board[i][j]=val++;
                }
            }
        }
    }

    private static void displayBoard()
    {
        for(int i=0;i<board.length;i++)
        {
            for(int j=0;j<board[0].length;j++)
            {
                if(board[i][j]>=1 || board[i][j]<=10)
                {
                    System.out.print(board[i][j]+"  ");
                }else {
                    System.out.print(board[i][j] + " ");
                }
            }
            System.out.println();
        }
    }
}
