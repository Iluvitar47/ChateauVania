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
        boolean collisionDetected = false;
        Rectangle hitBox = character.getHitBox();
        for (Rectangle solid : solidObjects) {
            if (hitBox.overlaps(solid)) {
                collisionDetected = true;
                resolveCollision(character, solid);
                break;
            }
        }
        character.setColliding(collisionDetected);
    }


    private void resolveCollision(Character character, Rectangle solid) {
        Rectangle hitBox = character.getHitBox();
        float spriteHeight = character.getHeight();
        Vector2 velocity = character.getVelocity();

        float leftOverlap = hitBox.x + hitBox.width - solid.x;
        float rightOverlap = solid.x + solid.width - hitBox.x;
        float topOverlap = hitBox.y + hitBox.height - solid.y;
        float bottomOverlap = solid.y + solid.height - hitBox.y;

        float minHorizontalOverlap = Math.min(Math.abs(leftOverlap), Math.abs(rightOverlap));
        float minVerticalOverlap = Math.min(Math.abs(topOverlap), Math.abs(bottomOverlap));

        if (minVerticalOverlap < minHorizontalOverlap) {
            if (Math.abs(bottomOverlap) < Math.abs(topOverlap)) {
                character.setPosition(new Vector2(character.getPosition().x, solid.y + solid.height));
                velocity.y = 0;
                character.setOnGround(true);
                System.out.println("bottom");
                System.out.println("Character x position:"+(character.getPosition().x+character.getHitBox().width));
            } else {
                character.setPosition(new Vector2(character.getPosition().x, solid.y - character.getHitBox().height));
                velocity.y = 0;
                    System.out.println("top");
                System.out.println("Character x position:"+(character.getPosition().x+character.getHitBox().width));

            }
        } else {
            if (Math.abs(leftOverlap) < Math.abs(rightOverlap)) {
                character.setPosition(new Vector2(solid.x -(character.getHitBox().width+character.getWeightBetweenHitBoxAndSprite()), character.getPosition().y));
                velocity.x = 0;

            } else {
                character.setPosition(new Vector2((solid.x+solid.width)-character.getWeightBetweenHitBoxAndSprite(), character.getPosition().y));
                velocity.x = 0;
                System.out.println("right");
            }
        }
    }
}
