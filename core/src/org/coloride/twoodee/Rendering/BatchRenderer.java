package org.coloride.twoodee.Rendering;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.coloride.twoodee.UI.UIHandler;
import org.coloride.twoodee.World.BoxLighting;
import org.coloride.twoodee.World.WorldRenderer;

public class BatchRenderer {
    public static SpriteBatch tilesBatch = new SpriteBatch();
    public static SpriteBatch uiBatch = new SpriteBatch();
    public static SpriteBatch debugUiBatch = new SpriteBatch();

    public static void process() {
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
        WorldRenderer.process();
        BoxLighting.process();

        UIHandler.process();
    }

    public static void draw() {
        WorldRenderer.draw();
        BoxLighting.update();

        // Ui batching
        UIHandler.draw();
    }
}
