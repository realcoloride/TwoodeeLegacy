package org.coloride.twoodee.World;

import box2dLight.Light;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.collision.BoundingBox;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.ScreenUtils;
import jdk.nashorn.internal.runtime.Debug;
import org.coloride.twoodee.Rendering.BatchRenderer;
import org.coloride.twoodee.Rendering.Camera;
import org.coloride.twoodee.UI.DebugUI;
import org.coloride.twoodee.Utilities.ColorUtilities;
import org.coloride.twoodee.Utilities.MathUtilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import static org.coloride.twoodee.Rendering.BatchRenderer.tilesBatch;
import static org.coloride.twoodee.Rendering.BatchRenderer.uiBatch;

public class WorldRenderer extends Thread {
    public static HashMap<Integer, Texture> tilesTextures = new HashMap<>();
    public static ArrayList<WorldTile> renderedTilesBuffer = new ArrayList();
    private static Thread lightProcessingThread = new TileLighting();
    private static Thread tilesProcessingThread = new WorldRenderer();

    public static ShapeRenderer shapeRenderer = new ShapeRenderer();
    public static LightingType lightingType = LightingType.MODERN_LIGHTING;  //todo: fully operational (needs MODERN)

    public static boolean needsTileProcessing = true;

    public void run() {
        // Chunk rendering
        Vector2 chunkPosition;
        BoundingBox chunkBounds;
        Vector2 tilePosition;
        Vector2 tileSpacePosition = new Vector2();
        BoundingBox blockBounds;
        boolean blockVisible;
        boolean chunkVisible;
        WorldTile tile;

        // todo: put rendered chunks buffering in process() and render in draw()

        MapInformation.MapSize mapSize = MapInformation.getLoadedMap().mapSize;

        // todo: get all chunks in the viewport idea instead of iterating all chunks

        for (Chunk chunk : MapInformation.getLoadedMap().getChunks().values()) {
            chunkPosition = chunk.getChunkPosition();

            chunkBounds = MathUtilities.Conversion.boundingBox2dTo3d(
                    chunkPosition.x * Chunk.chunkSize.x,
                    chunkPosition.y * Chunk.chunkSize.y,
                    chunkPosition.x * Chunk.chunkSize.x + Chunk.chunkSize.x,
                    chunkPosition.y * Chunk.chunkSize.y + Chunk.chunkSize.y
            );

            chunkVisible = Camera.camera.frustum.boundsInFrustum(chunkBounds);

            if (chunkVisible) {
                TileLighting.chunkRefreshBuffer.add(chunk);
                for (int x = 0; x < Chunk.chunkSize.x / WorldTile.tileSize.x; x++) {
                    for (int y = 0; y < Chunk.chunkSize.y / WorldTile.tileSize.y; y++) {
                        tilePosition = new Vector2(x, y);
                        tileSpacePosition.set(chunk.getChunkPosition().x * Chunk.chunkSize.x + x * WorldTile.tileSize.x, chunk.getChunkPosition().y * Chunk.chunkSize.y + y * WorldTile.tileSize.y);

                        blockBounds = MathUtilities.Conversion.boundingBox2dTo3d(
                                tileSpacePosition.x,
                                tileSpacePosition.y,
                                tileSpacePosition.x + WorldTile.tileSize.x,
                                tileSpacePosition.y + WorldTile.tileSize.y
                        );

                        blockVisible = Camera.camera.frustum.boundsInFrustum(blockBounds);
                        tile = chunk.getTileFromChunk(tilePosition);
                        tile.getTileLight().setActive(false);

                        if (blockVisible) {
                            AutoTiling.processAutoTile(tile);

                            if (TileType.getTileTypeById(tile.getTileId()).getLightInfluence() > 0) {
                                tile.getTileLight().setColor(TileType.getTileTypeById(tile.getTileId()).getLightColor());
                                tile.getTileLight().setDistance(TileType.getTileTypeById(tile.getTileId()).getLightInfluence()*4);
                                tile.getTileLight().setPosition(tileSpacePosition.x+WorldTile.tileSize.x/2,tileSpacePosition.y+WorldTile.tileSize.y/2);
                                tile.getTileLight().setActive(true);
                            }

                            if (tile.getTileId() != 0) { // AIR
                                renderedTilesBuffer.add(tile);
                            }
                        }
                    }
                }
            }
        }

        needsTileProcessing = false;
    }
    public static void loadTilesTextures() {
        for (TileType tileType : TileType.values()) {
            int tileId = tileType.getTileId();
            String texturePath = "img/tiles/tile_" + tileId + ".png";

            tilesTextures.put(tileId, new Texture(Gdx.files.internal(texturePath)));
        }
    }

