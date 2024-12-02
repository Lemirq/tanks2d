package com.tanks2d;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Bullet extends Rectangle {
    int vx, vy;
    int startX, startY;
    int maxD = 1000;
    public static int v = 1;

    Bullet(int x, int y, int width, int height, int vx, int vy) {
        super(x, y, width, height);
        this.startX = x;
        this.startY = y;
        this.vx = vx;
        this.vy = vy;
    }

    public void update() {
        x += vx;
        y += vy;
    }

    public boolean checkCollision(Rectangle r) {
        return this.intersects(r);
    }

    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.yellow);
        g2d.fillOval(x + GamePanel.offsetX, y + GamePanel.offsetY, width, height);
        g2d.setColor(Color.RED);
        if (GamePanel.debugging) {
            g2d.drawRect(x, y, width, height);
        }
    }
}