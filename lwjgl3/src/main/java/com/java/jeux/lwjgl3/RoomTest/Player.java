package com.java.jeux.lwjgl3.RoomTest;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.Pixmap;

public class Player extends Character {
    private SpriteBatch batch;
    private Texture idleSheet, walkSheet, attackSheet;
    private Animation<TextureRegion> idleAnimation, walkAnimation, attackAnimation;
    private Animation<TextureRegion> currentAnimation;
    private float elapsedTime = 0f, attackElapsedTime = 0f;
    private final float speed = 100f;
    private final float attackDuration = 0.5f;
    public boolean facingRight = true, isWalking = false, isAttacking = false;
    private Pixmap idlePixmap, walkPixmap, attackPixmap;

    public Player(float startX, float startY) {
        super(startX, startY);
    }

    @Override
    public void create() {
        batch = new SpriteBatch();
        idleSheet = new Texture(Gdx.files.internal("Idle.png"));
        walkSheet = new Texture(Gdx.files.internal("Walk.png"));
        attackSheet = new Texture(Gdx.files.internal("Attack_03.png"));

        idleAnimation = createAnimation(idleSheet, 7);
        walkAnimation = createAnimation(walkSheet, 13);
        attackAnimation = createAnimation(attackSheet, 10);

        idlePixmap = preparePixmap(idleSheet);
        walkPixmap = preparePixmap(walkSheet);
        attackPixmap = preparePixmap(attackSheet);

        currentAnimation = idleAnimation;
        calculateFrameDimensions(currentAnimation.getKeyFrame(0), idlePixmap);
    }

    private Animation<TextureRegion> createAnimation(Texture sheet, int frameCount) {
        TextureRegion[][] tmpFrames = TextureRegion.split(sheet, sheet.getWidth() / frameCount, sheet.getHeight());
        TextureRegion[] frames = new TextureRegion[frameCount];
        for (int i = 0; i < frameCount; i++) {
            frames[i] = tmpFrames[0][i];
        }
        return new Animation<>(0.1f, frames);
    }

    private Pixmap preparePixmap(Texture sheet) {
        sheet.getTextureData().prepare();
        return sheet.getTextureData().consumePixmap();
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
            calculateFrameDimensions(currentAnimation.getKeyFrame(0), getCurrentPixmap());
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

    private Pixmap getCurrentPixmap() {
        if (currentAnimation == idleAnimation) {
            return idlePixmap;
        } else if (currentAnimation == walkAnimation) {
            return walkPixmap;
        } else {
            return attackPixmap;
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
        idleSheet.dispose();
        walkSheet.dispose();
        attackSheet.dispose();
        idlePixmap.dispose();
        walkPixmap.dispose();
        attackPixmap.dispose();
    }
}
