package org.academiadecodigo.pang.movables.bullets.mechanics;

/**
 * Created by codecadet on 20/10/17.
 */
public class HookMechanics extends RopeMechanics{

    public HookMechanics(int x, int y) {
        super(x, y);
    }

    @Override
    public boolean checkEndingPoint() {
        return false;
    }
}
