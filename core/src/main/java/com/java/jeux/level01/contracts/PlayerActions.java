package com.java.jeux.level01.contracts;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

import java.util.List;

public interface PlayerActions {


    int getLives();
    void setLives(int lives);


    List<Rectangle> getAttackBoxes();


    Animation<TextureRegion> getCurrentAnimation();
    void setCurrentAnimation(Animation<TextureRegion> animation);


    void startInvincibility();
    boolean isInvincible();


    void startKnockBack();
    boolean isKnockedBack();
}
