package com.java.jeux.lwjgl3;

public abstract class Character {
    protected String name;
    protected int health;
    protected int damage;
    protected int lives;
    protected float gravity;
    protected String move;
    protected boolean invincible;

    public Character(String name, int health, int damage, int lives) {
        this.name = name;
        this.health = health;
        this.damage = damage;
        this.lives = lives;
        this.gravity = 9.8f;
        this.move = "none";
        this.invincible = false;
    }

    // Méthode pour prendre des dégâts
    public void takeDamage(int damageAmount) {
        if (!invincible) {  // Si le personnage n'est pas invincible
            health -= damageAmount;
            if (health <= 0) {
                die();
            }
        } else {
            System.out.println(name + " est invincible et ne prend pas de dégâts !");
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
    }

    // Méthode pour augmenter la santé du personnage
    public void increaseHealth(int amount) {
        this.health += amount;
        System.out.println(name + " a maintenant " + health + " de santé.");
    }

    // Méthode pour augmenter les dégâts du personnage
    public void increaseDamage(int amount) {
        this.damage += amount;
        System.out.println(name + " a maintenant " + damage + " de dégâts.");
    }

    // Méthode pour définir l'état d'invincibilité du personnage
    public void setInvincible(boolean invincible) {
        this.invincible = invincible;
        if (invincible) {
            System.out.println(name + " est maintenant invincible.");
        } else {
            System.out.println(name + " n'est plus invincible.");
        }
    }

    // Récupérer l'état d'invincibilité
    public boolean isInvincible() {
        return invincible;
    }

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
