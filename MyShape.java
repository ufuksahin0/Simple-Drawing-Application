package model;

import java.awt.Color;
import java.awt.Graphics;

// This is the main "parent" class for all our shapes.
// It's abstract, which means we can't create a "MyShape" object directly.
// Instead, we have to create a Line, Rectangle, etc.
public abstract class MyShape {
    // Coordinates for the shape. (x1, y1) is the start point, (x2, y2) is the end point.
    protected int x1, y1, x2, y2;
    // The color of the shape.
    protected Color color;
    // A boolean to check if the shape should be filled or just an outline.
    protected boolean filled;

    // Constructor to create a new shape.
    public MyShape(int x1, int y1, int x2, int y2, Color color, boolean filled) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.color = color;
        this.filled = filled;
    }

    // A "getter" method to check if the shape is filled.
    public boolean isFilled() {
        return filled;
    }

    public int getX1() {
        return x1;
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public int getY1() {
        return y1;
    }

    public void setY1(int y1) {
        this.y1 = y1;
    }

    public int getX2() {
        return x2;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    public int getY2() {
        return y2;
    }

    public void setY2(int y2) {
        this.y2 = y2;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    // Every shape that is a "child" of MyShape MUST have its own draw method.
    public abstract void draw(Graphics g);
}
