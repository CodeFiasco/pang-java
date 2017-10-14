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
    private Keyboard keyboard;

    public KeyboardListener(Player player) {
        this.player = player;

        keyboard = new Keyboard(this);

        KeyboardEvent rightArrow = new KeyboardEvent();
        rightArrow.setKey(KeyboardEvent.KEY_RIGHT);
        rightArrow.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(rightArrow);

        rightArrow = new KeyboardEvent();
        rightArrow.setKey(KeyboardEvent.KEY_RIGHT);
        rightArrow.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);
        keyboard.addEventListener(rightArrow);

        KeyboardEvent leftArrow = new KeyboardEvent();
        leftArrow.setKey(KeyboardEvent.KEY_LEFT);
        leftArrow.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(leftArrow);

        leftArrow = new KeyboardEvent();
        leftArrow.setKey(KeyboardEvent.KEY_LEFT);
        leftArrow.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);
        keyboard.addEventListener(leftArrow);

        KeyboardEvent space = new KeyboardEvent();
        space.setKey(KeyboardEvent.KEY_SPACE);
        space.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(space);
    }

    @Override
    public void keyPressed(KeyboardEvent e) {

        switch (e.getKey()){

            case KeyboardEvent.KEY_RIGHT:
                player.setDirection(Direction.RIGHT);
                break;

            case KeyboardEvent.KEY_LEFT:
                player.setDirection(Direction.LEFT);
                break;

            case KeyboardEvent.KEY_SPACE:
                player.shoot();
                break;
        }
    }

    @Override
    public void keyReleased(KeyboardEvent e) {

        switch (e.getKey()) {

            case KeyboardEvent.KEY_RIGHT:
                player.setDirection(null);
                break;

            case KeyboardEvent.KEY_LEFT:
                player.setDirection(null);
                break;
        }
    }
}
