package com.quick.coopfighter.gameobjects;

// Base class for game objects

import java.awt.*;

public abstract class GameObject {
    protected final static int IDLE = 0;
    protected final static int UP = 1;
    protected final static int DOWN = 2;
    protected final static int LEFT = 3;
    protected final static int RIGHT = 4;

    protected int direction;

    protected int x, y;
    protected ID id;
    protected int volX, volY;

    public GameObject(int x, int y, ID id) {
        this.x = x;
        this.y = y;
        this.id = id;
    }

    public void getDirection() {
        if (Math.abs(volX) > Math.abs(volY)) {
            if (volX < 0) {
                direction = LEFT;
            } else if (volX > 0){
                direction = RIGHT;
            }
        } else if (volY < 0) {
            direction = UP;
        } else if (volY > 0){
            direction = DOWN;
        } else {
            // Not moving
            direction = IDLE;
        }
    }

    public abstract void tick();

    public abstract void render(Graphics2D g);

    public abstract Rectangle getBounds();

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setID(ID id) {
        this.id = id;
    }

    public ID getID() {
        return id;
    }

    public void setVolX(int volX) {
        this.volX = volX;
    }

    public void setVolY(int volY) {
        this.volY = volY;
    }

    public int getVolX() {
        return volX;
    }

    public int getVolY() {
        return volY;
    }
}
