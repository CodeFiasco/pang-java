package org.academiadecodigo.pang.movables.splitables;

import org.academiadecodigo.pang.Game;

import java.util.LinkedList;

/**
 * Created by codecadet on 14/10/17.
 */
public abstract class SplittableFactory {

    public static LinkedList<Splittable> getSplittableList(Game g) {

        LinkedList<Splittable> splittables = new LinkedList<>();

        Ball ball = new Ball(g);
        splittables.add(ball);

        return splittables;
    }
}
