// variabel count fjernes...

package logic;
/**
 * Når en rute sjekker om den kan bruke en verdi, SKAL ruten kalle
 * metoder som gjør dette i rutens kolonne-, rad- og boksobjekt.
 * Skal _IKKE_ ha noen datastruktur, annet enn isin egen verdi.
 */
public abstract class Square{
	private char value;
	final static int MAX_SOLUTIONS = 500;
	static int solutionCounter = 0;
	int position;
	protected SudokuElement box, column, row;
	private Board board;
	protected Square next;
	public Square() {}
	public Square(char value) {
		this.value = value;
	}
	/**
	 *
	 */
	public Square getNext() {
		return next;
	}
	/**
	 *
	 */
	public void setNext(Square next) {
		this.next = next;
	}
	/**
	 *
	 */
	// gjør det mulig å sette en peker til brettet i alle ruter. 
	public void setBoard(Board board) {
		this.board = board;
	}
	/**
	 *
	 */
	public Board getBoard() {
		return this.board;
	}
	/**
	 *
	 */
	public void setBox(SudokuElement box) {
		this.box = box;
	}
	/**
	 *
	 */	
	public void setColumn(SudokuElement column) {
		this.column = column;
	}
	/**
	 *
	 */
	 public void setRow(SudokuElement row) {
		this.row = row;
	}
	/**
	 *
	 */
	public void setValue(char value) {
		this.value = value;
	}
	/**
	 *
	 */
	public char getValue() {
		return value;
	}
	/**
	 *
	 */
	public boolean gotValue(char value) {
		if (this.value == value) return true;
		return false;
	}
	/**
	 * Returnerer dimensjonen på sudokubrettet (lengden av en hel side.)
	 */
	public int getDim() {
		return row.size();
	}
	/**
	 * Legger til en løsning i brettet det jobbes med.
	 */
	protected void addSolution() {
		if (board.solutionCounter < MAX_SOLUTIONS) board.addSolution();
		board.solutionCounter++;
	}
	/**
	 * Metode som fyller inn eventuelle løsninger og ser om de er mulige å bruke.
	 * I en løkke:
	 * 	1. Sett inn et gyldig tegn fra listen over gyldige, start far index 0 -> ++
	 * 	2. Kall neste.fillInRemainingOfBoard
	 * Hvis ikke flere gyldige variabler, terminer.
	 * Hvis rute dim * dim, putt inn variabel som løsning hvis en finner en løsning
	 * Hvis rute 0 * 0, og ikke flere gyldige variabler, terminer (skal gjøre dette
	 * så lenge den følger opplegget der oppe.) 
	 *
	 * Har lært at det er viktig å ha fokus på at det er en mengde metoder som
	 * jobber med den samme datastrukturen og at det slik sett er viktig å også
	 * ha et perspektiv på dette når en programerer med rekursive metoder man 
	 * ikke har helt oversikt over hvor er i rekka.
	 */
	public abstract void fillInRemainingOfBoard();
}