package com.java.jeux;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import java.util.concurrent.Callable;

public class SettingsScreen implements Screen {
    private final Stage stage;


    public SettingsScreen(Runnable restoreAction) {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        HorizontalGroup volAndTextGroup = new HorizontalGroup();
        Slider volumeSlider = new Slider(0f, 1f, 0.01f, false, ChateauVania.getUiSkin());
        Label volumeLabel = new Label("100%", ChateauVania.getUiSkin());
        volumeSlider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Slider volSlider = (Slider) actor;
                float vol = volSlider.getValue();
                GlobalSettings.setGlobalVolume(vol);
                volumeLabel.setText(Math.round(vol * 100) + "%");
            }
        });
        volumeSlider.setValue(1f);
        volAndTextGroup.addActor(volumeSlider);
        volAndTextGroup.addActor(volumeLabel);
        table.add(volAndTextGroup).expandY();

        table.row();

        CheckBox showDebugHitboxesCheckbox = new CheckBox("Show debug hitboxes", ChateauVania.getUiSkin());
        showDebugHitboxesCheckbox.setChecked(GlobalSettings.getShowDebugHitboxes());
        showDebugHitboxesCheckbox.addListener(new ClickListener(){
            @Override
            public void clicked (InputEvent event, float x, float y) {
                GlobalSettings.toggleShowDebugHitboxes();
            }
        });
        table.add(showDebugHitboxesCheckbox).expandY();
        table.row();

        TextButton startButton = new TextButton("BACK", ChateauVania.getUiSkin());
        startButton.addListener(new ClickListener(){
            @Override
            public void clicked (InputEvent event, float x, float y) {
                restoreAction.run();
            }
        });
        table.add(startButton).expand();
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
