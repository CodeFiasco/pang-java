package org.academiadecodigo.pang.position;

import org.academiadecodigo.pang.Game;
import org.academiadecodigo.pang.GameConstants;

/**
 * Created by codecadet on 19/10/17.
 */
public abstract class YCoordinates {

    public static int converter(int y){

        return GameConstants.PADDING + GameConstants.GAME_HEIGHT - y;

    }
}
