package edu.wm.cs.cs301.sudoku.controller;

import edu.wm.cs.cs301.sudoku.model.SudokuPuzzle;
import edu.wm.cs.cs301.sudoku.view.CompletionDialog;
import edu.wm.cs.cs301.sudoku.view.SudokuFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 *  The observer class of each respective JButton contained
 *    within the KeyboardPanel class. Handles the functionality
 *    behind each button press.
 */
public class KeyboardButtonAction extends AbstractAction {
    private final SudokuFrame view;

    private final SudokuPuzzle model;

    public KeyboardButtonAction(SudokuFrame view, SudokuPuzzle model) {
        this.view = view;
        this.model = model;
    }

    /**
     * The method that controls the outcome of each button press,
     *    putting or removing a number in a square assuming
     *    the move is valid. Shows the congratulations dialog
     *    when the board is filled.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton b = (JButton) e.getSource();
        String text = b.getActionCommand();
        int[] selected = view.getSudokuGrid().getSelected();
        boolean changed;

        // when the user wants to delete a square, 0 is used as input
        if (text.equals("X")) {
            changed = model.makeMove(selected[0], selected[1], 0);
        // otherwise, make move using the specified number
        } else {
            changed = model.makeMove(selected[0], selected[1], Integer.parseInt(text));
        }
        // if the input was successful, repaint the sudoku grid
        if (changed) view.repaintSudokuGrid();

        // if the grid is full, then the user has won and the
        //  completion dialog will appear
        if (model.isFull()) new CompletionDialog(view, model);
    }
}
