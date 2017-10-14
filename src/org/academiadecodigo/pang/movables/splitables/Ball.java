package org.academiadecodigo.pang.movables.splitables;

import org.academiadecodigo.pang.Game;
import org.academiadecodigo.pang.directions.Direction;
import org.academiadecodigo.pang.position.Position;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

/**
 * Created by codecadet on 13/10/2017.
 */
public class Ball implements Splittable {

    private Position pos;
    private Game g;
    private Direction direction;


    public Ball(Game g) {
        int x = 20;
        int y = 20;
        int width = 20;
        int height = 20;
        pos = new Position(x, y, width, height);
        direction = Direction.RIGHT;

        this.g = g;

    }


    @Override
    public void move() {

        if (checkBoundaries()) {
            direction = direction.getOpposite();
        }

        switch (direction) {

            case UP:
                moveUp();
                break;
            case RIGHT:
                moveRight();
                break;
            case DOWN:
                moveDown();
                break;
            case LEFT:
                moveLeft();
                break;
        }
    }

        private void moveUp(){

        }

        private void moveRight(){
            pos.translate(1,0);
        }

        private void moveDown(){

        }

        private void moveLeft(){
            pos.translate(-1, 0);
        }

        private boolean checkBoundaries(){
            return (direction == Direction.RIGHT && pos.getX() + pos.getWidth() >= g.getWidth() + g.getPADDING()) ||
                    (direction == Direction.LEFT && pos.getX() <= g.getPADDING());
        }


    @Override
    public Splittable[] split() {
        return new Splittable[0];
    }
}
