package org.academiadecodigo.pang.keyboardListener;

import org.academiadecodigo.pang.Game;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;

/**
 * Created by codecadet on 22/10/17.
 */
public class KeyboardInitialListener implements KeyboardHandler {

    private Game game;
    private int startKey;

    public KeyboardInitialListener(Game game, int startKey) {
        this.game = game;
        this.startKey = startKey;

        Keyboard keyboard = new Keyboard(this);

        KeyboardEvent sKey = new KeyboardEvent();
        sKey.setKey(startKey);
        sKey.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(sKey);
    }


    @Override
    public void keyPressed(KeyboardEvent e) {
        if (e.getKey() == startKey) {
            /*try {
                game.removeInitialScreen();
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }*/

        }
    }

    @Override
    public void keyReleased(KeyboardEvent e) {

    }
}
