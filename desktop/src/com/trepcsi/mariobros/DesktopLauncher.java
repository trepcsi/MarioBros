package com.trepcsi.mariobros;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class DesktopLauncher {
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = MarioBros.V_WIDTH * 3;
        config.height = MarioBros.V_HEIGHT * 3;
        new LwjglApplication(new MarioBros(), config);
    }
}
