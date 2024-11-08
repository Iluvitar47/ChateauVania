package com.java.jeux.lwjgl3;

public class Health extends Items {
    private int healthAmount;

    public Health(Sprite sprite, String effect, int x, int y, int healthAmount) {
        super(sprite, effect, x, y);
        this.healthAmount = healthAmount;
    }

    // Méthode pour obtenir la quantité de santé
    public int getHealth() {
        return healthAmount;
    }

    // Implémentation de la méthode abstraite applyEffect
    @Override
    public void applyEffect(Character character) {
        // Logique pour appliquer l'effet de soin au personnage
        character.increaseHealth(healthAmount);
        System.out.println("La santé du personnage a été augmentée de " + healthAmount);
    }

    // Méthode pour rendre l'item visible (héritée de Items)
    @Override
    public void render() {
        super.render();
        System.out.println("Affichage de l'item de santé avec quantité de soin : " + healthAmount);
    }

    // Getters et Setters pour healthAmount
    public int getHealthAmount() {
        return healthAmount;
    }

    public void setHealthAmount(int healthAmount) {
        this.healthAmount = healthAmount;
    }
}
