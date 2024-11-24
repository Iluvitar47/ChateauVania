package com.java.jeux.level01.character;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.java.jeux.GlobalSettings;
import com.java.jeux.level01.contracts.DeathCycle;
import com.java.jeux.level01.contracts.CharacterState;

/**
 * The `Character` class represents a character in the game and implements the `DeathCycle` and `CharacterState` interfaces.
 */
public abstract class Character implements DeathCycle, CharacterState {

    protected Vector2 position;
    protected Vector2 velocity = new Vector2(0, 0);
    protected boolean onGround;
    protected boolean facingRight = true, isWalking = false;
    protected float hitboxOffsetX = 0;

    protected float spriteWidth, spriteHeight;
    protected float elapsedTime = 0f;

    protected boolean isDead = false, isDying = false;
    protected Sound deathSound;
    protected boolean isAttacking = false, takeHit = false;
    protected boolean isColliding;

    protected int MaxHealth;
    protected static int currentHealth;
    protected int AttackDamage;

    /**
     * Constructs a new `Character` with the specified starting position, maximum health, and attack damage.
     *
     * @param startX the starting x-coordinate
     * @param startY the starting y-coordinate
     * @param MaxHealth the maximum health
     * @param AttackDamage the attack damage
     */
    public Character(float startX, float startY, int MaxHealth, int AttackDamage) {
        this.position = new Vector2(startX, startY);
        this.MaxHealth = MaxHealth;
        this.AttackDamage = AttackDamage;
        this.currentHealth = MaxHealth;
    }

    /**
     * Initializes the character.
     */
    public abstract void create();

    /**
     * Updates the character's state based on the elapsed time.
     *
     * @param deltaTime the time in seconds since the last update
     */
    public abstract void update(float deltaTime);

    /**
     * Renders the character using the specified sprite batch.
     *
     * @param batch the sprite batch to use for rendering
     */
    public abstract void render(SpriteBatch batch);

    /**
     * Disposes of the character's resources.
     */
    public abstract void dispose();

    /**
     * Checks if the character is facing right.
     *
     * @return true if the character is facing right, false otherwise
     */
    public boolean isFacingRight() {
        return facingRight;
    }

    /**
     * Gets the hitbox of the character.
     *
     * @return the hitbox
     */
    public abstract Rectangle getHitBox();

    /**
     * Gets the width of the character.
     *
     * @return the width
     */
    public float getWidth() {
        return spriteWidth;
    }

    /**
     * Gets the height of the character.
     *
     * @return the height
     */
    public float getHeight() {
        return spriteHeight;
    }

    /**
     * Gets the position of the character.
     *
     * @return the position
     */
    public Vector2 getPosition() {
        return position;
    }

    /**
     * Sets the position of the character.
     *
     * @param position the new position
     */
    public void setPosition(Vector2 position) {
        this.position = position;
    }

    /**
     * Gets the velocity of the character.
     *
     * @return the velocity
     */
    public Vector2 getVelocity() {
        return velocity;
    }

    /**
     * Sets the velocity of the character.
     *
     * @param velocity the new velocity
     */
    public void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
    }

    /**
     * Checks if the character is on the ground.
     *
     * @return true if the character is on the ground, false otherwise
     */
    public boolean isOnGround() {
        return onGround;
    }

    /**
     * Sets the on-ground state of the character.
     *
     * @param onGround the new on-ground state
     */
    public void setOnGround(boolean onGround) {
        this.onGround = onGround;
    }

    /**
     * Checks if the character is colliding.
     *
     * @return true if the character is colliding, false otherwise
     */
    public boolean isColliding() {
        return isColliding;
    }

    /**
     * Sets the colliding state of the character.
     *
     * @param colliding the new colliding state
     */
    public void setColliding(boolean colliding) {
        this.isColliding = colliding;
    }

    /**
     * Gets the weight between the hitbox and the sprite.
     *
     * @return the weight between the hitbox and the sprite
     */
    public float getWeightBetweenHitBoxAndSprite() {
        return getHitBox().x - getPosition().x;
    }

    /**
     * Gets the maximum health of the character.
     *
     * @return the maximum health
     */
    @Override
    public int getMaxHealth() {
        return MaxHealth;
    }

    /**
     * Gets the current health of the character.
     *
     * @return the current health
     */
    public int getHealth() {
        return currentHealth;
    }

    /**
     * Sets the current health of the character.
     *
     * @param health the new health
     */
    @Override
    public void setHealth(int health) {
        this.currentHealth = Math.max(0, Math.min(health, MaxHealth));
    }

    /**
     * Sets the maximum health of the character.
     *
     * @param maxHealth the new maximum health
     */
    @Override
    public void setMaxHealth(int maxHealth) {
        this.MaxHealth = maxHealth;
    }

    /**
     * Checks if the character is alive.
     *
     * @return true if the character is alive, false otherwise
     */
    @Override
    public boolean isAlive() {
        return currentHealth > 0;
    }

    /**
     * Inflicts damage to the character.
     *
     * @param amount the amount of damage
     */
    @Override
    public void takeDamage(int amount) {
        if (isAlive()) {
            currentHealth -= amount;
            if (currentHealth <= 0) {
                currentHealth = 0;
                die();
            } else {
                hurt();
            }
        }
    }

    /**
     * Heals the character.
     *
     * @param amount the amount of healing
     */
    @Override
    public void heal(int amount) {
        if (isAlive()) {
            currentHealth = Math.min(currentHealth + amount, MaxHealth);
        }
    }

    /**
     * Kills the character.
     */
    @Override
    public void die() {
        if (!isDying && !isDead) {
            isDying = true;
            elapsedTime = 0;
            if (deathSound != null) {
                deathSound.play(GlobalSettings.getGlobalVolume());
            }
        }
    }

    /**
     * Respawns the character.
     */
    @Override
    public void respawn() {
        isDead = false;
        currentHealth = MaxHealth;
    }

    /**
     * Checks if the character is dying.
     *
     * @return true if the character is dying, false otherwise
     */
    @Override
    public boolean isDying() {
        return isDying;
    }

    /**
     * Checks if the character is dead.
     *
     * @return true if the character is dead, false otherwise
     */
    @Override
    public boolean isDead() {
        return isDead;
    }

    /**
     * Inflicts hurt to the character.
     *
     * @return true if the character is hurt, false otherwise
     */
    @Override
    public boolean hurt() {
        takeHit = true;
        return takeHit;
    }

    /**
     * Gets the attack damage of the character.
     *
     * @return the attack damage
     */
    @Override
    public int getAttackDamage() {
        return AttackDamage;
    }

    /**
     * Checks if the character is attacking.
     *
     * @return true if the character is attacking, false otherwise
     */
    public boolean isAttacking() {
        return isAttacking;
    }
}
