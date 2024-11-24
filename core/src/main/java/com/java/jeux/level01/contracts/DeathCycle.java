package com.java.jeux.level01.contracts;

/**
 * The `DeathCycle` interface defines the lifecycle methods for characters that can die and respawn.
 */
public interface DeathCycle {
    /**
     * Gets the maximum health of the character.
     *
     * @return the maximum health
     */
    int getMaxHealth();

    /**
     * Gets the current health of the character.
     *
     * @return the current health
     */
    int getHealth();

    /**
     * Checks if the character is alive.
     *
     * @return true if the character is alive, false otherwise
     */
    boolean isAlive();

    /**
     * Checks if the character is in the process of dying.
     *
     * @return true if the character is dying, false otherwise
     */
    boolean isDying();

    /**
     * Checks if the character is dead.
     *
     * @return true if the character is dead, false otherwise
     */
    boolean isDead();

    /**
     * Sets the current health of the character.
     *
     * @param health the new health value
     */
    void setHealth(int health);

    /**
     * Sets the maximum health of the character.
     *
     * @param maxHealth the new maximum health value
     */
    void setMaxHealth(int maxHealth);

    /**
     * Gets the attack damage of the character.
     *
     * @return the attack damage
     */
    int getAttackDamage();

    /**
     * Inflicts damage to the character.
     *
     * @param amount the amount of damage to inflict
     */
    void takeDamage(int amount);

    /**
     * Heals the character by a specified amount.
     *
     * @param amount the amount to heal
     */
    void heal(int amount);

    /**
     * Kills the character.
     */
    void die();

    /**
     * Respawns the character.
     */
    void respawn();

    /**
     * Checks if the character is hurt.
     *
     * @return true if the character is hurt, false otherwise
     */
    boolean hurt();
}
