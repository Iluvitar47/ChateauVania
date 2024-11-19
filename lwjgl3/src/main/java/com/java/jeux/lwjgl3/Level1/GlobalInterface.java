package com.java.jeux.lwjgl3.Level1;

public interface GlobalInterface {
    int CurrentHealth = 100;
    int currentLives = 3;

    // Méthode pour afficher l'interface
    void displayInterface();

    // Méthode pour mettre à jour la santé
    void updateHealth(int health);

    // Méthode pour mettre à jour le nombre de vies
    void updateLives(int lives);

    // Méthode pour masquer l'interface
    void hideInterface();

    // Méthode de mise à jour générale de l'interface
    void update();
}
