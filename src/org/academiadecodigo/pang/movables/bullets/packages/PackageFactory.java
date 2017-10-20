package org.academiadecodigo.pang.movables.bullets.packages;

/**
 * Created by codecadet on 20/10/17.
 */
public abstract class PackageFactory {

    public static Package getPackage(int x, int y) {

        BulletTypes rand = BulletTypes.getRandom();

        switch (rand) {
            case GUN:
                return new GunPackage(x, y);

            case HOOK:
                return new HookPackage(x, y);

            case BALL:
                return new BallPackage(x, y);
        }

        return new RopePackage(x, y);
    }
}
