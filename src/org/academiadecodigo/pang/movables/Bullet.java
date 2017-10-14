package org.academiadecodigo.pang.movables;

import org.academiadecodigo.pang.position.Position;

import java.util.List;

/**
 * Created by codecadet on 13/10/2017.
 */
public class Bullet implements Movable{

    List<Position> positions;

    public boolean checkBulletHit(Position compare) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void move() {

    }
}
