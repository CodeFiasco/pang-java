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
    private List<Splittable> splittables;
    private KeyboardListener kb;

    private Rectangle background;
    private final int PADDING = GameConstants.PADDING;

    private int width = GameConstants.DEFAULT_GAME_WIDTH;
    private int height = GameConstants.DEFAULT_GAME_HEIGHT;
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
        kb = new KeyboardListener(player, KeyboardEvent.KEY_RIGHT, KeyboardEvent.KEY_LEFT, KeyboardEvent.KEY_SPACE);

        splittables = SplittableFactory.getSplittableList(this);

        while (true) {
            moveObjects();

            Thread.sleep(delay);
        }

    }

    private void moveObjects() {

        player.move();

        ListIterator<Splittable> iterator = splittables.listIterator();

        while (iterator.hasNext()) {
            Splittable splittable = iterator.next();

            splittable.move();

            if (player.checkBulletHit(splittable.getPos())) {

                iterator.remove();

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

    public int getHeight() {
        return height;
    }

    public int getPADDING() {
        return PADDING;
    }
}
