package edu.wm.cs.cs301.sudoku.model;

import java.awt.*;

public class SudokuResponse {
    private final int n;

    private final Color background;

    public SudokuResponse(int n, Color background) {
        this.n = n;
        this.background = background;
    }

    public int getValue() {
        return this.n;
    }

    public Color getBackground() {
        return this.background;
    }
}
