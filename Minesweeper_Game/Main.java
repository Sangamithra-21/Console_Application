package Minesweeper_Game;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Minesweeper minesweeper = new Minesweeper();

         Scanner sc = new Scanner(System.in);
        System.out.println("Enter the Number of rows : ");
        int rows = sc.nextInt();
        System.out.println("Enter the number of columms : ");
        int cols = sc.nextInt();

        Minesweeper_console console = new Minesweeper_console(rows,cols);
    }
}
