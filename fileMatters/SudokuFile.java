// Legge inn tolkning av lowercase/uppercase slik at det ikke har noe å si?

package fileMatters;
import java.util.Scanner;
import logic.*;
import java.io.File;
import java.io.FileNotFoundException;


/**
 * Denne klassen skal sørge for metoder for innlesning av angitt oppgavefil.
 */
public class SudokuFile {
	private int dim, size, height, length;
	private Square [] squares;
	private String [] rows;
	private Scanner sc;
	private File file;
	public SudokuFile(String fileName) throws FileNotFoundException {
		readFile(fileName);
		createSquares(rows);
	}
	/**
	 * Returnerer lengden av hele sudokubrettet.
	 */
	public int getDim() {
		return dim;
	}
	/**
	 * Returnerer høyden på en sudokuboks.
	 */
	public int getHeight() {
		return height;
	}
	/**
	 * Returnerer lengden på en sudokuboks.
	 */
	public int getLength() {
		return length;
	}
	/**
	 * Returnerer de opprettede rutene.
	 */
	public Square [] getSquares() {
		return squares;
	}
	/*public Board getBoard() {
		return new Board(dim, height, length, squares);
	}*/
	/**
	 * Oppretter kontakt med fil gjennom filsystemet.
	 */
	private Scanner openFile(String fileName) throws FileNotFoundException {
		file = new File(fileName);
		Scanner sc = new Scanner(file);
		return sc;	
	}
	/**
	 * Leser inn informasjon fra filen i henhold til beskrivelsen av filformatet.
	 */
	private void readFile(String fileName) throws FileNotFoundException {
		this.sc = openFile(fileName);
		this.dim = sc.nextInt();
		this.size = dim * dim;
		this.height = sc.nextInt();
		this.length = sc.nextInt();
		this.rows = new String[this.dim];
		this.squares = new Square[size];
		sc.nextLine();
		for (int i = 0; i < this.dim; i++) {
			rows[i] = sc.nextLine();
		}
	}
	/**
	 * Denne metoden oppretter sudokuruter utfra de innleste dataene.
	 */
	private void createSquares(String[] rows) {
		char readChar;
		for (int i = 0; i < this.dim; i++) {
			for (int j = 0; j < rows.length; j++) {
				readChar = rows[i].charAt(j);
				if (readChar == '.')
					squares[i * dim + j] = new NotFilledSquare();
				if (readChar != '.')
					squares[i * dim + j] = new FilledSquare(rows[i].charAt(j));
			}
		}
	}
}