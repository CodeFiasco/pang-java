package org.academiadecodigo.pang.movables.bullets.packages;

import org.academiadecodigo.pang.Game;
import org.academiadecodigo.pang.GameConstants;
import org.academiadecodigo.pang.movables.Movable;
import org.academiadecodigo.pang.movables.bullets.packages.BulletTypes;
import org.academiadecodigo.pang.position.Position;

/**
 * Created by codecadet on 20/10/17.
 */
public abstract class Package implements Movable{

    private BulletTypes type;
    private Position position;

    public Package(int x, int y, BulletTypes type) {

        position = new Position(x, y, 50, 50, "anchor.png");
        this.type = type;

    }

    @Override
    public void move() {

        if (position.getY() + 55 > Game.getHeight() + GameConstants.PADDING) {
            return;
        }

        position.translate(0, 5);

    }

    @Override
    public Position getPos() {
        return position;
    }

    public BulletTypes getType() {
        return type;
    }
}
