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

import java.util.ArrayList;
import java.util.HashMap;

import static org.coloride.twoodee.Rendering.BatchRenderer.tilesBatch;
import static org.coloride.twoodee.World.WorldRenderer.*;

public class AutoTiling extends Thread {
    public static int northOrientation = 1;
    public static int eastOrientation = 2;
    public static int southOrientation = 4;
    public static int westOrientation = 8;

    public static ArrayList<WorldTile> autoTileBuffer = new ArrayList<>();

    public void run() {
        try {
            for (int i = 0; i < autoTileBuffer.size(); i++) {
                processAutoTile(autoTileBuffer.get(i));
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
        return neighbourTile.getTile() != null ? tile.getTileId() == neighbourTile.getTile().getTileId() : false;
    }

    public static void addTileToAutoTileBuffer(WorldTile tile) {
        autoTileBuffer.add(tile);
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
            autoTileBuffer.remove(tile);
        }
    }
}

