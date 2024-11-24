package com.java.jeux.level01.contracts;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * The `CharacterState` interface defines the state and properties of a character.
 */
public interface CharacterState {

    /**
     * Gets the position of the character.
     *
     * @return the position
     */
    Vector2 getPosition();

    /**
     * Sets the position of the character.
     *
     * @param position the new position
     */
    void setPosition(Vector2 position);

    /**
     * Gets the width of the character.
     *
     * @return the width
     */
    float getWidth();

    /**
     * Gets the height of the character.
     *
     * @return the height
     */
    float getHeight();

    /**
     * Gets the hitbox of the character.
     *
     * @return the hitbox
     */
    Rectangle getHitBox();

    /**
     * Gets the weight between the hitbox and the sprite.
     *
     * @return the weight between the hitbox and the sprite
     */
    float getWeightBetweenHitBoxAndSprite();

    /**
     * Gets the velocity of the character.
     *
     * @return the velocity
     */
    Vector2 getVelocity();

    /**
     * Sets the velocity of the character.
     *
     * @param velocity the new velocity
     */
    void setVelocity(Vector2 velocity);

    /**
     * Checks if the character is facing right.
     *
     * @return true if the character is facing right, false otherwise
     */
    boolean isFacingRight();

    /**
     * Checks if the character is on the ground.
     *
     * @return true if the character is on the ground, false otherwise
     */
    boolean isOnGround();

    /**
     * Sets the on-ground state of the character.
     *
     * @param onGround the new on-ground state
     */
    void setOnGround(boolean onGround);

    /**
     * Checks if the character is colliding.
     *
     * @return true if the character is colliding, false otherwise
     */
    boolean isColliding();

    /**
     * Sets the colliding state of the character.
     *
     * @param colliding the new colliding state
     */
    void setColliding(boolean colliding);

    /**
     * Checks if the character is attacking.
     *
     * @return true if the character is attacking, false otherwise
     */
    boolean isAttacking();

    /**
     * Checks if the character is hurt.
     *
     * @return true if the character is hurt, false otherwise
     */
    boolean hurt();
}
