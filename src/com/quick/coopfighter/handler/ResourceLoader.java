package com.quick.coopfighter.handler;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class ResourceLoader {

    // Resources
    public static ArrayList<BufferedImage> bgImages = new ArrayList<>();
    public static SpriteSheet playerSprite;
    public static SpriteSheet enemySprite;

    public static BufferedImage gameBg;

    private int bgNum = 1;
    public float loadPercent = 0;

    public void loadImages() {
        // Load the game background
        try {
            gameBg = ImageIO.read(new File("src/resources/gameimages/gameback.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Load the title background images
        if (bgNum <= 96) {
            try {
                if (bgNum < 10) {
                    bgImages.add(ImageIO.read(new File("src/resources/titleimages/image000000" + (bgNum) + ".png")));
                } else {
                    bgImages.add(ImageIO.read(new File("src/resources/titleimages/image00000" + (bgNum) + ".png")));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        // Load player animations
        playerSprite = new SpriteSheet("src/resources/gameimages/player/player.png", 4, 4);
        enemySprite = new SpriteSheet("src/resources/gameimages/enemy/enemy.png", 4, 4);

        bgNum++;

        loadPercent = Math.round((bgNum / 97f) * 100f);

        if (loadPercent > 100) {
            loadPercent = 100;
        }
    }
}
