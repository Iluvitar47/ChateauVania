package com.java.jeux.lwjgl3.level01;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.utils.Array;
import java.util.HashMap;
import java.util.Map;

public class Gorgon_1 extends Ennemies {
    private SpriteResourceManager spriteManager;
    private Pixmap currentPixmap;

    public Gorgon_1(float startX, float startY, int MaxHealth, int AttackDamage) {
        super(startX, startY, MaxHealth, AttackDamage);
        spriteManager = new SpriteResourceManager();
    }

    @Override
    public void create() {
        String directory = "assets/Characters/Gorgon_1";
        Map<String, Integer> animations = new HashMap<>();
        animations.put("Idle", 7);
        animations.put("Dead", 3);
        animations.put("Hurt", 3);

        spriteManager.loadSprites(directory, animations, "single");
        Array<TextureRegion> idleFrames = spriteManager.getAnimation(directory, "Idle");
        Array<TextureRegion> deadFrames = spriteManager.getAnimation(directory, "Dead");
        Array<TextureRegion> hurtFrames = spriteManager.getAnimation(directory, "Hurt");

        idleAnimation = new Animation<>(0.1f, idleFrames);
        deadAnimation = new Animation<>(0.2f, deadFrames);
        hurtAnimation = new Animation<>(0.1f, hurtFrames);

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
}
