package com.java.jeux.lwjgl3.RoomTest;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import java.util.List;

public class SolidObjectsManager {
    private List<Rectangle> solidObjects;

    public SolidObjectsManager(List<Rectangle> solidObjects) {
        this.solidObjects = solidObjects;
    }

    public void applyCollision(Character character) {
        Rectangle hitBox = character.getHitBox();
        for (Rectangle solid : solidObjects) {
            if (hitBox.overlaps(solid)) {

                resolveCollision(character, solid);
            }
        }
    }

    private void resolveCollision(Character character, Rectangle solid) {
        Rectangle hitBox = character.getHitBox();

        // Calcule les distances de chevauchement dans chaque direction
        float leftOverlap = hitBox.x + hitBox.width - solid.x;
        float rightOverlap = solid.x + solid.width - hitBox.x;
        float topOverlap = hitBox.y + hitBox.height - solid.y;
        float bottomOverlap = solid.y + solid.height - hitBox.y;

        // Vérifie quelle distance est la plus petite pour déterminer la direction de la collision
        float minHorizontalOverlap = Math.min(Math.abs(leftOverlap), Math.abs(rightOverlap));
        float minVerticalOverlap = Math.min(Math.abs(topOverlap), Math.abs(bottomOverlap));

        // Priorise l'axe vertical si le personnage est en train de tomber ou de sauter
        if (minVerticalOverlap < minHorizontalOverlap) {
            if (character.getVelocity().y > 0 && Math.abs(topOverlap) < Math.abs(bottomOverlap)) {
                // Collision venant du bas (le personnage monte)
                character.setPosition(new Vector2(character.getPosition().x, solid.y));
                character.getVelocity().y = 0;
                System.out.println("Collision from top");
            } else if (character.getVelocity().y <= 0 && Math.abs(bottomOverlap) < Math.abs(topOverlap)) {
                character.setPosition(new Vector2(character.getPosition().x, solid.y + solid.height));
                character.getVelocity().y = 0;
                character.setOnGround(true);
                System.out.println("Collision from bottom");
            }
        } else {
            if (Math.abs(leftOverlap) < Math.abs(rightOverlap)) {
                character.setPosition(new Vector2(solid.x -solid.width, character.getPosition().y));
                character.getVelocity().x = 0;
                System.out.println("Collision from left");
            } else {
                character.setPosition(new Vector2(solid.x +hitBox.width, character.getPosition().y));
                character.getVelocity().x = 0;
                System.out.println("Collision from right");
            }
        }
    }


}
