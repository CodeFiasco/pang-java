package org.academiadecodigo.pang;

import org.academiadecodigo.pang.keyboardListener.KeyboardListener;
import org.academiadecodigo.pang.movables.Player;
import org.academiadecodigo.pang.movables.splitables.Splittable;
import org.academiadecodigo.pang.movables.splitables.SplittableFactory;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.graphics.Text;
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
    private int levelDelay = GameConstants.LEVEL_DELAY;

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

            if (playerDead || splittables.size() == 0) {
                newLevel();
            }

            Thread.sleep(delay);
        }

    }

    private void newLevel() throws InterruptedException {

        if (playerDead) {

            player.getPos().delete();
            player.deleteBullet();

            for (Splittable s : splittables) {
                s.getPos().delete();
            }

            generateMessage("You died!!", Color.RED, 3000);
            playerDead = false;
            level = 0;
            player.getPos().draw();

        } else {    //new level
            generateMessage("Level Complete!!!", Color.YELLOW, 2000);
            level++;
        }

        //Start game messages
        generateMessage("Get Ready...", Color.GREEN, 2000);
        generateMessage("3", Color.GREEN, 1000);
        generateMessage("2", Color.GREEN, 1000);
        generateMessage("1", Color.GREEN, 1000);
        generateMessage("GOOOOOOO!!!", Color.GREEN, 1000);

        splittables = SplittableFactory.getSplittableList(this, level);


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

    private void generateMessage(String message, Color color, int sleepTime) throws InterruptedException {

        int grownFactor = 5;

        Text text = new Text(GameConstants.DEFAULT_GAME_WIDTH / 2, GameConstants.DEFAULT_GAME_HEIGHT / 2, message);
        text.setColor(color);
        text.translate(-(text.getWidth() / 2), -(text.getHeight() / 2));
        text.grow(text.getWidth() * grownFactor, text.getHeight() * grownFactor);
        text.draw();
        Thread.sleep(sleepTime);
        text.delete();
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
