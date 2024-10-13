package Minesweeper_Game;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

public class Minesweeper {

    private class MineTile extends JButton{
        int row;
        int col;

        public MineTile(int row,int col)
        {
            this.row = row;
            this.col = col;
        }
    }

    int tileSize = 70;
    int numRows = 8;
    int numCols = 8;
    int boardWidth = numCols * tileSize;
    int boardHeight = numRows * tileSize;

    JFrame frame = new JFrame("Minesweeper");
    JLabel textLabel = new JLabel();
    JPanel textPanel = new JPanel();
    JPanel boardPanel = new JPanel();

    int mineCount =10;
    MineTile[][] board = new MineTile[numRows][numCols];
    ArrayList<MineTile> mineList;

    Random random = new Random();
    int tilesClicked = 0;
    boolean gameOver =false;

    Minesweeper()
    {

        frame.setSize(boardWidth,boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.getDefaultCloseOperation();
        frame.setLayout(new BorderLayout());

        // text Label
        textLabel.setFont(new Font("Arial",Font.BOLD,25));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText("Minesweeper");
        textLabel.setOpaque(true);

        // text Panel
        textPanel.setLayout(new BorderLayout());
        textPanel.add(textLabel);
        frame.add(textPanel,BorderLayout.NORTH);

        //board Panel
        boardPanel.setLayout(new GridLayout(numRows,numCols));
        frame.add(boardPanel);

        for(int r=0;r<numRows;r++)
        {
            for(int c=0;c<numCols;c++)
            {
                MineTile tile = new MineTile(r,c);
                board[r][c]=tile;

                tile.setFocusable(false);
                tile.setMargin(new Insets(0,0,0,0));
                tile.setFont(new Font("Arial Unicode MS",Font.PLAIN,45));
                tile.addMouseListener(new MouseAdapter() {
                                          public void mousePressed(MouseEvent e) {

                                              if(gameOver)
                                              {
                                                  return;
                                              }
                                              MineTile tile = (MineTile) e.getSource();
                                              //left click
                                              if (e.getButton() == MouseEvent.BUTTON1) {
                                                  if (tile.getText() == "") {
                                                      if (mineList.contains(tile)) {
                                                          revealMines();
                                                      }
                                                      else {
                                                          checkMine(tile.row,tile.col);
                                                      }
                                                  }
                                              }
                                              else if(e.getButton()==MouseEvent.BUTTON3)
                                              {
                                                  if(tile.getText()=="" && !tile.isEnabled()){
                                                     tile.setText("🚩");
                                                  }
                                                  else if(tile.getText()=="🚩")
                                                  {
                                                      tile.setText("");
                                                  }
                                              }
                                          }
                                      });
                boardPanel.add(tile);
            }
        }

        frame.setVisible(true);

        setMines();

    }

    private void setMines() {

          mineList = new ArrayList<MineTile>();

//        mineList.add(board[2][2]);
//        mineList.add(board[2][3]);
//        mineList.add(board[5][6]);
//        mineList.add(board[3][4]);
//        mineList.add(board[1][1]);

        int mineLeft = mineCount;
        while(mineLeft>0)
        {
            int row = random.nextInt(numRows);
            int col = random.nextInt(numCols);

            MineTile tile = board[row][col];
            if(!mineList.contains(tile))
            {
                mineList.add(tile);
                mineLeft--;
            }

        }
    }

    private void revealMines()
    {
        for(int i=0;i<mineList.size();i++)
        {
            MineTile tile = mineList.get(i);
            tile.setText("💣");
        }
        gameOver = true;
        textLabel.setText("Game Over");

    }

    private void checkMine(int row,int col)
    {
        if(row<0 || row>=numRows || col<0 || col>=numCols)
        {
            return;
        }
        MineTile tile = board[row][col];
        if(!tile.isEnabled())
        {
            return;
        }
        tile.setEnabled(false);
        tilesClicked++;

        int minesFound = 0;

        //top 3
        minesFound += countMine(row-1,col-1);
        minesFound += countMine(row-1,col);
        minesFound += countMine(row-1,col+1);

        // left and right
        minesFound += countMine(row,col-1);
        minesFound += countMine(row,col+1);

        //bottom 3
        minesFound += countMine(row-1,col-1);
        minesFound += countMine(row-1,col);
        minesFound += countMine(row-1,col+1);

        if(minesFound>0)
        {
            tile.setText(Integer.toString(minesFound));
        }
        else {
            tile.setText("");

            // top 3
            checkMine(row-1,col-1);
            checkMine(row-1,col);
            checkMine(row-1,col+1);

            // left and right
            checkMine(row,col-1);
            checkMine(row,col+1);

            // bottom 3
            checkMine(row+1,col-1);
            checkMine(row+1,col);
            checkMine(row+1,col+1);



        }

        if(tilesClicked == numRows*numCols - mineList.size())
        {
            gameOver = true;
            textLabel.setText("Mines Cleared");
        }
    }

    private int countMine(int row,int col)
    {
        if(row<0 || row>=numRows || col<0 || col>=numCols)
        {
            return 0;
        }
        if(mineList.contains(board[row][col]))
        {
            return 1;
        }
        return 0;
    }
}
