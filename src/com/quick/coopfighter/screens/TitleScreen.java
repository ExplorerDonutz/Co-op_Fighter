package com.quick.coopfighter.screens;

import com.quick.coopfighter.Game;
import com.quick.coopfighter.handler.ResourceLoader;
import com.quick.coopfighter.ui.Button;
import com.quick.coopfighter.utils.Utils;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class TitleScreen extends Screen {

    private final ArrayList<BufferedImage> bgImages;
    private final Game game;
    private final Rectangle titleRect;
    private final Font font;
    private final Button playButton, exitButton, menuButton;
    private int bgNum;

    public TitleScreen(Game game) {
        super(game.handler);
        this.game = game;
        // Load background images
        bgImages = ResourceLoader.bgImages;
        bgNum = 0;
        // Title and buttons
        titleRect = new Rectangle(0, 0, Game.WIDTH, Game.HEIGHT / 4);
        font = new Font("Impact", Font.BOLD, 32);
        Font buttonFont = new Font("Impact", Font.BOLD, 18);

        playButton = new Button(game, Color.WHITE, buttonFont, Game.WIDTH / 2 - 64, Game.HEIGHT / 2 - 96, 128, 32, "Play");
        menuButton = new Button(game, Color.WHITE, buttonFont, Game.WIDTH / 2 - 64, Game.HEIGHT / 2 - 32, 128, 32, "Menu");
        exitButton = new Button(game, Color.WHITE, buttonFont, Game.WIDTH / 2 - 64, Game.HEIGHT / 2 + 32, 128, 32, "Exit");
    }

    @Override
    public void render(Graphics2D g) {
        g.drawImage(bgImages.get(bgNum), 0, 0, 823, 480, null);
        Utils.drawCenteredString(g, "Co-op Fighter", titleRect, font);

        playButton.render(g);
        menuButton.render(g);
        exitButton.render(g);
    }

    @Override
    public void tick() {

        if (bgNum < 95) {
            bgNum++;
        } else {
            bgNum = 0;
        }

        playButton.tick();
        menuButton.tick();
        exitButton.tick();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            if (playButton.hovering) {
                game.setScreen(Game.GAME);
            } else if (exitButton.hovering) {
                game.running = false;
            } else if (menuButton.hovering) {
                //game.setScreen(new MenuScreen);
            }
        }
    }
}
