package org.academiadecodigo.pang.movables.splitables;

import org.academiadecodigo.pang.Game;
import org.academiadecodigo.pang.GameConstants;
import org.academiadecodigo.pang.directions.Direction;
import org.academiadecodigo.pang.position.Position;

/**
 * Created by codecadet on 13/10/2017.
 */
public class Ball implements Splittable {

    private Position pos;
    private Game g;
    private Direction horizontalDirection;
    private Direction verticalDirection;
    private int size;
    private int speed;


    public Ball(Game g, int x, int y, int size, Direction dir) {
        this.size = size;

        pos = new Position(x, y, size, size);
        horizontalDirection = dir;
        verticalDirection = Direction.UP;

        this.g = g;
    }


    @Override
    public void move() {

        if (checkHorizontalBoundaries()) {
            horizontalDirection = horizontalDirection.getOpposite();
        }

        if (checkVerticalBoundaries()) {
            verticalDirection = verticalDirection.getOpposite();

            if (verticalDirection == Direction.UP) {
                int currentPosY = pos.getY();
                int maxY = g.getHeight() + g.getPADDING() - size;

                pos.translate(0, maxY - currentPosY);
                return;
            }

        }

        switch (horizontalDirection) {

            case RIGHT:
                moveRight();
                break;
            case LEFT:
                moveLeft();
                break;
        }

        switch (verticalDirection) {

            case UP:
                moveUp();
                break;
            case DOWN:
                moveDown();
                break;
        }
    }

    private void moveUp() {

        int pxHeight = g.getHeight() - GameConstants.BALL_MAX_HEIGHT;
        int speedInterval = pxHeight / 10;
        int speed = -1;

        for (int i = 10, j = 1; i > 0; i--, j++) {

            if (pos.getY() > g.getHeight() - (speedInterval * j) - GameConstants.PADDING) {
                speed = speed * i;
                break;
            }
        }

        pos.translate(0, speed);
    }

    private void moveRight() {
        pos.translate(2, 0);
    }

    private void moveDown() {
        int pxHeight = g.getHeight() - GameConstants.BALL_MAX_HEIGHT;
        int speedInterval = pxHeight / 10;
        int speed = 1;

        for (int i = 10, j = 1; i > 0; i--, j++) {

            if (pos.getY() > g.getHeight() - (speedInterval * j) - GameConstants.PADDING) {
                this.speed = speed * i;
                break;
            }
        }
        pos.translate(0, this.speed);
    }

    private void moveLeft() {
        pos.translate(-2, 0);
    }

    private boolean checkHorizontalBoundaries() {
        return (horizontalDirection == Direction.RIGHT && pos.getX() + pos.getWidth() >= g.getWidth() + g.getPADDING()) ||
                (horizontalDirection == Direction.LEFT && pos.getX() <= g.getPADDING());
    }

    private boolean checkVerticalBoundaries() {
        return (verticalDirection == Direction.UP && pos.getY() <= GameConstants.PADDING + GameConstants.BALL_MAX_HEIGHT) ||
                (verticalDirection == Direction.DOWN && pos.getY() + size + speed >= g.getHeight() + GameConstants.PADDING);
    }

    @Override
    public Splittable[] split() {


        if (size <= GameConstants.BALL_MIN_SIZE) {
            pos.delete();
            return new Splittable[]{};
        }

        Ball ball1 = new Ball(g, pos.getX(), pos.getY() + 25, size / 2, Direction.RIGHT);
        Ball ball2 = new Ball(g, pos.getX(), pos.getY() + 25, size / 2, Direction.LEFT);

        pos.delete();

        return new Splittable[]{ball1, ball2};
    }

    @Override
    public Position getPos() {
        return pos;
    }

    public String toString() {
        return "x = " + pos.getX() +
                "y = " + pos.getY() +
                "size = " + size;
    }
}
