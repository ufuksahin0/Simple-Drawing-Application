package model;

import java.awt.Color;
import java.awt.Graphics;

public class MyOval extends MyShape {

    // Constructor for an oval. Just calls the parent constructor.
    public MyOval(int x1, int y1, int x2, int y2, Color color, boolean filled) {
        super(x1, y1, x2, y2, color, filled);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(getColor());

        // Same as a rectangle, we need the top-left corner and width/height
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
        
        // if the filled checkbox is selected, draw a filled oval
        if (isFilled()) {
            g.fillOval(x, y, width, height);
        } else {
            g.drawOval(x, y, width, height);
        }
    }
}
