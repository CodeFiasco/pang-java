package org.academiadecodigo.pang.movables;

import org.academiadecodigo.pang.Game;
import org.academiadecodigo.pang.directions.Direction;
import org.academiadecodigo.pang.position.Position;

/**
 * Created by codecadet on 13/10/2017.
 */
public class Player implements Movable {

    private Game g;
    private Direction dir;
    private Bullet bullet;
    private Position pos;
    private int width = 40;
    private int height = 80;


    public Player(Game g) {
        this.g = g;
        pos = new Position((g.getPADDING() + (g.getWidth() / 2 - width / 2)), (g.getPADDING() + (g.getHeight() - height)), width, height);
    }


    public void shoot() {
        bullet = new Bullet(pos);
        bullet.move();
    }

    public boolean checkIsDead(Position ball) {
        return pos.equals(ball);
    }

    public boolean checkBulletHit(Position ball) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void move() {

    }
}
