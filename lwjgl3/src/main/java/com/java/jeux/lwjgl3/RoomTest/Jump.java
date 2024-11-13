package com.java.jeux.lwjgl3.RoomTest;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Jump {
    private float maxJumpHeight;
    private float jumpSpeed;
    private float horizontalSpeed;
    private boolean isJumping;
    private float currentHeight;
    private float initialYPosition;
    private boolean directionLocked;

    public Jump(float maxJumpHeight, float jumpSpeed, float horizontalSpeed) {
        this.maxJumpHeight = maxJumpHeight;
        this.jumpSpeed = jumpSpeed;
        this.horizontalSpeed = horizontalSpeed;
        this.isJumping = false;
        this.currentHeight = 0;
        this.initialYPosition = 0;
        this.directionLocked = false;
    }

    public void startJump(Character character) {
        if (!isJumping && character.isOnGround()) {
            isJumping = true;
            initialYPosition = character.getHitBox().y;
            character.setOnGround(false);
            directionLocked = true;
        }
    }

    public void updateJump(Character character, float deltaTime) {
        if (isJumping) {
            currentHeight += jumpSpeed * deltaTime;
            float newY = initialYPosition + currentHeight;


            Vector2 newPosition = character.getPosition();
            newPosition.y = newY;
            newPosition.x += (character.isFacingRight() ? 1 : -1) * horizontalSpeed * deltaTime;


            if (currentHeight >= maxJumpHeight) {
                isJumping = false;
                currentHeight = 0;
                character.setOnGround(true);
                directionLocked = false;
            }

            character.setPosition(newPosition);
        }
    }

    public boolean isJumping() {
        return isJumping;
    }

    public boolean isDirectionLocked() {
        return directionLocked;
    }
}

