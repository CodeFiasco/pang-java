package org.academiadecodigo.pang.movables.bullets.packages;

import org.academiadecodigo.pang.GameConstants;
import org.academiadecodigo.pang.movables.Movable;
import org.academiadecodigo.pang.position.Position;

/**
 * Created by codecadet on 22/10/17.
 */
public class ExtraLife implements Movable {

    private Position position;

    public ExtraLife(int x, int y) {

        position = new Position(x, y, 50, 50, "resources/heart.png");

    }

    @Override
    public void move() {

        if (position.getY() + 55 > GameConstants.GAME_HEIGHT + GameConstants.PADDING) {
            return;
        }

        position.translate(0, 5);

    }

    @Override
    public Position getPos() {
        return position;
    }

}
