package gamecode;

import javax.swing.*;
import java.awt.*;

/**
 * @author Kavin Ramasamy
 */
public class ReversiGame extends JFrame  {
    private int length = 1000;
    private int height = 1000;
    private JPanel panel = new JPanel();
    private BoardLayout board = new BoardLayout();

    /**
     * Constructor
     */
    public ReversiGame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel.setLayout(new GridLayout(8, 8));
        updateBoard();
        this.setTitle("Reversi Game:   " + board.whosMove(board.getMove()));
        this.add(panel);
        this.setSize(length, height);
    }

    /**
     * Method that makes the frame visible
     */
    public void run() {this.setVisible(true);}

    /**
     * Method that plays a move based on a row or column given from a click
     * @param row
     * @param col
     */
    public void onClick(int row, int col){
        if(board.isLegal(row, col)) {
            board.playMove(row, col);
            updateBoard();
        }
    }



    /**
     *Method that updates the board every time some action occurs
     */
    public void updateBoard() {
        panel.removeAll();
        this.setTitle("Reversi Game:   " + board.whosMove(board.getMove()));
        for(int x = 0; x<8; x++) {
            for(int y = 0; y<8; y++){
                Cell c = new Cell(x, y, this);
                c.highlighter(board.isLegal(x, y));
                c.placer(board.cellValue(x, y));
                panel.add(c);
            }
        }
        SwingUtilities.updateComponentTreeUI(this);
        if(board.gameOver() !=0) {
            if(board.whoIsWinner() == 1 ){
                JOptionPane.showMessageDialog(null, "White Wins");
            }
            else if(board.whoIsWinner()==-1){
                JOptionPane.showMessageDialog(null, "Black Wins");
            }

        }
    }
}




