package com.quick.coopfighter.handler;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SpriteSheet {
    private BufferedImage sheet;
    int cols;
    int rows;

    public SpriteSheet(String url, int rows, int cols) {
        try {
            sheet = ImageIO.read(new File(url));
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.rows = rows;
        this.cols = cols;
    }

    public BufferedImage getSprite(int x, int y) {
        BufferedImage sprite = null;
        try {
            sprite = sheet.getSubimage(
                    x * sheet.getWidth() / cols,
                    y * sheet.getHeight() / rows,
                    sheet.getWidth() / cols,
                    sheet.getHeight() / rows);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sprite;
    }

}
