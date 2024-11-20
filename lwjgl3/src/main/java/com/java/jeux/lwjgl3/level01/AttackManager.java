package com.java.jeux.lwjgl3.level01;

import com.badlogic.gdx.math.Rectangle;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AttackManager {

    private final Set<Ennemies> attackedEnemies = new HashSet<>();

    public void checkPlayerAttacks(Player player, List<Ennemies> enemies) {
        if (!player.isAttacking()) {

            attackedEnemies.clear();
            return;
        }


        for (Rectangle attackBox : player.getAttackBoxes()) {
            for (Ennemies enemy : enemies) {

                if (enemy.isDead() || attackedEnemies.contains(enemy)) {
                    continue;
                }


                if (attackBox.overlaps(enemy.getHitBox())) {
                    enemy.takeDamage(player.getAttackDamage());
                    attackedEnemies.add(enemy);
                }
            }
        }
    }
}
