package edu.wm.cs.cs301.sudoku.view;

import edu.wm.cs.cs301.sudoku.controller.KeyboardButtonAction;
import edu.wm.cs.cs301.sudoku.model.SudokuPuzzle;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Contains the interactable buttons for the user to input numbers
 *    using the mouse (and keyboard).
 */
public class KeyboardPanel extends JPanel {
    private final KeyboardButtonAction action;

    private final JPanel panel;

    private final String[] letters = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "X"};

    public KeyboardPanel(SudokuFrame view, SudokuPuzzle model) {
        this.action = new KeyboardButtonAction(view, model);
        this.panel = createMainPanel();
    }

    private JPanel createMainPanel() {
        JPanel panel = new JPanel(new GridLayout(2, 5));
        int MARGIN = 10;
        panel.setBorder(new EmptyBorder(MARGIN /2, MARGIN, MARGIN, MARGIN));
        for (String letter : letters) {
            JButton b = createButton(letter);
            panel.add(b);
        }

        return panel;
    }

    private void setKeyBinding(JButton button, String text) {
        InputMap inputMap = button.getInputMap(JButton.WHEN_IN_FOCUSED_WINDOW);
        if (text.equals("X")) {
            inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_BACK_SPACE, 0),
                    "action");
        } else {
            inputMap.put(KeyStroke.getKeyStroke(text), "action");
        }
        ActionMap actionMap = button.getActionMap();
        actionMap.put("action", action);
    }

    private JButton createButton(String text) {
        JButton b = new JButton(text);
        b.setFocusPainted(false);
        b.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        b.setPreferredSize(new Dimension(10, 50));
        b.setBackground(Color.LIGHT_GRAY);
        b.setFont(new Font("Dialog", Font.BOLD, 20));
        setKeyBinding(b, text);
        b.addActionListener(action);
        return b;
    }

    public JPanel getPanel() {
        return panel;
    }
}