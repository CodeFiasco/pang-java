package org.academiadecodigo.pang.movables.bullets.mechanics;

import org.academiadecodigo.pang.GameConstants;
import org.academiadecodigo.pang.movables.Movable;
import org.academiadecodigo.pang.movables.bullets.packages.BulletTypes;
import org.academiadecodigo.pang.position.Position;

import java.util.LinkedList;

/**
 * Created by codecadet on 13/10/2017.
 */
public class RopeMechanics implements Mechanics {

    private int width = GameConstants.BULLET_WIDTH;
    private int height = GameConstants.BULLET_GROWTH_SPEED;

    private LinkedList<Position> positions;

    public RopeMechanics(int x, int y) {

        Position pos = new Position(x, y, width, height, "rope.png");

        positions = new LinkedList<>();
        positions.add(pos);
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
