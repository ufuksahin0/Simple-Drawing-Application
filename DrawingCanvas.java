package view;

import model.MyShape;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

// This is the panel where we do all the drawing.
public class DrawingCanvas extends JPanel {
    // A list to hold all the shapes we've drawn.
    private ArrayList<MyShape> shapes = new ArrayList<>();
    // A temporary shape for previewing before the mouse is released.
    private MyShape currentShape;

    public DrawingCanvas() {
        setBackground(Color.WHITE);
    }

    // Adds a shape to our list and repaints the screen.
    public void addShape(MyShape shape) {
        shapes.add(shape);
        repaint();
    }

    // Sets the preview shape while dragging the mouse.
    public void setCurrentShape(MyShape shape) {
        this.currentShape = shape;
        repaint();
    }

    // Removes the last shape from the list.
    public void undoLastShape() {
        if (!shapes.isEmpty()) {
            shapes.remove(shapes.size() - 1);
            repaint();
        }
    }

    // Clears all shapes from the canvas.
    public void clearCanvas() {
        shapes.clear();
        currentShape = null;
        repaint();
    }
    
    public ArrayList<MyShape> getShapes() {
        return shapes;
    }

    // This method is called by Swing to draw the component.
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        // Draw all the finalized shapes.
        for (MyShape shape : shapes) {
            shape.draw(g);
        }
        
        // Draw the preview shape if it exists.
        if (currentShape != null) {
            currentShape.draw(g);
        }
    }
}
