package com.java.jeux.level01;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import java.util.List;

public class GravityTest {
    private float gravity = -500f;
    private List<Rectangle> groundObjects;

    public GravityTest(List<Rectangle> groundObjects) {
        this.groundObjects = groundObjects;
    }

    public void applyGravity(Character character, float deltaTime) {
        Vector2 position = character.getPosition();
        Vector2 velocity = character.getVelocity();
        Rectangle characterBounds = character.getHitBox();
        boolean onGroundDetected = false;
        float tolerance = 1.0f; // Marge de tolérance pour la détection du sol

        if (!character.isOnGround()) {
            velocity.y += gravity * deltaTime;
        }

        position.y += velocity.y * deltaTime;

        for (Rectangle ground : groundObjects) {
            if (characterBounds.overlaps(ground) ||
                (characterBounds.x + characterBounds.width > ground.x &&
                    characterBounds.x < ground.x + ground.width &&
                    Math.abs(characterBounds.y - (ground.y + ground.height)) <= tolerance)) {

                position.y = ground.y + ground.height;
                velocity.y = 0;
                onGroundDetected = true;
                break;
            }
        }

        character.setOnGround(onGroundDetected);

        if (position.y < 0) {
            position.y = 0;
            velocity.y = 0;
            character.setOnGround(true);
        }

        character.setPosition(position);
    }



}
