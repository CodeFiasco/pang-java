package org.academiadecodigo.pang.movables.splitables;

import org.academiadecodigo.pang.Game;
import org.academiadecodigo.pang.directions.Direction;
import org.academiadecodigo.pang.position.Position;

/**
 * Created by codecadet on 13/10/2017.
 */
public class Ball implements Splittable {

    private Position pos;
    private Game g;
    private Direction direction;


    public Ball() {
        pos = new Position( int x, int y, int width, int height);
        direction = Direction.RIGHT;
    }


    @Override
    public void move() {
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
            pos.setX(pos.getX() + 1);
        }

        private void moveDown(){

        }

        private void moveLeft(){
            pos.setX(pos.getX() - 1);
        }

        private void checkBoundaries(){
            if(direction == Direction.RIGHT && pos.getX() + pos.getWidth() >= g.getWidth()){
                direction = direction.getOpposite();
            }
            if (direction == Direction.LEFT && pos.getX() <= 0){
                direction = direction.getOpposite();
            }

        }


    @Override
    public Splittable[] split() {
        return new Splittable[0];
    }
}
