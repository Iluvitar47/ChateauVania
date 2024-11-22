package com.java.jeux.lwjgl3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.java.jeux.lwjgl3.level01.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PlayerTest {
    private Player player;

    @BeforeEach
    public void setUp() {
        player = new Player(36, 64, 100, 20);
        player.create();
    }

    @Test
    public void testInitialLives() {
        assertEquals(3, player.getLives(), "Le joueur devrait commencer avec 3 vies.");
    }

    @Test
    public void testRespawn() {
        player.takeDamage(100);
        player.respawn();
        assertEquals(100, player.getCurrentHealth(), "Le joueur devrait récupérer toute sa santé après respawn.");
        assertEquals(2, player.getLives(), "Le joueur devrait perdre une vie après respawn.");
    }

    @Test
    public void testInitialPosition() {
        assertEquals(36, player.getPosition().x, "La position X initiale du joueur devrait être 36.");
        assertEquals(64, player.getPosition().y, "La position Y initiale du joueur devrait être 64.");
    }
}
