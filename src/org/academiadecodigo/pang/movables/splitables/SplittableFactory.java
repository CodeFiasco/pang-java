package org.academiadecodigo.pang.movables.splitables;

import java.util.LinkedList;

/**
 * Created by codecadet on 14/10/17.
 */
public abstract class SplittableFactory {

    public static LinkedList<Splittable> getSplittableList() {

        return new LinkedList<Splittable>();
    }
}
