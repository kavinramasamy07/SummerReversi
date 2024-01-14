package gamecode;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

/**
 * @author Kavin Ramasamy
 */
public class Cell  extends JButton{
    private int row;
    private int col;
    private ReversiGame reversiGame;

    /**
     * Constructor
     * @param row
     * @param col
     * @param reversiP
     */
    public Cell(int row, int col, ReversiGame reversiP){
        this.row = row;
        this.col = col;
        this.reversiGame = reversiP;


        //Set Attributes of the Cell using JButton Attributes
        setPreferredSize(new Dimension(100, 100)); //Size of the button
        setBackground(new Color(34, 139, 34));//Color of the button
        setBorder(new LineBorder(Color.BLACK));//Border color of the button
        //Set the Mouse-Click Action to each cell
        CellMouseActionListener cmal = new CellMouseActionListener(row, col, reversiP);
        this.addMouseListener(cmal);
    }

    /**
     * New Method that puts the correct coin onto the cell
     * @param Color
     *
     */
    public void placer(int Color) {
        if (Color == 1) { //White
            Image image = getImage("/images/whiteCoin.png");
            Image newimg = image.getScaledInstance(110, 110,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
            ImageIcon imageIcon = new ImageIcon(newimg);  // transform it back
            this.setIcon(imageIcon);
        }

        else if (Color == -1) { //Black
            Image image = getImage("/images/blackCoin.png");
            Image newimg = image.getScaledInstance(73, 73,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
            ImageIcon imageIcon = new ImageIcon(newimg);  // transform it back
            this.setIcon(imageIcon);
        }
    }

    /**
     * Load the image from the given path
     * @param path
     * @return Image
     */
    private Image getImage(String path) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(getClass().getResource(path));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return image;
    }

    //

    /**
     * New Method that highlights cells
     * @param legal
     */
    public void highlighter(boolean legal) {
        if (legal){
            setBorder(new LineBorder(Color.WHITE));
        }
        else{
            setBorder(new LineBorder(Color.BLACK));
        }
    }
}


