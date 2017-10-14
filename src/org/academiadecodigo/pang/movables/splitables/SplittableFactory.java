package org.academiadecodigo.pang.movables.splitables;

import java.util.LinkedList;

/**
 * Created by codecadet on 14/10/17.
 */
public abstract class SplittableFactory {

    public static LinkedList<Splittable> getSplittableList() {

        LinkedList<Splittable> splittables = new LinkedList<>();

        Ball ball = new Ball();
        splittables.add(ball);

        return splittables;
    }
}
