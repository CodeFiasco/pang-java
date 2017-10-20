package org.academiadecodigo.pang.movables.splitables.balls;

import org.academiadecodigo.pang.Game;
import org.academiadecodigo.pang.GameConstants;
import org.academiadecodigo.pang.directions.Direction;
import org.academiadecodigo.pang.movables.splitables.Splittable;
import org.academiadecodigo.pang.position.Position;

/**
 * Created by codecadet on 13/10/2017.
 */
public class Ball implements Splittable {

    private Position pos;

    private Direction horizontalDirection;
    private Direction verticalDirection;
    private int speed;

    private BallSize size;
    private BallBehaviour ballBehaviour;
    private int jumpUp;
    private int flatline;

    public Ball(int x, int y, BallSize size, Direction dir) {
        this.size = size;

        pos = new Position(x, y, size.getSize(), size.getSize(), "black-ball-" + size.getSize() + ".png");
        horizontalDirection = dir;
        verticalDirection = Direction.UP;

        jumpUp = 30;

        if (y > GameConstants.PADDING + GameConstants.GAME_HEIGHT - ((GameConstants.PLAYER_HEIGHT + GameConstants.BALL_MIN_SIZE + 55) * size.getHeightLevels())) {
            jumpUp = 0;
        }

        ballBehaviour = new BallBehaviour(y - jumpUp, size.getSize(), size.getHeightLevels());
        flatline = 0;
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
                int maxY = GameConstants.GAME_HEIGHT + GameConstants.PADDING - size.getSize();

                pos.translate(0, maxY - currentPosY);
                return;
            }

            flatline = 12;

        }

        if (jumpUp > 0) {
            speed = 1;
            jumpUp--;
            verticalDirection = Direction.UP;
        }

        else if (flatline > 0 && jumpUp == 0) {
            speed = 0;
            flatline--;
        }

        else {
            speed = ballBehaviour.calculateYSpeed(pos.getY());
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
        return (horizontalDirection == Direction.RIGHT && pos.getX() + pos.getWidth() >= GameConstants.GAME_WIDTH + GameConstants.PADDING) ||
                (horizontalDirection == Direction.LEFT && pos.getX() <= GameConstants.PADDING);
    }

    private boolean checkVerticalBoundaries() {

        return (verticalDirection == Direction.UP && pos.getY() <= GameConstants.PADDING + GameConstants.GAME_HEIGHT - ((GameConstants.PLAYER_HEIGHT + GameConstants.BALL_MIN_SIZE + 55) * size.getHeightLevels())) ||
                (verticalDirection == Direction.DOWN && pos.getY() + size.getSize() + speed >= GameConstants.GAME_HEIGHT + GameConstants.PADDING);
    }

    @Override
    public Splittable[] split() {

        BallSize nextSize = size.getSmallerSize();

        if (nextSize == null) {
            pos.delete();
            return new Splittable[]{};
        }

        Ball ball1 = new Ball(pos.getX(), pos.getY() + 25, nextSize, Direction.RIGHT);
        Ball ball2 = new Ball(pos.getX(), pos.getY() + 25, nextSize, Direction.LEFT);

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
