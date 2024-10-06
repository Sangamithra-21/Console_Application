package Tic_Tac_Toe;

abstract public class Players {
    String name;
    char val;
    Players(String name,char val)
    {
         this.name = name;
         this.val = val;
    }

    boolean isValidMove(int row, int col, char val) {
        if(row>=0 && row<3 && col>=0 && col<3)
        {
            if(TicTacToe.board[row][col]==' ')
            {
                return true;
            }
        }
        return false;
    }
    abstract void makeMove();
}
