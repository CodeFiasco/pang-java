package org.academiadecodigo.pang.movables.bullets.mechanics;

import org.academiadecodigo.pang.movables.Movable;
import org.academiadecodigo.pang.position.Position;

import java.util.LinkedList;

/**
 * Created by codecadet on 13/10/2017.
 */
public class BulletMechanics implements Mechanics {

    private int width = 10;
    private int height = 10;

    private LinkedList<Position> positions;

    public BulletMechanics(Position position) {
        positions = new LinkedList<>();
        positions.add(position);
    }


    public boolean checkHit(Position splittable) {

        for (Position pos : positions) {
            if (pos.overlaps(splittable) || splittable.overlaps(pos)) {
                delete();
                return true;
            }
        }

        return false;
    }

    public boolean checkEndingPoint() {
        return positions.getLast().getY() <= 10;
    }

    public void delete() {

        for (Position pos : positions) {
            pos.delete();
        }
    }
    
    @Override
    public void move() {
        if (checkEndingPoint()) {
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
