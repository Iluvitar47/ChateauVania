package com.java.jeux.lwjgl3.RoomTest;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import javax.swing.plaf.synth.SynthTextAreaUI;

public abstract class Character implements DeathBehavior {
    protected Vector2 position;
    protected Vector2 velocity = new Vector2(0, 0);
    protected boolean onGround;
    protected float elapsedTime = 0f;
    public boolean isDead = false;
    protected float hitboxOffsetX = 0;
    protected float spriteWidth;
    protected float spriteHeight;
    protected boolean isColliding;

    public Character(float startX, float startY) {
        this.position = new Vector2(startX, startY);
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

    public boolean isOnGround() {
        return onGround;
    }

    public void setOnGround(boolean onGround) {
        this.onGround = onGround;
    }

    public boolean isDead() {
        return isDead;
    }

    public boolean isColliding() {
        return isColliding;
    }

    public void setColliding(boolean colliding) {
        this.isColliding = colliding;
    }

    public float getWeightBetweenHitBoxAndSprite(){
        return getHitBox().x - getPosition().x;
    }

}
