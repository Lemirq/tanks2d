package com.tanks2d;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel {
    public enum GameState {
        MENU, GAME, GAMEOVER
    }

    public static GameState gameState = GameState.MENU;
    PlayerTank p;
    // Game variables
    public static int direction = 90; // Direction of the tank
    public static int offsetX = 0; // Offset for the grid's X position
    public static int offsetY = 0; // Offset for the grid's Y position
    public static int MOVE_SPEED = 30; // Speed of movement
    public static int ROTATE_SPEED = 5; // Speed of rotation
    public static AffineTransform oldTransformation;
    public static int elapsedTime = 0;
    public static int totalTime = 30;
    public static int screenWidth = 1200;
    public static int screenHeight = 900;
    public static boolean upPressed = false;
    public static boolean downPressed = false;
    public static boolean leftPressed = false;
    public static boolean rightPressed = false;
    public static boolean shootPressed = false;
    public static ArrayList<Bullet> bullets = new ArrayList<>();
    AllCars allCars;
    CollisionManager cm = new CollisionManager();

    // Grid variables
    public static final int[] X_Bounds = { -5000, 5000 };
    public static final int[] Y_Bounds = { -2000, 2000 };
    Map map = new Map();

    GamePanel() {
        System.out.println("GamePanel");
        setPreferredSize(new Dimension(screenWidth, screenHeight));
        addKeyListener(new KeyEvents());
        setFocusable(true);
        requestFocusInWindow();
        Images.loadImages();
        p = new PlayerTank();
        allCars = new AllCars(cm);

        // new timer
        Timer timer = new Timer(10, new ActionListener() {
            // update elapsed time
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                elapsedTime++;
                moveMap();
                updateBullets();
                if (shootPressed) {
                    p.shoot(cm);
                }
                for (Bullet b : bullets) {
                    b.update();
                }
                repaint();
            }
        });

        timer.start();
    }

    void moveMap() {
        if (upPressed) {
            System.out.println("up");
            p.moveForward();
        }
        if (downPressed) {
            System.out.println("down");
            p.moveBackward();
        }
        if (leftPressed) {
            System.out.println("left");
            p.rotateLeft();
        }
        if (rightPressed) {
            System.out.println("right");
            p.rotateRight();
        }
    }

    public void updateBullets() {
        // Update bullets
        Iterator<Bullet> iterator = bullets.iterator();
        while (iterator.hasNext()) {
            Bullet bullet = iterator.next();
            bullet.update();
            if (Math.abs(bullet.getX() - bullet.startX) > bullet.maxD ||
                    Math.abs(bullet.getY() - bullet.startY) > bullet.maxD) {
                iterator.remove();
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Set the rendering hints
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        // set old transformation
        oldTransformation = g2d.getTransform();

        // Draw the map
        map.draw(g2d, getWidth(), getHeight());
        // Draw the cars
        allCars.draw(g2d);
        p.draw(g2d);

        // Draw the bullets
        g2d.setColor(Color.RED);
        for (Bullet b : bullets) {
            b.draw(g2d);
            // g2d.drawString("Bullet", b.x + GamePanel.offsetX, b.y + GamePanel.offsetY);
        }

        // debugging
        g2d.drawString("X: " + p.x + " Y: " + p.y, 10, 10);
        g2d.drawString("OffsetX: " + offsetX + " OffsetY: " + offsetY, 10, 30);
        g2d.drawString("Direction: " + direction, 10, 50);
        g2d.fillRect(screenWidth - 100, 0, 100, 100);

        // draw left over time
        g2d.setColor(Color.BLACK);
        g2d.drawString("Time: " + (totalTime - (elapsedTime / 100)), screenWidth - 90, 20);
    }
}