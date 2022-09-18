package org.coloride.twoodee.World;

import box2dLight.PointLight;
import box2dLight.RayHandler;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.GL30;
import org.coloride.twoodee.Rendering.Camera;

public class BoxLighting {
    public static RayHandler rayHandler = new RayHandler(GameWorld.world);

    public static PointLight pointLight;
    public static int tileLightRaysQuality = 6; // 8 = LOW, 10 = MEDIUM, 12 = HIGH
    private static float ambient = 0;

    public static void create() {
        rayHandler.setCulling(true);
        rayHandler.setLightMapRendering(true);
        //rayHandler.renderOnly();
        //pointLight = new PointLight(rayHandler, 8, Color.WHITE,100,128,128);
    }

    public static void process() {
        if (WorldRenderer.lightingType == LightingType.FUTURE) {
            ambient = Camera.camera.position.y * 1 / (Chunk.chunkSize.y * MapInformation.mapSizes.get(MapInformation.getLoadedMap().getMapSize()).y);
            rayHandler.setAmbientLight(ambient);
        }
    }

    public static void update() {
        if (WorldRenderer.lightingType == LightingType.FUTURE) {
            rayHandler.setCombinedMatrix(Camera.camera);
            rayHandler.updateAndRender();
        }

    }
}
