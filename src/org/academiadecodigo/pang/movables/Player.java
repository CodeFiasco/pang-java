package org.academiadecodigo.pang.movables;

import org.academiadecodigo.pang.Game;
import org.academiadecodigo.pang.directions.Direction;
import org.academiadecodigo.pang.position.Position;

/**
 * Created by codecadet on 13/10/2017.
 */
public class Player implements Movable {

    Position pos;
    Game g;
    Direction dir;
    Bullet bullet;

    public void shoot() {
    }

    public boolean checkIsDead(Position ball) {
        return pos.overlaps(ball);
    }

    public boolean checkBulletHit(Position ball) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void move() {

    }
}
