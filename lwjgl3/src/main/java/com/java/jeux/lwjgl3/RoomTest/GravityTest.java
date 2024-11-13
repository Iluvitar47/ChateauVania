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

        // Utiliser directement getHitBox() sans réajuster manuellement la position
        Rectangle characterBounds = character.getHitBox();

        if (!character.isOnGround()) {
            velocity.y += gravity * deltaTime;
        }

        position.y += velocity.y * deltaTime;
        character.setOnGround(false);

        // Mettre à jour la position de la hitbox après le déplacement
        characterBounds = character.getHitBox(); // Récupérer la hitbox mise à jour

        for (Rectangle ground : groundObjects) {
            if (characterBounds.overlaps(ground)) {
                // Ajuster la position pour que le personnage se pose correctement sur le sol
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
