package edu.wm.cs.cs301.sudoku.model;

import java.awt.*;

/*
  Not contained in the UML design, but allowed
    for smoother interaction between the model
    and the view.
 */

/**
 *  This class is used to organize number and color data to
 *    allow for easy distinction of squares contained in
 *    the original sudoku puzzle, as well as allow for
 *    easy customization.
 * <p>
 *  Derived from the WordleResponse/ColorResponse classes
 *    from the previous Wordle project.
 */
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
