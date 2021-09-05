package com.trepcsi.mariobros.sprites.tileobjects;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.math.Rectangle;
import com.trepcsi.mariobros.MarioBros;
import com.trepcsi.mariobros.screens.PlayScreen;

public class Brick extends InteractiveTileObject {

    public Brick(PlayScreen screen, MapObject object) {

        super(screen, object);
        fixture.setUserData(this);
        setCategoryFilter(MarioBros.BRICK_BIT);
    }

    @Override
    public void onHeadHit() {
        setCategoryFilter(MarioBros.DESTROYED_BIT);
        getCell().setTile(null);
        hud.addScore(100);
        manager.get("audio/sounds/breakblock.wav", Sound.class).play(.5f);
    }
}
