package logic;
import java.util.ArrayList;

/**
 * Skal lagre løsninger på Sudokuoppgaven.
 */ 
public class SudokuContainer {
	ArrayList <Square []> solutions = new ArrayList<Square []>();
	/**
	 * For å legge inn løsninger i containeren.
	 */
    public void insert(Square [] squares) {
    	Square [] solutionSquares = new Square [squares.length];
    	for (int i = 0; i < squares.length; i++)
    		solutionSquares[i] = new FilledSquare(squares[i].getValue());
    	solutions.add(solutionSquares);
    }
    /**
     * @return løsning på gitt posisjon.
     */
    public Square [] get(int position) {
    	return solutions.get(position);
    }
    /**
     * @return antall løsninger i løsningsbeholderen.
     */
    public int getSolutionCount() {
    	return solutions.size();
    }
}