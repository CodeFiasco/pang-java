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

        switch (level) {
            case 0:
                Ball ball = new Ball(g, 40, 40, 100, Direction.RIGHT);
                splittables.add(ball);
                break;

            case 1:
                Ball b = new Ball(g, 40, 40, 100, Direction.RIGHT);
                splittables.add(b);

                b = new Ball(g, g.getWidth() - 40 - 100, 40, 100, Direction.LEFT);
                splittables.add(b);
                break;
        }


        return splittables;
    }
}
