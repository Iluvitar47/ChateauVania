package com.java.jeux.lwjgl3.RoomTest;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.math.Rectangle;

import java.util.HashMap;
import java.util.Map;

public class Player extends Character {
    private SpriteBatch batch;
    private Animation<TextureRegion> idleAnimation, walkAnimation, attackAnimation;
    private Animation<TextureRegion> currentAnimation;
    private float elapsedTime = 0f, attackElapsedTime = 0f;
    private final float speed = 100f;
    public boolean facingRight = true, isWalking = false, isAttacking = false;
    private SpriteResourceManager spriteManager;
    private Pixmap currentPixmap;
    private Jump jump = new Jump(200, 500, 600);



    public Player(float startX, float startY) {
        super(startX, startY);
        spriteManager = new SpriteResourceManager();
    }

    @Override
    public void create() {
        batch = new SpriteBatch();


        String directory = "assets/Characters/Hero";
        Map<String, Integer> animations = new HashMap<>();
        animations.put("Idle", 8);
        animations.put("Walk", 10);
        animations.put("Attack", 6);


        spriteManager.loadSprites(directory, animations, "folder");


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
        jump.updateJump(this, deltaTime);
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
            if (!jump.isDirectionLocked()) {

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
            }

            if (Gdx.input.isKeyJustPressed(Input.Keys.E)) {
                isAttacking = true;
                attackElapsedTime = 0;
                elapsedTime = 0;
            }
            if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
                jump.startJump(this);
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

    @Override
    public Rectangle getHitBox() {
        if (facingRight && !isAttacking) {
            return new Rectangle(position.x + hitboxOffsetX / 2 + 18, position.y, spriteWidth - 28, spriteHeight);
        }
        if (!facingRight && !isAttacking) {
            return new Rectangle(position.x + hitboxOffsetX / 2 + 24, position.y, spriteWidth -28, spriteHeight);
        }
        if (isAttacking && facingRight) {
            return new Rectangle(position.x + hitboxOffsetX / 2 + 24, position.y, spriteWidth, spriteHeight);
        }
        if (isAttacking && !facingRight) {
            return new Rectangle(position.x + hitboxOffsetX / 2 + 24, position.y, spriteWidth, spriteHeight);
        }
        return null;
    }

    public float getX() {
        return this.position.x + hitboxOffsetX / 2;
    }

    public float getY() {
        return this.position.y;
    }
}
