package Tic_Tac_Toe;

import java.util.Scanner;

public class StartGame {
    public static void main(String[] args) {
           TicTacToe game = new TicTacToe();

           Scanner sc = new Scanner(System.in);
           System.out.println("Enter Name : ");
           String name = sc.next();
           System.out.println("Enter what symbol you want ( X or O ) : ");
           char symbol = sc.next().charAt(0);

           HumanPlayer player = new HumanPlayer(name,symbol);
           char aiSymbol;


           AIPlayer aiPlayer;
           Players currentPlayer;
           if(symbol=='X')
           {
               aiSymbol = 'O';
               aiPlayer = new AIPlayer("AI",aiSymbol);
               currentPlayer = player;

           }
           else {
               aiSymbol = 'X';
               aiPlayer = new AIPlayer("AI",aiSymbol);
               currentPlayer = aiPlayer;
           }

           while(true)
           {
               System.out.println(currentPlayer.name+"'s Turn");
               currentPlayer.makeMove();
               TicTacToe.displayBoard();
               if(TicTacToe.checkRowWin() || TicTacToe.checkColumnWin() || TicTacToe.checkDiagonalWin())
               {
                   System.out.println(currentPlayer.name+ " Won The Game...!");
                   break;
               }
               else if(TicTacToe.checkDraw())
               {
                   System.out.println("Match Draw...!");
                   break;
               }
               else {
                   if(currentPlayer==player)
                   {
                       currentPlayer = aiPlayer;
                   }
                   else {
                       currentPlayer = player;
                   }
               }
           }
    }
}
