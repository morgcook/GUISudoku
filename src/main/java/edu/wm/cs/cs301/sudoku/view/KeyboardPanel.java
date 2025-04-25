package edu.wm.cs.cs301.sudoku.view;

import javax.swing.*;
import java.awt.*;

public class KeyboardPanel extends JPanel {
    private final JButton[] buttons;

    private int buttonIndex = 0;

    private final JPanel panel;

    public KeyboardPanel() {
        this.buttons = new JButton[10];
        this.panel = createMainPanel();
    }

    private JPanel createMainPanel() {
        JPanel panel = new JPanel(new GridLayout(2, 5));
        for (int i = 1; i <= 9; i++) {
            JButton b = new JButton(String.valueOf(i));
            buttons[buttonIndex++] = b;
            panel.add(b);
        }
        JButton x = new JButton("x");
        buttons[buttonIndex] = x;
        panel.add(x);

        return panel;
    }

    public JPanel getPanel() {
        return panel;
    }
}