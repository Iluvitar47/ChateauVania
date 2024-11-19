package com.java.jeux.lwjgl3.RoomTest;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

import java.util.HashMap;
import java.util.Map;

public class Gorgon_3 extends Ennemies {
    private SpriteResourceManager spriteManager;
    private Pixmap currentPixmap;

    public Gorgon_3(float startX, float startY) {
        super(startX, startY);
        spriteManager = new SpriteResourceManager();
    }

    @Override
    public void create() {
        String directory = "assets/Characters/Gorgon_3";
        Map<String, Integer> animations = new HashMap<>();
        animations.put("Idle", 7);
        animations.put("Dead", 3);

        spriteManager.loadSprites(directory, animations, "single");
        Array<TextureRegion> idleFrames = spriteManager.getAnimation(directory, "Idle");
        Array<TextureRegion> deadFrames = spriteManager.getAnimation(directory, "Dead");

        idleAnimation = new Animation<>(0.1f, idleFrames);
        deadAnimation = new Animation<>(0.2f, deadFrames);

        deathSound = Gdx.audio.newSound(Gdx.files.internal("assets/deathSound.mp3"));


        currentPixmap = preparePixmap();
        float[] dimensions = spriteManager.calculateFrameDimensions(idleAnimation.getKeyFrame(0), currentPixmap);
        spriteWidth = dimensions[0];
        spriteHeight = dimensions[1];
        hitboxOffsetX = dimensions[2];
    }

    private Pixmap preparePixmap() {
        TextureRegion firstFrame = idleAnimation.getKeyFrame(0);
        firstFrame.getTexture().getTextureData().prepare();
        return firstFrame.getTexture().getTextureData().consumePixmap();
    }

    @Override
    public boolean isFacingRight() {
        return true;
    }

    @Override
    public void dispose() {
        spriteManager.dispose();
        if (deathSound != null) {
            deathSound.dispose();
        }
        if (currentPixmap != null) {
            currentPixmap.dispose();
        }
    }

    @Override
    public void playDeathAnimation() {

    }

    @Override
    public void resetAfterDeath() {

    }

    @Override
    public void playDeathSound(Sound deathSound) {
        super.playDeathSound(deathSound);
    }

    @Override
    public void renderDeadState(SpriteBatch batch, Animation<TextureRegion> idleAnimation, float elapsedTime, float positionX, float positionY, float repopTime, float deathTimer, float preRepopTime) {
        super.renderDeadState(batch, idleAnimation, elapsedTime, positionX, positionY, repopTime, deathTimer, preRepopTime);
    }

    @Override
    public void initializeDeathState(boolean isDying, boolean isDead, float elapsedTime, Sound deathSound) {
        super.initializeDeathState(isDying, isDead, elapsedTime, deathSound);
    }
}
