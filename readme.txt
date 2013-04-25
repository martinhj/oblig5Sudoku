Programmet kompilers med ./make.sh og kjøres med java SudokuSolver. Utfra hvor
mange argumenter SudokuSolver får kjører den på forskjellig vis.
- Ingen argument: En dialog hvor man kan velge en oppgavefil hopper opp.
- Ett argument: fil med sudokuoppgave som skal løses som argument, viser 
løsningen i GUI.
- To argument: Sudokuproblem som skal løses som første argument, fil som 
løsningen skal skrives til som andre argument.


I SudokuGUI har jeg kun lagt til kode som skal til for å få den til å fungere.

Exitkode 1: Noe gikk galt i lesingen av sudokufilen.
Exitkode 2: Fant ikke den angitte fila.
Exitkode 3: Noe gikk galt i skrivingen av sudokufilen.