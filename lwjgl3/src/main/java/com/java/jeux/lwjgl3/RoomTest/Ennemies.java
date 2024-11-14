package com.java.jeux.lwjgl3.RoomTest;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

import java.util.HashMap;
import java.util.Map;

public abstract class Ennemies extends Character {
    protected Animation<TextureRegion> idleAnimation;
    protected Animation<TextureRegion> deadAnimation;
    protected Sound deathSound;
    protected boolean isDying = false;
    protected float deathTimer = 0f;
    protected final float repopTime = 5f;
    protected final float preRepopTime = 2f;

    public Ennemies(float startX, float startY) {
        super(startX, startY);
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
        } else {
            TextureRegion currentFrame = idleAnimation.getKeyFrame(elapsedTime, true);
            batch.draw(currentFrame, position.x, position.y);
        }
    }

    public void die() {
        if (!isDying && !isDead) {
            isDying = true;
            elapsedTime = 0;
            if (deathSound != null) {
                deathSound.play(0.1f);
            }
        }
    }

    public void respawn() {
        isDead = false;
        isDying = false;
        elapsedTime = 0;
    }

    @Override
    public Rectangle getHitBox() {
        return new Rectangle(position.x + hitboxOffsetX, position.y, spriteWidth, spriteHeight);
    }
}
