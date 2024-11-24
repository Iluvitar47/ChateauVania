package com.java.jeux.level01.managers;

import com.badlogic.gdx.math.Rectangle;
import com.java.jeux.level01.character.Enemy;
import com.java.jeux.level01.character.Player;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AttackManager {

    private final Set<Enemy> attackedEnemies = new HashSet<>();
    private boolean playerHit = false;

    public void checkPlayerAttacks(Player player, List<Enemy> enemies) {
        if (!player.isAttacking()) {
            attackedEnemies.clear();
            return;
        }

        for (Rectangle attackBox : player.getAttackBoxes()) {
            for (Enemy enemy : enemies) {
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

    public void checkEnemyAttacks(Player player, List<Enemy> enemies) {
        if (playerHit) return;

        for (Enemy enemy : enemies) {
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
