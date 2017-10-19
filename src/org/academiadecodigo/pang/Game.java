package org.academiadecodigo.pang;

import org.academiadecodigo.pang.keyboardListener.KeyboardListener;
import org.academiadecodigo.pang.movables.Player;
import org.academiadecodigo.pang.movables.splitables.Splittable;
import org.academiadecodigo.pang.movables.splitables.SplittableFactory;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;

import java.util.List;
import java.util.ListIterator;

/**
 * Created by codecadet on 13/10/2017.
 */
public class Game {

    private Player player;
    private boolean playerDead = false;
    private List<Splittable> splittables;
    private int level = 0;

    private Rectangle background;
    private final int PADDING = GameConstants.PADDING;

    private int width = GameConstants.DEFAULT_GAME_WIDTH;
    private static int height = GameConstants.DEFAULT_GAME_HEIGHT;
    private int delay = GameConstants.DELAY;

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
        new KeyboardListener(player, KeyboardEvent.KEY_RIGHT, KeyboardEvent.KEY_LEFT, KeyboardEvent.KEY_SPACE);

        splittables = SplittableFactory.getSplittableList(this, level);

        while (true) {
            moveObjects();

            if (playerDead) {

                for (Splittable s : splittables) {
                    s.getPos().delete();
                }

                splittables = SplittableFactory.getSplittableList(this, level);
                playerDead = false;
            }

            if (splittables.size() == 0) {

                level++;

                splittables = SplittableFactory.getSplittableList(this, level);
            }

            Thread.sleep(delay);
        }

    }

    private void moveObjects() {

        player.move();

        ListIterator<Splittable> iterator = splittables.listIterator();

        // Loop through splittable list
        while (iterator.hasNext()) {
            Splittable splittable = iterator.next();
            splittable.move();

            // Check if bullet hit player
            if (player.checkIsDead(splittable)) {
                playerDead = true;
            }

            // Check if bullet hits splittable
            if (player.checkBulletHit(splittable.getPos())) {

                // Remove splittable from list
                iterator.remove();

                // Get resulting splittables and add them to the list
                Splittable[] newBalls = splittable.split();

                for (Splittable n : newBalls) {
                    iterator.add(n);
                }
            }
        }
    }

    private boolean checkCollisions() {
        return false;
    }

    public int getWidth() {
        return width;
    }

    public static int getHeight() {
        return height;
    }

    public int getPADDING() {
        return PADDING;
    }
}
