package com.quick.coopfighter.ui;

import com.quick.coopfighter.Game;

import java.awt.*;

public class HUD {

    public static int HEALTH = 100;

    public void tick() {
        Game.clamp(HEALTH, 0, 100);
    }

    public void render(Graphics g) {
        g.setColor(Color.GRAY);
        g.fillRect(16, 16, 192, 32);

        g.setColor(Color.GREEN);
        // Add health, with a padding of 2 (x = 16 +2, etc.)
        g.fillRect(18, 18, HEALTH + 88, 28);
    }
}
