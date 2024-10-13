package BrickBreaker_Game;

import java.util.HashMap;
import java.util.Map;

public class Brickbreaker {

    private static String wall = "w";
    private static String ball = "o";
    private static String ground = "g";
    private static String brick = "1";

    private static Map<Integer,Integer> bricksWithLife = new HashMap<>();
    private static int[] ballPos = null;
    private static int ballLife = 5;

    private String[][] gameBoard = null;


    Brickbreaker(int row,int col)
    {
        gameBoard = new String[row][col];
        prepareBoard();
        ballPos = new int[]{row-1,col/2}; // Initial position of Ball
    }

    private void prepareBoard() {

        int m = gameBoard.length;
        int n = gameBoard[0].length;
        for(int i=0;i<m;i++)
        {
            for(int j=0;j<n;j++)
            {
                if(i==0 || j==0 || j==n-1)
                {
                    gameBoard[i][j]=wall;
                }
                else if(i==m-1)
                {
                    gameBoard[i][j]=ground;
                }
                else {
                    gameBoard[i][j]=" ";
                }

            }
        }
        gameBoard[m-1][n/2]=ball;

    }

    public void placeBricks(int row,int col,int life)
    {
        gameBoard[row][col] = brick;
        int exactPosition = getExactBallPosition(row,col);
        bricksWithLife.put(exactPosition,life);
    }

    private int getExactBallPosition(int row, int col) {

        return (row * gameBoard[0].length)+col+1;
    }

    public void printGameBoard() {

        for(String[] a : gameBoard)
        {
            for(String b : a)
            {
                System.out.print(b+" ");
            }
            System.out.println();
        }
    }

    public static int[] getBallPos() {
        return ballPos;
    }

    public static int getBallLife() {
        return ballLife;
    }

    public void initiateBall(int ballRow, int ballCol, int rowDirection, int colDirection) throws InterruptedException {

        // move at this direction
        moveDirection(ballRow,ballCol,rowDirection,colDirection);

        if(!gameBoard[ballRow][ballCol].equals(ball)) gameBoard[ballRow][ballCol]=ground;
    }

    private void moveDirection(int ballRow, int ballCol, int rowDirection, int colDirection) throws InterruptedException {

        while(!gameBoard[ballRow][ballCol].equals(wall))
        {
            if(gameBoard[ballRow][ballCol].equals(brick))
            {
                ballGoesDown(ballRow,ballCol);
                return;
            }
            movingIllusion(ballRow,ballCol);
            ballRow += rowDirection;
            ballCol += colDirection;
        }
        wallHit(ballRow,ballCol);

        rowDirection = 0;
        colDirection = colDirection*-1;

        if(colDirection == 0)
        {
            ballGoesDown(ballRow+1,ballCol);
        }
        else {
            moveDirection(ballRow,ballCol+colDirection,rowDirection,colDirection);
        }
    }

    private void wallHit(int ballRow, int ballCol) throws InterruptedException {
        gameBoard[ballRow][ballCol]=ball;
        printGameBoard();
        sleepForOneSec();
        gameBoard[ballRow][ballCol]=wall;

    }

    private void ballGoesDown(int ballRow, int ballCol) throws InterruptedException {

        while(ballRow!=gameBoard.length)
        {
            movingIllusion(ballRow,ballCol);
            ballRow++;
        }
        ballPos = new int[]{ballRow-1,ballCol};
        gameBoard[ballPos[0]][ballPos[1]]=ball;
    }

    private void movingIllusion(int ballRow, int ballCol) throws InterruptedException {
        if(gameBoard[ballRow][ballCol].equals(brick))
        {
            reduceBrickAndBallLife(ballRow,ballCol);
            if(bricksWithLife.get(getExactBallPosition(ballRow,ballCol))==0)
            {
                gameBoard[ballRow][ballCol]=" ";
            }
        }
        else {
            gameBoard[ballRow][ballCol]=ball;
            printGameBoard();
            gameBoard[ballRow][ballCol]=" ";
            sleepForOneSec();
        }
    }

    private void reduceBrickAndBallLife(int ballRow, int ballCol) {
        int exactPosition = getExactBallPosition(ballRow,ballCol);
        ballLife--;

        if(ballLife>=0)
        {
            bricksWithLife.put(exactPosition,bricksWithLife.get(exactPosition)-1);
        }
    }

    private void sleepForOneSec() throws InterruptedException {
        try
        {
            Thread.sleep(1000);
        }
        catch(InterruptedException e)
        {
            e.getCause();
        }

    }
}
