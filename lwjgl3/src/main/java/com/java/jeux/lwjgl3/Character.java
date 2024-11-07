package com.java.jeux.lwjgl3;

public abstract class Character {
    protected String name;
    protected int health;
    protected int damage;
    protected int lives;
    protected float gravity;
    protected String move;

    public Character(String name, int health, int damage, int lives) {
        this.name = name;
        this.health = health;
        this.damage = damage;
        this.lives = lives;
        this.gravity = 9.8f;
        this.move = "none";
    }

    // Méthode pour prendre des dégâts
    public void takeDamage(int damageAmount) {
        health -= damageAmount;
        if (health <= 0) {
            die();
        }
    }

    // Méthode pour appliquer le mouvement
    public void move(String direction) {
        this.move = direction;
        System.out.println(name + " se déplace vers " + direction);
    }

    // Méthode pour attaquer un autre personnage
    public void attack(Character target) {
        System.out.println(name + " attaque " + target.getName() + " et inflige " + damage + " de dégâts.");
        target.takeDamage(damage);
    }

    // Méthode pour la mort du personnage
    public void die() {
        System.out.println(name + " est mort.");
    }

    // Vérifie si le personnage est toujours en vie
    public boolean isAlive() {
        return health > 0;
    }

    // Applique la gravité au personnage (modification de la position, etc.)
    public void applyGravity() {
        System.out.println(name + " subit la gravité et tombe.");
        // Vous pouvez ajouter ici des effets physiques liés à la gravité
    }

    // Getters et Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public float getGravity() {
        return gravity;
    }

    public void setGravity(float gravity) {
        this.gravity = gravity;
    }

    public String getMove() {
        return move;
    }

    public void setMove(String move) {
        this.move = move;
    }
}