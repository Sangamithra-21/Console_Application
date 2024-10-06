package TicTacToe_Human;

import TicTacToe_Human.TicTacToeHuman;

import java.util.Scanner;

import static TicTacToe_Human.TicTacToeHuman.board;

public class StartGame {

    static TicTacToeHuman game = new TicTacToeHuman();

    public static void main(String[] args) {

        Player player1 = new Player("Mithra",'X');
        Player player2 = new Player("Valli",'O');

        Player currentPlayer =player1;


        while(!game.isDrawMatch())
        {
            if(currentPlayer==player1)
            {
                game.displaysBoard();

                System.out.println(currentPlayer.name+ "'s turn");

                if(game.makeMove(currentPlayer))
                {
                    System.out.println(currentPlayer.name+" won the Game...!");
                    return;
                }

                currentPlayer=player2;

            }
            else {

                game.displaysBoard();
                System.out.println(currentPlayer.name+ "'s turn");

                if(game.makeMove(currentPlayer))
                {
                    System.out.println(currentPlayer.name+" won the Game...!");
                    return;
                }

                currentPlayer = player1;
            }
        }
        if(game.isDrawMatch())
        {
            System.out.println("Match Draw..!");
        }

    }

}
