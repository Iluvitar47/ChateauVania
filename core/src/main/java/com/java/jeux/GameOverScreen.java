package com.java.jeux;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

/**
 * The `GameOverScreen` class implements the `Screen` interface and represents the game over screen of the game.
 */
public class GameOverScreen implements Screen {
    private final Stage stage;

    /**
     * Constructs a new `GameOverScreen`.
     */
    public GameOverScreen() {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        Label gameOverText = new Label("Game Over! Put a piece in the machine to start again (restart the game)", ChateauVania.getUiSkin());
        table.add(gameOverText).expand();
    }

    /**
     * Called when this screen becomes the current screen for a {@link com.badlogic.gdx.Game}.
     */
    @Override
    public void show() {
        // Method implementation
    }

    /**
     * Called when the screen should render itself.
     *
     * @param delta The time in seconds since the last render.
     */
    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();
    }

    /**
     * Called when the screen is resized.
     *
     * @param width The new width of the screen.
     * @param height The new height of the screen.
     */
    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    /**
     * Called when the game is paused.
     */
    @Override
    public void pause() {
        // Method implementation
    }

    /**
     * Called when the game is resumed from a paused state.
     */
    @Override
    public void resume() {
        // Method implementation
    }

    /**
     * Called when this screen is no longer the current screen for a {@link com.badlogic.gdx.Game}.
     */
    @Override
    public void hide() {
        // Method implementation
    }

    /**
     * Called when this screen should release all resources.
     */
    @Override
    public void dispose() {
        stage.dispose();
    }
}
