package org.academiadecodigo.pang.movables;

import org.academiadecodigo.pang.Game;
import org.academiadecodigo.pang.GameConstants;
import org.academiadecodigo.pang.directions.Direction;
import org.academiadecodigo.pang.movables.splitables.Splittable;
import org.academiadecodigo.pang.position.Position;

/**
 * Created by codecadet on 13/10/2017.
 */
public class Player implements Movable {

    private Game g;
    private Direction dir;
    private Bullet bullet;
    private Position pos;
    private int width = GameConstants.PLAYER_WIDTH;
    private int height = GameConstants.PLAYER_HEIGHT;
    private int speed = GameConstants.PLAYER_SPEED;


    public Player(Game g) {
        this.g = g;
        pos = new Position((g.getPADDING() + (g.getWidth() / 2 - width / 2)), (g.getPADDING() + (g.getHeight() - height)), width, height, "player.png");
    }


    public void shoot() {

        if (bullet != null) {
            return;
        }

        Position bulletPosition = new Position(pos.getX() + width / 2 - GameConstants.BULLET_WIDTH / 2, g.getHeight() - GameConstants.BULLET_GROWTH_SPEED + GameConstants.PADDING, GameConstants.BULLET_WIDTH, GameConstants.BULLET_GROWTH_SPEED, "rope.png");
        bullet = new Bullet(bulletPosition);
    }

    public boolean checkIsDead(Splittable ball) {
        return pos.overlaps(ball.getPos());
    }

    public boolean checkBulletHit(Position ball) {

        if (bullet == null) {
            return false;
        }

        if (bullet.checkBulletHit(ball)) {
            bullet = null;
            return true;
        }

        return false;
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

        if (bullet == null) {
            return;
        }

        if (bullet.hitsTop()) {
            bullet.delete();
            bullet = null;
            return;
        }

        bullet.move();

    }

    private void moveRight() {
        if (pos.getX() + width < g.getWidth() + GameConstants.PADDING) {
            pos.translate(speed, 0);
        }
    }

    private void moveLeft() {
        if (pos.getX() > GameConstants.PADDING) {
            pos.translate(-speed, 0);
        }
    }
}
