package edu.wm.cs.cs301.sudoku.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class SudokuFrame {
    private final JFrame frame;

    private final SudokuGridPanel sudokuGrid;

    private final KeyboardPanel keyboard;

    public SudokuFrame() {
        this.sudokuGrid = new SudokuGridPanel(500);
        this.keyboard = new KeyboardPanel();
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
        //newGameItem.addActionListener(); future implementation
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
}
