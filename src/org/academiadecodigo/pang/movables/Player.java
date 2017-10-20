package org.academiadecodigo.pang.movables;

import org.academiadecodigo.pang.GameConstants;
import org.academiadecodigo.pang.directions.Direction;
import org.academiadecodigo.pang.movables.bullets.mechanics.*;
import org.academiadecodigo.pang.movables.bullets.packages.BulletTypes;
import org.academiadecodigo.pang.position.Position;

/**
 * Created by codecadet on 13/10/2017.
 */
public class Player implements Movable {

    private Direction dir;
    private Mechanics mechanics;
    private BulletTypes bulletType;
    private Position pos;
    private int width = GameConstants.PLAYER_WIDTH;
    private int height = GameConstants.PLAYER_HEIGHT;


    public Player() {
        pos = new Position((GameConstants.PADDING + (GameConstants.GAME_WIDTH / 2 - width / 2)), (GameConstants.PADDING + (GameConstants.GAME_HEIGHT - height)), width, height, "player.png");
        bulletType = BulletTypes.ROPE;
    }


    public void shoot() {

        if (mechanics != null) {
            return;
        }

        int x = pos.getX() + width / 2 - GameConstants.BULLET_WIDTH / 2;
        int y = GameConstants.GAME_HEIGHT - GameConstants.BULLET_GROWTH_SPEED + GameConstants.PADDING;

        switch (bulletType) {
            case ROPE:
                mechanics = new RopeMechanics(x, y);
                break;

            case HOOK:
                mechanics = new HookMechanics(x, y);
                break;

            case GUN:
                mechanics = new GunMechanics(x, y - height);
                break;

            case BALL:
                mechanics = new BallMechanics(x, y - height);
                break;
        }
    }

    public boolean checkHit(Position pos) {
        return this.pos.overlaps(pos) || pos.overlaps(this.pos);
    }

    public boolean checkBulletHit(Position ball) {

        if (mechanics == null) {
            return false;
        }

        if (mechanics.checkHit(ball)) {
            mechanics.delete();
            mechanics = null;
            return true;
        }

        return false;
    }

    public void deleteBullet() {

        if (mechanics == null) {
            return;
        }

        mechanics.delete();
        mechanics = null;
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

        if (mechanics == null) {
            return;
        }

        if (mechanics.checkEndingPoint()) {

            mechanics.delete();
            mechanics = null;
            return;
        }

        mechanics.move();

    }

    private void moveRight() {
        if (pos.getX() + width + GameConstants.PLAYER_SPEED < GameConstants.GAME_WIDTH + GameConstants.PADDING) {
            pos.translate(GameConstants.PLAYER_SPEED, 0);
        }
    }

    private void moveLeft() {
        if (pos.getX() - GameConstants.PLAYER_SPEED > GameConstants.PADDING) {
            pos.translate(-GameConstants.PLAYER_SPEED, 0);
        }
    }

    public Position getPos() {
        return pos;
    }

    public void setBulletType(BulletTypes type) {
        this.bulletType = type;
    }
}
