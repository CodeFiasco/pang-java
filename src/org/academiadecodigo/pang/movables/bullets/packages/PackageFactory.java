package org.academiadecodigo.pang.movables.bullets.packages;

/**
 * Created by codecadet on 20/10/17.
 */
public abstract class PackageFactory {

    public static Package getPackage(int x, int y) {
        BulletTypes rand = BulletTypes.getRandom();
        return new Package(x, y, rand);
    }
}
