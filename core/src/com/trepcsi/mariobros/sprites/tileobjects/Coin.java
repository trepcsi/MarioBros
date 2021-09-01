package com.trepcsi.mariobros.sprites.tileobjects;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.badlogic.gdx.math.Rectangle;
import com.trepcsi.mariobros.MarioBros;
import com.trepcsi.mariobros.screens.PlayScreen;

public class Coin extends InteractiveTileObject {

    private static TiledMapTileSet tileSet;
    private final int BLANK_COIN = 27 + 1; //tiled +1 because of indexing
    private boolean active = true;

    public Coin(PlayScreen screen, Rectangle bounds) {

        super(screen, bounds);
        tileSet = map.getTileSets().getTileSet("tileset_gutter");
        fixture.setUserData(this);
        setCategoryFilter(MarioBros.COIN_BIT);
    }

    @Override
    public void onHeadHit() {
        if (!active) {
            manager.get("audio/sounds/bump.wav", Sound.class).play(.5f);
        } else {
            hud.addScore(200);
            getCell().setTile(tileSet.getTile(BLANK_COIN));
            manager.get("audio/sounds/coin.wav", Sound.class).play(.5f);
            active = false;
        }

    }
}