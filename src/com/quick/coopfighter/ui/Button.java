package com.quick.coopfighter.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.RoundRectangle2D;

import javax.swing.SwingUtilities;

import com.quick.coopfighter.Game;
import com.quick.coopfighter.utils.Utils;

public class Button {
	
	private final Game game;
	private final Font font;
	private final Color semiTransparent;
	private final Color textColor;

	private final RoundRectangle2D button;
	private final Rectangle textRect;
	private final RoundRectangle2D buttonHoverRect;
	private final String text;
	private final int x;
	private final int y;
	private final int width;
	private final int height;
	public boolean hovering;

	public Button(Game game, Color textColor, Font font, int x, int y, int width, int height, String text) {
		this.game = game;
		this.textColor = textColor;
		this.font = font;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.text = text;
		button = new RoundRectangle2D.Double(x, y, width, height, 8, 8);
		buttonHoverRect = button;
		textRect = new Rectangle(x, y, width, height);
		semiTransparent = new Color(1, 1, 1, 69);
		hovering = false;
	}

	public void render(Graphics2D g) {
		if (hovering) {
			g.setColor(semiTransparent);
			g.fill(buttonHoverRect);
		}
		
		g.setColor(textColor);
		g.draw(button);

		Utils.drawCenteredString(g, text, textRect, font);
	}
	
	public void tick() {

		Point mousePos = getMousePos();

		hovering = mousePos.getX() >= button.getMinX() && mousePos.getX() <= button.getMaxX()
				&& mousePos.getY() >= button.getMinY() && mousePos.getY() <= button.getMaxY();
	}
	
	private Point getMousePos() {
		Point mousePoint = MouseInfo.getPointerInfo().getLocation();
		SwingUtilities.convertPointFromScreen(mousePoint, game);

		return mousePoint;
	}

	public int getMinX() {
		return x;
	}

	public int getMinY() {
		return y;
	}

	public int getMaxX() {
		return x + width;
	}

	public int getMaxY() {
		return y + height;
	}
}
