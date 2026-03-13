package model;

import java.awt.Color;
import java.awt.Graphics;

public class MyRectangle extends MyShape {

    // Constructor for a rectangle. Just calls the parent constructor.
    public MyRectangle(int x1, int y1, int x2, int y2, Color color, boolean filled) {
        super(x1, y1, x2, y2, color, filled);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(getColor());

        // figure out the top left corner's x and y and the width and height
        int x, y, width, height;

        if (getX1() < getX2()) {
            x = getX1();
            width = getX2() - getX1();
        } else {
            x = getX2();
            width = getX1() - getX2();
        }

        if (getY1() < getY2()) {
            y = getY1();
            height = getY2() - getY1();
        } else {
            y = getY2();
            height = getY1() - getY2();
        }

        // Check if the "filled" checkbox was checked.
        if (isFilled()) {
            g.fillRect(x, y, width, height); // Filled rectangle
        } else {
            g.drawRect(x, y, width, height); // Outline only
        }
    }
}
