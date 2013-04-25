package gui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


import java.awt.event.*;

import logic.*;

/** 
* Tegner ut et Sudoku-brett.
* @author Christian Tryti
* @author Stein Gjessing
*/
public class SudokuGUI extends JFrame implements ActionListener {

	private final int RUTE_STRELSE = 50;	/* Størrelsen på hver rute */
    private final int PLASS_TOPP = 50;	/* Plass pŒ toppen av brettet */

	private JTextField[][] brett;   /* for å tegne ut alle rutene */
	private int dimensjon;			/*størrelsen på brettet (n)*/
	private int vertikalAntall;	/* antall ruter i en boks loddrett */
	private int horisontalAntall;	/* antall ruter i en boks vannrett */
	private int currentSolution = -1;

	// ***mine
	private SudokuContainer sc;

	/** Lager et brett med knapper langs toppen. */
	public SudokuGUI(int dim, int hd, int br, SudokuContainer sc) {
       	this.sc = sc;
       	dimensjon = dim;
		vertikalAntall = hd;
		horisontalAntall = br;

		brett = new JTextField[dimensjon][dimensjon];

		setPreferredSize(new Dimension(dimensjon * RUTE_STRELSE, 
                                        dimensjon  * RUTE_STRELSE + PLASS_TOPP));
		setTitle("Sudoku " + dimensjon +" x "+ dimensjon );
		//setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());

		JPanel knappePanel = lagKnapper();
		JPanel brettPanel = lagBrettet();

		getContentPane().add(knappePanel,BorderLayout.NORTH);
		getContentPane().add(brettPanel,BorderLayout.CENTER);
		pack();
		setVisible(true);
	}
	/** 
	* Lager et panel med alle rutene. 
	* @return en peker til panelet som er laget.
	*/
	private JPanel lagBrettet() {
		int topp, venstre;
		JPanel brettPanel = new JPanel();
		brettPanel.setLayout(new GridLayout(dimensjon,dimensjon));
		brettPanel.setAlignmentX(CENTER_ALIGNMENT);
		brettPanel.setAlignmentY(CENTER_ALIGNMENT);
		setPreferredSize(new Dimension(new Dimension(dimensjon * RUTE_STRELSE, 
                                                             dimensjon * RUTE_STRELSE)));		
		for(int i = 0; i < dimensjon; i++) {
			/* finn ut om denne raden trenger en tykker linje på toppen: */
			topp = (i % vertikalAntall == 0 && i != 0) ? 4 : 1;
			for(int j = 0; j < dimensjon; j++) {
				/* finn ut om denne ruten er en del av en kolonne 
				   som skal ha en tykkere linje til venstre:       */
				venstre = (j % horisontalAntall == 0 && j != 0) ? 4 : 1;

				JTextField ruten = new JTextField();
				ruten.setBorder(BorderFactory.createMatteBorder
					(topp,venstre,1,1, Color.black));
				ruten.setHorizontalAlignment(SwingConstants.CENTER);
				ruten.setPreferredSize(new Dimension(RUTE_STRELSE, RUTE_STRELSE));
				ruten.setText("A");
				brett[i][j] = ruten;
				brettPanel.add(ruten);
			}
		}
		return brettPanel;
	}
	private void outPutBoard(Square [] squares) {
		for (int i = 0; i < squares.length; i++) 
			brett[i / dimensjon][i % dimensjon].setText(
				new Character (squares[i].getValue()).toString());
	}
	/** 
	* Lager et panel med noen knapper. 
	* @return en peker til panelet som er laget.
	*/
	private JPanel lagKnapper() {
		JPanel knappPanel = new JPanel();
		knappPanel.setLayout(new BoxLayout(knappPanel, BoxLayout.X_AXIS));;
		JButton nesteKnapp = new JButton("Next solution");
		JButton previousButton = new JButton("Previous solution");
		nesteKnapp.addActionListener(this);
		previousButton.addActionListener(this);
		knappPanel.add(nesteKnapp);
		knappPanel.add(previousButton);
		return knappPanel;
	}

	public void actionPerformed(ActionEvent ae) {
		if (ae.getActionCommand().equals("Next solution")
		&& currentSolution < sc.getSolutionCount() - 1)
			outPutBoard(sc.get(++currentSolution));
		if (ae.getActionCommand().equals("Previous solution")
		&& currentSolution > 0)
			outPutBoard(sc.get(--currentSolution));
	}
}