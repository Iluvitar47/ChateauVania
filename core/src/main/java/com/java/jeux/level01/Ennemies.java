package com.java.jeux.level01;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class Ennemies extends Character {
    protected Animation<TextureRegion> idleAnimation;
    protected Animation<TextureRegion> deadAnimation;
    protected Animation<TextureRegion> hurtAnimation;
    protected Animation<TextureRegion> walkAnimation;

    protected float deathTimer = 0f;
    protected final float repopTime = 5f;
    protected final float preRepopTime = 2f;
    private float hurtElapsedTime = 0f;


    protected float detectionRadius = 200f;
    protected float moveSpeed = 50f;

    protected Player player;

    public Ennemies(float startX, float startY, int MaxHealth, int AttackDamage, Player player) {
        super(startX, startY, MaxHealth, AttackDamage);
        this.player = player;
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
        } else if (takeHit) {
            hurtElapsedTime += deltaTime;
            if (hurtAnimation.isAnimationFinished(hurtElapsedTime)) {
                takeHit = false;
                hurtElapsedTime = 0f;
            }
        } else {
            Player player = getPlayer();
            if (player != null && detectPlayer()) {
                moveTowardsPlayer(deltaTime);
                isWalking = true;
            } else {
                isWalking = false;
            }
        }

        elapsedTime += deltaTime;
    }



    @Override
    public void render(SpriteBatch batch) {
        TextureRegion currentFrame;

        if (isDead) {
            if (deathTimer >= repopTime - preRepopTime) {
                batch.setColor(Color.GRAY);
                currentFrame = idleAnimation.getKeyFrame(elapsedTime, true);
                batch.draw(currentFrame, position.x, position.y);
                batch.setColor(Color.WHITE);
            }
            return;
        } else if (isDying) {
            batch.setColor(Color.GRAY);
            currentFrame = deadAnimation.getKeyFrame(elapsedTime, false);
            batch.draw(currentFrame, position.x, position.y);
            batch.setColor(Color.WHITE);
        } else if (takeHit) {
            currentFrame = hurtAnimation.getKeyFrame(hurtElapsedTime, false);
            batch.draw(currentFrame, position.x, position.y);
        } else if (isWalking) {
            currentFrame = walkAnimation.getKeyFrame(elapsedTime, true);
            if (facingRight) {
                batch.draw(currentFrame, position.x, position.y, currentFrame.getRegionWidth(), currentFrame.getRegionHeight());
            } else {

                batch.draw(currentFrame, position.x + currentFrame.getRegionWidth(), position.y, -currentFrame.getRegionWidth(), currentFrame.getRegionHeight());
            }
        } else {
            currentFrame = idleAnimation.getKeyFrame(elapsedTime, true);
            if (facingRight) {
                batch.draw(currentFrame, position.x, position.y, currentFrame.getRegionWidth(), currentFrame.getRegionHeight());
            } else {

                batch.draw(currentFrame, position.x + currentFrame.getRegionWidth(), position.y, -currentFrame.getRegionWidth(), currentFrame.getRegionHeight());
            }
        }
    }



    @Override
    public void respawn() {
        super.respawn();
        deathTimer = 0f;
        facingRight = true;
    }

    @Override
    public Rectangle getHitBox() {
        return new Rectangle(position.x + hitboxOffsetX, position.y, spriteWidth, spriteHeight);
    }

    private boolean detectPlayer() {
        Vector2 playerPosition = player.getPosition();
        float distance = position.dst(playerPosition);
        return distance <= detectionRadius;
    }

    private void moveTowardsPlayer(float deltaTime) {
        Vector2 playerPosition = player.getPosition();
        Vector2 direction = playerPosition.cpy().sub(position).nor();
        position.add(direction.scl(moveSpeed * deltaTime));
        facingRight = direction.x >= 0;
    }


    protected Player getPlayer() {
        return player;
    }

}

