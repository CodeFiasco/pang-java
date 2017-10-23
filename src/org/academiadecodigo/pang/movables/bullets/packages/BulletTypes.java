package org.academiadecodigo.pang.movables.bullets.packages;

/**
 * Created by codecadet on 20/10/17.
 */
public enum BulletTypes {
    ROPE("resources/grappling.png"),
    HOOK("resources/anchor.png"),
    GUN("resources/gun.png"),
    BALL("resources/sun-50.png");

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
