package org.academiadecodigo.pang.movables.bullets.mechanics;

import org.academiadecodigo.pang.GameConstants;
import org.academiadecodigo.pang.movables.bullets.packages.BulletTypes;

public class MechanicsFactory {

    public static Mechanics getMechanics(int x, int y, BulletTypes type) {

        switch (type) {
            case ROPE:
                return new RopeMechanics(x, y);

            case HOOK:
                return new HookMechanics(x, y);

            case GUN:
                return new GunMechanics(x, y - GameConstants.PLAYER_HEIGHT);

            default:
            case BALL:
                return new BallMechanics(x, y - GameConstants.PLAYER_HEIGHT);
        }
    }
}
