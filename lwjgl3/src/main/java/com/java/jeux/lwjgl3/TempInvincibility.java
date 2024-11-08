package com.java.jeux.lwjgl3;

import java.util.Timer;
import java.util.TimerTask;

public class TempInvincibility extends Items {
    private int duration;

    public TempInvincibility(Sprite sprite, String effect, int x, int y, int duration) {
        super(sprite, effect, x, y);
        this.duration = duration;
    }

    // Méthode pour démarrer l'invincibilité pour un personnage
    public void startInvincibility(Character character) {
        character.setInvincible(true);
        System.out.println("Le personnage est maintenant invincible pour " + duration + " secondes.");

        // Planification de la fin de l'invincibilité après la durée spécifiée
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                character.setInvincible(false);
                System.out.println("Le personnage n'est plus invincible.");
            }
        }, duration * 1000); // en millisecondes
    }

    // Méthode pour obtenir la durée d'invincibilité
    public int getDuration() {
        return duration;
    }

    @Override
    public void applyEffect(Character character) {
        startInvincibility(character);
    }
}
