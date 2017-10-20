package org.academiadecodigo.pang.movables.splitables.balls;

/**
 * Created by codecadet on 20/10/17.
 */
public enum BallSize {
    SMALL(25, 1),
    MEDIUM(50, 2),
    LARGE(100, 3);

    private int size;
    private int heightLevels;

    BallSize(int size, int heightLevels) {
        this.size = size;
        this.heightLevels = heightLevels;
    }

    public BallSize getSmallerSize() {

        switch (this) {
            case LARGE:
                return MEDIUM;

            case MEDIUM:
                return SMALL;

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
