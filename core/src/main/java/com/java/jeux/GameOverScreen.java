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

    @Override
    public void show() {
        // Method implementation
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {
        // Method implementation
    }

    @Override
    public void resume() {
        // Method implementation
    }

    @Override
    public void hide() {
        // Method implementation
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
