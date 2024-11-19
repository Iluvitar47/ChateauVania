package com.java.jeux.lwjgl3.level01;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsWindow extends JFrame {
    private JTextField widthField;
    private JTextField heightField;
    private JSlider brightnessSlider;
    private JSlider opacitySlider;
    private boolean settingsApplied = false;

    public SettingsWindow() {
        setTitle("Settings");
        setSize(300, 200);
        setLayout(new GridLayout(5, 2));

        add(new JLabel("Width:"));
        widthField = new JTextField("1900");
        add(widthField);

        add(new JLabel("Height:"));
        heightField = new JTextField("1000");
        add(heightField);

        add(new JLabel("Brightness:"));
        brightnessSlider = new JSlider(0, 100, 50);
        add(brightnessSlider);

        add(new JLabel("Opacity:"));
        opacitySlider = new JSlider(0, 100, 100);
        add(opacitySlider);

        JButton applyButton = new JButton("Apply");
        applyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                settingsApplied = true;
                setVisible(false);
            }
        });
        add(applyButton);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public boolean isSettingsApplied() {
        return settingsApplied;
    }

    public int getWidthValue() {
        return Integer.parseInt(widthField.getText());
    }

    public int getHeightValue() {
        return Integer.parseInt(heightField.getText());
    }

    public float getBrightnessValue() {
        return brightnessSlider.getValue() / 100.0f;
    }

    public float getOpacityValue() {
        return opacitySlider.getValue() / 100.0f;
    }
}
