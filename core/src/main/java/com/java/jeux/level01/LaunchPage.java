package com.java.jeux.level01;

import javax.swing.*;
import java.awt.*;

public class LaunchPage extends JFrame {
    private boolean startGame = false;

    public LaunchPage(SettingsWindow settingsWindow) {
        setTitle("Launch Page");
        setSize(settingsWindow.getWidthValue(), settingsWindow.getHeightValue());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon icon = new ImageIcon(getClass().getResource("/assets/Castle.png"));
                g.drawImage(icon.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        panel.setLayout(new GridBagLayout());

        JButton startButton = new JButton("Start");
        startButton.setFont(new Font("Arial", Font.BOLD, 24));
        startButton.setPreferredSize(new Dimension(200, 100));
        startButton.addActionListener(e -> {
            startGame = true;
            dispose();
        });

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(startButton, gbc);

        add(panel);
    }

    public boolean isStartGame() {
        return startGame;
    }
}
