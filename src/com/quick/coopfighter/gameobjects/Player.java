package com.quick.coopfighter.gameobjects;

import com.quick.coopfighter.Game;
import com.quick.coopfighter.handler.Handler;
import com.quick.coopfighter.handler.ResourceLoader;
import com.quick.coopfighter.ui.HUD;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends GameObject {
    private final Animation upAnim, downAnim, leftAnim, rightAnim;
    Handler handler;
    private BufferedImage lastFrame;


    public Player(int x, int y, ID id, Handler handler) {
        super(x, y, id);

        this.handler = handler;


        setX(Game.WIDTH / 2 - 16);
        setY(Game.HEIGHT / 2 + 128);

        upAnim = new Animation();
        downAnim = new Animation();
        leftAnim = new Animation();
        rightAnim = new Animation();

        upAnim.setFrames(ResourceLoader.playerUp);
        downAnim.setFrames(ResourceLoader.playerDown);
        leftAnim.setFrames(ResourceLoader.playerLeft);
        rightAnim.setFrames(ResourceLoader.playerRight);

        lastFrame = ResourceLoader.playerUp.get(1);

        upAnim.setDelay(100);
        downAnim.setDelay(100);
        leftAnim.setDelay(100);
        rightAnim.setDelay(100);
    }

    public void tick() {
        x += volX;
        y += volY;

        // Clamp coordinates inside window
        x = Game.clamp(x, 0, Game.WIDTH - 32);
        y = Game.clamp(y, 0, Game.HEIGHT - 32);

        collision();

        upAnim.tick();
        downAnim.tick();
        leftAnim.tick();
        rightAnim.tick();
    }

    public void collision() {
        for (GameObject object : handler.objects) {

            if (object.getID() == ID.BasicEnemy) {
                if (getBounds().intersects(object.getBounds())) {
                    // Collision with basic enemy
                    HUD.HEALTH -= 2;
                }
            }
        }
    }

    public void render(Graphics2D g) {
        g.setColor(Color.WHITE);
        g.fillRect(x, y, 32, 32);

        animation(g);
    }

    public void animation(Graphics2D g) {

        switch (direction) {
            case UP -> {
                g.drawImage(upAnim.getImage(), x - 16, y - 48, null);
                lastFrame = ResourceLoader.playerUp.get(1);
            }
            case DOWN -> {
                g.drawImage(downAnim.getImage(), x - 16, y - 48, null);
                lastFrame = ResourceLoader.playerDown.get(1);
            }
            case LEFT -> {
                g.drawImage(leftAnim.getImage(), x - 16, y - 48, null);
                lastFrame = ResourceLoader.playerLeft.get(1);
            }
            case RIGHT -> {
                g.drawImage(rightAnim.getImage(), x - 16, y - 48, null);
                lastFrame = ResourceLoader.playerRight.get(1);
            }
            case IDLE -> g.drawImage(lastFrame, x - 16, y - 48, null);
        }
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, 32, 32);
    }
}