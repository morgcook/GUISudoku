package edu.wm.cs.cs301.sudoku.view;

import javax.swing.*;
import java.awt.*;

public class SudokuGridPanel extends JPanel {
    private static final long serialVersionUID = 1L;

    private final int numWidth;

    private final Rectangle[][] grid;

    public SudokuGridPanel(int width) {
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

        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                Rectangle r = grid[row][col];
                drawOutline(g2d, r);
            }
        }
    }

    private void drawOutline(Graphics2D g2d, Rectangle r) {
        int x = r.x + 1;
        int y = r.y + 1;
        int width = r.width - 2;
        int height = r.height - 2;
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(1f));
        g2d.drawLine(x, y, x + width, y);
        g2d.drawLine(x, y + height, x + width, y + height);
        g2d.drawLine(x, y, x, y + height);
        g2d.drawLine(x + width, y, x + width, y + height);
    }
}
