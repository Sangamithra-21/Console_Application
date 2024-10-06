package TicTacToe_Human;

import java.util.Scanner;

public class TicTacToeHuman {

    static char[][] board;

    public TicTacToeHuman()
    {
        board = new char[3][3];
        initBoard();
    }

    Scanner sc = new Scanner(System.in);

    void displaysBoard() {

        System.out.println("------------");
        for(int i=0;i<3;i++)
        {
            System.out.print("|");
            for(int j=0;j<3;j++)
            {
                System.out.print(board[i][j]+" | ");
            }

            System.out.println();
            System.out.println("------------");

        }
    }

    private void initBoard() {

        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                board[i][j]=' ';
            }
        }
    }

    public boolean isDrawMatch()
    {
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                if(board[i][j]==' ')
                {
                    return false;
                }
            }
        }
        return true;
    }


    public boolean isValidPlace(int row, int col) {
            if (row < 3 && row >= 0 && col < 3 && col >= 0) {
                if (board[row][col] == ' ') {
                    return true;
                }
            }
        return false;
    }

    public boolean makeMove(Player player)
    {
        int row=-1;
        int col=-1;
        do{
            if(col !=-1 && row !=-1) {
                System.out.println("Invalid input Try again");
            }
            System.out.println("Enter the row and column : ");
            row = sc.nextInt();
            col = sc.nextInt();
        }while(!isValidPlace(row,col));

        board[row][col]=player.symbol;

        boolean win = checkWinCondition(row,col);
        return win;
    }


    public boolean checkWinCondition(int row,int col) {
        if(checkRow(row) || checkColumn(col) || checkDiagonal())
        {
            return true;
        }
        return false;
    }

    private boolean checkDiagonal() {
        if((board[0][0]!=' ' && board[0][0]==board[1][1] && board[1][1]==board[2][2]) || (board[0][2]!=' ' && board[0][2]==board[1][1] && board[1][1]==board[2][0]))
        {
            return true;
        }
        return false;
    }

    private boolean checkColumn(int col) {
        if(board[0][col]!=' ' && board[0][col]==board[1][col] && board[1][col]==board[2][col])
        {
            return true;
        }
        return false;
    }

    private boolean checkRow(int row) {
       if(board[row][0]!=' ' && board[row][0]==board[row][1] && board[row][1]==board[row][2])
       {
           return true;
       }
       return false;

    }
}
