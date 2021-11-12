package com.quick.coopfighter;

import com.quick.coopfighter.handler.Handler;
import com.quick.coopfighter.handler.ResourceLoader;
import com.quick.coopfighter.input.InputListener;
import com.quick.coopfighter.screens.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.io.Serial;

public class Game extends Canvas implements Runnable {
    // Screen numbers
    public static final int TITLE = 0;
    public static final int GAME = 1;
    public static final int LOAD = 2;
    public static final int END = 3;
    // Display Dimensions
    public static final int WIDTH = 1024;
    public static final int HEIGHT = WIDTH / 16 * 9;
    // World Dimensions
    public static final int WORLD_WIDTH = 2048;
    public static final int WORLD_HEIGHT = 2048;
    public static final int VIEWPORT_WIDTH = 800;
    public static final int VIEWPORT_HEIGHT = 600;
    @Serial
    private static final long serialVersionUID = -3543438401720086416L;
    public final ResourceLoader loader;
    public final Handler handler;
    private final Window window;
    public volatile boolean running = false;
    private Thread thread;
    private Screen screen;

    private double delta;

    public Game() {
        handler = new Handler();
        loader = new ResourceLoader();
        screen = setScreen(LOAD);

        window = new Window(WIDTH, HEIGHT, "Co-op Fighter", this);

        this.addKeyListener(new InputListener(handler));
        this.addKeyListener(screen);
        this.addMouseListener(screen);
        this.requestFocus();
    }

    public static int clamp(int var, int min, int max) {
        return Math.max(min, Math.min(max, var));
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(Game::new);
    }

    public synchronized void start() {
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized void stop() {
        window.close();

        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        double t = 0.0;
        double lastTime = System.nanoTime();
        double amountOfTicks = 20.0;
        double ns = 1e9 / amountOfTicks;
        delta = 1.0 / 60.0;
        long timer = System.currentTimeMillis();
        int frames = 0;

        while (running) {

            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;

            while (delta >= 1) {
                tick();
                delta--;
            }

            if (running)
                render();
            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
        stop();
    }

    private void tick() {
        screen.tick();
        handler.tick();
    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics2D g = (Graphics2D) bs.getDrawGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g.setColor(Color.black);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        screen.render(g);

        handler.render(g);

        g.dispose();
        bs.show();
    }

    public Screen setScreen(int screenNum) {
        switch (screenNum) {
            case TITLE -> {
                screen = new TitleScreen(this);
                this.addKeyListener(screen);
                this.addMouseListener(screen);
                return screen;
            }
            case GAME -> {
                screen = new GameScreen(this);

                for (MouseListener mListener : this.getMouseListeners())
                    this.removeMouseListener(mListener);
                this.addKeyListener(screen);
                this.addMouseListener(screen);
                return screen;
            }
            case LOAD -> {
                screen = new LoadingScreen(this);
                this.addKeyListener(screen);
                this.addMouseListener(screen);
                return screen;
            }

            case END -> {
                screen = new EndScreen(handler);
                this.addKeyListener(screen);
                this.addKeyListener(screen);
            }
        }

        return null;
    }

    public double getDelta() {
        return delta;
    }
}
