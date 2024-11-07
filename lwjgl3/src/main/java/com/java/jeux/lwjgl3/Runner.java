package com.java.jeux.lwjgl3;

public class Runner extends Ennemies {

    public Runner(AIType aiType, String name, int health, int damage, int lives) {
        super(aiType, name, health, damage, lives);
    }

    @Override
    public void behaviourAI() {
        // Comportement spécifique au Runner
        if (aiType == AIType.RUNNER) {
            System.out.println(getName() + " fuit le joueur en courant à toute vitesse !");
            // Logique pour le comportement de fuite
        }
    }

    // Méthode pour attaquer un joueur
    @Override
    public void attackPlayer(Hero player) {
        System.out.println(getName() + " attaque " + player.getName() + " avec des dégâts de " + getDamage());
        player.takeDamage(getDamage());  // Infliger les dégâts
    }
}