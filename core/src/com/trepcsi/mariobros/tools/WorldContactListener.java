package com.trepcsi.mariobros.tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.*;
import com.trepcsi.mariobros.MarioBros;
import com.trepcsi.mariobros.sprites.Mario;
import com.trepcsi.mariobros.sprites.enemies.Enemy;
import com.trepcsi.mariobros.sprites.items.Item;
import com.trepcsi.mariobros.sprites.tileobjects.InteractiveTileObject;

public class WorldContactListener implements ContactListener {

    @Override
    public void beginContact(Contact contact) {
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();

        int cDef = fixA.getFilterData().categoryBits | fixB.getFilterData().categoryBits;

        if (fixA.getUserData() == "head" || fixB.getUserData() == "head") {
            Fixture head = fixA.getUserData() == "head" ? fixA : fixB;
            Fixture object = head == fixA ? fixB : fixA;

            if (object.getUserData() != null && InteractiveTileObject.class.isAssignableFrom(object.getUserData().getClass())) {
                ((InteractiveTileObject) object.getUserData()).onHeadHit();
            }
        }

        switch (cDef) {
            case MarioBros.ENEMY_HEAD_BIT | MarioBros.MARIO_BIT:
                if (fixA.getFilterData().categoryBits == MarioBros.ENEMY_HEAD_BIT) {
                    ((Enemy) fixA.getUserData()).hitOnHead();
                    break;
                } else if (fixB.getFilterData().categoryBits == MarioBros.ENEMY_HEAD_BIT) {
                    ((Enemy) fixB.getUserData()).hitOnHead();
                    break;
                }
            case MarioBros.ENEMY_BIT | MarioBros.OBJECT_BIT:
                if (fixA.getFilterData().categoryBits == MarioBros.ENEMY_BIT) {
                    ((Enemy) fixA.getUserData()).reverseVelocity(true, false);
                    break;
                } else {
                    ((Enemy) fixB.getUserData()).reverseVelocity(true, false);
                    break;
                }
            case MarioBros.MARIO_BIT | MarioBros.ENEMY_BIT:
                Gdx.app.log("Mario", "DIED");
                break;
            case MarioBros.ENEMY_BIT:
                ((Enemy) fixA.getUserData()).reverseVelocity(true, false);
                ((Enemy) fixB.getUserData()).reverseVelocity(true, false);
            case MarioBros.ITEM_BIT | MarioBros.OBJECT_BIT:
                if (fixA.getFilterData().categoryBits == MarioBros.ITEM_BIT) {
                    ((Item) fixA.getUserData()).reverseVelocity(true, false);
                    break;
                } else {
                    ((Item) fixB.getUserData()).reverseVelocity(true, false);
                    break;
                }
            case MarioBros.ITEM_BIT | MarioBros.MARIO_BIT:
                if (fixA.getFilterData().categoryBits == MarioBros.ITEM_BIT) {
                    ((Item) fixA.getUserData()).use((Mario) fixB.getUserData());
                    break;
                } else {
                    ((Item) fixB.getUserData()).use((Mario) fixA.getUserData());
                    break;
                }
        }

    }

    @Override
    public void endContact(Contact contact) {
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
