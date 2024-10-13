package Minesweeper_Game;

import java.util.*;

public class Minesweeper_console {


    char[][] board;
    int row;
    int col;

    Scanner sc = new Scanner(System.in);
    List<int[]> mines = new ArrayList<>();


    int totalCount = 0;
    boolean gameOver = true;
    Minesweeper_console(int row,int col)
    {
        this.row = row;
        this.col = col;
        board = new char[row][col];
        initialize();
        setMines();
        playMineGame();

    }

    private void playMineGame() {

        while(gameOver)
        {
            System.out.println("Enter the row && column : ");
            int rowPos = sc.nextInt();
            int colPos = sc.nextInt();
            if(isMine(rowPos,colPos))
            {
                showMines();
                gameOver = false;
                System.out.println("Game Over");
                return;
            }
            else {
                checkMines(rowPos,colPos);
                displayMineBoard();
            }

        }
    }

    private boolean isMine(int rowPos, int colPos) {
        for(int[] mineList : mines)
        {
            if(mineList[0]==rowPos && mineList[1]==colPos)
            {
                return true;
            }
        }
        return false;
    }

    private void checkMines(int rowPos, int colPos) {
        if(rowPos<0 || rowPos>=row || colPos<0 || colPos>=col || board[rowPos][colPos]!='-')
        {
            return;
        }
        int mineCount = 0;

        totalCount++;

        // top 3
        mineCount += countMine(rowPos-1,colPos-1);
        mineCount += countMine(rowPos-1,colPos);
        mineCount += countMine(rowPos-1,colPos+1);

        // left and right
        mineCount += countMine(rowPos,colPos-1);
        mineCount += countMine(rowPos,colPos+1);

        // bottom 3
        mineCount += countMine(rowPos+1,colPos-1);
        mineCount += countMine(rowPos+1,colPos);
        mineCount += countMine(rowPos+1,colPos+1);

        if(mineCount>0)
        {
            board[rowPos][colPos] = (char)(mineCount+'0');
        }
        else {

            board[rowPos][colPos] = '*';
            // top 3
            checkMines(rowPos-1,colPos-1);
            checkMines(rowPos-1,colPos);
            checkMines(rowPos-1,colPos+1);

            //left and right
            checkMines(rowPos,colPos-1);
            checkMines(rowPos,colPos+1);

            // bottom 3
            checkMines(rowPos+1,colPos-1);
            checkMines(rowPos+1,colPos);
            checkMines(rowPos+1,colPos+1);

        }

        if(totalCount == row*col-mines.size())
        {
            gameOver = false;
            System.out.println("Mines cleared");
        }

    }

    private int countMine(int rowPos, int colPos) {
        if(rowPos<0 || rowPos>=row || col<0 || colPos>=col)
        {
            return 0;
        }
        else if(isMine(rowPos,colPos))
        {
            return 1;
        }
        return 0;
    }

    private void showMines() {

         for(int[] mine : mines)
         {
             board[mine[0]][mine[1]]='M';
         }
         displayMineBoard();
    }

    private void displayMineBoard() {
        for(char[] a : board)
        {
            for(char b : a)
            {
                System.out.print(b+" ");
            }
            System.out.println();
        }
    }

    private void setMines() {

        int minesCount = 10;

        Random random = new Random();
        while(minesCount>0)
        {
            int rowNo = random.nextInt(row);
            int colNo = random.nextInt(col);
            if(!isMine(rowNo,colNo))
            {
                mines.add(new int[]{rowNo,colNo});
                minesCount--;
            }
        }
    }


    private void initialize()
    {
        for(char[] a : board)
        {
            Arrays.fill(a,'-');
        }
    }
}
