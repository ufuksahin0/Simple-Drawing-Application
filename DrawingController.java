package controller;

import model.MyLine;
import model.MyOval;
import model.MyRectangle;
import model.MyShape;
import view.MainFrame;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

// Controller for the whole application
public class DrawingController {
    private MainFrame frame;
    private String currentShapeType = "Line";
    private Color currentColor = Color.BLACK;
    private boolean isFilled = false;
    private int startX, startY;

    public DrawingController(MainFrame frame) {
        this.frame = frame;
        initController();
    }

    // Sets up all the listeners for the GUI
    private void initController() {
        // Listener for the shape dropdown
        frame.addShapeSelectorListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentShapeType = (String) frame.getShapeSelector().getSelectedItem();
                frame.setStatus("Ready | Tool: " + currentShapeType);
            }
        });
        
        frame.addColorButtonListener(new ColorButtonListener());

        // Listener for the clear button
        frame.addClearButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getCanvas().clearCanvas();
                frame.setStatus("Canvas Cleared | Tool: " + currentShapeType);
            }
        });
        
        // Listener for the undo button
        frame.addUndoButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getCanvas().undoLastShape();
                frame.setStatus("Undo | Tool: " + currentShapeType);
            }
        });
        
        // Listener for the "filled" checkbox
        frame.addFilledCheckboxListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isFilled = frame.getFilledCheckbox().isSelected();
            }
        });

        // Add mouse listeners to the canvas
        DrawingMouseListener mouseListener = new DrawingMouseListener();
        frame.getCanvas().addMouseListener(mouseListener);
        frame.getCanvas().addMouseMotionListener(mouseListener);
        
        frame.setStatus("Ready | Tool: Line");
    }

    // Handles picking a new color
    private class ColorButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Color chosenColor = frame.chooseColor();
            if (chosenColor != null) {
                currentColor = chosenColor;
            }
        }
    }

    // This class handles all the mouse events for drawing
    private class DrawingMouseListener extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            startX = e.getX();
            startY = e.getY();
            frame.setStatus("Drawing... | Start: (" + startX + ", " + startY + ") | Tool: " + currentShapeType);
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            int endX = e.getX();
            int endY = e.getY();
            
            // Create a temporary shape for preview
            MyShape shape = null;
            switch (currentShapeType) {
                case "Rectangle":
                    shape = new MyRectangle(startX, startY, endX, endY, currentColor, isFilled);
                    break;
                case "Oval":
                    shape = new MyOval(startX, startY, endX, endY, currentColor, isFilled);
                    break;
                case "Line":
                default:
                    shape = new MyLine(startX, startY, endX, endY, currentColor, isFilled);
                    break;
            }
            frame.getCanvas().setCurrentShape(shape);
            
            frame.setStatus("Drawing... | Current: (" + endX + ", " + endY + ") | Tool: " + currentShapeType);
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            int endX = e.getX();
            int endY = e.getY();

            // Create the final shape and add it to the canvas
            MyShape shape = null;
            switch (currentShapeType) {
                case "Rectangle":
                    shape = new MyRectangle(startX, startY, endX, endY, currentColor, isFilled);
                    break;
                case "Oval":
                    shape = new MyOval(startX, startY, endX, endY, currentColor, isFilled);
                    break;
                case "Line":
                default:
                    shape = new MyLine(startX, startY, endX, endY, currentColor, isFilled);
                    break;
            }
            frame.getCanvas().addShape(shape);
            
            frame.getCanvas().setCurrentShape(null); // Clear the preview shape
            frame.setStatus("Shape Added | Tool: " + currentShapeType);
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            frame.setStatus("Mouse at: (" + e.getX() + ", " + e.getY() + ") | Tool: " + currentShapeType);
        }
    }
}
