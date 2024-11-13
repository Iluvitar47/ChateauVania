package com.java.jeux.lwjgl3.RoomTest;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import java.util.List;

public class MapsBordersManager {
    private List<Rectangle> leftBorders;
    private List<Rectangle> upBorders;
    private List<Rectangle> rightBorders;

    public MapsBordersManager(List<Rectangle> leftBorders, List<Rectangle> upBorders, List<Rectangle> rightBorders) {
        this.leftBorders = leftBorders;
        this.upBorders = upBorders;
        this.rightBorders = rightBorders;
    }

    public void applyBorders(Character character) {
        Vector2 position = character.getPosition();
        Rectangle characterBounds = character.getHitBox();


        for (Rectangle border : leftBorders) {
            if (characterBounds.x < border.x + border.width) {
                position.x = position.x + 5;
                character.setPosition(position);
                break;
            }
        }


        for (Rectangle border : rightBorders) {
            if (characterBounds.x + characterBounds.width > border.x) {
                position.x = position.x -5;
                character.setPosition(position);
                break;
            }
        }


        for (Rectangle border : upBorders) {
            if (characterBounds.y + characterBounds.height > border.y) {
                position.y = border.y - characterBounds.height;
                character.setPosition(position);
                break;
            }
        }
    }
}
