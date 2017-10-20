package org.academiadecodigo.pang.movables.bullets;

import org.academiadecodigo.pang.movables.Movable;
import org.academiadecodigo.pang.position.Position;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by codecadet on 13/10/2017.
 */
public class BulletMechanic implements Movable {

    private int width = 10;
    private int height = 10;

    private LinkedList<Position> positions;

    public BulletMechanic(Position position) {
        positions = new LinkedList<>();
        positions.add(position);
    }


    public boolean checkBulletHit(Position splittable) {

        for (Position pos : positions) {
            if (pos.overlaps(splittable) || splittable.overlaps(pos)) {
                delete();
                return true;
            }
        }

        return false;
    }

    public boolean hitsTop() {
        return positions.getLast().getY() <= 10;
    }

    public void delete() {

        for (Position pos : positions) {
            pos.delete();
        }
    }
    @Override
    public void move() {
        if (hitsTop()) {
            return;
        }

        Position pos = new Position(positions.getLast().getX(), positions.getLast().getY() - height, width, height, "rope.png");
        positions.add(pos);
    }

    @Override
    public Position getPos() {
        return null;
    }
}
