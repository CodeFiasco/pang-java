package org.academiadecodigo.pang.movables.bullets.mechanics;

import org.academiadecodigo.pang.position.Position;

/**
 * Created by codecadet on 20/10/17.
 */
public class BallMechanics implements Mechanics {

    private Position pos;

    public BallMechanics(Position position) {
        pos = position;
    }

    @Override
    public void move() {

    }

    @Override
    public Position getPos() {
        return null;
    }

    @Override
    public boolean checkHit(Position pos) {
        return false;
    }

    @Override
    public void delete() {

    }

    @Override
    public boolean checkEndingPoint() {
        return false;
    }
}
