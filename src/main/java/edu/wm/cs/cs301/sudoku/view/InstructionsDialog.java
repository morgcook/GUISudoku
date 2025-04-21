package edu.wm.cs.cs301.sudoku.view;

import javax.swing.*;
import java.awt.*;

public class InstructionsDialog extends JDialog {
    private SudokuFrame view;

    public InstructionsDialog(SudokuFrame view) {
        super(view.getFrame(), "Instructions", true);

        pack();
        setLocationRelativeTo(view.getFrame());
        setVisible(true);
    }
}
