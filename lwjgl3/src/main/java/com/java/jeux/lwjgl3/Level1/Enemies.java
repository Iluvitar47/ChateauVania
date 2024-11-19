package com.java.jeux.lwjgl3.Level1;

public abstract class Enemies extends Character {
    protected AIType aiType;

    public enum AIType {
        MONSTER,
        RUNNER,
        BOSS
    }

    public Enemies(AIType aiType, String name, int health, int damage, int lives) {
        super(name, health, damage, lives);
        this.aiType = aiType;
    }

    // Méthode abstraite pour définir le comportement de l'IA
    public abstract void behaviourAI();

    // Méthode pour déplacer l'ennemi selon un certain motif
    public void movePattern() {
        System.out.println(getName() + " se déplace selon un motif.");
        // Logique spécifique ici si besoin
    }

    // Méthode pour attaquer le joueur (en l'occurrence un Hero)
    public void attackPlayer(Hero player) {
        System.out.println(getName() + " attaque " + player.getName() + ".");
        // Ajouter la logique d'attaque ici
    }

    public AIType getAiType() {
        return aiType;
    }

    public void setAiType(AIType aiType) {
        this.aiType = aiType;
    }
}
