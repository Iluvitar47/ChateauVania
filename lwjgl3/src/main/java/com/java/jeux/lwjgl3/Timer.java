package com.java.jeux.lwjgl3;

import java.time.Duration;
import java.time.LocalTime;

public class Timer {
    private LocalTime time;

    public Timer() {
        time = LocalTime.now(); // Initialise l'heure de départ à l'heure actuelle
    }

    // Méthode pour démarrer le timer
    public void start() {
        time = LocalTime.now(); // Redémarre l'heure actuelle
    }

    // Méthode pour obtenir le temps écoulé
    public Duration getElapsedTime() {
        return Duration.between(time, LocalTime.now()); // Retourne la durée depuis le début
    }

    // Méthode pour vérifier si le temps est écoulé (par exemple, après un certain seuil)
    public boolean isTimeUp() {
        Duration elapsedTime = getElapsedTime();
        return elapsedTime.getSeconds() >= 60;
    }

    // Méthode pour réinitialiser le timer
    public void reset() {
        time = LocalTime.now(); // Réinitialise l'heure de départ à l'heure actuelle
    }
}