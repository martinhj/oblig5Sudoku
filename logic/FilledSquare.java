package logic;
/**
 * Denne klassen utvider Square og skal holde på verdier som leses inn fra en
 * oppgavefil.
 */
public class FilledSquare extends Square {
	public FilledSquare(char value) {
		super(value);
	}
	public void fillInRemainingOfBoard() {
		if (next != null) next.fillInRemainingOfBoard();
		if (next == null) addSolution();
	}
}