package com.quick.coopfighter.gameobjects;

import com.quick.coopfighter.Game;
import com.quick.coopfighter.handler.Handler;
import com.quick.coopfighter.handler.ResourceLoader;
import com.quick.coopfighter.ui.HUD;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Player extends GameObject {
    private final Animation upAnim, downAnim, leftAnim, rightAnim;

    private final ArrayList<BufferedImage> upImgs, downImgs, leftImgs, rightImgs;
    Handler handler;
    private BufferedImage lastFrame;


    public Player(int x, int y, ID id, Handler handler) {
        super(x, y, id);

        this.handler = handler;

        upImgs = new ArrayList<>();
        downImgs = new ArrayList<>();
        leftImgs = new ArrayList<>();
        rightImgs = new ArrayList<>();

        setX(Game.WIDTH / 2 - 16);
        setY(Game.HEIGHT / 2 + 128);

        for (int i = 0; i < 4; i++) {
            upImgs.add(ResourceLoader.playerSprite.getSprite(i, 0));
            downImgs.add(ResourceLoader.playerSprite.getSprite(i, 1));
            rightImgs.add(ResourceLoader.playerSprite.getSprite(i, 2));
            leftImgs.add(ResourceLoader.playerSprite.getSprite(i, 3));
        }

        upAnim = new Animation();
        downAnim = new Animation();
        leftAnim = new Animation();
        rightAnim = new Animation();

        upAnim.setFrames(upImgs);
        downAnim.setFrames(downImgs);
        leftAnim.setFrames(leftImgs);
        rightAnim.setFrames(rightImgs);

        lastFrame = upImgs.get(1);

        upAnim.setDelay(100);
        downAnim.setDelay(100);
        leftAnim.setDelay(100);
        rightAnim.setDelay(100);
    }

    public void tick() {
        x += volX;
        y += volY;

        // Clamp coordinates inside window
        x = Game.clamp(x, 0, Game.WORLD_WIDTH - 64);
        y = Game.clamp(y, 0, Game.WORLD_HEIGHT - 64);

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
        //g.fillRect(x, y, 64, 64);

        animation(g);
    }

    public void animation(Graphics2D g) {

        switch (direction) {
            case UP -> {
                g.drawImage(upAnim.getImage(), x , y, 64, 64, null);
                lastFrame = upImgs.get(1);
            }
            case DOWN -> {
                g.drawImage(downAnim.getImage(), x, y, 64, 64, null);
                lastFrame = downImgs.get(1);
            }
            case LEFT -> {
                g.drawImage(leftAnim.getImage(), x, y, 64, 64, null);
                lastFrame = leftImgs.get(1);
            }
            case RIGHT -> {
                g.drawImage(rightAnim.getImage(), x, y, 64, 64, null);
                lastFrame = rightImgs.get(1);
            }
            case IDLE -> g.drawImage(lastFrame, x, y, 64, 64, null);
        }
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, 32, 32);
    }
}