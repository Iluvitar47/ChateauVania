package com.java.jeux;

import com.badlogic.gdx.Game;
import com.java.jeux.level01.Level01Screen;
import com.java.jeux.level01.Player;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class ChateauVania extends Game {
    private Player player;

    @Override
    public void create() {
        setScreen(new MainMenuScreen(this));
    }

    public void startGame(){
        player = new Player(10, 64, 10, 2);
        player.create();
        setScreen(new Level01Screen(player));
    }
}
