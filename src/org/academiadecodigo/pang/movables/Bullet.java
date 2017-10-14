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
    private Position lastAddedPosition;

    private LinkedList<Position> positions;

    public Bullet(Position position) {
        positions = new LinkedList<>();
        positions.add(position);
        lastAddedPosition = position;
    }


    public boolean checkBulletHit(Position compare) {
        throw new UnsupportedOperationException();
    }

    public boolean hitsTop() {
        return positions.getLast().getY() <= 10;
    }

    @Override
    public void move() {
        if (positions.getLast().getY() == 0) {
            return;
        }

        Position pos = new Position(lastAddedPosition.getX(), lastAddedPosition.getY() - height, width, height);
        positions.add(pos);
        lastAddedPosition = pos;
    }
}
