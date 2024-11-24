package com.java.jeux.level01.character;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.math.Rectangle;
import com.java.jeux.ChateauVania;
import com.java.jeux.GameOverScreen;
import com.java.jeux.level01.contracts.PlayerActions;
import com.java.jeux.level01.managers.SpriteResourceManager;
import com.java.jeux.level01.managers.AttackBoxManager;
import com.java.jeux.level01.managers.JumpManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The `Player` class represents the player character in the game.
 */
public class Player extends Character implements PlayerActions {
    private SpriteBatch batch;
    private Animation<TextureRegion> idleAnimation, walkAnimation, attackAnimation, hurtAnimation, deathAnimation;
    private Animation<TextureRegion> currentAnimation;
    private float elapsedTime = 0f, attackElapsedTime = 0f;
    private final float speed = 100f;
    private SpriteResourceManager spriteManager;
    private Pixmap currentPixmap;
    private JumpManager jumpManager = new JumpManager(150, 250, 300);
    private int lives = 3;
    private AttackBoxManager attackBoxManager;
    private boolean isKnockedBack = false;
    private float knockBackTime = 0.5f;
    private float knockBackElapsed = 0f;

    private boolean isInvincible = false;
    private float invincibilityTime = 1f;
    private float invincibilityElapsed = 0f;

    private float knockBackSpeed = 200f;

    /**
     * Constructs a new `Player` with the specified starting position, maximum health, and attack damage.
     *
     * @param startX the starting x-coordinate
     * @param startY the starting y-coordinate
     * @param MaxHealth the maximum health
     * @param AttackDamage the attack damage
     */
    public Player(float startX, float startY, int MaxHealth, int AttackDamage) {
        super(startX, startY, MaxHealth, AttackDamage);
        this.currentHealth = MaxHealth;
        spriteManager = new SpriteResourceManager();
    }

    /**
     * Initializes the player by loading animations and setting up the sprite batch.
     */
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

    /**
     * Prepares the pixmap for the player's sprite.
     *
     * @return the prepared pixmap
     */
    private Pixmap preparePixmap() {
        TextureRegion firstFrame = idleAnimation.getKeyFrame(0);
        firstFrame.getTexture().getTextureData().prepare();
        return firstFrame.getTexture().getTextureData().consumePixmap();
    }

