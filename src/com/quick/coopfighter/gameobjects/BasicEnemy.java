package com.quick.coopfighter.gameobjects;

import com.quick.coopfighter.handler.Handler;
import com.quick.coopfighter.handler.ResourceLoader;

import java.awt.*;
import java.awt.image.BufferedImage;

public class BasicEnemy extends GameObject {

    private final Animation upAnim, downAnim, leftAnim, rightAnim;
    private BufferedImage lastFrame;

    private final Player player;

    public BasicEnemy(int x, int y, ID id, Handler handler, Player player) {
        super(x, y, id);

        this.player = player;

        upAnim = new Animation();
        downAnim = new Animation();
        leftAnim = new Animation();
        rightAnim = new Animation();

        upAnim.setFrames(ResourceLoader.enemyUp);
        downAnim.setFrames(ResourceLoader.enemyDown);
        leftAnim.setFrames(ResourceLoader.enemyLeft);
        rightAnim.setFrames(ResourceLoader.enemyRight);

        lastFrame = ResourceLoader.enemyUp.get(1);

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
        g.fillRect(x, y, 32, 32);

        animation(g);
    }

    public void animation(Graphics2D g) {

        switch (direction) {
            case UP -> {
                g.drawImage(upAnim.getImage(), x - 16, y - 48, null);
                lastFrame = ResourceLoader.enemyUp.get(1);
            }
            case DOWN -> {
                g.drawImage(downAnim.getImage(), x - 16, y - 48, null);
                lastFrame = ResourceLoader.enemyDown.get(1);
            }
            case LEFT -> {
                g.drawImage(leftAnim.getImage(), x - 16, y - 48, null);
                lastFrame = ResourceLoader.enemyLeft.get(1);
            }
            case RIGHT -> {
                g.drawImage(rightAnim.getImage(), x - 16, y - 48, null);
                lastFrame = ResourceLoader.enemyRight.get(1);
            }
            case IDLE -> g.drawImage(lastFrame, x - 16, y - 48, null);
        }
    }

    public void followPlayer() {
        int moveToX = player.getX();
        int moveToY = player.getY();

        int diffX = moveToX - x;
        int diffY = moveToY - y;

        float angle = (float)Math.atan2(diffY, diffX);

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
