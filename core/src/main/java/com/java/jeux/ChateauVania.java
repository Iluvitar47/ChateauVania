package com.java.jeux;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.java.jeux.level01.Level01Screen;
import com.java.jeux.level01.character.Player;

/**
 * The `ChateauVania` class extends the `Game` class and serves as the main entry point for the game.
 * It manages the different screens and the global game state.
 */
public class ChateauVania extends Game {
    private static ChateauVania INSTANCE;
    private static Skin uiSkin;
    private Player player;

    /**
     * Gets the UI skin used for the game's user interface.
     *
     * @return the UI skin
     */
    public static Skin getUiSkin() {
        return uiSkin;
    }

    @Override
    public void create() {
        INSTANCE = this;
        uiSkin = new Skin(Gdx.files.internal("UI/uiskin.json"));
        setScreen(new MainMenuScreen(this));
    }

    /**
     * Starts the game by creating a new player and setting the screen to the first level.
     */
    public void startGame() {
        player = new Player(10, 64, 10, 2);
        player.create();
        setScreen(new Level01Screen(player));
    }

    /**
     * Gets the singleton instance of the `ChateauVania` game.
     *
     * @return the singleton instance
     */
    public static ChateauVania getInstance() {
        return INSTANCE;
    }
}
