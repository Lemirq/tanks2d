package com.tanks2d;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class Images {
    public static ArrayList<BufferedImage> explosion = new ArrayList<>();
    public static BufferedImage[] car = new BufferedImage[4];
    static BufferedImage hull, bullet, map, track1, track2, shell;

    public static void loadImages() {
        try {
            hull = ImageIO.read(Images.class.getResource("/hull.png"));
            map = scaleImage(ImageIO.read(Images.class.getResource("/map.png")), 50, 50);
            track1 = ImageIO.read(Images.class.getResource("/track1.png"));
            track2 = ImageIO.read(Images.class.getResource("/track2.png"));
            bullet = ImageIO.read(Images.class.getResource("/shell.png"));

            // cars
            for (int i = 0; i <= 3; i++) {
                System.out.println("Loading car" + (i + 1) + ".png");
                car[i] = ImageIO.read(Images.class.getResource("/car" + (i + 1) + ".png"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error loading images");
        }
    }

    static BufferedImage scaleImage(BufferedImage img, int width, int height) {
        BufferedImage scaledImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        scaledImage.getGraphics().drawImage(img, 0, 0, width, height, null);
        return scaledImage;
    }

    static BufferedImage scaleImage(BufferedImage img, double scale) {
        BufferedImage scaledImage = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_ARGB);
        System.out.println("Scaling to " + scale + " width: " + img.getWidth() * scale + " height: "
                + img.getHeight() * scale);
        scaledImage.getGraphics().drawImage(img, 0, 0, (int) (img.getWidth() * scale), (int) (img.getHeight() * scale),
                null);
        return scaledImage;
    }
}
