package org.academiadecodigo.pang.movables.splitables.balls;

import org.academiadecodigo.pang.Game;
import org.academiadecodigo.pang.GameConstants;
import org.academiadecodigo.pang.directions.Direction;
import org.academiadecodigo.pang.movables.splitables.Splittable;
import org.academiadecodigo.pang.position.Position;
import org.academiadecodigo.pang.position.YCoordinates;

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
    private BallBehaviour ballBehaviour;


    public Ball(Game g, int x, int y, int size, Direction dir) {
        this.size = size;

        pos = new Position(x, y, size, size, "red-ball-" + size + ".png");
        horizontalDirection = dir;
        verticalDirection = Direction.UP;

        ballBehaviour = new BallBehaviour(y, size);

        this.g = g;
    }


    @Override
    public void move() {

        speed = ballBehaviour.calculateYSpeed(pos.getY());

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


        switch (verticalDirection) {

            case UP:
                moveUp();
                break;
            case DOWN:
                moveDown();
                break;
        }

        if (horizontalDirection == null) {
            return;
        }

        switch (horizontalDirection) {

            case RIGHT:
                moveRight();
                break;
            case LEFT:
                moveLeft();
                break;
            default:
                break;
        }
    }

    private void moveUp() {
        pos.translate(0, speed * -1);
    }

    private void moveRight() {
        pos.translate(2, 0);
    }

    private void moveDown() {
        pos.translate(0, speed);
    }

    private void moveLeft() {
        pos.translate(-2, 0);
    }

    private boolean checkHorizontalBoundaries() {
        return (horizontalDirection == Direction.RIGHT && pos.getX() + pos.getWidth() >= g.getWidth() + g.getPADDING()) ||
                (horizontalDirection == Direction.LEFT && pos.getX() <= g.getPADDING());
    }

    private boolean checkVerticalBoundaries() {

        int jumps = 1;

        if (size == 50) {
            jumps = 2;
        }
        if (size == 100) {
            jumps = 3;
        }

        return (verticalDirection == Direction.UP && pos.getY() <= YCoordinates.converter((GameConstants.PLAYER_HEIGHT + GameConstants.BALL_MIN_SIZE + 55) * jumps)) ||
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
