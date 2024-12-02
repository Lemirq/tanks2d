package com.tanks2d;

import java.awt.Graphics2D;

public class Map {
    public static final int GRID_SIZE = 50;

    void draw(Graphics2D g2d, int width, int height) {
        for (int x = GamePanel.offsetX % GRID_SIZE - Images.map.getWidth(); x <= width
                + Images.map.getWidth(); x += GRID_SIZE) {
            for (int y = GamePanel.offsetY % GRID_SIZE - Images.map.getHeight(); y <= height
                    + Images.map.getHeight(); y += GRID_SIZE) {
                g2d.drawImage(Images.map, x, y, null);
            }
        }
    }
}
