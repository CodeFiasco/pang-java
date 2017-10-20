package org.academiadecodigo.pang.movables.splitables.balls;

/**
 * Created by codecadet on 20/10/17.
 */
public enum BallSize {
    SMALL(24, 1),
    MEDIUM(48, 2),
    MEDIUM_LARGE(128, 3),
    LARGE(256, 4);

    private int size;
    private int heightLevels;

    BallSize(int size, int heightLevels) {
        this.size = size;
        this.heightLevels = heightLevels;
    }

    public BallSize getSmallerSize() {

        switch (this) {
            case LARGE:
                return MEDIUM_LARGE;

            case MEDIUM:
                return SMALL;
                
            case MEDIUM_LARGE:
                return MEDIUM;

            default:
                return null;
        }
    }

    public int getHeightLevels() {
        return heightLevels;
    }

    public int getSize() {
        return size;
    }
}
