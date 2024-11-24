package com.java.jeux.level01.contracts;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.java.jeux.level01.character.Player;

public interface EnemyBehavior {

    boolean detectPlayer();
    Player getPlayer();

    void moveTowardsPlayer(float deltaTime);

    Animation<TextureRegion> getIdleAnimation();
    Animation<TextureRegion> getDeadAnimation();
    Animation<TextureRegion> getHurtAnimation();
    Animation<TextureRegion> getWalkAnimation();

    void respawn();
    float getRepopTime();
    float getDeathTimer();
}
