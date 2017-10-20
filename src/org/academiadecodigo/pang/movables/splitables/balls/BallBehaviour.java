package org.academiadecodigo.pang.movables.splitables.balls;

import org.academiadecodigo.pang.GameConstants;

/**
 * Created by codecadet on 19/10/17.
 */
public class BallBehaviour {

    private boolean firstFall = true;
    private int maxSpeed = 6;
    private int yStart;
    private int maxY;
    private int size;

    public BallBehaviour(int yStart, int size, int intervals) {
        this.yStart = yStart;
        this.size = size;

        maxSpeed *= intervals;

        maxY = GameConstants.PADDING + GameConstants.GAME_HEIGHT - ((GameConstants.PLAYER_HEIGHT + GameConstants.BALL_MIN_SIZE + 55) * intervals);

        if (yStart > maxY) {
            firstFall = false;
        }
    }

    public int calculateYSpeed(int y) {

        if (firstFall) {
            return firstFall(y);
        }

        int heightInterval = GameConstants.GAME_HEIGHT - maxY;
        int speedInterval = heightInterval / maxSpeed;

        for (int i = 1, j = maxSpeed; i < maxSpeed + 1; i++, j--) {

            if (y < GameConstants.GAME_HEIGHT - (speedInterval * j) - GameConstants.PADDING) {
                return i;
            }
        }

        return maxSpeed;
    }

    private int firstFall(int y) {

        if (y + size >= GameConstants.GAME_HEIGHT - GameConstants.PADDING) {
            firstFall = false;
        }

        int heightInterval = GameConstants.GAME_HEIGHT - yStart;
        int speedInterval = heightInterval / maxSpeed;

        for (int i = maxSpeed, j = 1; i > 0; i--, j++) {

            if (y > GameConstants.GAME_HEIGHT - (speedInterval * j) - GameConstants.PADDING) {
                return i;
            }
        }

        return maxSpeed;
    }
}
