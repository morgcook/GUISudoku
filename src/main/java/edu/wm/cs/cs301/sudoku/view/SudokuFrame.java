package edu.wm.cs.cs301.sudoku.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class SudokuFrame {
    private JFrame frame;

    public SudokuFrame() {
        this.frame = createAndShowGUI();
    }

    private JFrame createAndShowGUI() {
        JFrame frame = new JFrame("Sudoku");
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setResizable(false);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                shutdown();
            }
        });

        frame.pack();
        frame.setVisible(true);

        return frame;
    }

    public void shutdown() {
        frame.dispose();
        System.exit(0);
    }
}
