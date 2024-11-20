package com.java.jeux.lwjgl3.level01;

import com.badlogic.gdx.math.Rectangle;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AttackManager {

    private final Set<Ennemies> attackedEnemies = new HashSet<>();
    private boolean playerHit = false;

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

    public void checkEnemyAttacks(Player player, List<Ennemies> enemies) {
        if (playerHit) return;

        for (Ennemies enemy : enemies) {
            if (enemy.isDead()) continue;


            if (enemy.getHitBox().overlaps(player.getHitBox())) {
                player.takeDamage(enemy.getAttackDamage());
                playerHit = true;
                break;
            }
        }
    }

    public void resetPlayerHit() {
        playerHit = false;
    }
}
