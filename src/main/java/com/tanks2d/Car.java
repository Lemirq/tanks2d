package com.tanks2d;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Car extends Rectangle {
    int imgIndex = 0;
    int rotation; // Rotation in degrees
    Random random = new Random();

    Car(BufferedImage i, int x, int y) {
        super(x, y, i.getWidth(), i.getHeight());
        this.rotation = random.nextInt(360); // Random rotation between 0 and 359 degrees
    }

    public void draw(Graphics2D g2d) {
        // this.x = mapX + GamePanel.offsetX;
        // this.y = mapY + GamePanel.offsetY;
        // Draw the car image
        g2d.drawImage(Images.car[imgIndex], x + GamePanel.offsetX, y + GamePanel.offsetY, null);
        if (GamePanel.debugging)
            g2d.drawRect(x, y, width, height);

    }
}