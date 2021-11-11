package com.quick.coopfighter.handler;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class ResourceLoader {

    // Resources
    public static ArrayList<BufferedImage> bgImages = new ArrayList<>();
    public static ArrayList<BufferedImage> playerUp = new ArrayList<>();
    public static ArrayList<BufferedImage> playerDown = new ArrayList<>();
    public static ArrayList<BufferedImage> playerLeft = new ArrayList<>();
    public static ArrayList<BufferedImage> playerRight = new ArrayList<>();

    public static ArrayList<BufferedImage> enemyUp = new ArrayList<>();
    public static ArrayList<BufferedImage> enemyDown = new ArrayList<>();
    public static ArrayList<BufferedImage> enemyLeft = new ArrayList<>();
    public static ArrayList<BufferedImage> enemyRight = new ArrayList<>();

    public static BufferedImage gameBg;

    private int bgNum = 1;
    private int playerNum = 1;
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
        if (playerNum <= 4) {
            try {
                playerUp.add(ImageIO.read(new File("src/resources/gameimages/player/forward (" + playerNum + ").png")));
                playerDown.add(ImageIO.read(new File("src/resources/gameimages/player/back (" + playerNum + ").png")));
                playerLeft.add(ImageIO.read(new File("src/resources/gameimages/player/left (" + playerNum + ").png")));
                playerRight.add(ImageIO.read(new File("src/resources/gameimages/player/right (" + playerNum + ").png")));

                enemyUp.add(ImageIO.read(new File("src/resources/gameimages/enemy/forward (" + playerNum + ").png")));
                enemyDown.add(ImageIO.read(new File("src/resources/gameimages/enemy/back (" + playerNum + ").png")));
                enemyLeft.add(ImageIO.read(new File("src/resources/gameimages/enemy/left (" + playerNum + ").png")));
                enemyRight.add(ImageIO.read(new File("src/resources/gameimages/enemy/right (" + playerNum + ").png")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        playerNum++;
        bgNum++;

        loadPercent = Math.round((bgNum / 97f) * 100f);
    }
}
