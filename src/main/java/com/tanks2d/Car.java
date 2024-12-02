package com.tanks2d;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.util.Random;

public class Car extends Rectangle {
    int mapX, mapY;
    int imgIndex = 0;
    int rotation; // Rotation in degrees
    Random random = new Random();

    Car(int x, int y) {
        super(x, y);
        // select random 0-3
        this.imgIndex = random.nextInt(4);
        this.rotation = random.nextInt(360); // Random rotation between 0 and 359 degrees
        mapX = x;
        mapY = y;
        width = Images.car[imgIndex].getWidth();
        height = Images.car[imgIndex].getHeight();
    }

    public void draw(Graphics2D g2d) {
        this.x = mapX + GamePanel.offsetX;
        this.y = mapY + GamePanel.offsetY;

        // Save the current transform
        AffineTransform old = g2d.getTransform();

        // Rotate around the center of the car
        AffineTransform transform = new AffineTransform();
        transform.rotate(Math.toRadians(rotation), x + width / 2, y + height / 2);

        // Draw the car image
        g2d.setTransform(transform);
        g2d.drawImage(Images.car[imgIndex], x, y, null);

        // Restore the original transform
        g2d.setTransform(old);

        // Optionally, draw the hitbox for debugging
        // g2d.draw(rotatedHitbox);
    }
}