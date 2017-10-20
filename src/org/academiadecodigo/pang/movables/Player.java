package org.academiadecodigo.pang.movables;

import org.academiadecodigo.pang.Game;
import org.academiadecodigo.pang.GameConstants;
import org.academiadecodigo.pang.directions.Direction;
import org.academiadecodigo.pang.movables.bullets.mechanics.BulletMechanics;
import org.academiadecodigo.pang.movables.bullets.packages.BulletTypes;
import org.academiadecodigo.pang.position.Position;

/**
 * Created by codecadet on 13/10/2017.
 */
public class Player implements Movable {

    private Game g;
    private Direction dir;
    private BulletMechanics bulletMechanics;
    private BulletTypes bulletType;
    private Position pos;
    private int width = GameConstants.PLAYER_WIDTH;
    private int height = GameConstants.PLAYER_HEIGHT;
    private int speed = GameConstants.PLAYER_SPEED;


    public Player(Game g) {
        this.g = g;
        pos = new Position((g.getPADDING() + (g.getWidth() / 2 - width / 2)), (g.getPADDING() + (g.getHeight() - height)), width, height, "player.png");
    }


    public void shoot() {

        if (bulletMechanics != null) {
            return;
        }

        Position bulletPosition = new Position(pos.getX() + width / 2 - GameConstants.BULLET_WIDTH / 2, g.getHeight() - GameConstants.BULLET_GROWTH_SPEED + GameConstants.PADDING, GameConstants.BULLET_WIDTH, GameConstants.BULLET_GROWTH_SPEED, "rope.png");
        bulletMechanics = new BulletMechanics(bulletPosition);
    }

    public boolean checkIsDead(Position pos) {
        return this.pos.overlaps(pos) || pos.overlaps(this.pos);
    }

    public boolean checkBulletHit(Position ball) {

        if (bulletMechanics == null) {
            return false;
        }

        if (bulletMechanics.checkBulletHit(ball)) {
            bulletMechanics = null;
            return true;
        }

        return false;
    }

    public void deleteBullet() {

        if (bulletMechanics == null) {
            return;
        }

        bulletMechanics.delete();
        bulletMechanics = null;
    }

    public void setDirection(Direction dir) {
        this.dir = dir;
    }

    @Override
    public void move() {

        if (dir != null) {

            switch (dir) {
                case RIGHT:
                    moveRight();
                    break;

                case LEFT:
                    moveLeft();
            }
        }

        if (bulletMechanics == null) {
            return;
        }

        if (bulletMechanics.hitsTop()) {

            if (bulletType == BulletTypes.HOOK) {
                return;
            }

            bulletMechanics.delete();
            bulletMechanics = null;
            return;
        }

        bulletMechanics.move();

    }

    private void moveRight() {
        if (pos.getX() + width + speed < g.getWidth() + GameConstants.PADDING) {
            pos.translate(speed, 0);
        }
    }

    private void moveLeft() {
        if (pos.getX() - speed > GameConstants.PADDING) {
            pos.translate(-speed, 0);
        }
    }

    public Position getPos() {
        return pos;
    }

    public void setBulletType(BulletTypes type) {
        this.bulletType = type;
    }
}
