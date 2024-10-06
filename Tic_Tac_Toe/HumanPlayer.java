package Tic_Tac_Toe;
import Tic_Tac_Toe.Players;

import java.util.Scanner;

public class HumanPlayer extends Players {

    public HumanPlayer(String name,char val)
    {
        super(name,val);
    }

    void makeMove() {
        Scanner sc = new Scanner(System.in);
        int row;
        int col;
        do {
            System.out.println("Enter Row and column Number");
            row = sc.nextInt();
            col = sc.nextInt();
        } while (!isValidMove(row, col,val));

        TicTacToe.placeMark(row,col,this.val);
    }
}
