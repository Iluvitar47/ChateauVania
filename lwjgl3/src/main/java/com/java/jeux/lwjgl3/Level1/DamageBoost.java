package com.java.jeux.lwjgl3.Level1;

public class DamageBoost extends Items {
    private int boostAmount;

    public DamageBoost(Sprite sprite, String effect, int x, int y, int boostAmount) {
        super(sprite, effect, x, y);
        this.boostAmount = boostAmount;
    }

    // Méthode pour obtenir la quantité de boost de dégâts
    public int getBoost() {
        return boostAmount;
    }

    // Implémentation de la méthode abstraite applyEffect
    @Override
    public void applyEffect(Character character) {
        // Logique pour appliquer le boost de dégâts au personnage
        character.increaseDamage(boostAmount);
        System.out.println("Les dégâts du personnage ont été augmentés de " + boostAmount);
    }

    // Méthode pour rendre l'item visible (héritée de Items)
    @Override
    public void render() {
        super.render();
        System.out.println("Affichage de l'item de boost de dégâts avec quantité de boost : " + boostAmount);
    }

    // Getters et Setters pour boostAmount
    public int getBoostAmount() {
        return boostAmount;
    }

    public void setBoostAmount(int boostAmount) {
        this.boostAmount = boostAmount;
    }
}
