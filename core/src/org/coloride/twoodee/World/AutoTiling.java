package org.coloride.twoodee.World;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.collision.BoundingBox;
import com.badlogic.gdx.physics.box2d.World;
import org.coloride.twoodee.Rendering.Camera;
import org.coloride.twoodee.Utilities.MathUtilities;

import java.util.HashMap;

import static org.coloride.twoodee.Rendering.BatchRenderer.tilesBatch;

public class AutoTiling extends Thread {
    public static int northOrientation = 1;
    public static int eastOrientation = 2;
    public static int southOrientation = 4;
    public static int westOrientation = 8;

    public void run() {
        try {
            // Chunk rendering
            Vector2 chunkPosition;
            BoundingBox chunkBounds;
            Vector2 tilePosition;
            Vector2 tileSpacePosition = new Vector2();
            BoundingBox blockBounds;
            boolean blockVisible;
            boolean chunkVisible;
            WorldTile tile;

            MapInformation.MapSize mapSize = MapInformation.getLoadedMap().mapSize;

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

            NeighbourTile neighbourNorthTile = WorldTile.getBlockNeighbourTile(tile, new Vector2(0, 1));
            NeighbourTile neighbourEastTile  = WorldTile.getBlockNeighbourTile(tile, new Vector2(-1,0));
            NeighbourTile neighbourSouthTile = WorldTile.getBlockNeighbourTile(tile, new Vector2(0,-1));
            NeighbourTile neighbourWestTile  = WorldTile.getBlockNeighbourTile(tile, new Vector2(1,0));

            int orientation = 0;

            orientation += areTilesTheSameId(tile, neighbourNorthTile) ? northOrientation : 0;
            orientation += areTilesTheSameId(tile, neighbourEastTile) ? eastOrientation : 0;
            orientation += areTilesTheSameId(tile, neighbourSouthTile) ? southOrientation : 0;
            orientation += areTilesTheSameId(tile, neighbourWestTile) ? westOrientation : 0;

            tile.tileOrientation = orientation;
        }
    }
}

