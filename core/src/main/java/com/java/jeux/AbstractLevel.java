package com.java.jeux;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;

public abstract class AbstractLevel implements Screen {

    @Override
    public final void render(float deltaTime){
        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
            AbstractLevel that = this;
            ChateauVania.getInstance().setScreen(new SettingsScreen(() -> {
                ChateauVania.getInstance().setScreen(that);
                Gdx.input.setInputProcessor(null);
            }));
            return;
        }
        this.renderLevel(deltaTime);
    }

    protected abstract void renderLevel(float delta);
}
