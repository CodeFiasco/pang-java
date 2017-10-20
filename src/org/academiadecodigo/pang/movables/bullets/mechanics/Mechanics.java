package org.academiadecodigo.pang.movables.bullets.mechanics;

import org.academiadecodigo.pang.movables.Movable;
import org.academiadecodigo.pang.position.Position;

/**
 * Created by codecadet on 20/10/17.
 */
public interface Mechanics extends Movable {

    boolean checkHit(Position pos);
    void delete();
    boolean checkEndingPoint();
}
