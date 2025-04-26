package edu.wm.cs.cs301.sudoku.view;

import javax.swing.*;
import java.awt.*;

public class CompletionDialog extends JDialog {
    private final SudokuFrame view;

    public CompletionDialog(SudokuFrame view) {
        super(view.getFrame(), "", true);

        this.view = view;

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
        JButton playButton = new JButton("Play again");

        panel.add(quitButton);
        panel.add(playButton);

        return panel;
    }
}
