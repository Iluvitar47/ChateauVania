package com.java.jeux.lwjgl3.Level1;

import java.util.List;

public class GameControls {
    private List<String> controls;
    private float brightness;
    private int width;
    private int height;
    private float saturation;

    public GameControls(List<String> controls, float brightness, int width, int height, float saturation) {
        this.controls = controls;
        this.brightness = brightness;
        this.width = width;
        this.height = height;
        this.saturation = saturation;
    }

    // Méthode pour définir les contrôles
    public void setControl(List<String> controlMap) {
        this.controls = controlMap;
    }

    // Méthode pour obtenir la liste des contrôles
    public List<String> getControls() {
        return controls;
    }

    // Méthode pour ajuster la luminosité
    public void adjustBrightness(float level) {
        this.brightness = level;
    }

    // Méthode pour obtenir le niveau de luminosité
    public float getBrightness() {
        return brightness;
    }

    // Méthode pour définir la taille de la fenêtre
    public void setWindowSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    // Méthode pour obtenir la taille de la fenêtre
    public int[] getWindowSize() {
        return new int[] { width, height };
    }

    // Méthode pour ajuster la saturation
    public void adjustSaturation(float level) {
        this.saturation = level;
    }

    // Méthode pour obtenir le niveau de saturation
    public float getSaturation() {
        return saturation;
    }

    // Méthode pour appliquer les paramètres
    public void applySettings() {
        System.out.println("Applying settings:");
        System.out.println("Controls: " + controls);
        System.out.println("Brightness: " + brightness);
        System.out.println("Window Size: " + width + "x" + height);
        System.out.println("Saturation: " + saturation);
    }
}
