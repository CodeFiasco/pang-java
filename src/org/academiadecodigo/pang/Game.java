package org.academiadecodigo.pang;

import org.academiadecodigo.pang.keyboardListener.KeyboardListener;
import org.academiadecodigo.pang.movables.Player;
import org.academiadecodigo.pang.movables.splitables.Splittable;
import org.academiadecodigo.pang.movables.splitables.SplittableFactory;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

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
        kb = new KeyboardListener(player);

        splittables = SplittableFactory.getSplittableList(this);

        while (true) {
            moveObjects();

            Thread.sleep(delay);
        }

    }

    private void moveObjects() {

        player.move();

        for (Splittable s : splittables) {
            s.move();
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
