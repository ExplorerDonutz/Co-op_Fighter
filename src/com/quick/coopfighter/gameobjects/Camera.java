package com.quick.coopfighter.gameobjects;

import com.quick.coopfighter.Game;

public class Camera {

    public int offsetMaxX;
    public int offsetMaxY;
    public int offsetMinX;
    public int offsetMinY;

    public int camX;
    public int camY;

    private Player player;

    public Camera(Player p) {

        //Setting offset max's and minimums
        offsetMaxX = Game.WORLD_WIDTH - Game.WIDTH;
        offsetMaxY = Game.WORLD_HEIGHT - Game.HEIGHT;
        offsetMinX = 0;
        offsetMinY = 0;

        player = p;

    }

    public void tick() {

        camX = (player.getX()) - (Game.WIDTH / 2);
        camY = (player.getY()) - (Game.HEIGHT / 2);

        if (camX > offsetMaxX)
            camX = offsetMaxX;
        else if (camX < offsetMinX)
            camX = offsetMinX;
        if (camY > offsetMaxY)
            camY = offsetMaxY;
        else if (camY < offsetMinY)
            camY = offsetMinY;
    }

}
