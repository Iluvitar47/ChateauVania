package com.java.jeux.level01.character;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.java.jeux.level01.contracts.EnemyBehavior;

/**
 * The `Enemy` class represents an enemy character in the game and implements the `EnemyBehavior` interface.
 */
public abstract class Enemy extends Character implements EnemyBehavior {
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

    /**
     * Constructs a new `Enemy` with the specified starting position, maximum health, attack damage, and player reference.
     *
     * @param startX the starting x-coordinate
     * @param startY the starting y-coordinate
     * @param MaxHealth the maximum health
     * @param AttackDamage the attack damage
     * @param player the player character
     */
    public Enemy(float startX, float startY, int MaxHealth, int AttackDamage, Player player) {
        super(startX, startY, MaxHealth, AttackDamage);
        this.player = player;
    }

    /**
     * Updates the enemy's state based on the elapsed time.
     *
     * @param deltaTime the time in seconds since the last update
     */
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

    /**
     * Renders the enemy using the specified sprite batch.
     *
     * @param batch the sprite batch to use for rendering
     */
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

    /**
     * Detects if the player is within range.
     *
     * @return true if the player is detected, false otherwise
     */
    @Override
    public boolean detectPlayer() {
        Vector2 playerPosition = player.getPosition();
        float distance = position.dst(playerPosition);
        return distance <= detectionRadius;
    }

    /**
     * Gets the player character.
     *
     * @return the player character
     */
    @Override
    public Player getPlayer() {
        return player;
    }

    /**
     * Moves the enemy towards the player.
     *
     * @param deltaTime the time in seconds since the last update
     */
    @Override
    public void moveTowardsPlayer(float deltaTime) {
        Vector2 playerPosition = player.getPosition();
        Vector2 direction = playerPosition.cpy().sub(position).nor();
        position.add(direction.scl(moveSpeed * deltaTime));
        facingRight = direction.x >= 0;
    }

    /**
     * Gets the idle animation of the enemy.
     *
     * @return the idle animation
     */
    @Override
    public Animation<TextureRegion> getIdleAnimation() {
        return idleAnimation;
    }

    /**
     * Gets the dead animation of the enemy.
     *
     * @return the dead animation
     */
    @Override
    public Animation<TextureRegion> getDeadAnimation() {
        return deadAnimation;
    }

    /**
     * Gets the hurt animation of the enemy.
     *
     * @return the hurt animation
     */
    @Override
    public Animation<TextureRegion> getHurtAnimation() {
        return hurtAnimation;
    }

    /**
     * Gets the walk animation of the enemy.
     *
     * @return the walk animation
     */
    @Override
    public Animation<TextureRegion> getWalkAnimation() {
        return walkAnimation;
    }

    /**
     * Gets the respawn time of the enemy.
     *
     * @return the respawn time
     */
    @Override
    public float getRepopTime() {
        return repopTime;
    }

    /**
     * Gets the death timer of the enemy.
     *
     * @return the death timer
     */
    @Override
    public float getDeathTimer() {
        return deathTimer;
    }

    /**
     * Respawns the enemy.
     */
    @Override
    public void respawn() {
        super.respawn();
        deathTimer = 0f;
        facingRight = true;
    }
}
