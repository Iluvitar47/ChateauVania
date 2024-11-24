package com.java.jeux.level01;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The `SettingsWindow` class represents a settings window in the game.
 */
public class SettingsWindow extends JFrame {
    private final JTextField widthField;
    private final JTextField heightField;
    private final JSlider brightnessSlider;
    private final JSlider saturationSlider;
    private boolean settingsApplied = false;

    /**
     * Constructs a new `SettingsWindow` and initializes the UI components.
     */
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

        add(new JLabel("Saturation:"));
        saturationSlider = new JSlider(0, 100, 100);
        add(saturationSlider);

        JButton applyButton = new JButton("Apply");
        applyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                settingsApplied = true;
                dispose();
            }
        });
        add(applyButton);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    /**
     * Checks if the settings have been applied.
     *
     * @return true if the settings have been applied, false otherwise
     */
    public boolean isSettingsApplied() {
        return settingsApplied;
    }

    /**
     * Gets the width value from the text field.
     *
     * @return the width value
     */
    public int getWidthValue() {
        return Integer.parseInt(widthField.getText());
    }

    /**
     * Gets the height value from the text field.
     *
     * @return the height value
     */
    public int getHeightValue() {
        return Integer.parseInt(heightField.getText());
    }

    /**
     * Gets the brightness value from the slider.
     *
     * @return the brightness value as a float between 0.0 and 1.0
     */
    public float getBrightnessValue() {
        return brightnessSlider.getValue() / 100.0f;
    }

    /**
     * Gets the saturation value from the slider.
     *
     * @return the saturation value as a float between 0.0 and 1.0
     */
    public float getSaturationValue() {
        return saturationSlider.getValue() / 100.0f;
    }
}
