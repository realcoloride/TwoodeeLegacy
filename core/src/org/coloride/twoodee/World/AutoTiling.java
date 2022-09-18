package org.coloride.twoodee.World;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.collision.BoundingBox;
import com.badlogic.gdx.physics.box2d.World;
import org.coloride.twoodee.Rendering.Camera;
import org.coloride.twoodee.Utilities.MathUtilities;

import java.util.HashMap;

import static org.coloride.twoodee.Rendering.BatchRenderer.tilesBatch;
import static org.coloride.twoodee.World.WorldRenderer.*;

public class AutoTiling extends Thread {
    public static int northOrientation = 1;
    public static int eastOrientation = 2;
    public static int southOrientation = 4;
    public static int westOrientation = 8;

    public void run() {
        try {
            // Chunk rendering
            /*Vector2 chunkPosition;
            BoundingBox chunkBounds;
            Vector2 tilePosition;
            Vector2 tileSpacePosition = new Vector2();
            BoundingBox blockBounds;
            boolean blockVisible;
            boolean chunkVisible;
            WorldTile tile;

            Vector2 normalizedMapSize = MapInformation.mapSizes.get(MapInformation.getLoadedMap().getMapSize());

            float cameraPositionX = (Camera.camera.position.x - Camera.camera.viewportWidth/2);
            float cameraPositionY = (Camera.camera.position.y - Camera.camera.viewportHeight/2);

            int minChunkX = MathUtils.floor(cameraPositionX/Chunk.chunkSize.x); // drawing anchor point (x)
            int minChunkY = MathUtils.floor(cameraPositionY/Chunk.chunkSize.y); // drawing anchor point (y)
            int maxChunkX = MathUtils.ceil(Camera.camera.viewportWidth/Chunk.chunkSize.x + 0.5f); // chunk render amount (x)
            int maxChunkY = MathUtils.ceil(Camera.camera.viewportHeight/Chunk.chunkSize.y + 0.5f); // chunk render amount (y)

            for (int cx = minChunkX; cx < MathUtils.clamp(minChunkX + maxChunkX, 0, normalizedMapSize.x); cx++) {
                for (int cy = minChunkY; cy < MathUtils.clamp(minChunkY + maxChunkY, 0, normalizedMapSize.y); cy++) {

                    // if its below 0, put it back to 0 (avoid crash)
                    cx = cx < 0 ? 0 : cx;
                    cy = cy < 0 ? 0 : cy;

                    chunkPosition = chunk.getChunkPosition();

                    chunkBounds = MathUtilities.Conversion.boundingBox2dTo3d(
                            chunkPosition.x * Chunk.chunkSize.x,
                            chunkPosition.y * Chunk.chunkSize.y,
                            chunkPosition.x * Chunk.chunkSize.x + Chunk.chunkSize.x,
                            chunkPosition.y * Chunk.chunkSize.y + Chunk.chunkSize.y
                    );

                    chunkVisible = Camera.camera.frustum.boundsInFrustum(chunkBounds);

                    if (chunkVisible) {
                        for (int x = 0; x < Chunk.chunkSize.x / WorldTile.tileSize.x; x++) {
                            for (int y = 0; y < Chunk.chunkSize.y / WorldTile.tileSize.y; y++) {
                                tilePosition = new Vector2(x,y);
                                tileSpacePosition.set(chunk.getChunkPosition().x * Chunk.chunkSize.x + x * WorldTile.tileSize.x, chunk.getChunkPosition().y * Chunk.chunkSize.y + y * WorldTile.tileSize.y);

                                blockBounds = MathUtilities.Conversion.boundingBox2dTo3d(
                                        tileSpacePosition.x,
                                        tileSpacePosition.y,
                                        tileSpacePosition.x + WorldTile.tileSize.x,
                                        tileSpacePosition.y + WorldTile.tileSize.y
                                );

                                blockVisible = Camera.camera.frustum.boundsInFrustum(blockBounds);

                                if (blockVisible) {
                                    tile = chunk.getTileFromChunk(tilePosition);
                                    AutoTiling.processAutoTile(tile);
                                }
                            }
                        }
                    }
                }
            }*/

            // Chunk rendering
            if (renderedTilesBuffer.size() > 0) {
                for (int i = 0; i < renderedTilesBuffer.size(); i++) {
                    WorldTile tile = renderedTilesBuffer.get(i);
                    processAutoTile(tile);
                }
            }
        } catch (Exception e) {

        }
    }

    public static TextureRegion getTextureRegionFromCoordinates(TextureRegion[][] textureRegion, int x, int y) {
        return textureRegion[x][y];
    }
    public static TextureRegion getTextureRegionFromCoordinates(TextureRegion[][] textureRegion, Vector2 coordinates) {
        return textureRegion[(int)coordinates.x][(int)coordinates.y];
    }
    public static TextureRegion getTextureRegionFromWorldTile(TextureRegion[][] textureRegion, WorldTile tile) {
        return getTextureRegionFromCoordinates(
                textureRegion,
                tile.hasTileFront() ? 0 : 1, tile.getTileOrientation()
        );
    }

    public static boolean areTilesTheSameId(WorldTile tile, NeighbourTile neighbourTile) {
        if (neighbourTile.getTile() != null) {
            return tile.getTileId() == neighbourTile.getTile().getTileId();
        }
        return false;
    }

    public static void processAutoTile(WorldTile tile) {
        if (tile.needsTileOrientationRefreshing()) {
            Vector2 direction = new Vector2(0,1);

            direction.set(0,1);
            NeighbourTile neighbourNorthTile = WorldTile.getBlockNeighbourTile(tile, direction);
            direction.set(-1,0);
            NeighbourTile neighbourEastTile  = WorldTile.getBlockNeighbourTile(tile, direction);
            direction.set(0,-1);
            NeighbourTile neighbourSouthTile = WorldTile.getBlockNeighbourTile(tile, direction);
            direction.set(1,0);
            NeighbourTile neighbourWestTile  = WorldTile.getBlockNeighbourTile(tile, direction);

            int orientation = 0;

            orientation += areTilesTheSameId(tile, neighbourNorthTile) ? northOrientation : 0;
            orientation += areTilesTheSameId(tile, neighbourEastTile) ? eastOrientation : 0;
            orientation += areTilesTheSameId(tile, neighbourSouthTile) ? southOrientation : 0;
            orientation += areTilesTheSameId(tile, neighbourWestTile) ? westOrientation : 0;

            tile.tileOrientation = orientation;
        }
    }
}

