package com.java.jeux.lwjgl3.Level1;

public class GameRunning {
    private boolean isRunning;
    private int currentLevel;

    public GameRunning(int startingLevel) {
        this.isRunning = false;
        this.currentLevel = startingLevel;
    }

    // Méthode pour démarrer le jeu
    public void startGame() {
        isRunning = true;
        System.out.println("Le jeu démarre au niveau " + currentLevel);
    }

    // Méthode pour mettre le jeu en pause
    public void pauseGame() {
        isRunning = false;
        System.out.println("Le jeu est en pause.");
    }

    // Méthode pour reprendre le jeu
    public void resumeGame() {
        if (!isRunning) {
            isRunning = true;
            System.out.println("Le jeu reprend au niveau " + currentLevel);
        }
    }

    // Méthode pour mettre fin au jeu
    public void endGame() {
        isRunning = false;
        System.out.println("Le jeu est terminé.");
    }

    // Vérifie si le jeu est en cours
    public boolean isGameRunning() {
        return isRunning;
    }

    // Met à jour l'état du jeu, comme le niveau actuel ou autres logiques
    public void update() {
        if (isRunning) {
            System.out.println("Mise à jour du niveau " + currentLevel);
            // Logique pour mettre à jour le jeu au niveau actuel
        }
    }

    // Getters et Setters pour currentLevel
    public int getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(int level) {
        this.currentLevel = level;
    }
}
