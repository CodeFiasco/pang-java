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
    private Direction direction;
    private int size;


    public Ball(Game g, int x, int y, int size, Direction dir) {
        this.size = size;

        pos = new Position(x, y, size, size);
        direction = dir;

        this.g = g;
    }


    @Override
    public void move() {

        if (checkBoundaries()) {
            direction = direction.getOpposite();
        }

        switch (direction) {

            case UP:
                moveUp();
                break;
            case RIGHT:
                moveRight();
                break;
            case DOWN:
                moveDown();
                break;
            case LEFT:
                moveLeft();
                break;
        }
    }

    private void moveUp() {

    }

    private void moveRight() {
        pos.translate(1, 0);
    }

    private void moveDown() {

    }

    private void moveLeft() {
        pos.translate(-1, 0);
    }

    private boolean checkBoundaries() {
        return (direction == Direction.RIGHT && pos.getX() + pos.getWidth() >= g.getWidth() + g.getPADDING()) ||
                (direction == Direction.LEFT && pos.getX() <= g.getPADDING());
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
