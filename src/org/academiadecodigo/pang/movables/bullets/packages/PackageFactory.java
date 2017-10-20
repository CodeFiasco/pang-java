package org.academiadecodigo.pang.movables.bullets.packages;

/**
 * Created by codecadet on 20/10/17.
 */
public abstract class PackageFactory {

    public static Package getPackage(int x, int y) {

        BulletTypes rand = BulletTypes.getRandom();

        switch (rand) {
            case GUN:
                return new Gun(x, y);

            case HOOK:
                return new Hook(x, y);

            case BULLET_BALL:
                return new BulletBall(x, y);
        }

        return new Rope(x, y);
    }
}
