package edu.wm.cs.cs301.sudoku;

import edu.wm.cs.cs301.sudoku.model.SudokuPuzzle;
import edu.wm.cs.cs301.sudoku.view.SudokuFrame;

public class Main {
    public static void main(String[] args) {
        new SudokuFrame(new SudokuPuzzle());
    }
}