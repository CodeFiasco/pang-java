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
        }

        switch (horizontalDirection) {

            case RIGHT:
                moveRight();
                break;
            case LEFT:
                moveLeft();
                break;
        }

        switch (verticalDirection){

            case UP:
                moveUp();
                break;
            case DOWN:
                moveDown();
                break;
        }
    }

    private void moveUp() {
        pos.translate(0, -1);
    }

    private void moveRight() {
        pos.translate(1, 0);
    }

    private void moveDown() {
        pos.translate(0,1);
    }

    private void moveLeft() {
        pos.translate(-1, 0);
    }

    private boolean checkHorizontalBoundaries() {
        return (horizontalDirection == Direction.RIGHT && pos.getX() + pos.getWidth() >= g.getWidth() + g.getPADDING()) ||
                (horizontalDirection == Direction.LEFT && pos.getX() <= g.getPADDING());
    }

    private boolean checkVerticalBoundaries() {
        return (verticalDirection == Direction.UP && pos.getY() <= GameConstants.PADDING + 20) ||
                (verticalDirection == Direction.DOWN && pos.getY() + size >= g.getHeight() + GameConstants.PADDING);
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
