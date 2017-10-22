package org.academiadecodigo.pang;

import org.academiadecodigo.pang.keyboardListener.KeyboardInitialListener;
import org.academiadecodigo.pang.keyboardListener.KeyboardListener;
import org.academiadecodigo.pang.movables.Player;
import org.academiadecodigo.pang.movables.bullets.packages.BulletTypes;
import org.academiadecodigo.pang.movables.bullets.packages.Package;
import org.academiadecodigo.pang.movables.bullets.packages.PackageFactory;
import org.academiadecodigo.pang.movables.splitables.Splittable;
import org.academiadecodigo.pang.movables.splitables.SplittableFactory;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.graphics.Text;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.pictures.Picture;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by codecadet on 13/10/2017.
 */
public class Game {

    private Player player;
    private boolean playerDead = false;
    private List<Splittable> splittables;

    private List<Package> powerUps;
    private Picture[] backgrounds;
    private int level = 1;

    private LinkedList<Rectangle> timerBlocks;
    private int timeCounter = 0;

    private LinkedList<Picture> lives;


    /*public void removeInitialScreen() throws InterruptedException {
        initialScreen.delete();
        newText.delete();
        init();

    }*/

    public void init() throws InterruptedException {

        initialScreen();

        backgrounds = generateBackgrounds();

        for (Picture background : backgrounds) {
            background.draw();
        }
        //timer();

        player = new Player();
        new KeyboardListener(player, KeyboardEvent.KEY_RIGHT, KeyboardEvent.KEY_LEFT, KeyboardEvent.KEY_SPACE);

        splittables = SplittableFactory.getSplittableList(this, level);
        powerUps = new LinkedList<>();

        lives();

        start();
    }

    public void start() throws InterruptedException {

        generateMessage("Level " + level, Color.YELLOW, 2000);
        gamePreparationMessages();

        while (!playerDead && splittables.size() > 0 /*&& timerBlocks.size() != 0*/) {

            moveObjects();
            Thread.sleep(GameConstants.DELAY);
        }
        newLevel();
        start();
    }

    private void moveObjects() {

        player.move();

        movePackages();
        moveSplittables();
        //timerDelete();
    }

    public void movePackages() {
        ListIterator<Package> powerUpIterator = powerUps.listIterator();

        while (powerUpIterator.hasNext()) {
            Package pw = powerUpIterator.next();
            pw.move();

            if (player.checkHit(pw.getPos())) {

                powerUpIterator.remove();
                player.setBulletType(pw.getType());
                pw.getPos().delete();
            }
        }
    }

    public void moveSplittables() {
        ListIterator<Splittable> iterator = splittables.listIterator();

        // Loop through splittable list
        while (iterator.hasNext()) {
            Splittable splittable = iterator.next();
            splittable.move();

            // Check if bullet hit player
            if (player.checkHit(splittable.getPos())) {

                livesRemoval();
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

                if (newBalls.length == 0) {
                    continue;
                }

                int rand = (int) (Math.random() * GameConstants.CHANCE_FOR_POWER_UP);

                if (rand == 0) {
                    powerUps.add(PackageFactory.getPackage(splittable.getPos().getX() + 25, splittable.getPos().getY() + 50));
                }
            }
        }
    }

    private void newLevel() throws InterruptedException {

        for (Package b : powerUps) {
            b.getPos().delete();
        }

        if (playerDead /*|| timerBlocks.size() == 0*/) {

            player.getPos().delete();
            player.deleteBullet();

            for (Splittable s : splittables) {
                s.getPos().delete();
            }

            generateMessage("BANG!!", Color.RED, 3000);
            playerDead = false;
            player.getPos().draw();

            if (lives.size() == 0) {

                generateMessage("GAME OVER", Color.RED, 2000);
                level = 1;
                splittables.removeAll(splittables);
                lives();

                init();
                //initialScreen();
            }

            /*if (timerBlocks.size() == 0) {
                generateMessage("Your time is up!!!", Color.RED, 3000);
                playerDead = false;
                player.getPos().draw();
                livesRemoval();
            }*/

        } else {
            generateMessage("Level " + level + " Complete!!!", Color.YELLOW, 1000);
            removeBackground();
            level++;
        }
        if (level > 3) {
            finishScreen();
            waitingScreen();
        }

        //Start game messages
        //gamePreparationMessages();

        //timerReset();
        //timer();
        player.setBulletType(BulletTypes.ROPE);
        powerUps = new LinkedList<>();
        splittables = SplittableFactory.getSplittableList(this, level);
    }

    private void generateMessage(String message, Color color, int sleepTime) throws InterruptedException {

        int grownFactor = 4;

        Text text = new Text(GameConstants.GAME_WIDTH / 2, GameConstants.GAME_HEIGHT / 2, message);
        text.setColor(color);
        text.translate(-(text.getWidth() / 2), -(text.getHeight() / 2));
        text.grow(text.getWidth() * grownFactor, text.getHeight() * grownFactor);
        text.draw();
        Thread.sleep(sleepTime);
        text.delete();
    }

