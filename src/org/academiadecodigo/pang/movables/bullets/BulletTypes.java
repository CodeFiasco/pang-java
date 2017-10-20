package org.academiadecodigo.pang.movables.bullets;

/**
 * Created by codecadet on 20/10/17.
 */
public enum BulletTypes {
    ROPE,
    HOOK;

    public static BulletTypes getRandom(){
        return HOOK;
    }
}
