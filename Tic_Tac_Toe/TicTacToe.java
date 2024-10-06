package Tic_Tac_Toe;

public class TicTacToe {

    static char[][] board;

    public TicTacToe()
    {
        board = new char[3][3];
        initBoard();
    }

    public void initBoard()
    {
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                board[i][j] = ' ';
            }
        }
    }

    static void displayBoard() {

        System.out.println("-------------");
        for(int i=0;i<3;i++)
        {
            System.out.print("| ");
            for(int j=0;j<3;j++)
            {
                System.out.print(board[i][j] +" | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }


    static boolean checkDraw()
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

    static void placeMark(int row, int col, char val)
    {
        if(row>=0 && row<3 && col<3 && col>=0) {
            if(board[row][col]==' ') {
                board[row][col] = val;
            }
            else {
                System.out.println("Try Another Place...!");
            }
        }
        else {
            System.out.println("Please Enter Valid Position...!");
        }
    }

    public static  boolean checkColumnWin()
    {
        for(int j=0;j<3;j++)
        {
            if(board[0][j]!=' ' && board[0][j] == board[1][j] && board[1][j]==board[2][j])
            {
                return true;
            }
        }
        return false;
    }

    public static boolean checkRowWin()
    {

        for(int i=0;i<3;i++)
        {

            if(board[i][0]!=' ' && board[i][0]==board[i][1] && board[i][1]==board[i][2])
            {
                return true;
            }

        }
        return false;
    }

    public static boolean checkDiagonalWin()
    {
            if (board[0][0]!=' ' && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
                return true;
            } else if (board[0][2]!=' ' && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
                return true;
            }
        return false;
    }
}
