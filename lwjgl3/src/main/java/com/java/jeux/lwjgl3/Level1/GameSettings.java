package com.java.jeux.lwjgl3.Level1;

public interface GameSettings {
    boolean isDisplayed = false;

    // Méthode pour afficher les paramètres
    void displaySettings();

    // Méthode pour masquer les paramètres
    void hideSettings();

    // Méthode pour basculer l'affichage des paramètres
    boolean toggleDisplay();

    // Méthode pour vérifier si les paramètres sont affichés
    boolean isDisplayed();

    // Méthode pour mettre à jour l'affichage des paramètres
    void updateDisplay();

    // Méthode pour gérer les entrées utilisateur
    void handleInput();
}
