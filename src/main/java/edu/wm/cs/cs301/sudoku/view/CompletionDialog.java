package edu.wm.cs.cs301.sudoku.view;

import edu.wm.cs.cs301.sudoku.model.SudokuPuzzle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CompletionDialog extends JDialog {
    private final SudokuFrame view;
    private final SudokuPuzzle model;

    public CompletionDialog(SudokuFrame view, SudokuPuzzle model) {
        super(view.getFrame(), "", true);

        this.view = view;
        this.model = model;

        add(mainTextPanel(), BorderLayout.CENTER);
        add(buttonPanel(), BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(view.getFrame());
        setVisible(true);
    }

    private JPanel mainTextPanel() {
        JPanel panel = new JPanel();
        panel.add(new JLabel("Congrats!"));
        return panel;
    }

    private JPanel buttonPanel() {
        JPanel panel = new JPanel(new GridLayout(1, 2));

        JButton quitButton = new JButton("Quit");
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                view.shutdown();
            }
        });

        JButton playButton = new JButton("Play again");
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                model.initialize();
                view.repaintSudokuGrid();
            }
        });

        panel.add(quitButton);
        panel.add(playButton);

        return panel;
    }
}
