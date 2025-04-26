package edu.wm.cs.cs301.sudoku.controller;

import edu.wm.cs.cs301.sudoku.view.SudokuFrame;
import edu.wm.cs.cs301.sudoku.view.SudokuGridPanel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GridMouseAdapter extends MouseAdapter {
    private final SudokuFrame view;

    public GridMouseAdapter(SudokuFrame view) {
        this.view = view;
    }

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
