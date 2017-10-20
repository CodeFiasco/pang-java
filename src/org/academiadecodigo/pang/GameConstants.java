package org.academiadecodigo.pang;

import org.academiadecodigo.simplegraphics.pictures.Picture;

/**
 * Created by codecadet on 15/10/2017.
 */
public abstract class GameConstants {

    // Game
    public static final int DEFAULT_GAME_WIDTH = 1280;
    public static final int DEFAULT_GAME_HEIGHT = 800;
    public static final int PADDING = 10;
    public static final int DELAY = 10;
    public static final int LEVEL_DELAY = 4000;
    public static final int CHANCE_FOR_POWER_UP = 20;

    // Balls
    public static final int BALL_MIN_SIZE = 25;
    public static final int BALL_MAX_HEIGHT = 20;
    public static final int BALL_SPEED_CHANGE_LEVELS = 15;

    // Player
    public static final int PLAYER_WIDTH = 96;
    public static final int PLAYER_HEIGHT = 96;
    public static final int PLAYER_SPEED = 10;

    // BulletMechanics
    public static final int BULLET_GROWTH_SPEED = 10;
    public static final int BULLET_WIDTH = 10;

    //Background Images

    public static final Picture LEVEL_1_IMAGE = new Picture(PADDING,PADDING,"level1-background.jpg");
    public static final Picture LEVEL_2_IMAGE = new Picture(PADDING,PADDING,"level2-background.jpg");
    public static final Picture LEVEL_3_IMAGE = new Picture(PADDING,PADDING,"level3-background.jpg");




}
