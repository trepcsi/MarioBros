package com.trepcsi.mariobros.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.trepcsi.mariobros.MarioBros;

public class Coin extends InteractiveTileObject {

    public Coin(World world, TiledMap map, Rectangle bounds, SpriteBatch sb) {

        super(world, map, bounds, sb);
        fixture.setUserData(this);
        setCategoryFilter(MarioBros.COIN_BIT);
    }

    @Override
    public void onHeadHit() {
        hud.addScore(200);
    }
}
