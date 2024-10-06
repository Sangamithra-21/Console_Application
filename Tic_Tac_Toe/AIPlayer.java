package Tic_Tac_Toe;

import java.util.Random;


public class AIPlayer extends Players{

    public AIPlayer(String name,char val)
    {
        super(name,val);
    }

    void makeMove()
    {
        int row;
        int col;
        do {
            Random r = new Random();
            row = r.nextInt(3);
            col = r.nextInt(3);
        }while(!isValidMove(row,col,val));

        TicTacToe.placeMark(row,col,this.val);
    }

}
