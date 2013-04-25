#!/bin/sh

./make.sh

if [ $? -eq 0 ]
then
echo "Kompilerte greit, kjører programmet."
#java logic.TestBoard puzzles/9x9_1.txt
#java logic.TestBoard puzzles/9x9_2.txt
#java logic.TestBoard puzzles/6x6oppg28losn.txt
#java logic.TestBoard puzzles/brett.6.1.txt
#java logic.TestBoard puzzles/eksempelbrett.txt
#java logic.TestBoard puzzles/brett.12.2.txt
#java logic.TestBoard puzzles/brett.12.1.txt
#java logic.TestBoard puzzles/brett.9.5.txt
#java SudokuSolver puzzles/6x6oppg28losn.txt solutions/6x6losninger.txt
#java SudokuSolver puzzles/9x9_2.txt solutions/9x9_2-sol.txt
#java SudokuSolver puzzles/brett.12.1.txt
#java SudokuSolver puzzles/brett.6.a.txt
java SudokuSolver
else
echo "Kompilerte med feil, kjører ikke programmet."
fi
