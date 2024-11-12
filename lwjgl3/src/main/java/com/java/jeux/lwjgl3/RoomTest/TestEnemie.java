package com.java.jeux.lwjgl3.RoomTest;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

import java.util.HashMap;
import java.util.Map;

public class TestEnemie extends Character {
    private Animation<TextureRegion> idleAnimation;
    private Animation<TextureRegion> deadAnimation;
    private Sound deathSound;
    public boolean isDead = false;
    private boolean isDying = false;
    private float deathTimer = 0f;
    private final float repopTime = 5f;
    private final float preRepopTime = 2f;
    private SpriteResourceManager spriteManager;

    public TestEnemie(float startX, float startY) {
        super(startX, startY);
        spriteManager = new SpriteResourceManager();
    }

    @Override
    public void create() {
        // Charger les animations depuis le dossier de ressources
        String directory = "assets/Characters/Gorgon_1"; // Modifier le chemin si nécessaire
        Map<String, Integer> animations = new HashMap<>();
        animations.put("Idle", 7); // 7 frames pour l'animation "Idle"
        animations.put("Dead", 3); // 3 frames pour l'animation "Dead"

        // Utiliser la méthode de découpe pour charger les animations
        spriteManager.loadSprites(directory, animations, "single");

        // Initialiser les animations
        Array<TextureRegion> idleFrames = spriteManager.getAnimation(directory, "Idle");
        Array<TextureRegion> deadFrames = spriteManager.getAnimation(directory, "Dead");
        idleAnimation = new Animation<>(0.1f, idleFrames);
        deadAnimation = new Animation<>(0.2f, deadFrames);

        deathSound = Gdx.audio.newSound(Gdx.files.internal("assets/deathSound.mp3"));
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
        spriteManager.dispose(); // Assure-toi de libérer les ressources du gestionnaire
        deathSound.dispose();
    }

    @Override
    public boolean isFacingRight() {
        return true; // Peut être ajusté selon les besoins
    }
}
