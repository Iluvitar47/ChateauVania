package com.java.jeux.lwjgl3.RoomTest;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public interface DeathBehavior {

    void die();

    boolean isDead();

    void playDeathAnimation();

    void resetAfterDeath();


    default void playDeathSound(Sound deathSound) {
        if (deathSound != null) {
            deathSound.play(0.1f);
        }
    }


    default void renderDeadState(SpriteBatch batch, Animation<TextureRegion> idleAnimation, float elapsedTime, float positionX, float positionY, float repopTime, float deathTimer, float preRepopTime) {
        if (deathTimer >= repopTime - preRepopTime) {
            batch.setColor(Color.GRAY);
            TextureRegion currentFrame = idleAnimation.getKeyFrame(elapsedTime, true);
            batch.draw(currentFrame, positionX, positionY);
            batch.setColor(Color.WHITE);
        }
    }


    default void initializeDeathState(boolean isDying, boolean isDead, float elapsedTime, Sound deathSound) {
        if (!isDying && !isDead) {
            isDying = true;
            elapsedTime = 0;
            playDeathSound(deathSound);
        }
    }

    default void respawn() {

    }
}
