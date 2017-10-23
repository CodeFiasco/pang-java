package org.academiadecodigo.pang.movables.bullets.mechanics;

import org.academiadecodigo.pang.GameConstants;
import org.academiadecodigo.pang.position.Position;

/**
 * Created by codecadet on 20/10/17.
 */
public class GunMechanics implements Mechanics {

    private Position pos;

    public GunMechanics(int x, int y){

        pos = new Position(x, y, 50, 9, "resources/chain.png");
    }

    @Override
    public void move() {
        pos.translate(0, -10);
    }

    @Override
    public Position getPos() {
        return pos;
    }

    @Override
    public boolean checkHit(Position pos) {
        return this.pos.overlaps(pos) || pos.overlaps(this.pos);
    }

    @Override
    public void delete() {
        pos.delete();
    }

    @Override
    public boolean checkEndingPoint() {
        return pos.getY() <= GameConstants.PADDING;
    }
}
