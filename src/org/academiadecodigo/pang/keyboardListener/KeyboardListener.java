package org.academiadecodigo.pang.keyboardListener;

import org.academiadecodigo.pang.directions.Direction;
import org.academiadecodigo.pang.movables.Player;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;

/**
 * Created by codecadet on 13/10/2017.
 */
public class KeyboardListener implements KeyboardHandler {

    private Player player;

    private int rightKey;
    private int leftKey;
    private int shootKey;

    public KeyboardListener(Player player, int rightKey, int leftKey, int shootKey) {
        this.player = player;

        Keyboard keyboard = new Keyboard(this);

        KeyboardEvent rightArrow = new KeyboardEvent();
        rightArrow.setKey(rightKey);
        rightArrow.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(rightArrow);

        rightArrow = new KeyboardEvent();
        rightArrow.setKey(rightKey);
        rightArrow.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);
        keyboard.addEventListener(rightArrow);

        KeyboardEvent leftArrow = new KeyboardEvent();
        leftArrow.setKey(leftKey);
        leftArrow.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(leftArrow);

        leftArrow = new KeyboardEvent();
        leftArrow.setKey(leftKey);
        leftArrow.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);
        keyboard.addEventListener(leftArrow);

        KeyboardEvent space = new KeyboardEvent();
        space.setKey(shootKey);
        space.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(space);

        this.rightKey = rightKey;
        this.leftKey = leftKey;
        this.shootKey = shootKey;
    }

    @Override
    public void keyPressed(KeyboardEvent e) {

        if (e.getKey() == rightKey) {
            player.setDirection(Direction.RIGHT);
            return;
        }

        if (e.getKey() == leftKey) {
            player.setDirection(Direction.LEFT);
            return;
        }

        player.shoot();
    }

    @Override
    public void keyReleased(KeyboardEvent e) {

        if (e.getKey() != shootKey) {
            player.setDirection(null);
        }
    }
}
