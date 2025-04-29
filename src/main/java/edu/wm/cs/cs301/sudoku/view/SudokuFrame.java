package edu.wm.cs.cs301.sudoku.view;

import edu.wm.cs.cs301.sudoku.controller.GridMouseAdapter;
import edu.wm.cs.cs301.sudoku.model.SudokuPuzzle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * The main frame containing all the necessary visuals
 *    for the puzzle. Contains the keyboard panel and
 *    sudoku grid panel, as well as menu items.
 */

public class SudokuFrame {
    private final JFrame frame;

    private final SudokuPuzzle model;

    private final SudokuGridPanel sudokuGrid;

    private final KeyboardPanel keyboard;

    public SudokuFrame(SudokuPuzzle model) {
        this.model = model;
        this.sudokuGrid = new SudokuGridPanel(model, 50);
        this.sudokuGrid.addMouseListener(new GridMouseAdapter(this));
        addKeyBindings();
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
        newGameItem.addActionListener(e -> {
            model.initialize();
            repaintSudokuGrid();
        });
        menuBar.add(newGameItem);

        JMenuItem quitItem = new JMenuItem("Quit");
        quitItem.addActionListener(e -> shutdown());
        menuBar.add(quitItem);

        return menuBar;
    }

    private void addKeyBindings() {
        InputMap inputMap = sudokuGrid.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = sudokuGrid.getActionMap();

        inputMap.put(KeyStroke.getKeyStroke("UP")   , "up");
        actionMap.put("up", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[] selected = sudokuGrid.getSelected();
                if (selected[0] > 0) {
                    sudokuGrid.setSelected(new int[]{selected[0] - 1, selected[1]});
                    repaintSudokuGrid();
                }
            }
        });

        inputMap.put(KeyStroke.getKeyStroke("DOWN") , "down");
        actionMap.put("down", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[] selected = sudokuGrid.getSelected();
                if (selected[0] < 8) {
                    sudokuGrid.setSelected(new int[]{selected[0] + 1, selected[1]});
                    repaintSudokuGrid();
                }
            }
        });

        inputMap.put(KeyStroke.getKeyStroke("LEFT") , "left");
        actionMap.put("left", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[] selected = sudokuGrid.getSelected();
                if (selected[1] > 0) {
                    sudokuGrid.setSelected(new int[]{selected[0], selected[1] - 1});
                    repaintSudokuGrid();
                }
            }
        });

        inputMap.put(KeyStroke.getKeyStroke("RIGHT"), "right");
        actionMap.put("right", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[] selected = sudokuGrid.getSelected();
                if (selected[1] < 8) {
                    sudokuGrid.setSelected(new int[]{selected[0], selected[1] + 1});
                    repaintSudokuGrid();
                }
            }
        });
    }

    /**
     * Closes the main window and
     *    ends the program.
     */
    public void shutdown() {
        frame.dispose();
        System.exit(0);
    }

    /**
     *  Signals the sudoku grid panel to
     *      repaint the grid
     */
    public void repaintSudokuGrid() {
        sudokuGrid.repaint();
    }

    public JFrame getFrame() {
        return this.frame;
    }

    public SudokuGridPanel getSudokuGrid() {
        return sudokuGrid;
    }
}
