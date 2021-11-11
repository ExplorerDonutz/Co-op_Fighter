package com.quick.coopfighter.screens;

import java.awt.Graphics2D;
import java.io.IOException;

import com.quick.coopfighter.handler.Handler;
import com.quick.coopfighter.input.InputListener;

public abstract class Screen extends InputListener{

	public Screen(Handler handler) {
		super(handler);
	}

	public abstract void render(Graphics2D g);
	
	public abstract void tick();
	
	
}
