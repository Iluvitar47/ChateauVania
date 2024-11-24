package com.java.jeux.level01.contracts;

public interface DeathCycle {
    int getMaxHealth();
    int getHealth();

    boolean isAlive();
    boolean isDying();
    boolean isDead();

    void setHealth(int health);
    void setMaxHealth(int maxHealth);
    int getAttackDamage();
    void takeDamage(int amount);
    void heal(int amount);
    void die();
    void respawn();
    boolean hurt();
}
