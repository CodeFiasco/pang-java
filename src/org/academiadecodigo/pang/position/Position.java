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

    public void delete() {
        rectangle.delete();
    }

    public boolean overlaps(Position compare) {

        // X axis check
        boolean topLeftCorner = getX() >= compare.getX() && getX() <= compare.getWidth() + compare.getX();
        // Y axis check
        topLeftCorner = topLeftCorner && getY() >= compare.getY() && getY() <= compare.getHeight() + compare.getY();

        // X axis check
        boolean topRightCorner = getX() + width >= compare.getX() && getX() + width <= compare.getX() + compare.getWidth();
        // Y axis check
        topRightCorner = topRightCorner && getY() >= compare.getY() && getY() <= compare.getHeight() + compare.getY();

        // X axis check
        boolean bottomLeftCorner = getX() >= compare.getX() && getX() <= compare.getX() + compare.getWidth();
        // Y axis check
        bottomLeftCorner = bottomLeftCorner && getY() + height >= compare.getY() && getY() + height <= compare.getY() + compare.getHeight();

        // X axis check
        boolean bottomRightCorner = getX() + width >= compare.getX() && getX() + width <= compare.getX() + compare.getWidth();
        // Y axis check
        bottomRightCorner = bottomRightCorner && getY() + height >= compare.getY() && getY() + height <= compare.getY() + compare.getHeight();

        return topLeftCorner || topRightCorner || bottomLeftCorner || bottomRightCorner;
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

    @Override
    public String toString() {
        return "x = " + getX() +
                " y = " + getY() +
                " width = " + getWidth() +
                " height = " + getHeight();
    }
}















