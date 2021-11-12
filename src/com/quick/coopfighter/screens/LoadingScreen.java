package com.quick.coopfighter.screens;

import com.quick.coopfighter.Game;
import com.quick.coopfighter.handler.ResourceLoader;
import com.quick.coopfighter.utils.Utils;

import java.awt.*;

public class LoadingScreen extends Screen {

    private final ResourceLoader loader;
    private final Game game;
    private final Rectangle textRect;
    private final Font textFont;

    public LoadingScreen(Game game) {
        super(game.handler);
        this.game = game;
        loader = game.loader;
        textRect = new Rectangle(0, Game.HEIGHT / 2, Game.WIDTH, 0);
        textFont = new Font("Impact", Font.PLAIN, 12);
    }

    @Override
    public void render(Graphics2D g) {

        g.setColor(Color.WHITE);

        Utils.drawCenteredString(g, "Loading: " + loader.loadPercent + "%", textRect, textFont);

        g.drawRect(100, 200, (int) ((Game.WIDTH - 200) * (loader.loadPercent / 100)), 20);
    }

    @Override
    public void tick() {
        loader.loadImages();

        if (loader.loadPercent >= 100) {
            game.setScreen(Game.TITLE);
        }
    }
}
