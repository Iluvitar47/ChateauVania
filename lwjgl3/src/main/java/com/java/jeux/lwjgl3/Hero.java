package com.java.jeux.lwjgl3;

public class Hero extends Character {
    private String specialAttack;
    private int defaultHealth;
    private int defaultLives;


    public Hero(String name, int health, int damage, int lives, String specialAttack, int defaultHealth, int defaultLives) {
        super(name, health, damage, lives);
        this.specialAttack = specialAttack;
        this.defaultHealth = defaultHealth;
        this.defaultLives = defaultLives;
    }

    // Implémentation de la méthode abstraite takeDamage
    @Override
    public void takeDamage(int damageAmount) {
        health -= damageAmount;
        if (health <= 0) {
            System.out.println(name + " est mort.");
        }
    }

    // Implémentation de la méthode abstraite attack
    @Override
    public void attack(Character target) {
        System.out.println(name + " attaque " + target.getName() + " et inflige " + damage + " de dégâts.");
        target.takeDamage(damage);
    }

    // Méthode de saut
    public void jump() {
        System.out.println(name + " saute !");
    }

    // Méthode pour utiliser une attaque spéciale
    public void useSpecialAttack() {
        System.out.println(name + " utilise son attaque spéciale : " + specialAttack);
    }

    // Méthode pour réinitialiser les stats du héros
    public void respawn() {
        health = defaultHealth;
        lives = defaultLives;
        System.out.println(name + " a été ressuscité avec " + health + " de santé et " + lives + " vies.");
    }

    // Méthode pour collecter un objet (abstractItems)
    public void collectItem(AbstractItems item) {
        System.out.println(name + " a collecté un objet : " + item.getName());
    }

    // Getters et Setters
    public String getSpecialAttack() {
        return specialAttack;
    }

    public void setSpecialAttack(String specialAttack) {
        this.specialAttack = specialAttack;
    }

    public int getDefaultHealth() {
        return defaultHealth;
    }

    public void setDefaultHealth(int defaultHealth) {
        this.defaultHealth = defaultHealth;
    }

    public int getDefaultLives() {
        return defaultLives;
    }

    public void setDefaultLives(int defaultLives) {
        this.defaultLives = defaultLives;
    }
}