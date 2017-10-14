package org.academiadecodigo.pang.position;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

/**
 * Created by codecadet on 13/10/2017.
 */
public class Position {

    private int width;
    private int height;
    private Rectangle rectangle;

    public Position(int x, int y, int width, int height) {
        this.width = width;
        this.height = height;

        rectangle = new Rectangle(x, y, width, height);
        rectangle.setColor(Color.BLUE);
        rectangle.fill();
    }

    public void translate(int dx,int dy){
        rectangle.translate(dx, dy);

    }

    public boolean equals(Position compare) {
        throw new UnsupportedOperationException();
    }

    public int getX() {
        return rectangle.getX();
    }

    public int getY() {
        return rectangle.getY();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

}
