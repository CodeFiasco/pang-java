package org.academiadecodigo.pang;

import org.academiadecodigo.pang.keyboardListener.KeyboardListener;
import org.academiadecodigo.pang.movables.Player;
import org.academiadecodigo.pang.movables.splitables.Splittable;
import org.academiadecodigo.pang.movables.splitables.SplittableFactory;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by codecadet on 13/10/2017.
 */
public class Game {

    private Player player;
    private LinkedList<Splittable> splittables;
    private KeyboardListener kb;

    private Rectangle background;
    private final int PADDING = 10;

    private int width = 900;
    private int height = 500;
    private int delay = 10;

    public Game() {
        background = new Rectangle(PADDING, PADDING, width, height);
        background.fill();
    }

    public Game(int width, int height) {
        this.width = width;
        this.height = height;

        background = new Rectangle(PADDING, PADDING, this.width, this.height);
        background.fill();
    }

    public void init() throws InterruptedException {

        player = new Player(this);
        kb = new KeyboardListener(player, KeyboardEvent.KEY_RIGHT, KeyboardEvent.KEY_LEFT, KeyboardEvent.KEY_SPACE);

        splittables = SplittableFactory.getSplittableList(this);

        while (true) {
            moveObjects();

            Thread.sleep(delay);
        }

    }

    private void moveObjects() {

        Splittable[] remove = new Splittable[50];
        int removeIndex = 0;

        Splittable[] add = new Splittable[50];
        int addIndex = 0;

        player.move();

        for (Splittable splittable : splittables) {
            splittable.move();

            if (player.checkBulletHit(splittable.getPos())) {

                Splittable[] newBalls = splittable.split();

                for (Splittable n : newBalls) {
                    add[addIndex] = n;
                    addIndex++;
                }

                remove[removeIndex] = splittable;
                removeIndex++;
            }
        }

        for (int i = 0; i < removeIndex; i++) {
            remove[i].getPos().delete();
            splittables.remove(remove[i]);
        }

        for (int i = 0; i < addIndex; i++) {
            splittables.add(add[i]);
        }
    }

    private boolean checkCollisions() {
        return false;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getPADDING() {
        return PADDING;
    }
}
