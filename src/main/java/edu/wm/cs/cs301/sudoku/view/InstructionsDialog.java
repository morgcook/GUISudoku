package edu.wm.cs.cs301.sudoku.view;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

/**
 *  Creates the instructions pop-up window created by the
 *      "instructions" menu item.
 */
public class InstructionsDialog extends JDialog {
    public InstructionsDialog(SudokuFrame view) {
        super(view.getFrame(), "Instructions", true);

        add(createMainPanel(), BorderLayout.CENTER);
        add(createButtonPanel(), BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(view.getFrame());
        setVisible(true);
    }

    private JPanel createMainPanel() {
        JPanel panel = new JPanel();

        URL url = InstructionsDialog.class.getResource("/instructions.html");

        JEditorPane editorPane = new JEditorPane();
        editorPane.setEditable(false);
        editorPane.setContentType("text/html");
        try {
            editorPane.setPage(url);
        } catch (IOException e) {
            e.printStackTrace();
        }

        JScrollPane scrollPane = new JScrollPane(editorPane);
        scrollPane.setPreferredSize(new Dimension(400, 400));
        panel.add(scrollPane);

        return panel;
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel();
        JButton button = new JButton("Cancel");
        button.addActionListener(e -> dispose());

        panel.add(button);
        return panel;
    }
}
