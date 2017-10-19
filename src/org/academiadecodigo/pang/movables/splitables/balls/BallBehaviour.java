package org.academiadecodigo.pang.movables.splitables.balls;

import org.academiadecodigo.pang.Game;
import org.academiadecodigo.pang.GameConstants;
import org.academiadecodigo.pang.position.YCoordinates;

/**
 * Created by codecadet on 19/10/17.
 */
public class BallBehaviour {

    private boolean firstFall = true;
    private int maxSpeed = 6;
    private int yStart;
    private int maxY;
    private int size;

    public BallBehaviour(int yStart, int size) {
        this.yStart = yStart;
        this.size = size;

        int jumps = 1;

        if (size == 50) {
            jumps = 2;
            maxSpeed = 12;
        }
        if (size == 100) {
            jumps = 3;
            maxSpeed = 16;
        }


        maxY = YCoordinates.converter((GameConstants.PLAYER_HEIGHT + GameConstants.BALL_MIN_SIZE + 55) * jumps);

        if (yStart > maxY) {
            firstFall = false;
        }
    }

    public int calculateYSpeed(int y) {

        if (firstFall) {
            return firstFall(y);
        }

        int heightInterval = Game.getHeight() - maxY;
        int speedInterval = heightInterval / maxSpeed;

        for (int i = 1, j = maxSpeed; i < maxSpeed + 1; i++, j--) {

            if (y < Game.getHeight() - (speedInterval * j) - GameConstants.PADDING) {
                return i;
            }
        }

        return maxSpeed;
    }

    private int firstFall(int y) {

        if (y + size >= Game.getHeight() - GameConstants.PADDING) {
            firstFall = false;
        }

        int heightInterval = Game.getHeight() - yStart;
        int speedInterval = heightInterval / maxSpeed;

        for (int i = maxSpeed, j = 1; i > 0; i--, j++) {

            if (y > Game.getHeight() - (speedInterval * j) - GameConstants.PADDING) {
                return i;
            }
        }

        return maxSpeed;
    }
}
