package com.quick.coopfighter.gameobjects;

import com.quick.coopfighter.handler.Handler;
import com.quick.coopfighter.handler.ResourceLoader;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class BasicEnemy extends GameObject {

    private final Animation upAnim, downAnim, leftAnim, rightAnim;
    private final ArrayList<BufferedImage> upImgs, downImgs, leftImgs, rightImgs;
    private final Player player;
    private BufferedImage lastFrame;

    public BasicEnemy(int x, int y, ID id, Handler handler, Player player) {
        super(x, y, id);

        this.player = player;

        upImgs = new ArrayList<>();
        downImgs = new ArrayList<>();
        leftImgs = new ArrayList<>();
        rightImgs = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            upImgs.add(ResourceLoader.enemySprite.getSprite(i, 0));
            downImgs.add(ResourceLoader.enemySprite.getSprite(i, 1));
            rightImgs.add(ResourceLoader.enemySprite.getSprite(i, 2));
            leftImgs.add(ResourceLoader.enemySprite.getSprite(i, 3));
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

        followPlayer();

        upAnim.tick();
        downAnim.tick();
        leftAnim.tick();
        rightAnim.tick();
    }

    public void render(Graphics2D g) {
        g.setColor(Color.red);
        //g.fillRect(x, y, 32, 32);

        animation(g);
    }

    public void animation(Graphics2D g) {

        switch (direction) {
            case UP -> {
                g.drawImage(upAnim.getImage(), x - 16, y - 16, 64, 64, null);
                lastFrame = upImgs.get(1);
            }
            case DOWN -> {
                g.drawImage(downAnim.getImage(), x - 16, y - 16, 64, 64, null);
                lastFrame = downImgs.get(1);
            }
            case LEFT -> {
                g.drawImage(leftAnim.getImage(), x - 16, y - 16, 64, 64, null);
                lastFrame = leftImgs.get(1);
            }
            case RIGHT -> {
                g.drawImage(rightAnim.getImage(), x - 16, y - 16, 64, 64, null);
                lastFrame = rightImgs.get(1);
            }
            case IDLE -> g.drawImage(lastFrame, x - 16, y - 16, 64, 64, null);
        }
    }

    public void followPlayer() {
        int moveToX = player.getX();
        int moveToY = player.getY();

        int diffX = moveToX - x;
        int diffY = moveToY - y;

        float angle = (float) Math.atan2(diffY, diffX);

        if (diffX < -1 || diffX > 1)
            volX = (int) (3 * Math.cos(angle));
        else
            volX = 0;

        if (diffY < -1 || diffY > 1)
            volY = (int) (3 * Math.sin(angle));
        else
            volY = 0;
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, 16, 16);
    }


}
