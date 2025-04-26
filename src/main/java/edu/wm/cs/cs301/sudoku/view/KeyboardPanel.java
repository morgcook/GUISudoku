package edu.wm.cs.cs301.sudoku.view;

import edu.wm.cs.cs301.sudoku.controller.KeyboardButtonAction;
import edu.wm.cs.cs301.sudoku.model.SudokuPuzzle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class KeyboardPanel extends JPanel {
    private final SudokuFrame view;
    private final SudokuPuzzle model;
    private final KeyboardButtonAction action;

    private final JPanel panel;

    private final JButton[] buttons;

    private int buttonIndex = 0;

    private final String[] letters = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "x"};

    public KeyboardPanel(SudokuFrame view, SudokuPuzzle model) {
        this.view = view;
        this.model = model;
        this.buttons = new JButton[10];
        this.action = new KeyboardButtonAction(view, model);
        this.panel = createMainPanel();
    }

    private JPanel createMainPanel() {
        JPanel panel = new JPanel(new GridLayout(2, 5));
        for (String letter : letters) {
            JButton b = new JButton(letter);
            setKeyBinding(b, letter);
            b.addActionListener(action);
            buttons[buttonIndex++] = b;
            panel.add(b);
        }

        return panel;
    }

    private void setKeyBinding(JButton button, String text) {
        InputMap inputMap = button.getInputMap(JButton.WHEN_IN_FOCUSED_WINDOW);
        if (text.equals("x")) {
            inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_BACK_SPACE, 0),
                    "action");
        } else {
            inputMap.put(KeyStroke.getKeyStroke(text), "action");
        }
        ActionMap actionMap = button.getActionMap();
        actionMap.put("action", action);
    }

    public JPanel getPanel() {
        return panel;
    }
}