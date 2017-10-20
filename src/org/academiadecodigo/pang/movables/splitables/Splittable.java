package org.academiadecodigo.pang.movables.splitables;

import org.academiadecodigo.pang.movables.Movable;

/**
 * Created by codecadet on 13/10/2017.
 */
public interface Splittable extends Movable {
    Splittable[] split();
}
