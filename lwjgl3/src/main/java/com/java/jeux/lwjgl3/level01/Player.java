package com.java.jeux.lwjgl3.level01;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.math.Rectangle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Player extends Character {
    private SpriteBatch batch;
    private Animation<TextureRegion> idleAnimation, walkAnimation, attackAnimation, hurtAnimation, deathAnimation;
    private Animation<TextureRegion> currentAnimation;
    private float elapsedTime = 0f, attackElapsedTime = 0f;
    private final float speed = 100f;
    private boolean facingRight = true, isWalking = false, isAttacking = false;
    private SpriteResourceManager spriteManager;
    private Pixmap currentPixmap;
    private Jump jump = new Jump(150, 250, 300);
    private int lives = 3;
    private AttackBoxManager attackBoxManager;

    public Player(float startX, float startY, int MaxHealth, int AttackDamage) {
        super(startX, startY, MaxHealth, AttackDamage);
        this.currentHealth = MaxHealth;
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
        animations.put("Hurt", 3);
        animations.put("Death", 10);

        spriteManager.loadSprites(directory, animations, "folder");
        idleAnimation = new Animation<>(0.1f, spriteManager.getAnimation(directory, "Idle"));
        walkAnimation = new Animation<>(0.1f, spriteManager.getAnimation(directory, "Walk"));
        attackAnimation = new Animation<>(0.1f, spriteManager.getAnimation(directory, "Attack"));
        hurtAnimation = new Animation<>(0.1f, spriteManager.getAnimation(directory, "Hurt"));
        deathAnimation = new Animation<>(0.1f, spriteManager.getAnimation(directory, "Death"));
        currentAnimation = idleAnimation;


        currentPixmap = preparePixmap();


        float[] dimensions = spriteManager.calculateFrameDimensions(idleAnimation.getKeyFrame(0), currentPixmap);
        spriteWidth = dimensions[0] - 15;
        spriteHeight = dimensions[1] + 4;
        hitboxOffsetX = dimensions[2];

        attackBoxManager = new AttackBoxManager(35, getHitBox().height + 5);
    }

    private Pixmap preparePixmap() {
        TextureRegion firstFrame = idleAnimation.getKeyFrame(0);
        firstFrame.getTexture().getTextureData().prepare();
        return firstFrame.getTexture().getTextureData().consumePixmap();
    }

    @Override
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
        }

        else {
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
                boolean pressingLeft = Gdx.input.isKeyPressed(Input.Keys.Q) || Gdx.input.isKeyPressed(Input.Keys.A);
                boolean pressingRight = Gdx.input.isKeyPressed(Input.Keys.D);

                if (pressingLeft && pressingRight) {
                    isWalking = false;
                } else if (pressingLeft) {
                    position.x -= speed * deltaTime;
                    facingRight = false;
                    isWalking = true;
                } else if (pressingRight) {
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

    @Override
    public void render(SpriteBatch batch) {
        TextureRegion currentFrame = currentAnimation.getKeyFrame(elapsedTime, true);
        if (facingRight) {
            batch.draw(currentFrame, position.x, position.y, currentFrame.getRegionWidth(), currentFrame.getRegionHeight());
        } else {
            batch.draw(currentFrame, position.x + currentFrame.getRegionWidth(), position.y, -currentFrame.getRegionWidth(), currentFrame.getRegionHeight());
        }
    }

    @Override
    public Rectangle getHitBox() {

        if (facingRight) {
            return new Rectangle(position.x + hitboxOffsetX / 2 + 15, position.y, spriteWidth, spriteHeight);
        } else {
            return new Rectangle(position.x + hitboxOffsetX / 2 + 20, position.y, spriteWidth, spriteHeight);
        }
    }

    public List<Rectangle> getAttackBoxes() {
        return attackBoxManager.generateAttackBoxes(getHitBox(), true, true);
    }

    @Override
    public boolean isDying() {
        return isDying;
    }

    @Override
    public void die() {
        super.die();
        lives--;
        if (lives <= 0) {
            System.out.println("Game Over!");
        } else {
            System.out.println("Respawning...");
            respawn();
        }
    }

    @Override
    public void respawn() {
        super.respawn();
        currentHealth = MaxHealth;
        position.set(36, 36);
    }

    @Override
    public void dispose() {
        batch.dispose();
        spriteManager.dispose();
        if (currentPixmap != null) {
            currentPixmap.dispose();
        }
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    @Override
    public boolean isFacingRight() {
        return facingRight;
    }

    public boolean isAttacking() {
        return isAttacking;
    }


}
