package com.java.jeux.lwjgl3.level01;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class Character implements DeathCycle {
    protected Vector2 position;
    protected Vector2 velocity = new Vector2(0, 0);
    protected boolean onGround;
    protected float elapsedTime = 0f;
    public boolean isDead = false;
    protected float hitboxOffsetX = 0;
    protected float spriteWidth;
    protected float spriteHeight;
    protected boolean isColliding;
    protected int MaxHealth;
    protected int AttackDamage;
    protected int currentHealth;
    protected boolean isDying = false;
    protected boolean takeHit = false;

    public Character(float startX, float startY, int MaxHealth, int AttackDamage) {
        this.position = new Vector2(startX, startY);
        this.MaxHealth = MaxHealth;
        this.AttackDamage = AttackDamage;
        this.currentHealth = MaxHealth; // Initialisation des PV
    }

    public abstract void create();
    public abstract void update(float deltaTime);
    public abstract void render(SpriteBatch batch);
    public abstract void dispose();
    public abstract boolean isFacingRight();

    public abstract Rectangle getHitBox();

    public float getWidth() {
        return spriteWidth;
    }

    public float getHeight() {
        return spriteHeight;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
    }

    public boolean isOnGround() {
        return onGround;
    }

    public void setOnGround(boolean onGround) {
        this.onGround = onGround;
    }

    public boolean isColliding() {
        return isColliding;
    }

    public void setColliding(boolean colliding) {
        this.isColliding = colliding;
    }

    public float getWeightBetweenHitBoxAndSprite() {
        return getHitBox().x - getPosition().x;
    }

    @Override
    public int getMaxHealth() {
        return MaxHealth;
    }

    @Override
    public int getHealth() {
        return currentHealth;
    }

    @Override
    public void setHealth(int health) {
        this.currentHealth = Math.max(0, Math.min(health, MaxHealth));
    }

    @Override
    public void setMaxHealth(int maxHealth) {
        this.MaxHealth = maxHealth;
    }

    @Override
    public boolean isAlive() {
        return currentHealth > 0;
    }

    @Override
    public void takeDamage(int amount) {
        if (isAlive()) {
            currentHealth -= amount;
            if (currentHealth <= 0) {
                currentHealth = 0;
                die();
            }
            else {
                hurt();
            }
        }
    }

    @Override
    public void heal(int amount) {
        if (isAlive()) {
            currentHealth = Math.min(currentHealth + amount, MaxHealth);
        }
    }

    @Override
    public void die() {
    }

    @Override
    public void respawn() {
        isDead = false;
        currentHealth = MaxHealth;
    }

    @Override
    public boolean isDying() {
        return isDying;
    }

    @Override
    public boolean isDead() {
        return isDead;
    }

    @Override
    public boolean hurt() {
        takeHit = true;
        return takeHit;
    }

    @Override
    public int getAttackDamage() {
        return AttackDamage;
    }
}
