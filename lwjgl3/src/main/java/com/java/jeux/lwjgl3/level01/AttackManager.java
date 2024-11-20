package com.java.jeux.lwjgl3.level01;

import com.badlogic.gdx.math.Rectangle;
import java.util.List;

public class AttackManager {

    public void checkPlayerAttacks(Player player, List<Ennemies> enemies) {
        if (!player.isAttacking()) return;

        for (Rectangle attackBox : player.getAttackBoxes()) {
            for (Ennemies enemy : enemies) {
                if (!enemy.isDead() && attackBox.overlaps(enemy.getHitBox())) {
                    enemy.die();
                    break;
                }
            }
        }
    }
}
