package org.academiadecodigo.pang;

import org.academiadecodigo.pang.keyboardListener.KeyboardListener;
import org.academiadecodigo.pang.movables.Player;
import org.academiadecodigo.pang.movables.splitables.Splittable;

import java.util.LinkedList;

/**
 * Created by codecadet on 13/10/2017.
 */
public class Game {

    private Player player;
    private LinkedList<Splittable> balls;
    private KeyboardListener kb;

    private final int width = 900;
    private final int height = 500;

    public void init() {

    }

    private void moveObjects() {

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
}
