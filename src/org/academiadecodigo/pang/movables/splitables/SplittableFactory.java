package org.academiadecodigo.pang.movables.splitables;

import org.academiadecodigo.pang.Game;
import org.academiadecodigo.pang.directions.Direction;

import java.util.LinkedList;

/**
 * Created by codecadet on 14/10/17.
 */
public abstract class SplittableFactory {

    public static LinkedList<Splittable> getSplittableList(Game g) {

        LinkedList<Splittable> splittables = new LinkedList<>();

        Ball ball = new Ball(g, 40, 40, 100, Direction.RIGHT);
        splittables.add(ball);

        return splittables;
    }
}
