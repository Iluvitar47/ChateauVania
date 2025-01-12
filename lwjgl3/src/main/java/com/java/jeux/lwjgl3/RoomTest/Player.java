package com.java.jeux.lwjgl3.RoomTest;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.math.Rectangle;
import java.util.ArrayList;
import java.util.List;
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
    private Jump jump = new Jump(150, 250, 300);

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
        spriteWidth = dimensions[0] -15;
        spriteHeight = dimensions[1] +4;
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
        jump.updateJump(this, deltaTime );
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

    private String lastDirection = "right";

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
                    lastDirection = "left";
                    isWalking = true;
                } else if (pressingRight) {
                    position.x += speed * deltaTime;
                    facingRight = true;
                    lastDirection = "right";
                    isWalking = true;
                }

                if (pressingLeft && pressingRight) {
                    if (lastDirection.equals("right")) {
                        facingRight = true;
                    } else if (lastDirection.equals("left")) {
                        facingRight = false;
                    }
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
        if (facingRight) {
            return new Rectangle(position.x + hitboxOffsetX / 2 + 15, position.y, spriteWidth, spriteHeight);
        }
        if (!facingRight) {
            return new Rectangle(position.x + hitboxOffsetX / 2 + 20, position.y, spriteWidth, spriteHeight);
        }

        return null;
    }

    public float getX() {
        return this.position.x + hitboxOffsetX / 2;
    }

    public float getY() {
        return this.position.y;
    }

    public List<Rectangle> getAttackBoxes() {
        List<Rectangle> attackBoxes = new ArrayList<>();
        Rectangle hitBox = getHitBox();

        float attackBoxWidth = 35;
        float attackBoxHeight = hitBox.height+5;


        Rectangle leftAttackBox = new Rectangle(
            hitBox.x - attackBoxWidth,
            hitBox.y,
            attackBoxWidth,
            attackBoxHeight
        );

        Rectangle rightAttackBox = new Rectangle(
            hitBox.x + hitBox.width,
            hitBox.y,
            attackBoxWidth,
            attackBoxHeight
        );

        attackBoxes.add(leftAttackBox);
        attackBoxes.add(rightAttackBox);

        return attackBoxes;
    }

}
