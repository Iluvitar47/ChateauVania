package com.java.jeux;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * The `MainMenuScreen` class implements the `Screen` interface and represents the main menu screen of the game.
 */
public class MainMenuScreen implements Screen {
    private final Stage stage;
    private final ChateauVania game;

    /**
     * Constructs a new `MainMenuScreen` with the specified game instance.
     *
     * @param game the game instance
     */
    public MainMenuScreen(ChateauVania game) {
        this.game = game;
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        Label titleLabel = new Label("Main Menu", ChateauVania.getUiSkin());
        table.add(titleLabel).expandY();
        table.row();

        TextButton startButton = new TextButton("START", ChateauVania.getUiSkin());
        startButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.startGame();
            }
        });
        table.add(startButton).expandY();
        table.row();

        TextButton settingsButton = new TextButton("SETTINGS", ChateauVania.getUiSkin());
        settingsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new SettingsScreen(() -> game.setScreen(MainMenuScreen.this)));
            }
        });
        table.add(settingsButton).expandY();
        table.row();

        TextButton exitButton = new TextButton("EXIT", ChateauVania.getUiSkin());
        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });
        table.add(exitButton).expandY();
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
