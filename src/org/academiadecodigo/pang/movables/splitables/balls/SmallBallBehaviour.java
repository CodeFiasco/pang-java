package org.academiadecodigo.pang.movables.splitables.balls;

import org.academiadecodigo.pang.Game;
import org.academiadecodigo.pang.GameConstants;
import org.academiadecodigo.pang.position.YCoordinates;

/**
 * Created by codecadet on 19/10/17.
 */
public class SmallBallBehaviour implements BallBehaviour {

    private boolean firstFall = true;
    private int maxSpeed = 6;
    private int yStart;
    private int maxY;

    public SmallBallBehaviour(int yStart, int size) {
        this.yStart = yStart;

        maxY = YCoordinates.converter(GameConstants.PLAYER_HEIGHT + GameConstants.BALL_MIN_SIZE + 55);

        if (yStart > maxY) {
            firstFall = false;
        }
    }

    @Override
    public int calculateYSpeed(int y) {

        if (firstFall) {
            return firstFall(y);
        }


        int heightInterval = Game.getHeight() - maxY;
        int speedInterval = heightInterval / maxSpeed;

        for (int i = maxSpeed, j = 1; i > 0; i--, j++) {

            if (y > Game.getHeight() - (speedInterval * j) - GameConstants.PADDING) {
                return i;
            }
        }

        return maxSpeed;
    }

    private int firstFall(int y) {

        if (YCoordinates.converter(GameConstants.BALL_MIN_SIZE) == y) {
            firstFall = false;
        }

        int heightInterval = Game.getHeight() - yStart;
        int speedInterval = heightInterval / maxSpeed + 5;

        for (int i = maxSpeed + 5, j = 1; i > 0; i--, j++) {

            if (y > Game.getHeight() - (speedInterval * j) - GameConstants.PADDING) {
                return i;
            }
        }

        return maxSpeed + 5;
    }
}
