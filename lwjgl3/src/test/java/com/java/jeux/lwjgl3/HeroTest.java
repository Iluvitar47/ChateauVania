package com.java.jeux.lwjgl3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class HeroTest {

    private Hero hero;
    private Hero target;

    @BeforeEach
    public void setUp() {
        hero = new Hero("Guerrier", 100, 20, 3, "Coup Fatal", 100, 3);
        target = new Hero("Méchant", 80, 10, 2, "Contre-Attaque", 80, 2);
    }

    @Test
    public void testHeroAttributes() {
        assertEquals("Guerrier", hero.getName());
        assertEquals(100, hero.getHealth());
        assertEquals(20, hero.getDamage());
        assertEquals(3, hero.getLives());
        assertEquals("Coup Fatal", hero.getSpecialAttack());
        assertEquals(100, hero.getDefaultHealth());
        assertEquals(3, hero.getDefaultLives());
    }

    @Test
    public void testAttack() {
        hero.attack(target);
        assertEquals(60, target.getHealth(), "La cible devrait avoir 60 points de vie après l'attaque.");
    }

    @Test
    public void testSpecialAttack() {
        hero.useSpecialAttack();
        assertTrue(hero.getSpecialAttack().contains("Coup Fatal"), "L'attaque spéciale devrait être 'Coup Fatal'.");
    }

    @Test
    public void testRespawn() {
        hero.takeDamage(100); // Réduit les points de vie à zéro
        hero.respawn();
        assertEquals(100, hero.getHealth(), "Le héros devrait avoir 100 points de vie après respawn.");
        assertEquals(3, hero.getLives(), "Le héros devrait avoir 3 vies après respawn.");
    }
}
