package com.java.jeux.level01.contracts;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.java.jeux.level01.character.Player;

/**
 * The `EnemyBehavior` interface defines the behavior and properties of an enemy character.
 */
public interface EnemyBehavior {

    /**
     * Detects if the player is within range.
     *
     * @return true if the player is detected, false otherwise
     */
    boolean detectPlayer();

    /**
     * Gets the player character.
     *
     * @return the player character
     */
    Player getPlayer();

    /**
     * Moves the enemy towards the player.
     *
     * @param deltaTime the time in seconds since the last update
     */
    void moveTowardsPlayer(float deltaTime);

    /**
     * Gets the idle animation of the enemy.
     *
     * @return the idle animation
     */
    Animation<TextureRegion> getIdleAnimation();

    /**
     * Gets the dead animation of the enemy.
     *
     * @return the dead animation
     */
    Animation<TextureRegion> getDeadAnimation();

    /**
     * Gets the hurt animation of the enemy.
     *
     * @return the hurt animation
     */
    Animation<TextureRegion> getHurtAnimation();

    /**
     * Gets the walk animation of the enemy.
     *
     * @return the walk animation
     */
    Animation<TextureRegion> getWalkAnimation();

    /**
     * Respawns the enemy.
     */
    void respawn();

    /**
     * Gets the respawn time of the enemy.
     *
     * @return the respawn time
     */
    float getRepopTime();

    /**
     * Gets the death timer of the enemy.
     *
     * @return the death timer
     */
    float getDeathTimer();
}
