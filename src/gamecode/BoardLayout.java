package gamecode;

import java.util.ArrayList;

/**
 * @author Kavin Ramasamy
 */
public class BoardLayout {

    private int[][] board = new int[8][8]; //Starting with a 10x10 board size


    private int move = 1;

    private int[][] surroundingCells =
    {
        //Used to track all cells around a certain cell (including diagonal cells)
        {0, 1}, {0, -1}, {1, 0}, {-1, 0}, {1, 1}, {-1, 1}, {1, -1}, {-1, -1}
    };

    /**
     * Constructor
     */
    public BoardLayout(){
        board[3][3] = 1;
        board[4][4] = 1;
        board[3][4] = -1;
        board[4][3] = -1;

    }
    public int getMove() {
        return move;
    }

    /**
     * Method that shows the value of each cell (0, 1, or -1)
     * @param row
     * @param col
     * @return int
     */
    public int cellValue(int row, int col) {
        return board[row][col];
    }

    /**
     * Returns who's move it is
     * @param move
     * @return
     */
    public String whosMove(int move){
        if(move==-1) {
            return "Black's Move";
        }
      return "White's Move";
    }

    /**
     * Method that changes a cell from black to white or white to black
     * @param row
     * @param col
     */
    public void changeColor(int row, int col){
        board[row][col] *= -1;
    }

    /**
     * New Method that checks if a cell has any color assigned to it
     * @param row
     * @param col
     * @return boolean
     */
    public boolean emptyCell(int row, int col) {
        if (board[row][col] == 0) {
            return true;
        }
        else {
            return false;
        }
    }


    /**
     * Method that changes an empty cell to a specified color
     * @param row
     * @param col
     * @param color
     */
    public void changeCell( int row, int col, int color) {
        if (emptyCell(row, col)) {
            board[row][col] = color;
        }
    }

    //

    /**
     * Method that checks to see if a row & column is within range of 8x8
     * @param row
     * @param col
     * @return
     */
    public boolean withinRange(int row, int col) {
        if (row<8 && col<8 && row>=0 && col>0) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Method that checks to see if entire board is filled
     * @return
     */
    public boolean fullBoard() {
        for(int x = 0; x<8; x++) {
            for(int y = 0; y<8; y++) {
                if( board[x][y] == 0)
                    return false;
            }
        }
        return true;
    }

    /**
     * Method that finds which color occupies the most cells in the board
     * @return
     */
    public int winner() {
        int w = 0;
        int b = 0;

        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                if (board[x][y] == 1) {
                    w += 1;
                } else if (board[x][y] == -1) {
                    b += 1;
                }
            }

        }
        if (w > b) {
            System.out.println("Total White = "+w);
            return w;
        } else {
            System.out.println("Total black = "+b);
            return b *= -1;
        }
    }

    /**
     * Method that outputs which color is the winner (-1, or 1)
     * @return
     */
    public int whoIsWinner() {
        int w = 0;
        int b = 0;

        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                if (board[x][y] == 1) {
                    w += 1;
                } else if (board[x][y] == -1) {
                    b += 1;
                }
            }

        }
        if (w > b) {
            return 1;
        } else {
            return -1;
        }
    }

    /**
     * Method that checks all nearby cells for any certain color
     * @param row
     * @param col
     * @param color
     * @return boolean
     */
    public boolean existColorsNearCell(int row, int col, int color){
        for(int[] surroundingCell: surroundingCells) {
            int srow = row + surroundingCell[0];
            int scol = col + surroundingCell[1];
            if(withinRange(srow, scol) && board[srow][scol] == color){
                return true;
            }
        }
        return false;
    }

    /**
     * Method that lists all the legal moves on the current board
     * @return
     */
    public ArrayList<Integer[]> listLegalMoves() {
        ArrayList<Integer[]> legalMoves = new ArrayList<Integer[]>();
        for(int x= 0; x<8; x++) {
            for(int y = 0; y<8; y++) {
                if (emptyCell(x, y) && existColorsNearCell(x, y, move*-1)){
                    //Check if any surrounding cells of a certain cell contain a piece of another color
                    //If there is, it will continue adding until it reaches the end
                    for(int[] surroundingCell: surroundingCells){
                        int srow = x + surroundingCell[0];
                        int scol = y + surroundingCell[1];
                        while(withinRange(srow, scol) && board[srow][scol] == move*-1  ){
                            srow += surroundingCell[0];
                            scol += surroundingCell[1];

                        }
                        //Once the end has been reached, it checks if the end is the same color as who's ever move it is
                        //If it is then the square that was checked is a legal move
                        //If not then it is not a legal move
                        //Then it adds the move to the list
                        if(withinRange(srow, scol) &&  board[srow][scol] == move ) {
                            if ((srow == x && Math.abs(scol - y) > 1) || // In the same row?
                               (scol == y && Math.abs(srow - x) > 1) || // In the same col?
                               (Math.abs(scol - y) > 1 && Math.abs(srow - x) > 1)) { //Diagonal?
                                Integer[] legalMoveArr = new Integer[]{x, y};
                                legalMoves.add(legalMoveArr);

                                break;
                            }
                        }
                    }
                }
            }
        }
        return legalMoves;
    }

    /**
     * Checks to see if a certain row and column is a legal move
     * @param row
     * @param col
     * @return boolean
     */
    public boolean isLegal(int row, int col) {
        if(listLegalMoves().isEmpty()) {
            move*=-1;
        }
        ArrayList<Integer[]> legalList = listLegalMoves();
        for(Integer[] listElement: legalList) {
            if (row == listElement[0] && col == listElement[1] ) {
                return true;
            }
        }
        return false;
    }

    /**
     * Method that plays a move based on an inputted row and column
     * @param row
     * @param col
     */
    public void playMove(int row, int col) {
        if (isLegal(row, col)) {
            this.changeCell(row, col, move);
            //Loop adds surroundingCells to row param and col param to check for opposite color cells
            //Once identified, while loop is activated, and continues adding surroundingCell in same direction
            //Additionally, a pass variable is measured to check how many times we find an opposite colored cell
            for (int[] surroundingCell : surroundingCells) {
                int srow = row + surroundingCell[0];
                int scol = col + surroundingCell[1];
                int pass = 1;
                while (withinRange(srow, scol) && board[srow][scol] == move * -1  ) {
                    srow += surroundingCell[0];
                    scol += surroundingCell[1];
                    pass += 1;
                }
                /*
                * Once all the opp cells in a diag/row/col identified
                * exits while loop to check if cell after last opp cell is a same color cell as the move
                * If it is, then the square originally checked is a legal move
                * */
                if (withinRange(srow, scol) && board[srow][scol] == move ) {
                    for (int i = 1; i < pass; i++) {
                        this.changeColor(srow - surroundingCell[0] * i, scol - surroundingCell[1]*i);
                    }
                }
            }
            move *= -1;
        }


    }

    /**
     * Method that checks to see if the game is done
     * @return int
     */
    public int gameOver(){
        if(fullBoard()) {
            return winner();
        }

        else if (listLegalMoves().size() == 0){
            move *= -1;
            if (listLegalMoves().size() == 0) {
                move *= -1;
                // Count the majority
                return whoIsWinner();
            }
        }
        else if(winner() >= 40) {
            return whoIsWinner();
        }
        return 0;
    }
}
