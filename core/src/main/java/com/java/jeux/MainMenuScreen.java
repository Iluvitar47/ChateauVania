package com.java.jeux;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.loaders.SkinLoader;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class MainMenuScreen implements Screen {
    private final Stage stage;


    public MainMenuScreen(ChateauVania chateauVania) {
        final MainMenuScreen that = this;
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

//        table.setDebug(true); // This is optional, but enables debug lines for tables.

        TextButton startButton = new TextButton("START", ChateauVania.getUiSkin());
        startButton.addListener(new ClickListener(){
            @Override
            public void clicked (InputEvent event, float x, float y) {
                chateauVania.startGame();
                that.dispose();
            }
        });
        table.add(startButton).expand();

        table.row();

        TextButton optButton = new TextButton("SETTINGS", ChateauVania.getUiSkin());
        optButton.addListener(new ClickListener(){
            @Override
            public void clicked (InputEvent event, float x, float y) {
                chateauVania.setScreen(new SettingsScreen(() -> {
                    chateauVania.setScreen(that);
                    Gdx.input.setInputProcessor(stage);
                }));
            }
        });
        table.add(optButton).expand();

        table.row();

        TextButton quitButton = new TextButton("QUIT", ChateauVania.getUiSkin());
        quitButton.addListener(new ClickListener(){
            @Override
            public void clicked (InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });
        table.add(quitButton).expand();
    }

    @Override
    public void show() {

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

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
