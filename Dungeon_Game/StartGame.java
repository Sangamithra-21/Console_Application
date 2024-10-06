package Dungeon_Game;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StartGame {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the size of Grid (row and column) : ");
        int gridRow = sc.nextInt();
        int gridCol = sc.nextInt();

        char[][] grid = new char[gridRow][gridCol];

        for(int i=0;i<gridRow;i++)
        {
            for(int j=0;j<gridCol;j++)
            {
                grid[i][j]='.';
            }
        }

        System.out.println("Enter the position of adventurer : ");
        int adRow = sc.nextInt();
        int adCol = sc.nextInt();

        grid[adRow-1][adCol-1]='A';


        System.out.println("Enter the position of Monster  : ");
        int monRow = sc.nextInt();
        int monCol = sc.nextInt();

        grid[monRow-1][monCol-1]='M';

        System.out.println("Enter the position of Trigger   : ");
        int tRow = sc.nextInt();
        int tCol = sc.nextInt();

        grid[tRow-1][tCol-1]='T';

        System.out.println("Enter the position of Gold      : ");
        int goldRow = sc.nextInt();
        int goldCol = sc.nextInt();

        grid[goldRow-1][goldCol-1]='G';



        System.out.println("Enter the number of pits : ");
        int pits = sc.nextInt();

        for(int i=0;i<pits;i++)
        {
            System.out.println("Enter position for pit "+(i+1));
            int pitRow = sc.nextInt();
            int pitCol = sc.nextInt();
            grid[pitRow-1][pitCol-1]='P';
        }


        displayGrid(grid);


        List<String> list1 = new ArrayList<>();
        findPath(adRow-1,adCol-1,list1,"",grid,'G',true);
        String advent = findMinimum(list1);

        if(list1.size()==0)
        {
            System.out.println("No possible solution");
            return;
        }

        List<String> list2 = new ArrayList<>();
        findPath(monRow-1,monCol-1,list2,"",grid,'G',false);
        String monster = findMinimum(list2);

        if(monster.length()<=advent.length())
        {
            List<String> list3 = new ArrayList<String>();
            grid[tRow-1][tCol-1]='T';
            findPath(adRow-1,adCol-1,list3,"",grid,'T',true);
            String trigger = findMinimum(list3);

            List<String> list4 = new ArrayList<>();
            grid[tRow-1][tCol-1]='T';
            findPath(tRow-1,tCol-1,list4,"",grid,'G',true);
            advent = findMinimum(list4);

            System.out.println("Minimum route to Reach Gold : "+(advent.length()+trigger.length()));
            printPath(advent,adRow,adCol,goldRow,goldCol);
        }
        else {
            System.out.println("Minimum route to reach a Gold :"+advent.length());
            printPath(advent,adRow,adCol,goldRow,goldCol);
        }


    }

    private static void printPath(String advent, int adRow, int adCol, int goldRow, int goldCol) {

        for(int i=0;i<advent.length();i++)
        {
            char ch = advent.charAt(i);
            System.out.print("("+adRow+","+adCol+")->");
            if(ch=='U')
            {
                adRow--;
            }
            else if(ch=='D') {
                adRow++;
            }
            else if(ch=='L')
            {
                adCol--;
            }
            else
            {
                adCol++;
            }
        }
        System.out.print("("+goldRow+","+goldCol+")");
    }

    private static String findMinimum(List<String> list1) {
        String str = "";
        int len = Integer.MAX_VALUE;
        for(String s : list1)
        {
            if(s.length()<len)
            {
                len=s.length();
                str=s;
            }
        }
        return str;
    }

    private static void findPath(int row, int col, List<String> list, String path, char[][] grid,char target,boolean isAdventurer)
    {
        if(row<0 || row>=grid.length || col<0 || col>=grid[0].length)
        {
            return;
        }
        if(isAdventurer && grid[row][col]=='P')
        {
            return;
        }
        if(grid[row][col]==target)
        {
            list.add(path);
            return;
        }
        if(grid[row][col]=='.' || grid[row][col]=='A' ||grid[row][col]=='M'|| grid[row][col]=='T'||  (!isAdventurer && (grid[row][col]=='P')))
        {
            char temp = grid[row][col];
            grid[row][col]=' ';
            findPath(row,col+1,list,path+'R',grid,target,isAdventurer);
            findPath(row+1,col,list,path+'D',grid,target,isAdventurer);
            findPath(row,col-1,list,path+'L',grid,target,isAdventurer);
            findPath(row-1,col,list,path+'U',grid,target,isAdventurer);
            grid[row][col]=temp;
        }
    }

    private static void displayGrid(char[][] grid)
    {
        for(int i=0;i<grid.length;i++)
        {
            for(int j=0;j<grid[0].length;j++)
            {
                System.out.print(grid[i][j]+" ");
            }
            System.out.println();
        }
    }
}
