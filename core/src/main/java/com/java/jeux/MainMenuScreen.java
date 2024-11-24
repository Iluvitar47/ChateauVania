package com.java.jeux;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * The `MainMenuScreen` class implements the `Screen` interface and represents the main menu screen of the game.
 */
public class MainMenuScreen implements Screen {
    private final Stage stage;
    private final Runnable startGameAction;
    private final Runnable settingsAction;

    /**
     * Constructs a new `MainMenuScreen` with the specified actions for the buttons.
     *
     * @param startGameAction the action to be performed when the start game button is clicked
     * @param settingsAction the action to be performed when the settings button is clicked
     */
    public MainMenuScreen(Runnable startGameAction, Runnable settingsAction) {
        this.startGameAction = startGameAction;
        this.settingsAction = settingsAction;
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        TextButton startButton = new TextButton("Start Game", ChateauVania.getUiSkin());
        startButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                startGameAction.run();
            }
        });
        table.add(startButton).expand().row();

        TextButton settingsButton = new TextButton("Settings", ChateauVania.getUiSkin());
        settingsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                settingsAction.run();
            }
        });
        table.add(settingsButton).expand().row();

        TextButton exitButton = new TextButton("Exit", ChateauVania.getUiSkin());
        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });
        table.add(exitButton).expand();
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