    // Tile textures
    public static Texture getTileTexture(int tileId) { return tilesTextures.get(tileId); }
    public static TextureRegion[][] getRegionFromTexture(Texture texture) { return TextureRegion.split(texture, (int)WorldTile.tileSize.x, (int)WorldTile.tileSize.y); }

    public static void drawSky() {
        Color skyColor = ColorUtilities.Conversion.RGBtoraw(145,222,255,255);
        ScreenUtils.clear(skyColor);
    }

    public static Chunk getChunkFromSpacePosition(Vector2 spacePosition) {
        for (Chunk chunk : MapInformation.getLoadedMap().getChunks().values()) {
            Vector2 chunkPosition = chunk.getChunkPosition();

            BoundingBox chunkBounds = MathUtilities.Conversion.boundingBox2dTo3d(
                    chunkPosition.x * Chunk.chunkSize.x,
                    chunkPosition.y * Chunk.chunkSize.y,
                    chunkPosition.x * Chunk.chunkSize.x + Chunk.chunkSize.x,
                    chunkPosition.y * Chunk.chunkSize.y + Chunk.chunkSize.y
            );

            boolean isInChunk = (chunkBounds.contains(MathUtilities.Conversion.vector2dTo3d(spacePosition)));
            if (isInChunk) { // ignore if null
                return chunk;
            }
        }
        return null;
    }
    public static WorldTile getTileFromChunkSpacePosition(Chunk chunk, Vector2 spacePosition) {
        if (chunk != null) {
            // todo: get only in viewport area
            for (int x = 0; x < Chunk.chunkSize.x / WorldTile.tileSize.x; x++) {
                for (int y = 0; y < Chunk.chunkSize.y / WorldTile.tileSize.y; y++) {
                    BoundingBox tileBounds = MathUtilities.Conversion.boundingBox2dTo3d(
                            chunk.getChunkPosition().x * Chunk.chunkSize.x + x * WorldTile.tileSize.x,
                            chunk.getChunkPosition().y * Chunk.chunkSize.y + y * WorldTile.tileSize.y,
                            chunk.getChunkPosition().x * Chunk.chunkSize.x + x * WorldTile.tileSize.x + WorldTile.tileSize.x,
                            chunk.getChunkPosition().y * Chunk.chunkSize.y + y * WorldTile.tileSize.y + WorldTile.tileSize.y
                    );

                    boolean isInChunk = (tileBounds.contains(MathUtilities.Conversion.vector2dTo3d(spacePosition)));
                    if (isInChunk) {
                        return chunk.getTileFromChunk(new Vector2(x,y));
                    }
                }
            }
        }
        return null;
    }
    public static WorldTile getTileFromSpacePosition(Vector2 spacePosition) {
        Chunk chunk = getChunkFromSpacePosition(spacePosition);
        return getTileFromChunkSpacePosition(chunk, spacePosition);
    }
    private static Color getTileColorFromLight(WorldTile tile) {

        if (WorldRenderer.lightingType == LightingType.SPIKE) {
            Color color = new Color();

            Integer lightIntensity = tile.getlightIntensity();

            color.r = (float) lightIntensity * 1 / 16;
            color.g = (float) lightIntensity * 1 / 16;
            color.b = (float) lightIntensity * 1 / 16;
            color.a = 1;

            return color;
        } else {
            return new Color(1,1,1,1);
        }
    }

    public static void process() {
        if (WorldRenderer.lightingType == LightingType.SPIKE) {
            if (TileLighting.chunkRefreshBuffer.size() > 0 && !TileLighting.rendering) { lightProcessingThread.run(); }
        }
        if (needsTileProcessing) { tilesProcessingThread.run(); }
    }
    public static void draw() {

        // Draw from Camera
        tilesBatch.setProjectionMatrix(Camera.camera.combined);
        Camera.draw();

        // Draw terrain
        tilesBatch.begin();

        if (renderedTilesBuffer.size() > 0) {
            for (int i = 0; i < renderedTilesBuffer.size(); i++) {
                WorldTile tile = renderedTilesBuffer.get(i);
                Chunk chunk = tile.getChunk();

                //tile.getTileLight().setDistance(); todo

                Sprite sprite = new Sprite(AutoTiling.getTextureRegionFromWorldTile(getRegionFromTexture(getTileTexture(tile.getTileId())), tile));
                sprite.setPosition(chunk.getChunkPosition().x * Chunk.chunkSize.x + tile.getTilePosition().x * WorldTile.tileSize.x, chunk.getChunkPosition().y * Chunk.chunkSize.y + tile.getTilePosition().y * WorldTile.tileSize.y);
                sprite.setColor(getTileColorFromLight(tile));
                sprite.draw(tilesBatch);
            }
            renderedTilesBuffer.clear();
        }
        needsTileProcessing = true;

        tilesBatch.end();
    }
}
