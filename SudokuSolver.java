import javax.swing.*;
import javax.swing.filechooser.*;
import java.awt.*;
import java.io.File;
import java.util.InputMismatchException;
import java.io.FileNotFoundException;
import java.io.IOException;

import logic.*;
import fileMatters.*;
import gui.*;
class SudokuSolver {
	Board board = null;
	public static void main (String [] args) {
		if (args.length == 0)
			new SudokuSolver();
		if (args.length == 1)
			new SudokuSolver(args[0]);
		if (args.length == 2)
			new SudokuSolver(args[0], args[1]);
	}
	public SudokuSolver() {
		File file = new File(".");
		JFileChooser jf = new JFileChooser(file);
		String boardFile;
		jf.setDialogTitle("Find SudokuFile");
		jf.showOpenDialog(null);
		if (jf.getSelectedFile() == null) System.exit(0);
		startGUI(createBoard(jf.getSelectedFile().getPath()));
	}
	public SudokuSolver(String boardFile) {
		startGUI(createBoard(boardFile));
	}
	public SudokuSolver(String boardFile, String solutionFile) {
		writeSolutions(createBoard(boardFile), solutionFile);
	}
	private Board createBoard(String sudokufile) {
		Board board = null;
		try {
			board = new Board(sudokufile);
		} catch (InputMismatchException e) {
			System.out.println("Trenger en gyldig sudokufil.");
			System.exit(1);
		} catch (FileNotFoundException e) {
			System.out.println("Finner ikke den fila.");
			System.exit(2);
		}
		
		return board;
	}
	private void startGUI(Board board) {
		new SudokuGUI(board.getDim(), board.getHeight(), board.getLength()
			, board.getSolutions());	
	}
	private void writeSolutions(Board board, String solutionFile) {
		try {
			new WriteSolutions(board.getDim(), board.getHeight(), board.getLength()
				, board.getSolutions(), solutionFile);
		} catch (IOException e) {
			System.out.println("Noe galt skjedde med skrivingen.");
			System.exit(3);
		}
	}
}