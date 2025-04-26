package edu.wm.cs.cs301.sudoku.view;

import edu.wm.cs.cs301.sudoku.controller.GridMouseAdapter;
import edu.wm.cs.cs301.sudoku.model.SudokuPuzzle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SudokuFrame {
    private final JFrame frame;

    private SudokuPuzzle model;

    private final SudokuGridPanel sudokuGrid;

    private final KeyboardPanel keyboard;

    public SudokuFrame(SudokuPuzzle model) {
        this.model = model;
        this.sudokuGrid = new SudokuGridPanel(model, 50);
        this.sudokuGrid.addMouseListener(new GridMouseAdapter(this));
        this.keyboard = new KeyboardPanel(this, model);
        this.frame = createAndShowGUI();
    }

    private JFrame createAndShowGUI() {
        JFrame frame = new JFrame("Sudoku");
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setJMenuBar(createMenuBar());
        frame.setResizable(false);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                shutdown();
            }
        });

        frame.add(sudokuGrid, BorderLayout.CENTER);
        frame.add(keyboard.getPanel(), BorderLayout.SOUTH);

        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);

        return frame;
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenuItem instructionsItem = new JMenuItem("Instructions");
        instructionsItem.addActionListener(event -> new InstructionsDialog(this));
        menuBar.add(instructionsItem);

        JMenuItem newGameItem = new JMenuItem("New Game");
        newGameItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.initialize();
                repaintSudokuGrid();
            }
        });
        menuBar.add(newGameItem);

        JMenuItem quitItem = new JMenuItem("Quit");
        quitItem.addActionListener(event -> shutdown());
        menuBar.add(quitItem);

        return menuBar;
    }

    public void shutdown() {
        frame.dispose();
        System.exit(0);
    }

    public JFrame getFrame() {
        return this.frame;
    }

    public SudokuGridPanel getSudokuGrid() {
        return sudokuGrid;
    }

    public void repaintSudokuGrid() {
        sudokuGrid.repaint();
    }
}
