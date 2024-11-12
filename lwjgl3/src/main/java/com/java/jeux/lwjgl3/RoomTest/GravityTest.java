package com.java.jeux.lwjgl3.RoomTest;

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
        Rectangle gravityHitbox = character.getBounds();
        gravityHitbox.setPosition(position.x, position.y);
        if (!character.isOnGround()) {
            velocity.y += gravity * deltaTime;
        }
        position.y += velocity.y * deltaTime;
        character.setOnGround(false);
        gravityHitbox.setPosition(position.x, position.y);
        for (Rectangle ground : groundObjects) {
            if (gravityHitbox.overlaps(ground)) {
                position.y = ground.y + ground.height;
                velocity.y = 0;
                character.setOnGround(true);
                break;
            }
        }
        if (position.y < 0) {
            position.y = 0;
            velocity.y = 0;
            character.setOnGround(true);
        }
        character.setPosition(position);
    }
}
