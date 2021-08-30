package com.trepcsi.mariobros.sprites;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.trepcsi.mariobros.MarioBros;

public class Coin extends InteractiveTileObject {

    private static TiledMapTileSet tileSet;
    private final int BLANK_COIN = 27 + 1; //tiled +1 because of indexing
    private boolean active = true;

    public Coin(World world, TiledMap map, Rectangle bounds, SpriteBatch sb) {

        super(world, map, bounds, sb);
        tileSet = map.getTileSets().getTileSet("tileset_gutter");
        fixture.setUserData(this);
        setCategoryFilter(MarioBros.COIN_BIT);
    }

    @Override
    public void onHeadHit() {
        if (active) {
            hud.addScore(200);
            active = false;
        }
        getCell().setTile(tileSet.getTile(BLANK_COIN));
    }
}
