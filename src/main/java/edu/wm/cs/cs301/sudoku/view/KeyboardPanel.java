package edu.wm.cs.cs301.sudoku.view;

import edu.wm.cs.cs301.sudoku.controller.KeyboardButtonAction;
import edu.wm.cs.cs301.sudoku.model.SudokuPuzzle;

import javax.swing.*;
import java.awt.*;

public class KeyboardPanel extends JPanel {
    private final SudokuFrame view;

    private final SudokuPuzzle model;

    private final KeyboardButtonAction action;

    private final JButton[] buttons;

    private int buttonIndex = 0;

    private final JPanel panel;

    public KeyboardPanel(SudokuFrame view, SudokuPuzzle model) {
        this.view = view;
        this.model = model;
        this.buttons = new JButton[10];
        this.action = new KeyboardButtonAction(view, model);
        this.panel = createMainPanel();
    }

    private JPanel createMainPanel() {
        JPanel panel = new JPanel(new GridLayout(2, 5));
        for (int i = 1; i <= 9; i++) {
            JButton b = new JButton(String.valueOf(i));
            b.addActionListener(action);
            buttons[buttonIndex++] = b;
            panel.add(b);
        }
        JButton x = new JButton("x");
        x.addActionListener(action);
        buttons[buttonIndex] = x;
        panel.add(x);

        return panel;
    }

    public JPanel getPanel() {
        return panel;
    }
}