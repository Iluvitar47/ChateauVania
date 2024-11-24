package com.java.jeux;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.java.jeux.level01.Level01Screen;
import com.java.jeux.level01.character.Player;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class ChateauVania extends Game {
    private static ChateauVania INSTANCE;
    private static Skin uiSkin;
    private Player player;

    public static Skin getUiSkin(){
        return uiSkin;
    }

    @Override
    public void create() {
        INSTANCE = this;
        uiSkin = new Skin(Gdx.files.internal("UI/uiskin.json"));
        setScreen(new MainMenuScreen(this));

//        this.settingsScreen = ;
    }

    public void startGame(){
        player = new Player(10, 64, 10, 2);
        player.create();
        setScreen(new Level01Screen(player));
    }

    public static ChateauVania getInstance(){
        return INSTANCE;
    }
}
