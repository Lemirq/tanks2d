package com.tanks2d;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;

public class MainFrame {
    static JFrame frame;
    static GamePanel g = new GamePanel();
    static Menu m = new Menu();

    public MainFrame() {
        frame = new JFrame();
        frame.setTitle("Tanks");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setFocusable(true);

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(GamePanel.screenWidth, GamePanel.screenHeight));
        layeredPane.add(g, JLayeredPane.DEFAULT_LAYER);
        g.setBounds(0, 0, GamePanel.screenWidth, GamePanel.screenHeight);
        layeredPane.add(m, JLayeredPane.PALETTE_LAYER);
        m.setBounds(0, 0, GamePanel.screenWidth, GamePanel.screenHeight);
        // frame.setLayeredPane(layeredPane);
        frame.add(layeredPane);

        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MainFrame();
            }
        });
    }
}