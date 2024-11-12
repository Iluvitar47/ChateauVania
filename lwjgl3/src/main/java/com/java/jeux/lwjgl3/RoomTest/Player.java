package com.java.jeux.lwjgl3.RoomTest;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.Pixmap;

import java.util.HashMap;
import java.util.Map;

public class Player extends Character {
    private SpriteBatch batch;
    private Animation<TextureRegion> idleAnimation, walkAnimation, attackAnimation;
    private Animation<TextureRegion> currentAnimation;
    private float elapsedTime = 0f, attackElapsedTime = 0f;
    private final float speed = 100f;
    private final float attackDuration = 0.5f;
    public boolean facingRight = true, isWalking = false, isAttacking = false;
    private SpriteResourceManager spriteManager;
    private Pixmap currentPixmap;

    public Player(float startX, float startY) {
        super(startX, startY);
        spriteManager = new SpriteResourceManager();
    }

    @Override
    public void create() {
        batch = new SpriteBatch();


        String directory = "assets/Characters/Gorgon_1";
        Map<String, Integer> animations = new HashMap<>();
        animations.put("Idle", 7);
        animations.put("Walk", 13);
        animations.put("Attack", 10);


        spriteManager.loadSprites(directory, animations, "single");


        idleAnimation = new Animation<>(0.1f, spriteManager.getAnimation(directory, "Idle"));
        walkAnimation = new Animation<>(0.1f, spriteManager.getAnimation(directory, "Walk"));
        attackAnimation = new Animation<>(0.1f, spriteManager.getAnimation(directory, "Attack"));
        currentAnimation = idleAnimation;


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

    public void render(SpriteBatch batch) {

        TextureRegion currentFrame = currentAnimation.getKeyFrame(elapsedTime, true);

        if (facingRight) {
            batch.draw(currentFrame, position.x, position.y, currentFrame.getRegionWidth(), currentFrame.getRegionHeight());
        } else {
            batch.draw(currentFrame, position.x + currentFrame.getRegionWidth(), position.y, -currentFrame.getRegionWidth(), currentFrame.getRegionHeight());
        }

    }

    public void update(float deltaTime) {
        elapsedTime += deltaTime;
        Animation<TextureRegion> newAnimation = currentAnimation;
        if (isAttacking) {
            attackElapsedTime += deltaTime;
            if (attackAnimation.isAnimationFinished(attackElapsedTime)) {
                isAttacking = false;
                attackElapsedTime = 0;
                elapsedTime = 0;
            }
            newAnimation = attackAnimation;
        } else if (isWalking) {
            newAnimation = walkAnimation;
        } else {
            newAnimation = idleAnimation;
        }

        if (newAnimation != currentAnimation) {
            currentAnimation = newAnimation;
        }

        handleMovement(deltaTime);
    }

    private void handleMovement(float deltaTime) {
        if (!isAttacking) {
            isWalking = false;
            if (Gdx.input.isKeyPressed(Input.Keys.Q) || Gdx.input.isKeyPressed(Input.Keys.A)) {
                position.x -= speed * deltaTime;
                facingRight = false;
                isWalking = true;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.D)) {
                position.x += speed * deltaTime;
                facingRight = true;
                isWalking = true;
            }

            if (Gdx.input.isKeyJustPressed(Input.Keys.E)) {
                isAttacking = true;
                attackElapsedTime = 0;
                elapsedTime = 0;
            }
        }
    }

    public boolean isAttacking() {
        return isAttacking;
    }

    @Override
    public float getWidth() {
        return spriteWidth;
    }

    @Override
    public float getHeight() {
        return spriteHeight;
    }

    @Override
    public boolean isFacingRight() {
        return facingRight;
    }

    @Override
    public void dispose() {
        batch.dispose();
        spriteManager.dispose();
        if (currentPixmap != null) {
            currentPixmap.dispose();
        }
    }

    public float getX() {
        return this.position.x;
    }

    public float getY() {
        return this.position.y;
    }
}
