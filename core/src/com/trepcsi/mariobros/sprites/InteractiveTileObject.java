package com.trepcsi.mariobros.sprites;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;
import com.trepcsi.mariobros.MarioBros;
import com.trepcsi.mariobros.scenes.Hud;

public abstract class InteractiveTileObject {
    protected World world;
    protected TiledMap map;
    protected TiledMapTile tile;
    protected Rectangle bounds;
    protected Body body;

    protected Fixture fixture;

    protected Hud hud;

    public InteractiveTileObject(World world, TiledMap map, Rectangle bounds, SpriteBatch sb) {
        this.world = world;
        this.map = map;
        this.bounds = bounds;
        this.hud = Hud.getInstance(sb);

        BodyDef bdef = new BodyDef();
        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();

        bdef.type = BodyDef.BodyType.StaticBody;
        bdef.position.set(bounds.getX() / MarioBros.PPM + bounds.getWidth() / 2 / MarioBros.PPM, bounds.getY() / MarioBros.PPM + bounds.getHeight() / 2 / MarioBros.PPM);

        body = world.createBody(bdef);

        shape.setAsBox(bounds.getWidth() / 2 / MarioBros.PPM, bounds.getHeight() / 2 / MarioBros.PPM);
        fdef.shape = shape;
        fixture = body.createFixture(fdef);
    }

    public abstract void onHeadHit();

    public void setCategoryFilter(short filterBit) {
        Filter filter = new Filter();
        filter.categoryBits = filterBit;
        fixture.setFilterData(filter);
    }

    public TiledMapTileLayer.Cell getCell() {
        TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(1); //graphic layer
        return layer.getCell((int) (body.getPosition().x * MarioBros.PPM / 16), (int) (body.getPosition().y * MarioBros.PPM / 16));  //scale up, 16 - tile size in bits
    }
}