    private void gamePreparationMessages() throws InterruptedException {
        generateMessage("Get Ready...", Color.GREEN, 2000);
        generateMessage("3", Color.GREEN, 1000);
        generateMessage("2", Color.GREEN, 1000);
        generateMessage("1", Color.GREEN, 1000);
        generateMessage("GOOOOOOO!!!", Color.GREEN, 1000);
    }

    private Picture[] generateBackgrounds() {

        return new Picture[]{
                new Picture(GameConstants.PADDING, GameConstants.PADDING, GameConstants.LEVEL_3_IMAGE),
                new Picture(GameConstants.PADDING, GameConstants.PADDING, GameConstants.LEVEL_2_IMAGE),
                new Picture(GameConstants.PADDING, GameConstants.PADDING, GameConstants.LEVEL_1_IMAGE)
        };
    }

    private void removeBackground() {
        if (backgrounds.length - level >= 0) {

            backgrounds[backgrounds.length - level].delete();
        }
    }


    private void timer() {

        timerBlocks = new LinkedList<>();
        timeCounter = 0;

        for (int i = 0; i < GameConstants.LEVEL_TIME; i++) {

            Rectangle timerBlock = new Rectangle((GameConstants.GAME_WIDTH - GameConstants.PADDING - 180) + i * 2, GameConstants.PADDING + 20, 2, 10);

            timerBlocks.add(timerBlock);
            timerBlocks.getLast().setColor(Color.GREEN);
            timerBlocks.getLast().fill();
        }
    }

    private void timerDelete() {

        ListIterator<Rectangle> it = timerBlocks.listIterator();

        if (timerBlocks.size() == 0) {
            return;
        }
        timeCounter++;

        if (timeCounter == 100) {
            //Rectangle next = it.next();
            it.next().delete();
            it.remove();
            timeCounter = 0;
        }
    }

    public void timerReset() {

        for (Rectangle r : timerBlocks) {
            r.delete();
        }
    }


    public void lives() {

        lives = new LinkedList<>();


        for (int i = 0; i < GameConstants.PLAYERS_LIVES; i++) {


            Picture hearts = new Picture(20 + i * 50, 20, "heart.png");
            lives.add(hearts);
            lives.getLast().draw();
        }
    }

    public void livesRemoval() {

        if (lives.size() == 0) {
            return;
        }
        lives.getLast().delete();
        lives.removeLast();

    }

    public void initialScreen() throws InterruptedException {

        Rectangle rectangle = new Rectangle(GameConstants.PADDING, GameConstants.PADDING, GameConstants.GAME_WIDTH, GameConstants.GAME_HEIGHT);
        Picture initPicture1 = new Picture(GameConstants.PADDING, GameConstants.PADDING, "start-img1.png");
        Picture initPicture2 = new Picture(GameConstants.PADDING, GameConstants.PADDING, "start-img2.png");
        Picture initPicture3 = new Picture(GameConstants.PADDING, GameConstants.PADDING, "start-img3.png");

        rectangle.setColor(Color.BLACK);
        rectangle.fill();
        initPicture1.draw();
        Thread.sleep(2000);
        initPicture2.translate(GameConstants.GAME_WIDTH / 2 - (initPicture2.getWidth() / 2), 350);
        initPicture2.draw();
        Thread.sleep(2000);
        initPicture3.translate(GameConstants.GAME_WIDTH / 2 - (initPicture3.getWidth() / 2), 600);
        initPicture3.draw();
        Thread.sleep(2000);

        waitingScreen();

    }

    private void waitingScreen() throws InterruptedException {
        Picture initPicture4 = new Picture(GameConstants.PADDING, GameConstants.PADDING, "startMessage.png");
        Picture initPicture5 = new Picture(GameConstants.PADDING, GameConstants.PADDING, "initial-img.png");

        initPicture4.translate(GameConstants.GAME_WIDTH / 2 - (initPicture4.getWidth() / 2), 610);
        initPicture5.draw();
        Thread.sleep(4000);

        /*while (true) {

            initPicture4.draw();
            Thread.sleep(500);
            initPicture4.delete();
            Thread.sleep(500);
        }*/
    }

    public void finishScreen() throws InterruptedException {

        Rectangle rectangle = new Rectangle(GameConstants.PADDING, GameConstants.PADDING, 1280, 800);
        Picture picture1 = new Picture(GameConstants.PADDING, GameConstants.PADDING, "start-img1.png");
        Picture picture2 = new Picture(GameConstants.PADDING, GameConstants.PADDING, "start-img2.png");

        rectangle.setColor(Color.BLACK);
        rectangle.fill();

        picture2.translate(GameConstants.GAME_WIDTH / 2 - (picture2.getWidth() / 2), 600);
        picture1.draw();
        picture2.draw();


        generateMessage("CONGRATULATIONS!!", Color.RED, 5000);
        generateMessage("THE END!!", Color.RED, 1000);
        level = 1;

        initialScreen();
    }
}
