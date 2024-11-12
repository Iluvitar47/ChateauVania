package com.java.jeux.lwjgl3.RoomTest;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.Gdx;

public class TestEnemie extends Character {
    private Texture idleSheet;
    private Texture deadSheet;
    private Animation<TextureRegion> idleAnimation;
    private Animation<TextureRegion> deadAnimation;
    private Sound deathSound;
    public boolean isDead = false;
    private boolean isDying = false;
    private float deathTimer = 0f;
    private final float repopTime = 5f;
    private final float preRepopTime = 2f;

    @Override
    public boolean isFacingRight() {
        return true;
    }

    public TestEnemie(float startX, float startY) {
        super(startX, startY);
    }

    @Override
    public void create() {
        idleSheet = new Texture(Gdx.files.internal("Idle.png"));
        deadSheet = new Texture(Gdx.files.internal("Dead.png"));
        deathSound = Gdx.audio.newSound(Gdx.files.internal("deathSound.mp3"));


        TextureRegion[][] tmpIdleFrames = TextureRegion.split(idleSheet,
            idleSheet.getWidth() / 7,
            idleSheet.getHeight());
        TextureRegion[] idleFrames = new TextureRegion[7];
        for (int i = 0; i < 7; i++) {
            idleFrames[i] = tmpIdleFrames[0][i];
        }
        idleAnimation = new Animation<>(0.1f, idleFrames);


        TextureRegion[][] tmpDeadFrames = TextureRegion.split(deadSheet,
            deadSheet.getWidth() / 3,
            deadSheet.getHeight());
        TextureRegion[] deadFrames = new TextureRegion[3];
        for (int i = 0; i < 3; i++) {
            deadFrames[i] = tmpDeadFrames[0][i];
        }
        deadAnimation = new Animation<>(0.2f, deadFrames);
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
            deathSound.play(0.1f);
        }
    }

    public void respawn() {
        isDead = false;
        isDying = false;
        elapsedTime = 0;
    }

    @Override
    public Rectangle getBounds() {
        TextureRegion currentFrame = isDying ? deadAnimation.getKeyFrame(elapsedTime, false)
            : idleAnimation.getKeyFrame(elapsedTime, true);
        return new Rectangle(position.x, position.y, currentFrame.getRegionWidth(), currentFrame.getRegionHeight());
    }

    @Override
    public void dispose() {
        idleSheet.dispose();
        deadSheet.dispose();
        deathSound.dispose();
    }
}
