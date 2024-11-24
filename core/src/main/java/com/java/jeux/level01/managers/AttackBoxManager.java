package com.java.jeux.level01.managers;

import com.badlogic.gdx.math.Rectangle;
import java.util.ArrayList;
import java.util.List;

/**
 * The `AttackBoxManager` class manages the attack boxes for characters in the game.
 */
public class AttackBoxManager {
    private float attackBoxWidth;
    private float attackBoxHeight;

    /**
     * Constructs a new `AttackBoxManager` with the specified attack box dimensions.
     *
     * @param attackBoxWidth the width of the attack box
     * @param attackBoxHeight the height of the attack box
     */
    public AttackBoxManager(float attackBoxWidth, float attackBoxHeight) {
        this.attackBoxWidth = attackBoxWidth;
        this.attackBoxHeight = attackBoxHeight;
    }

    /**
     * Generates attack boxes based on the character's hitbox and attack directions.
     *
     * @param hitBox the hitbox of the character
     * @param front whether to generate an attack box in front of the character
     * @param back whether to generate an attack box behind the character
     * @return a list of generated attack boxes
     */
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

    /**
     * Gets the width of the attack box.
     *
     * @return the width of the attack box
     */
    public float getAttackBoxWidth() {
        return attackBoxWidth;
    }

    /**
     * Sets the width of the attack box.
     *
     * @param attackBoxWidth the new width of the attack box
     */
    public void setAttackBoxWidth(float attackBoxWidth) {
        this.attackBoxWidth = attackBoxWidth;
    }

    /**
     * Gets the height of the attack box.
     *
     * @return the height of the attack box
     */
    public float getAttackBoxHeight() {
        return attackBoxHeight;
    }

    /**
     * Sets the height of the attack box.
     *
     * @param attackBoxHeight the new height of the attack box
     */
    public void setAttackBoxHeight(float attackBoxHeight) {
        this.attackBoxHeight = attackBoxHeight;
    }
}
