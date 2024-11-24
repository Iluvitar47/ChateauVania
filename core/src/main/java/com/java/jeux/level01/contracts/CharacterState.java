package com.java.jeux.level01.contracts;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public interface CharacterState {


    Vector2 getPosition();
    void setPosition(Vector2 position);
    float getWidth();
    float getHeight();


    Rectangle getHitBox();
    float getWeightBetweenHitBoxAndSprite();


    Vector2 getVelocity();
    void setVelocity(Vector2 velocity);
    boolean isFacingRight();


    boolean isOnGround();
    void setOnGround(boolean onGround);
    boolean isColliding();
    void setColliding(boolean colliding);


    boolean isAttacking();
    boolean hurt();
}
