package com.quick.coopfighter.input;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.quick.coopfighter.gameobjects.GameObject;
import com.quick.coopfighter.gameobjects.ID;
import com.quick.coopfighter.handler.Handler;

public class InputListener extends KeyAdapter implements MouseListener {

	private final Handler handler;

	private boolean up = false;
	private boolean down = false;
	private boolean left = false;
	private boolean right = false;

	public InputListener(Handler handler) {
		this.handler = handler;
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		for (GameObject object : handler.objects) {
			if (object.getID() == ID.Player) {
				if (key == KeyEvent.VK_W) {
					up = true;
					object.setVolY(-5);
				}

				if (key == KeyEvent.VK_S) {
					down = true;
					object.setVolY(5);
				}

				if (key == KeyEvent.VK_A) {
					left = true;
					object.setVolX(-5);
				}

				if (key == KeyEvent.VK_D) {
					right = true;
					object.setVolX(5);
				}
			}
		}
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		for (GameObject object : handler.objects) {
			if (object.getID() == ID.Player) {
				if (key == KeyEvent.VK_W) {
					up = false;
					if(down) {
						object.setVolY(5);
					} else {
						object.setVolY(0);
					}
				}

				if (key == KeyEvent.VK_S) {
					down = false;
					if(up) {
						object.setVolY(-5);
					} else {
						object.setVolY(0);
					}
				}

				if (key == KeyEvent.VK_A) {
					left = false;
					if(right) {
						object.setVolX(5);
					} else {
						object.setVolX(0);
					}
				}

				if (key == KeyEvent.VK_D) {
					right = false;
					if(left) {
						object.setVolX(-5);
					} else {
						object.setVolX(0);
					}
				}
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}
}
