package Snake_Game;

import java.util.*;

public class Snake {

    static char[][] board;
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the Row  : ");
        int row = sc.nextInt();

        System.out.println("Enter the Column :");
        int col = sc.nextInt();

        board = new char[row][col];

        for(char[] b : board)
        {
            Arrays.fill(b,'-');
        }

        fillFruits();

        board[0][0] = '*';

        playGame();




    }

    public static void fillFruits()
    {
        int count = 0;
        Random r = new Random();
        while(count<5)
        {
            int row = r.nextInt(board.length);
            int col = r.nextInt(board[0].length);

            if(board[row][col]=='-')
            {
                board[row][col] = 'F';
                count++;
            }
        }
    }
    public static void displayBoard(Queue<int[]> queue)
    {
        Queue<int[]> q = new LinkedList<>(queue);
        while(!q.isEmpty())
        {
            int[] pos = q.poll();
            board[pos[0]][pos[1]] = '*';
        }
        for(char[] a : board)
        {
            for(char ch : a)
            {
                System.out.print(ch+" ");
            }
            System.out.println();
        }
    }

    public static void playGame()
    {

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0,0});

        int[] currLoc = new int[]{0,0};
        while(true)
        {
            displayBoard(queue);
            System.out.println("Enter Direction : [ U | D | L | R ] ");
            char dir = sc.next().charAt(0);

            int[] pos = getPosition(dir,currLoc);

            if(pos[0]==-1 || pos[1]==-1) {
                System.out.println("Game Over...>!");
                return;
            }
            else
            {
                if(board[pos[0]][pos[1]]=='*')
                {
                    System.out.println("Game Over...>!");
                    return;
                }
                else if(board[pos[0]][pos[1]]!='F')
                {
                    int[] last = queue.poll();
                    board[last[0]][last[1]] = '-';
                    queue.add(pos);
                }
                else {
                    queue.add(pos);
                }

                currLoc = pos;
            }

        }
    }

    public static int[] getPosition(char dir, int[] currLoc)
    {
        int row = currLoc[0];
        int col = currLoc[1];

        if(dir=='U') row -=1;
        else if(dir=='D') row+=1;
        else if(dir=='L') col-=1;
        else col+= 1;

        if(row<0 || row>=board.length || col<0 || col>=board[0].length) return new int[]{-1,-1};

        return new int[]{row,col};

    }

}
