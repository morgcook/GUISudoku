package edu.wm.cs.cs301.sudoku.view;

import edu.wm.cs.cs301.sudoku.model.SudokuPuzzle;
import edu.wm.cs.cs301.sudoku.model.SudokuResponse;

import javax.swing.*;
import java.awt.*;

public class SudokuGridPanel extends JPanel {
    private static final long serialVersionUID = 1L;

    private final SudokuPuzzle model;

    private final int numWidth;

    private final Rectangle[][] grid;

    public SudokuGridPanel(SudokuPuzzle model, int width) {
        this.model = model;
        this.numWidth = width / 9;

        this.setPreferredSize(new Dimension(width, width));

        // The Sudoku grid code primarily references similar code
        //  in the previous Wordle project.
        this.grid = calculateRectangles();
    }

    private Rectangle[][] calculateRectangles() {
        Rectangle[][] grid = new Rectangle[9][9];

        int x = 0;
        int y = 0;

        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                grid[row][col] = new Rectangle(x, y, numWidth, numWidth);
                x += numWidth;
            }
            x = 0;
            y += numWidth;
        }

        return grid;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        SudokuResponse[][] sudokuGrid = model.getGrid();
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                Rectangle r = grid[row][col];
                SudokuResponse response = sudokuGrid[row][col];
                drawResponse(g2d, response, r);
                drawOutline(g2d, r);
            }
        }
    }

    private void drawResponse(Graphics2D g2d, SudokuResponse response, Rectangle r) {
        if (response != null) {
            g2d.setColor(response.getBackground());
            g2d.fillRect(r.x, r.y, r.width, r.height);
            g2d.setColor(Color.BLACK);
            g2d.drawString(String.valueOf(response.getValue()), r.x+r.width/2, r.y+r.height/2);
        }
    }

    private void drawOutline(Graphics2D g2d, Rectangle r) {
        int x = r.x;
        int y = r.y;
        int width = r.width;
        int height = r.height;
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(1f));
        g2d.drawLine(x, y, x + width, y);
        g2d.drawLine(x, y + height, x + width, y + height);
        g2d.drawLine(x, y, x, y + height);
        g2d.drawLine(x + width, y, x + width, y + height);
    }
}
