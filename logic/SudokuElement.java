package logic;
/**
 * Dette er en abstrakt klasse som holder på koden brukt i Column, Row og Box.
 */
public abstract class SudokuElement {
	private Square [] elementSquares;
	public SudokuElement(Square [] elementSquares) {
		this.elementSquares = elementSquares.clone();
	}
	public SudokuElement() {
	}
	public Square [] getSquares() {
		return elementSquares;
	}
	public void addSquares(Square [] squares) {
		this.elementSquares = squares.clone();
	}
	public int size() {
		return elementSquares.length;
	}
	public boolean containsValue(char value) {
		for (Square s: elementSquares)
			if (s.gotValue(value)) return true;
		return false;
	}
}