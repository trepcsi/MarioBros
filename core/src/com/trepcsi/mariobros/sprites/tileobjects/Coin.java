package com.trepcsi.mariobros.sprites.tileobjects;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.trepcsi.mariobros.MarioBros;
import com.trepcsi.mariobros.screens.PlayScreen;
import com.trepcsi.mariobros.sprites.items.ItemDef;
import com.trepcsi.mariobros.sprites.items.Mushroom;

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
            screen.spawnItem(new ItemDef(new Vector2(body.getPosition().x, body.getPosition().y + 16 / MarioBros.PPM), Mushroom.class));
        }

    }
}
