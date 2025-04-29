package edu.wm.cs.cs301.sudoku.controller;

import edu.wm.cs.cs301.sudoku.view.SudokuFrame;
import edu.wm.cs.cs301.sudoku.view.SudokuGridPanel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/* Not contained in the original UML diagram as it was planned
     to be defined within the SudokuGridPanel class. When coding,
     it felt more necessary to make an individual class.
*/

/**
 *  This class handles the functionality of using the
 *    mouse to select a square in the SudokuGridPanel.
 */
public class GridMouseAdapter extends MouseAdapter {
    private final SudokuFrame view;

    public GridMouseAdapter(SudokuFrame view) {
        this.view = view;
    }

    /**
     *  Uses the mouse position to calculate what square in the
     *    SudokuGridPanel the user is attempting to select, and
     *    updates the "selected" variable and grid as necessary.
     *
     * @param e the event to be processed
     */
    @Override
    public void mousePressed(MouseEvent e) {
        SudokuGridPanel gridPanel = view.getSudokuGrid();
        int width = gridPanel.getWidth();
        int height = gridPanel.getHeight();
        int row = e.getY() / (height/ 9);
        int col = e.getX() / (width / 9);
        gridPanel.setSelected(new int[]{row, col});
        view.repaintSudokuGrid();
    }
}
