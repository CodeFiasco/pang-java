package org.academiadecodigo.pang.movables.bullets.packages;

/**
 * Created by codecadet on 20/10/17.
 */
public enum BulletTypes {
    ROPE,
    HOOK,
    GUN,
    BULLETBALL;

    public static BulletTypes getRandom(){
        return BulletTypes.values()[(int) (Math.random() * BulletTypes.values().length)];
    }
}
