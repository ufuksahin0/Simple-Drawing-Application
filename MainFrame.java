package view;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.ActionEvent;

public class MainFrame extends JFrame {
    private DrawingCanvas canvas;
    private JComboBox<String> shapeSelector;
    private JButton colorButton;
    private JButton clearButton;
    private JButton undoButton;
    private JCheckBox filledCheckbox;
    private JLabel statusBar;

    public MainFrame() {
        setTitle("Simple Drawing Application");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        canvas = new DrawingCanvas();
        add(canvas, BorderLayout.CENTER);

        JPanel controlPanel = new JPanel();
        shapeSelector = new JComboBox<>(new String[]{"Line", "Rectangle", "Oval"});
        colorButton = new JButton("Choose Color");
        clearButton = new JButton("Clear");
        undoButton = new JButton("Undo");
        filledCheckbox = new JCheckBox("Filled");

        controlPanel.add(shapeSelector);
        controlPanel.add(colorButton);
        controlPanel.add(clearButton);
        controlPanel.add(undoButton);
        controlPanel.add(filledCheckbox);
        add(controlPanel, BorderLayout.NORTH);

        statusBar = new JLabel("Ready");
        add(statusBar, BorderLayout.SOUTH);
    }

    public DrawingCanvas getCanvas() {
        return canvas;
    }

    public JComboBox<String> getShapeSelector() {
        return shapeSelector;
    }
    
    public JCheckBox getFilledCheckbox() {
        return filledCheckbox;
    }

    public void setStatus(String text) {
        statusBar.setText(text);
    }

    public void addShapeSelectorListener(ActionListener listener) {
        shapeSelector.addActionListener(listener);
    }

    public void addColorButtonListener(ActionListener listener) {
        colorButton.addActionListener(listener);
    }

    public void addClearButtonListener(ActionListener listener) {
        clearButton.addActionListener(listener);
    }
    
    public void addUndoButtonListener(ActionListener listener) {
        undoButton.addActionListener(listener);
    }
    
    public void addFilledCheckboxListener(ActionListener listener) {
        filledCheckbox.addActionListener(listener);
    }

    public Color chooseColor() {
        final JColorChooser colorChooser = new JColorChooser(Color.BLACK);
        // Remove the preview panel
        colorChooser.setPreviewPanel(new JPanel());

        // Use a variable to hold the selected color
        final Color[] selectedColor = new Color[1];

        // This listener will be called when the user clicks "OK"
        ActionListener okListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedColor[0] = colorChooser.getColor();
            }
        };

        // Create the dialog
        JDialog dialog = JColorChooser.createDialog(this, "Choose a Color", true, colorChooser, okListener, null);
        dialog.setVisible(true);

        return selectedColor[0];
    }
}
