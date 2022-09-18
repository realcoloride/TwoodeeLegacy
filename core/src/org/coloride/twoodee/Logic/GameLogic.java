package org.coloride.twoodee.Logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import org.coloride.twoodee.Content.UI.DebugUI;
import org.coloride.twoodee.Rendering.BatchRenderer;
import org.coloride.twoodee.Rendering.Camera;
import org.coloride.twoodee.Utilities.TimeUtilities;
import org.coloride.twoodee.World.*;

import java.util.HashMap;

public class GameLogic {
    public static void onLoad() {
        MapInformation.loadedMap = new MapData(MapInformation.MapSize.TINY, new HashMap<Vector2, Chunk>());

        // todo: seed or other parameters
        WorldGenerator.generateWorld();
        TileLighting.bakeLighting();
        Runtime.getRuntime().gc();

        Camera.create();
        BoxLighting.create();
    }
    public static void process() {
        BoxLighting.process();
        BatchRenderer.process();
        DebugUI.process();
    }
    public static void update() {
        BatchRenderer.draw();
        DebugUI.draw();
    }
    public static void dispose() {

    }
}
