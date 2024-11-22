package com.java.jeux.level01;

import com.badlogic.gdx.math.Rectangle;
import java.util.ArrayList;
import java.util.List;

public class AttackBoxManager {
    private float attackBoxWidth;
    private float attackBoxHeight;

    public AttackBoxManager(float attackBoxWidth, float attackBoxHeight) {
        this.attackBoxWidth = attackBoxWidth;
        this.attackBoxHeight = attackBoxHeight;
    }

    public List<Rectangle> generateAttackBoxes(Rectangle hitBox, boolean front, boolean back) {
        List<Rectangle> attackBoxes = new ArrayList<>();

        if (back) {
            attackBoxes.add(new Rectangle(
                hitBox.x - attackBoxWidth,
                hitBox.y,
                attackBoxWidth,
                attackBoxHeight
            ));
        }

        if (front) {
            attackBoxes.add(new Rectangle(
                hitBox.x + hitBox.width,
                hitBox.y,
                attackBoxWidth,
                attackBoxHeight
            ));
        }

        return attackBoxes;
    }


    public float getAttackBoxWidth() {
        return attackBoxWidth;
    }

    public void setAttackBoxWidth(float attackBoxWidth) {
        this.attackBoxWidth = attackBoxWidth;
    }

    public float getAttackBoxHeight() {
        return attackBoxHeight;
    }

    public void setAttackBoxHeight(float attackBoxHeight) {
        this.attackBoxHeight = attackBoxHeight;
    }
}
