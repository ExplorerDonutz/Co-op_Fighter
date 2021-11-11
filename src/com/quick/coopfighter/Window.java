package com.quick.coopfighter;

import java.awt.Canvas;
import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.io.Serial;
import java.awt.event.WindowEvent;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Window extends Canvas {
    @Serial
    private static final long serialVersionUID = -6816081495257137122L;
    private final JFrame frame;
    
    public Window(int width, int height, String title, Game game) {
        frame = new JFrame(title);

        frame.add(game);
        frame.pack();

        int x = frame.getInsets().left + frame.getInsets().right;
        int y = frame.getInsets().top + frame.getInsets().bottom;

        // Set the size of window as the size wanted PLUS the amount of pixels hidden
        frame.setPreferredSize(new Dimension(width + x, height + y));
        frame.setMaximumSize(new Dimension(width + x, height + y));
        frame.setMinimumSize(new Dimension(width + x, height + y));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        // Try to set icon for jFrame
        try {
            frame.setIconImage(ImageIO.read(new File("src/resources/icon.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        frame.setVisible(true);
        game.start();
    }
    
    public void close() {
    	frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
    }
}
