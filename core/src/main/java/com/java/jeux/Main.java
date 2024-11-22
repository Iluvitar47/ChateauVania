package com.java.jeux;

import com.badlogic.gdx.Game;
import com.java.jeux.level01.Level01Screen;
import com.java.jeux.level01.Player;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {
    private Player player;
    @Override
    public void create() {
        player = new Player(64, 50, 10, 2);
        player.create();
        setScreen(new Level01Screen(player));
    }
}
