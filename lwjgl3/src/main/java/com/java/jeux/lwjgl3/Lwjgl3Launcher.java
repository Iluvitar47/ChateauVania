package com.java.jeux.lwjgl3;

import com.java.jeux.lwjgl3.level01.Level01Render;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

public class Lwjgl3Launcher {
    public static void main(String[] args) {
        com.java.jeux.lwjgl3.level01.SettingsWindow settingsWindow = new com.java.jeux.lwjgl3.level01.SettingsWindow();
        settingsWindow.setVisible(true);

        // Wait for the settings to be applied
        while (!settingsWindow.isSettingsApplied()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        createApplication(settingsWindow);
    }

    private static Lwjgl3Application createApplication(com.java.jeux.lwjgl3.level01.SettingsWindow settingsWindow) {
        return new Lwjgl3Application(new Level01Render(), getDefaultConfiguration(settingsWindow));
    }

    private static Lwjgl3ApplicationConfiguration getDefaultConfiguration(com.java.jeux.lwjgl3.level01.SettingsWindow settingsWindow) {
        Lwjgl3ApplicationConfiguration configuration = new Lwjgl3ApplicationConfiguration();
        configuration.setTitle("ProjetJava");
        configuration.useVsync(true);
        configuration.setForegroundFPS(Lwjgl3ApplicationConfiguration.getDisplayMode().refreshRate + 1);
        configuration.setWindowedMode(settingsWindow.getWidthValue(), settingsWindow.getHeightValue());
        configuration.setInitialBackgroundColor(new com.badlogic.gdx.graphics.Color(
                settingsWindow.getBrightnessValue(),
                settingsWindow.getBrightnessValue(),
                settingsWindow.getBrightnessValue(),
                settingsWindow.getOpacityValue()
        ));
        configuration.setWindowIcon("libgdx128.png", "libgdx64.png", "libgdx32.png", "libgdx16.png");
        return configuration;
    }
}
