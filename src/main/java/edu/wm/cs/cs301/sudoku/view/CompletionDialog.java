package edu.wm.cs.cs301.sudoku.view;

import edu.wm.cs.cs301.sudoku.model.SudokuPuzzle;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Creates the congratulations pop-up window upon
 *    completing the puzzle.
 */
public class CompletionDialog extends JDialog {
    private final SudokuFrame view;
    private final SudokuPuzzle model;

    public CompletionDialog(SudokuFrame view, SudokuPuzzle model) {
        super(view.getFrame(), "Congratualtions", true);

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
        panel.setBorder(new EmptyBorder(0, 10, 0, 10));
        JLabel label = new JLabel("You Win!");
        label.setFont(new Font("Dialog", Font.BOLD, 36));
        panel.add(label);
        return panel;
    }

    private JPanel buttonPanel() {
        JPanel panel = new JPanel(new FlowLayout());

        JButton playButton = getButton("Play again");
        playButton.addActionListener(new PlayAction());

        JButton quitButton = getButton("Quit");
        quitButton.addActionListener(new QuitAction());

        panel.add(playButton);
        panel.add(quitButton);

        return panel;
    }

    private JButton getButton(String text) {
        JButton b = new JButton(text);
        b.setFocusPainted(false);
        b.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        b.setPreferredSize(new Dimension(100, 30));
        b.setBackground(Color.LIGHT_GRAY);
        b.setFont(new Font("Dialog", Font.BOLD, 15));
        return b;
    }

    private class QuitAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
            view.shutdown();
        }
    }

    private class PlayAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
            model.initialize();
            view.repaintSudokuGrid();
        }
    }
}
