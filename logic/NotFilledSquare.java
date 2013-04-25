package logic;
/**
 * Denne klassen utvider Square og skal fylles og holde på verdier som 
 * problemløsningsmetodene etterhvert finner.
 */
public class NotFilledSquare extends Square {
	public void fillInRemainingOfBoard() {
		for (int i = 0; i < getDim(); i++) { 
			if (!valueExists(getBoard().getLegalValues()[i])) {
				setValue(getBoard().getLegalValues()[i]);
				if (next != null) next.fillInRemainingOfBoard();
				if (next == null) addSolution();
			}
		}
		setValue('.');
	}
	/**
	 * Sjekker om karakteren finnes i raden, kolonnen eller boksen den er
	 * plassert i.
	 */
	public boolean valueExists(char value) {
		if (box.containsValue(value) || column.containsValue(value)
			|| row.containsValue(value))
			return true;
		return false;
	} 
}