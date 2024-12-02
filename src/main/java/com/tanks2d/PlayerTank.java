package com.tanks2d;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;

public class PlayerTank extends Rectangle {
    int vx = 0, vy = 0;
    int speed = 5;
    public int score;

    PlayerTank() {
        this.speed = GamePanel.MOVE_SPEED;
        this.width = Images.hull.getWidth();
        this.height = Images.hull.getWidth();
        this.x = GamePanel.screenWidth / 2 - width / 2;
        this.y = GamePanel.screenHeight / 2 - width / 2;
    }

    // public void update() {
    // x += vx;
    // y += vy;
    // }

    public void moveForward() {
        vx = (int) (speed * Math.cos(Math.toRadians(GamePanel.direction)));
        vy = (int) (speed * Math.sin(Math.toRadians(GamePanel.direction)));
        GamePanel.offsetX += vx;
        GamePanel.offsetY += vy;

        x -= vx;
        y -= vy;
    }

    public void moveBackward() {
        vx = (int) (-speed * Math.cos(Math.toRadians(GamePanel.direction)));
        vy = (int) (-speed * Math.sin(Math.toRadians(GamePanel.direction)));
        GamePanel.offsetX += vx;
        GamePanel.offsetY += vy;

        x -= vx;
        y -= vy;
    }

    public void rotateLeft() {
        GamePanel.direction -= GamePanel.ROTATE_SPEED;
        if (GamePanel.direction < 0) {
            GamePanel.direction += 360;
        }
    }

    public void rotateRight() {
        GamePanel.direction += GamePanel.ROTATE_SPEED;
        if (GamePanel.direction >= 360) {
            GamePanel.direction -= 360;
        }
    }

    public void shoot() {
        int h = Images.hull.getHeight();
        int w = Images.hull.getWidth();

        // Calculate the initial position of the bullet based on the tank's position and
        // direction
        int bulletX = x + w / 2 + (int) (w / 2 * Math.cos(Math.toRadians(GamePanel.direction)));
        int bulletY = y + h / 2 + (int) (h / 2 * Math.sin(Math.toRadians(GamePanel.direction)));

        // Calculate the velocity of the bullet based on the direction
        int bulletVx = (int) (10 * -Math.cos(Math.toRadians(GamePanel.direction)));
        int bulletVy = (int) (10 * -Math.sin(Math.toRadians(GamePanel.direction)));

        // Print all the values for debugging
        System.out.println("BulletX: " + bulletX + " BulletY: " + bulletY + " BulletVx: " + bulletVx + " BulletVy: "
                + bulletVy + " Direction: " + GamePanel.direction);

        // Create a new bullet
        Bullet b = new Bullet(bulletX, bulletY, 10, 10, bulletVx, bulletVy);
        GamePanel.bullets.add(b);
    }

    public void draw(Graphics2D g2d) {
        // Save the current transform
        AffineTransform old = g2d.getTransform();

        // Calculate the center of the tank
        int centerX = GamePanel.screenWidth / 2;
        int centerY = GamePanel.screenHeight / 2;

        // Apply the rotation transformation around the center of the tank
        g2d.rotate(Math.toRadians(GamePanel.direction), centerX, centerY);

        // Draw the tank image
        g2d.drawImage(Images.hull, centerX - Images.hull.getWidth() / 2, centerY - Images.hull.getHeight() / 2, null);

        g2d.setTransform(old);
        if (GamePanel.debugging) {
            g2d.drawRect(x, y, width, height);
        }

    }
}