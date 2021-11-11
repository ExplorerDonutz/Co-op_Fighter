package com.quick.coopfighter.handler;

import java.awt.*;
import java.util.concurrent.CopyOnWriteArrayList;
/* Loops through all game objects to update and
 * render them to the screen*/

import com.quick.coopfighter.gameobjects.GameObject;

public class Handler {
    public CopyOnWriteArrayList<GameObject> objects = new CopyOnWriteArrayList<>();

    public void tick() {
        for (GameObject object : objects) {
            object.getDirection();
            object.tick();
        }
    }

    public void render(Graphics2D g) {
        for (GameObject object : objects) {
            object.render(g);
        }
    }

    public void addObject(GameObject object) {
        this.objects.add(object);
    }

    public void removeObject(GameObject object) {
        this.objects.remove(object);
    }
}
