package com.quick.coopfighter.gameobjects;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Animation {

    private ArrayList<BufferedImage> frames;
    private int currentFrame;

    private long startTime;
    private long delay;

    private boolean playedOnce;

    public Animation() {
        playedOnce = false;
    }

    public void setFrames(ArrayList<BufferedImage> frames) {
        this.frames = frames;
        currentFrame = 0;
        startTime = System.nanoTime();
        playedOnce = false;
    }

    public void setDelay(long d) {
        delay = d;
    }

    public void tick() {

        if (delay == -1)
            return;

        long elapsed = (System.nanoTime() - startTime) / 1000000;
        if (elapsed > delay) {
            currentFrame++;
            startTime = System.nanoTime();
        }
        if (currentFrame == frames.size()) {
            currentFrame = 0;
            playedOnce = true;
        }
    }

    public int getFrame() {
        return currentFrame;
    }

    public void setFrame(int i) {
        currentFrame = i;
    }

    public BufferedImage getImage() {
        return frames.get(currentFrame);
    }

    public boolean hasPlayedOnce() {
        return playedOnce;
    }
}
