package com.tanks2d;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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

    public static boolean debugging = false;

    public static GameState gameState = GameState.MENU;
    PlayerTank p;
    // Game variables
    public static int direction = 0; // Direction of the tank
    public static int offsetX = 0; // Offset for the grid's X position
    public static int offsetY = 0; // Offset for the grid's Y position
    public static int MOVE_SPEED = 6; // Speed of movement
    public static int ROTATE_SPEED = 1; // Speed of rotation
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
        allCars = new AllCars();

        // new timer
        Timer timer = new Timer(10, new ActionListener() {
            // update elapsed time
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                if (gameState == GameState.MENU) {
                    return;
                }
                if (totalTime - (elapsedTime / 100) <= 0) {
                    gameState = GameState.GAMEOVER;
                }
                elapsedTime++;
                moveMap();
                updateBullets();
                checkCollisions();
                if (shootPressed) {
                    p.shoot();
                    shootPressed = false;
                }
                for (Bullet b : bullets) {
                    b.update();
                }
                repaint();
            }
        });

        timer.start();
    }

    public void reset() {
        offsetX = 0;
        offsetY = 0;
        // Reset player tank
        p = new PlayerTank();

        // Reset all cars
        allCars = new AllCars();

        // Reset bullets
        bullets.clear();

        // Reset elapsed time
        elapsedTime = 0;

        // Reset shoot pressed flag
        shootPressed = false;

        // Request focus in window again
        requestFocusInWindow();

        // Repaint the panel to reflect the reset state
        repaint();
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

    public void checkCollisions() {
        // Use an iterator to safely remove elements from the list
        Iterator<Bullet> bulletIterator = bullets.iterator();
        while (bulletIterator.hasNext()) {
            Bullet b = bulletIterator.next();
            Iterator<Car> carIterator = allCars.cars.iterator();
            while (carIterator.hasNext()) {
                Car c = carIterator.next();
                if (b.intersects(c)) {
                    carIterator.remove();
                    bulletIterator.remove();
                    p.score++;
                    break; // Exit the inner loop since the bullet is already removed
                }
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        if (gameState == GameState.GAMEOVER) {
            g2d.setColor(Color.RED);
            // set big font
            g2d.setFont(g2d.getFont().deriveFont(50f));
            g2d.drawString("Game Over", screenWidth / 2 - 100, screenHeight / 2);
            g2d.setFont(g2d.getFont().deriveFont(30f));
            g2d.drawString("Your score: " + p.score, screenWidth / 2 - 100, screenHeight / 2 + 50);
            g2d.drawString("Press R to restart", screenWidth / 2 - 100, screenHeight / 2 + 100);
            return;
        }

        // Set the rendering hints
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        // set old transformation
        oldTransformation = g2d.getTransform();

        // Draw the map
        map.draw(g2d, getWidth(), getHeight());
        // Draw the cars
        allCars.draw(g2d);
        for (Bullet b : bullets) {
            b.draw(g2d);

            if (debugging) {
                g2d.drawString("X: " + b.x + " Y: " + b.y + "VX: " + b.vx + " VY: " + b.vy, screenWidth - 90,
                        bullets.indexOf(b) * 20 + 40);
            }
        }
        p.draw(g2d);

        // Draw the bullets
        g2d.setColor(Color.RED);

        // debugging
        // g2d.drawString("X: " + p.x + " Y: " + p.y, 10, 10);
        // g2d.drawString("OffsetX: " + offsetX + " OffsetY: " + offsetY, 10, 30);
        // g2d.drawString("Direction: " + direction, 10, 50);
        // g2d.fillRect(screenWidth - 100, 0, 100, 100);

        // draw left over time

        // top left display score
        g2d.setColor(Color.black);
        g2d.fillRect(0, 0, 120, 50);
        g2d.setColor(Color.white);
        g2d.setFont(new Font("Arial", Font.BOLD, 20));
        g2d.drawString("Score: " + p.score, 20, 20);
        g2d.drawString("Time: " + (totalTime - (elapsedTime / 100)), 20, 40);
    }
}