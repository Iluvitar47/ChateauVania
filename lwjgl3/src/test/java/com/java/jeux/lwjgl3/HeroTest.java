package com.java.jeux.lwjgl3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.java.jeux.lwjgl3.level01.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class HeroTest {

    private Player Player;
    private Player target;

    @BeforeEach
    public void setUp() {
        Player = new Player("Guerrier", 100, 20, 3, "Coup Fatal", 100, 3);
        target = new Player("Méchant", 80, 10, 2, "Contre-Attaque", 80, 2);
    }

    @Test
    public void testPlayerAttributes() {
        assertEquals("Guerrier", Player.getName());
        assertEquals(100, Player.getHealth());
        assertEquals(20, Player.getDamage());
        assertEquals(3, Player.getLives());
        assertEquals("Coup Fatal", Player.getSpecialAttack());
        assertEquals(100, Player.getDefaultHealth());
        assertEquals(3, Player.getDefaultLives());
    }

    @Test
    public void testAttack() {
        Player.attack(target);
        assertEquals(60, target.getHealth(), "La cible devrait avoir 60 points de vie après l'attaque.");
    }

    @Test
    public void testSpecialAttack() {
        Player.useSpecialAttack();
        assertTrue(Player.getSpecialAttack().contains("Coup Fatal"), "L'attaque spéciale devrait être 'Coup Fatal'.");
    }

    @Test
    public void testRespawn() {
        Player.takeDamage(100); // Réduit les points de vie à zéro
        Player.respawn();
        assertEquals(100, Player.getHealth(), "Le héros devrait avoir 100 points de vie après respawn.");
        assertEquals(3, Player.getLives(), "Le héros devrait avoir 3 vies après respawn.");
    }
}
