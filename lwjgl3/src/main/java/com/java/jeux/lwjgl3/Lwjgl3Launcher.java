package com.java.jeux.lwjgl3;

import com.badlogic.gdx.graphics.Color;
import com.java.jeux.lwjgl3.level01.Level01Render;
import com.java.jeux.lwjgl3.level01.LaunchPage;
import com.java.jeux.lwjgl3.level01.SettingsWindow;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

public class Lwjgl3Launcher {
    public static void main(String[] args) {
        SettingsWindow settingsWindow = new SettingsWindow();
        settingsWindow.setVisible(true);

        while (!settingsWindow.isSettingsApplied()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        LaunchPage launchPage = new LaunchPage(settingsWindow);
        launchPage.setVisible(true);

        while (!launchPage.isStartGame()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        createApplication(settingsWindow);
    }

    private static Lwjgl3Application createApplication(SettingsWindow settingsWindow) {
        return new Lwjgl3Application(new Level01Render(), getDefaultConfiguration(settingsWindow));
    }

    private static Lwjgl3ApplicationConfiguration getDefaultConfiguration(SettingsWindow settingsWindow) {
        Lwjgl3ApplicationConfiguration configuration = new Lwjgl3ApplicationConfiguration();
        configuration.setTitle("ProjetJava");
        configuration.useVsync(true);
        configuration.setForegroundFPS(Lwjgl3ApplicationConfiguration.getDisplayMode().refreshRate + 1);
        configuration.setWindowedMode(settingsWindow.getWidthValue(), settingsWindow.getHeightValue());

        float brightness = settingsWindow.getBrightnessValue();
        float saturation = settingsWindow.getSaturationValue();
        configuration.setInitialBackgroundColor(new Color(
                brightness * saturation,
                brightness * (1 - saturation) * 0.5f,
                brightness * (1 - saturation) * 0.5f,
                1.0f
        ));

        configuration.setWindowIcon("libgdx128.png", "libgdx64.png", "libgdx32.png", "libgdx16.png");
        return configuration;
    }
}
