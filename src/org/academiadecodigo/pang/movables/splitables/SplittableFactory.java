package org.academiadecodigo.pang.movables.splitables;

import org.academiadecodigo.pang.Game;
import org.academiadecodigo.pang.directions.Direction;

import java.util.LinkedList;

/**
 * Created by codecadet on 14/10/17.
 */
public abstract class SplittableFactory {

    public static LinkedList<Splittable> getSplittableList(Game g, int level) {

        LinkedList<Splittable> splittables = new LinkedList<>();
        Splittable[] objects;
        Ball ball;

        switch (level) {
            case 0:
                ball = new Ball(g, 40, 40, 100, Direction.RIGHT);
                objects = new Splittable[]{ball};
                break;

            case 1:
                objects = new Splittable[2];

                ball = new Ball(g, 40, 40, 100, Direction.RIGHT);
                objects[0] = ball;

                ball = new Ball(g, g.getWidth() - 40 - 100, 40, 100, Direction.LEFT);
                objects[1] = ball;
                break;

            default:
            case 2:
                objects = new Splittable[3];

                ball = new Ball(g, 40, 40, 100, Direction.RIGHT);
                objects[0] = ball;

                ball = new Ball(g, g.getWidth() - 40 - 100, 40, 100, Direction.LEFT);
                objects[1] = ball;

                ball = new Ball(g, g.getWidth() / 2 - 50, 40, 100, null);
                objects[2] = ball;
                break;
        }

        for (Splittable s : objects) {
            splittables.add(s);
        }


        return splittables;
    }
}
