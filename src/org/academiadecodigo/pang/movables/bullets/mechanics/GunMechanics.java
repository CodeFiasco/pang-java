package org.academiadecodigo.pang.movables.bullets.mechanics;

import org.academiadecodigo.pang.position.Position;

/**
 * Created by codecadet on 20/10/17.
 */
public class GunMechanics implements Mechanics {

   private int x;
   private int y;

    public GunMechanics(int x, int y){

        this.x = x;
        this.y = y;
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