    /**
     * Updates the player's state based on the elapsed time.
     *
     * @param deltaTime the time in seconds since the last update
     */
    @Override
    public void update(float deltaTime) {
        elapsedTime += deltaTime;

        if (isKnockedBack) {
            handleKnockBack(deltaTime);
        } else {
            handleMovement(deltaTime);
        }

        if (isInvincible) {
            handleInvincibility(deltaTime);
        }

        Animation<TextureRegion> newAnimation = currentAnimation;
        jumpManager.updateJump(this, deltaTime);

        if (isDying) {
            if (deathAnimation.isAnimationFinished(elapsedTime)) {
                if (lives > 0) {
                    respawn();
                } else {
                    isDead = true;
                }
            }
            newAnimation = deathAnimation;
        } else if (takeHit) {
            if (hurtAnimation.isAnimationFinished(elapsedTime)) {
                takeHit = false;
                elapsedTime = 0;
                if (currentHealth <= 0) {
                    die();
                }
            }
            newAnimation = hurtAnimation;
        } else if (isAttacking) {
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
    }

    /**
     * Handles the player's movement based on input.
     *
     * @param deltaTime the time in seconds since the last update
     */
    private void handleMovement(float deltaTime) {
        if (!isAttacking) {
            isWalking = false;

            if (!jumpManager.isDirectionLocked()) {
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
                jumpManager.startJump(this);
            }
        }
    }

    /**
     * Renders the player using the specified sprite batch.
     *
     * @param batch the sprite batch to use for rendering
     */
    @Override
    public void render(SpriteBatch batch) {
        if (isDead) {
            return;
        }

        if (isInvincible && ((int)(invincibilityElapsed * 10) % 2 == 0)) {
            return;
        }

        TextureRegion currentFrame = currentAnimation.getKeyFrame(elapsedTime, true);
        if (facingRight) {
            batch.draw(currentFrame, position.x, position.y-5, currentFrame.getRegionWidth(), currentFrame.getRegionHeight());
        } else {
            batch.draw(currentFrame, position.x + currentFrame.getRegionWidth(), position.y-5, -currentFrame.getRegionWidth(), currentFrame.getRegionHeight());
        }
    }

    /**
     * Gets the hitbox of the player.
     *
     * @return the hitbox
     */
    @Override
    public Rectangle getHitBox() {
        if (facingRight) {
            return new Rectangle(position.x + hitboxOffsetX / 2 + 15, position.y, spriteWidth, spriteHeight);
        } else {
            return new Rectangle(position.x + hitboxOffsetX / 2 + 20, position.y, spriteWidth, spriteHeight);
        }
    }

    /**
     * Gets the attack boxes of the player.
     *
     * @return a list of rectangles representing the attack boxes
     */
    public List<Rectangle> getAttackBoxes() {
        return attackBoxManager.generateAttackBoxes(getHitBox(), true, true);
    }

    @Override
    public Animation<TextureRegion> getCurrentAnimation() {
        return null;
    }

    @Override
    public void setCurrentAnimation(Animation<TextureRegion> animation) {

    }

    @Override
    public boolean isDying() {
        return isDying;
    }

    @Override
    public void die() {
        if (!isDying && !isDead) {
            isDying = true;
            elapsedTime = 0;
            currentAnimation = deathAnimation;
            lives--;

            if (lives <= 0) {
                ChateauVania.getInstance().setScreen(new GameOverScreen());
            } else {
                System.out.println("Respawning...");
            }
        }
    }

    @Override
    public void respawn() {
        isDead = false;
        isDying = false;
        elapsedTime = 0;
        currentHealth = MaxHealth;
        position.set(36, 64);
        currentAnimation = idleAnimation;
    }

    @Override
    public void dispose() {
        batch.dispose();
        spriteManager.dispose();
        if (currentPixmap != null) {
            currentPixmap.dispose();
        }
    }

    /**
     * Gets the number of lives the player has.
     *
     * @return the number of lives
     */
    public int getLives() {
        return lives;
    }

    /**
     * Sets the number of lives the player has.
     *
     * @param lives the new number of lives
     */
    public void setLives(int lives) {
        this.lives = lives;
    }

    /**
     * Gets the current health of the player.
     *
     * @return the current health
     */
    public int getCurrentHealth() {
        return this.currentHealth;
    }

    @Override
    public void takeDamage(int amount) {
        if (!isInvincible) {
            currentHealth -= amount;
            if (currentHealth <= 0) {
                die();
            } else {
                startKnockBack();
                startInvincibility();
            }
        }
    }

    /**
     * Starts the knockback effect for the player.
     */
    public void startKnockBack() {
        isKnockedBack = true;
        knockBackElapsed = 0f;
    }

    @Override
    public boolean isKnockedBack() {
        return false;
    }

    /**
     * Starts the invincibility period for the player.
     */
    public void startInvincibility() {
        isInvincible = true;
        invincibilityElapsed = 0f;
    }

    @Override
    public boolean isInvincible() {
        return false;
    }

    /**
     * Handles the knockback effect for the player.
     *
     * @param deltaTime the time in seconds since the last update
     */
    private void handleKnockBack(float deltaTime) {
        knockBackElapsed += deltaTime;
        float knockBackDirection = facingRight ? -1 : 1;
        position.x += knockBackSpeed * knockBackDirection * deltaTime;

        if (knockBackElapsed >= knockBackTime) {
            isKnockedBack = false;
        }
    }

    /**
     * Handles the invincibility period for the player.
     *
     * @param deltaTime the time in seconds since the last update
     */
    private void handleInvincibility(float deltaTime) {
        invincibilityElapsed += deltaTime;

        if ((int)(invincibilityElapsed * 10) % 2 == 0) {

        }

        if (invincibilityElapsed >= invincibilityTime) {
            isInvincible = false;
        }
    }
}
