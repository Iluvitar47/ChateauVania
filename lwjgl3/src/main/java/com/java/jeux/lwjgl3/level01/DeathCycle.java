package com.java.jeux.lwjgl3.level01;

public interface DeathCycle {
    int getMaxHealth();
    int getHealth();

    boolean isAlive();
    boolean isDying();

    void setHealth(int health);
    void setMaxHealth(int maxHealth);
    int getAttackDamage();
    void takeDamage(int amount);
    void heal(int amount);
    void die();
    void respawn();
    boolean hurt();
}
