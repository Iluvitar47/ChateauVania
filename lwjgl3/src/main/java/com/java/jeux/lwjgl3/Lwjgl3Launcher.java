package com.java.jeux.lwjgl3;

import com.badlogic.gdx.graphics.Color;
import com.java.jeux.lwjgl3.level01.Level01Render;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

public class Lwjgl3Launcher {
    public static void main(String[] args) {
        // Vérifie si le programme tourne sous macOS
        if (System.getProperty("os.name").toLowerCase().contains("mac")) {
            if (!"1".equals(System.getProperty("java.awt.headless"))) {
                System.setProperty("java.awt.headless", "true");
            }
        }
        createApplication();
    }

    private static Lwjgl3Application createApplication() {
        return new Lwjgl3Application(new Level01Render(), getDefaultConfiguration());
    }

    private static Lwjgl3ApplicationConfiguration getDefaultConfiguration() {
        Lwjgl3ApplicationConfiguration configuration = new Lwjgl3ApplicationConfiguration();
        configuration.setTitle("CastleVania");
        configuration.useVsync(true);
        configuration.setForegroundFPS(Lwjgl3ApplicationConfiguration.getDisplayMode().refreshRate + 1);

        configuration.setWindowedMode(1280, 720); // Largeur et hauteur par défaut
        configuration.setInitialBackgroundColor(new Color(0.5f, 0.5f, 0.5f, 1.0f)); // Gris moyen

        configuration.setWindowIcon("libgdx128.png", "libgdx64.png", "libgdx32.png", "libgdx16.png");
        return configuration;
    }
}
