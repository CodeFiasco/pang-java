package org.academiadecodigo.pang.movables.bullets.packages;

/**
 * Created by codecadet on 20/10/17.
 */
public enum BulletTypes {
    ROPE("grappling.png"),
    HOOK("anchor.png"),
    GUN("gun.png"),
    BULLET_BALL("red-ball-50.png");

    private String imageSource;

    BulletTypes(String imageSource) {
        this.imageSource = imageSource;
    }

    public static BulletTypes getRandom(){
        return BulletTypes.values()[(int) (Math.random() * BulletTypes.values().length)];
    }

    public String getImageSource() {
        return imageSource;
    }
}
