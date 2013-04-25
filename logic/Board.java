// addSolution støtter nok ikke løsning med tråder.

// Kunne ha gjort....
// logikk-array for variabler å teste ut.
// Hvor er det mest produktivt å sjekke 
// arrayet som bestemmer hvilke variabler som er brukbare sjekker bare de 
// variablene som er lovlige.

// Denne løsningen krever at brettene den leser inn bruker tallene og 
// bokstavene (store bokstaver) i den rekkefølgen det er definert i 
// LEGAL_VALUES. 

package logic;
import fileMatters.*;
import java.io.FileNotFoundException;
/**
 * Denne klassen skal holde på alle variablene.
 * Holder på columns, rows og boxes.
 */
public class Board {
	protected long solutionCounter = 0;
	protected SudokuContainer solutions = new SudokuContainer();
	private int dim, height, length;
	private SudokuElement [] columns, rows, boxes;
	private Square [] squares;
	private final static char [] LEGAL_VALUES
		= {'1','2','3','4','5','6','7','8','9', 'A', 'B', 'C', 'D', 'E', 'F'
		, 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T'
		, 'U', 'V', 'W', 'X', 'Y', 'Z','@'};
	/**
	 * Denne konstruktøren setter opp et brett for så å sette i gang løsningen av brettet.
	 */
	public Board(String filename) throws FileNotFoundException{
		SudokuFile sudokuFile = new SudokuFile(filename);
		this.dim = sudokuFile.getDim();
		this.height = sudokuFile.getHeight();
		this.length = sudokuFile.getLength();
		this.squares = sudokuFile.getSquares();
		this.columns = new Column[dim];
		this.rows = new Row[dim];
		this.boxes = new Box[dim];
		fill();
		squares[0].fillInRemainingOfBoard();
		System.out.println("Antall løsningner: " + solutionCounter);
	}
	/**
	 * Returnerer et array med tegn som er lovlige å bruke i løsningen av 
	 * sudokubrettet. Det er avgjørende at oppgaven følger samme rekkefølge på
	 * tegnene som i listen her.
	 */
	public char [] getLegalValues() {
		return LEGAL_VALUES;
	}
	/**
	 * Legger til en løsning i beholderen.
	 */
	public void addSolution() {
		// denne metoden støtter ikke tråder tror jeg. Den legger inn rutene 
		// slik de blir funnet når metoden blir kjørt, og de rekursive metodene
		// for å finne løsningene endrer på rutene felles for alle. Kan kanskje
		// rettes ved å bruke squares som argument hele veien fra der den blir
		// kalt fra.
		solutions.insert(squares);
	}
	private void fill() {
		fillColumns();
		fillRows();
		fillBoxes();
		setSquareBoard();
		fillInNextPointers();
	}
	/**
	 * Setter next-pekeren i rutene til den ruten som kommer etter denne i
	 * brettet. Følger fra venstre mot høyre, fra topp til bunn. Setter i alle
	 * bortsett fra den siste ruten.
	 */
	private void fillInNextPointers() {
		for (int i = 0; i < squares.length - 1; i++) {
			squares[i].setNext(squares[i + 1]);
		}
	}
	/**
	 * Setter en peker til brettet i hver rute slik at en har tilgang til all
	 * informasjon i hver rute...
	 */
	private void setSquareBoard() {
		for (int i = 0; i < squares.length; i++)
			squares[i].setBoard(this);
	}
	/**
	 * Setter pekere til riktige ruter i en array for hver kolonne.
	 */ 
	private void fillColumns() {
		Square [] mSquares = new Square [dim];
		for (int i = 0; i < dim; i++) {
			Column mColumn = new Column();
			for (int j = 0; j < dim; j++) {
				mSquares[j] = this.squares[dim * j + i];
				mSquares[j].setColumn(mColumn);
			}
			mColumn.addSquares(mSquares);
			columns[i] = mColumn;
		}
	}
	/**
	 * Setter pekere til riktige ruter i en array for hver rad.
	 */ 
	private void fillRows() {
		Square [] mSquares = new Square [dim];
		for (int i = 0; i < dim; i++) {
			Row mRow = new Row();
			for (int j = 0; j < dim; j++) {
				mSquares[j] = this.squares[dim * i + j];
				mSquares[j].setRow(mRow);
			}
			mRow.addSquares(mSquares);
			rows[i] = mRow;
		}
	}
	/**
	 * Setter pekere til riktige ruter i en array for hver boks.
	 */ 
	private void fillBoxes() {
		Square [] mSquares = new Square [dim];
		for (int i = 0; i < dim; i++) {
			Box mBox = new Box();
			for (int j = 0; j < dim; j++) {
				mSquares[j] 
				= rows[j/length + i / height * height].getSquares()
				[j % length + i % height * length];
				mSquares[j].setBox(mBox);
			}
			mBox.addSquares(mSquares);
			boxes[i] = mBox;
		}
	}
	/**
	 * @return dimensjonen på sudokubrettet.
	 */ 
	public int getDim() {
		return dim;
	}
	/**
	 * @return høyden på en sudokuboks.
	 */ 
	public int getHeight() {
		return height;
	}
	/**
	 * @return lengden på en sudokuboks.
	 */
	public int getLength() {
		return length;
	}
	/**
	 * @return en beholder med alle løsningene.
	 */ 
	public SudokuContainer getSolutions() {
		return solutions;
	}
}