package com.quick.coopfighter.screens;

import com.quick.coopfighter.handler.Handler;

import java.awt.*;

public class EndScreen extends Screen{

    public EndScreen(Handler handler) {
        super(handler);
    }

    @Override
    public void render(Graphics2D g) {
        g.drawString("You lose!", 10, 10);
    }

    @Override
    public void tick() {

    }
}
