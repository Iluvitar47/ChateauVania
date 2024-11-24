package com.java.jeux;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;

/**
 * The `AbstractLevel` class implements the `Screen` interface and serves as a base class for all game levels.
 */
public abstract class AbstractLevel implements Screen {

    /**
     * Renders the screen. If the ESCAPE key is pressed, it switches to the settings screen.
     *
     * @param deltaTime the time in seconds since the last render
     */
    @Override
    public final void render(float deltaTime) {
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            AbstractLevel that = this;
            ChateauVania.getInstance().setScreen(new SettingsScreen(() -> {
                ChateauVania.getInstance().setScreen(that);
                Gdx.input.setInputProcessor(null);
            }));
            return;
        }
        this.renderLevel(deltaTime);
    }

    /**
     * Renders the level. This method should be implemented by subclasses to define the specific rendering logic.
     *
     * @param delta the time in seconds since the last render
     */
    protected abstract void renderLevel(float delta);
}
