package com.java.jeux.level01.contracts;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

import java.util.List;

/**
 * The `PlayerActions` interface defines the actions and properties of a player character.
 */
public interface PlayerActions {

    /**
     * Gets the number of lives the player has.
     *
     * @return the number of lives
     */
    int getLives();

    /**
     * Sets the number of lives the player has.
     *
     * @param lives the new number of lives
     */
    void setLives(int lives);

    /**
     * Gets the attack boxes of the player.
     *
     * @return a list of rectangles representing the attack boxes
     */
    List<Rectangle> getAttackBoxes();

    /**
     * Gets the current animation of the player.
     *
     * @return the current animation
     */
    Animation<TextureRegion> getCurrentAnimation();

    /**
     * Sets the current animation of the player.
     *
     * @param animation the new animation
     */
    void setCurrentAnimation(Animation<TextureRegion> animation);

    /**
     * Starts the invincibility period for the player.
     */
    void startInvincibility();

    /**
     * Checks if the player is invincible.
     *
     * @return true if the player is invincible, false otherwise
     */
    boolean isInvincible();

    /**
     * Starts the knockback effect for the player.
     */
    void startKnockBack();

    /**
     * Checks if the player is knocked back.
     *
     * @return true if the player is knocked back, false otherwise
     */
    boolean isKnockedBack();
}
