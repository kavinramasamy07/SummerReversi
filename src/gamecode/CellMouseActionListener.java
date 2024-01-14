package gamecode;

import java.awt.event.*;

/**
 * @author Kavin Ramasamy
 */
public class CellMouseActionListener implements MouseListener {


    private ReversiGame reversiGame;

    private int row;

    private int col;

    public CellMouseActionListener(int row, int col, ReversiGame reversiGame) {

        this.row = row;
        this.col = col;
        this.reversiGame = reversiGame;
    }

    /**
     * Invoked when the mouse button has been clicked (pressed
     * and released) on a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        reversiGame.onClick(row, col);
    }

    /**
     * Invoked when a mouse button has been pressed on a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mousePressed(MouseEvent e) {

    }

    /**
     * Invoked when a mouse button has been released on a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseReleased(MouseEvent e) {

    }

    /**
     * Invoked when the mouse enters a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseEntered(MouseEvent e) {
    }

    /**
     * Invoked when the mouse exits a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseExited(MouseEvent e) {

    }

}
