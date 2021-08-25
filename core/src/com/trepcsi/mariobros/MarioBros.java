package com.trepcsi.mariobros;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.trepcsi.mariobros.screens.PlayScreen;

public class MarioBros extends Game {

    public SpriteBatch batch; //only 1 batch allowed (memory) in the whole game!

    @Override
    public void create () {
        batch = new SpriteBatch();
        setScreen(new PlayScreen(this));
    }

    @Override
    public void render () {
        super.render();
    }

    @Override
    public void dispose () {
        batch.dispose();
    }
}
