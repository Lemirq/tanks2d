package com.tanks2d;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.tanks2d.GamePanel.GameState;

public class KeyEvents extends KeyAdapter {
    boolean released = true;

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        switch (key) {
            case KeyEvent.VK_W:
                GamePanel.upPressed = true;
                break;
            case KeyEvent.VK_S:
                GamePanel.downPressed = true;
                break;
            case KeyEvent.VK_A:
                GamePanel.leftPressed = true;
                break;
            case KeyEvent.VK_D:
                GamePanel.rightPressed = true;
                break;
            case KeyEvent.VK_SPACE:
                if (released) {
                    GamePanel.shootPressed = true;
                    released = false;
                }
                break;
            case KeyEvent.VK_R:
                MainFrame.g.reset();
                GamePanel.gameState = GameState.MENU;
                MainFrame.m.setVisible(true);
                MainFrame.m.requestFocusInWindow();
                break;

            case KeyEvent.VK_F:
                GamePanel.debugging = !GamePanel.debugging;
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        switch (key) {
            case KeyEvent.VK_W:
                GamePanel.upPressed = false;
                break;
            case KeyEvent.VK_S:
                GamePanel.downPressed = false;
                break;
            case KeyEvent.VK_A:
                GamePanel.leftPressed = false;
                break;
            case KeyEvent.VK_D:
                GamePanel.rightPressed = false;
                break;
            case KeyEvent.VK_SPACE:
                released = true;
                break;
        }
    }
}