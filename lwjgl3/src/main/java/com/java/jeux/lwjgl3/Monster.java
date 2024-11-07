package com.java.jeux.lwjgl3;

public class Monster extends Ennemies {

    public Monster(AIType aiType, String name, int health, int damage, int lives) {
        super(aiType, name, health, damage, lives); // Appel du constructeur parent
    }

    @Override
    public void behaviourAI() {
        // Comportement spécifique au Monster
        if (aiType == AIType.MONSTER) {
            System.out.println(getName() + " attaque le joueur avec une force brutale !");
            // Logique spécifique pour l'attaque du monstre
        }
    }

    // Méthode pour attaquer un joueur
    @Override
    public void attackPlayer(Hero player) {
        System.out.println(getName() + " attaque " + player.getName() + " avec des dégâts de " + getDamage());
        player.takeDamage(getDamage());  // Infliger les dégâts
    }
}