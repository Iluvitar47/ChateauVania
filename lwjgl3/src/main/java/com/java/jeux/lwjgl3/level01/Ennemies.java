package com.java.jeux.lwjgl3.level01;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public abstract class Ennemies extends Character {
    protected Animation<TextureRegion> idleAnimation;
    protected Animation<TextureRegion> deadAnimation;
    protected Animation<TextureRegion> hurtAnimation;
    protected Sound deathSound;
    protected float deathTimer = 0f;
    protected final float repopTime = 5f;
    protected final float preRepopTime = 2f;
    private float hurtElapsedTime = 0f;

    public Ennemies(float startX, float startY, int MaxHealth, int AttackDamage) {
        super(startX, startY, MaxHealth, AttackDamage);
    }

    @Override
    public void update(float deltaTime) {
        if (isDying) {
            elapsedTime += deltaTime;
            if (deadAnimation.isAnimationFinished(elapsedTime)) {
                isDying = false;
                isDead = true;
                deathTimer = 0f;
            }
        } else if (isDead) {
            deathTimer += deltaTime;
            if (deathTimer >= repopTime) {
                respawn();
            }
        } else if (takeHit) {

            hurtElapsedTime += deltaTime;
            if (hurtAnimation.isAnimationFinished(hurtElapsedTime)) {
                takeHit = false;
                hurtElapsedTime = 0f;
            }
        } else {
            elapsedTime += deltaTime;
        }
    }

    @Override
    public void render(SpriteBatch batch) {
        if (isDead) {
            if (deathTimer >= repopTime - preRepopTime) {
                batch.setColor(Color.GRAY);
                TextureRegion currentFrame = idleAnimation.getKeyFrame(elapsedTime, true);
                batch.draw(currentFrame, position.x, position.y);
                batch.setColor(Color.WHITE);
            }
            return;
        } else if (isDying) {
            batch.setColor(Color.GRAY);
            TextureRegion currentFrame = deadAnimation.getKeyFrame(elapsedTime, false);
            batch.draw(currentFrame, position.x, position.y);
            batch.setColor(Color.WHITE);
        } else if (takeHit) {
            TextureRegion currentFrame = hurtAnimation.getKeyFrame(hurtElapsedTime, false);
            batch.draw(currentFrame, position.x, position.y);
        } else {
            TextureRegion currentFrame = idleAnimation.getKeyFrame(elapsedTime, true);
            batch.draw(currentFrame, position.x, position.y);
        }
    }

    @Override
    public void die() {
        if (!isDying && !isDead) {
            isDying = true;
            elapsedTime = 0;
            if (deathSound != null) {
                deathSound.play(0.1f);
            }
        }
    }

    @Override
    public void respawn() {
        super.respawn();
        deathTimer = 0f;
    }

    @Override
    public Rectangle getHitBox() {
        return new Rectangle(position.x + hitboxOffsetX, position.y, spriteWidth, spriteHeight);
    }
}
