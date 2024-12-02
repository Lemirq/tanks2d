package com.tanks2d;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.tanks2d.GamePanel.GameState;

public class Menu extends JPanel implements ActionListener {
    // draw play button and a combo box for selcting easy, medium, hard difficulty
    // with SWING components
    JButton playButton = new JButton("Play");
    JComboBox<String> difficulty = new JComboBox<String>(new String[] { "Easy (30s)", "Medium (20s)", "Hard (10s)" });
    JLabel label = new JLabel("Tanks!");

    Menu() {
        setPreferredSize(new Dimension(GamePanel.screenWidth, GamePanel.screenHeight));
        // set box layout
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        // set huge font size
        label.setFont(new Font("Arial", Font.PLAIN, 80));
        difficulty.setFont(new Font("Arial", Font.PLAIN, 20));
        playButton.setFont(new Font("Arial", Font.PLAIN, 40));

        playButton.setActionCommand("play");
        playButton.addActionListener(this);
        setBorder(new EmptyBorder(200, 200, 200, 200));
        // add another label explaining the game
        JLabel l = new JLabel("Use WASD to move and space to shoot");
        JLabel l1 = new JLabel("Shoot as many cars as you can in the time limit");
        l.setFont(new Font("Arial", Font.PLAIN, 20));
        l1.setFont(new Font("Arial", Font.PLAIN, 20));

        add(label);
        add(l);
        add(l1);

        // make a jpanel and put difficulty and play button in it, flow layout

        JPanel p = new JPanel();
        p.setPreferredSize(new Dimension(GamePanel.screenWidth, GamePanel.screenHeight));

        difficulty.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (difficulty.getSelectedIndex()) {
                    case 0:
                        GamePanel.totalTime = 30;
                        break;
                    case 1:
                        GamePanel.totalTime = 20;
                        break;
                    case 2:
                        GamePanel.totalTime = 10;
                        break;
                }
            }
        });
        p.add(difficulty);
        p.add(playButton);
        add(p);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("play")) {
            GamePanel.gameState = GameState.GAME;
            MainFrame.m.setVisible(false);
            MainFrame.g.requestFocusInWindow();
        }
    }
}
