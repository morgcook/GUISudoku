package edu.wm.cs.cs301.sudoku.view;

import edu.wm.cs.cs301.sudoku.model.SudokuPuzzle;
import edu.wm.cs.cs301.sudoku.model.SudokuResponse;

import javax.swing.*;
import java.awt.*;

// Much of the code for the visualization of the SudokuGridPanel
//   is derived from the previous WordleGridPanel implementation
public class SudokuGridPanel extends JPanel {
    private final int MARGIN = 10;

    private final SudokuPuzzle model;

    private final int width;

    private final Rectangle[][] grid;

    private int[] selected;

    public SudokuGridPanel(SudokuPuzzle model, int width) {
        this.model = model;
        this.width = width;

        this.setPreferredSize(new Dimension(width * 9 + 2 * MARGIN, width * 9 + 2 * MARGIN));

        for (int i = 0; i < 9; i++) {
            if (model.getGrid()[0][i] == null) {
                selected = new int[]{0, i};
                break;
            }
        }

        this.grid = calculateRectangles();
    }

    private Rectangle[][] calculateRectangles() {
        Rectangle[][] grid = new Rectangle[9][9];

        int x = MARGIN;
        int y = MARGIN;

        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                grid[row][col] = new Rectangle(x, y, width, width);
                x += width;
            }
            x = MARGIN;
            y += width;
        }

        return grid;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        SudokuResponse[][] sudokuGrid = model.getGrid();
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                Rectangle r = grid[row][col];
                SudokuResponse response = sudokuGrid[row][col];
                if (row == selected[0] && col == selected[1]) {
                    int n = response == null ? 0 : response.getValue();
                    drawResponse(g2d, new SudokuResponse(n, Color.ORANGE), r);
                } else {
                    drawResponse(g2d, response, r);
                }
                drawRectangleOutline(g2d, r);
            }
        }
        drawMajorOutline(g2d);
    }

    private void drawResponse(Graphics2D g2d, SudokuResponse response, Rectangle r) {
        if (response != null) {
            g2d.setColor(response.getBackground());
            g2d.fillRect(r.x, r.y, r.width, r.height);
            g2d.setColor(Color.BLACK);
            if (response.getValue() != 0) {
                drawNumber(g2d, r, String.valueOf(response.getValue()));
            }
        } else {
            g2d.setColor(Color.WHITE);
            g2d.fillRect(r.x, r.y, r.width, r.height);
        }
    }

    private void drawRectangleOutline(Graphics2D g2d, Rectangle r) {
        int x = r.x;
        int y = r.y;
        int width = r.width;
        int height = r.height;
        g2d.setColor(Color.GRAY);
        g2d.setStroke(new BasicStroke(1f));
        g2d.drawRect(x, y, width, height);
    }

    private void drawMajorOutline(Graphics2D g2d) {
        // Separating subsquares
        g2d.setColor(Color.GRAY);
        g2d.setStroke(new BasicStroke(5));
        g2d.drawLine(MARGIN + width * 3, MARGIN, MARGIN + width * 3, MARGIN + width * 9);
        g2d.drawLine(MARGIN + width * 6, MARGIN, MARGIN + width * 6, MARGIN + width * 9);
        g2d.drawLine(MARGIN, MARGIN + width * 3, MARGIN + width * 9, MARGIN + width * 3);
        g2d.drawLine(MARGIN, MARGIN + width * 6, MARGIN + width * 9, MARGIN + width * 6);
        // Outline around entire grid
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(5));
        g2d.drawRect(MARGIN, MARGIN, width * 9, width * 9);
    }

    private void drawNumber(Graphics2D g2d, Rectangle r, String text) {
        Font font = new Font("Dialog", Font.BOLD, 34);
        FontMetrics fontMetrics = g2d.getFontMetrics(font);
        int x = r.x + (r.width - fontMetrics.stringWidth(text)) / 2;
        int y = r.y + ((r.height - fontMetrics.getHeight()) / 2)
                + fontMetrics.getAscent();
        g2d.setFont(font);
        g2d.drawString(text, x, y);
    }

    public int[] getSelected() {
        return selected;
    }

    public void setSelected(int[] selected) {
        this.selected = selected;
    }
}
