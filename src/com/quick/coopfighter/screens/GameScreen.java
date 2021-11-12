package com.quick.coopfighter.screens;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.quick.coopfighter.Game;
import com.quick.coopfighter.gameobjects.BasicEnemy;
import com.quick.coopfighter.gameobjects.Camera;
import com.quick.coopfighter.gameobjects.ID;
import com.quick.coopfighter.gameobjects.Player;
import com.quick.coopfighter.handler.ResourceLoader;
import com.quick.coopfighter.ui.HUD;

public class GameScreen extends Screen {
    private final HUD hud;
    private final BufferedImage bgImage;
    private final Player player;
    private final BasicEnemy enemy;
    private final Game game;
    private final Camera camera;

    public GameScreen(Game game) {
        super(game.handler);
        this.game = game;

        bgImage = ResourceLoader.gameBg;
        hud = new HUD();
        player = new Player(100, 100, ID.Player, game.handler);
        enemy = new BasicEnemy(100, 200, ID.BasicEnemy, game.handler, player);
        game.handler.addObject(player);
        game.handler.addObject(enemy);

        camera = new Camera(player);
    }

    @Override
    public void render(Graphics2D g) {

        g.translate(-camera.camX, -camera.camY);
        g.drawImage(bgImage, 0, 0, 1067, Game.HEIGHT, null);
        hud.render(g);
    }

    @Override
    public void tick() {
        camera.tick();
        hud.tick();

        if (HUD.HEALTH + 88 <= 0) {
            game.handler.removeObject(player);
            game.handler.removeObject(enemy);
            game.setScreen(Game.END);
        }
    }

}
