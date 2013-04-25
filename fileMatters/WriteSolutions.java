package fileMatters;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import logic.SudokuContainer;
/**
 * Klasse for å skrive løsninger til fil.
 */
public class WriteSolutions {
	int dim, height, length;
	String fileName;
	SudokuContainer sc;
	public WriteSolutions(int dim, int heigth, int length, SudokuContainer sc
		, String fileName) throws IOException
	{
		this.dim = dim;
		this.height = height;
		this.length = length;
		this.sc = sc;
		this.fileName = fileName;
		writeSolutions(createOutPut(sc));
	}
	/**
	 * @return En string med alle løsningene.
	 */
	String createOutPut(SudokuContainer sc) {
		String solutionsToWrite = "";
		for (int i = 0; i < sc.getSolutionCount(); i++) {
			solutionsToWrite += ((i + 1) + ": ");
			for (int j = 0; j < sc.get(i).length; j++) {
				solutionsToWrite += (sc.get(i)[j].getValue());
				if (j % dim == dim - 1) solutionsToWrite += ("// ");
			}
			solutionsToWrite += ("\n");		
		}
		return solutionsToWrite;
	}
	/**
	 * Skriver stringen til gitt fil.
	 */
	void writeSolutions(String solutions) throws IOException {
		File file = new File(fileName);
			FileWriter fw 
			= new FileWriter(file, true);
			fw.write(solutions);
			fw.close();
	}
}