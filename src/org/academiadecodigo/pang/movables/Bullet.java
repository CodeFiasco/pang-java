package org.academiadecodigo.pang.movables;

import org.academiadecodigo.pang.position.Position;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by codecadet on 13/10/2017.
 */
public class Bullet implements Movable {

    private int width = 10;
    private int height = 10;

    private LinkedList<Position> positions;

    public Bullet(Position position) {
        positions = new LinkedList<>();
        positions.add(position);
    }


    public boolean checkBulletHit(Position splittable) {

        for (Position pos : positions) {
            if (pos.overlaps(splittable)) {
                return true;
            }
        }

        return false;
    }

    public boolean hitsTop() {
        return positions.getLast().getY() <= 10;
    }

    @Override
    public void move() {
        if (hitsTop()) {
            return;
        }

        Position pos = new Position(positions.getLast().getX(), positions.getLast().getY() - height, width, height);
        positions.add(pos);
    }
}
