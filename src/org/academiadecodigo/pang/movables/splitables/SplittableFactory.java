package org.academiadecodigo.pang.movables.splitables;

import org.academiadecodigo.pang.Game;
import org.academiadecodigo.pang.GameConstants;
import org.academiadecodigo.pang.directions.Direction;
import org.academiadecodigo.pang.movables.splitables.balls.Ball;
import org.academiadecodigo.pang.movables.splitables.balls.BallSize;

import java.util.LinkedList;

/**
 * Created by codecadet on 14/10/17.
 */
public abstract class SplittableFactory {

    public static LinkedList<Splittable> getSplittableList(Game g, int level) {

        LinkedList<Splittable> splittables = new LinkedList<>();
        Ball ball;

        switch (level) {
            case 1:
                ball = new Ball(g, 40, GameConstants.GAME_HEIGHT / 2, BallSize.LARGE, Direction.RIGHT);
                splittables.add(ball);
                break;

            case 2:
                ball = new Ball(g, 40, GameConstants.GAME_HEIGHT / 2, BallSize.LARGE, Direction.RIGHT);
                splittables.add(ball);

                ball = new Ball(g, GameConstants.GAME_WIDTH - 40 - BallSize.LARGE.getSize(), GameConstants.GAME_HEIGHT / 2, BallSize.LARGE, Direction.LEFT);
                splittables.add(ball);
                break;

            default:
            case 3:
                ball = new Ball(g, 40, GameConstants.GAME_HEIGHT / 2, BallSize.LARGE, Direction.RIGHT);
                splittables.add(ball);

                ball = new Ball(g, GameConstants.GAME_WIDTH - 40 - BallSize.LARGE.getSize(), GameConstants.GAME_HEIGHT / 2, BallSize.LARGE, Direction.LEFT);
                splittables.add(ball);

                ball = new Ball(g, GameConstants.GAME_WIDTH / 2 - BallSize.MEDIUM.getSize() / 2, GameConstants.GAME_HEIGHT / 2, BallSize.MEDIUM, null);
                splittables.add(ball);
                break;
        }

        return splittables;
    }
}
