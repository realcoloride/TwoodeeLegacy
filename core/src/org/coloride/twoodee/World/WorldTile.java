package org.coloride.twoodee.World;

import box2dLight.PointLight;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import org.coloride.twoodee.Utilities.MathUtilities;

import javax.swing.*;

public class WorldTile {

    public static Vector2 tileSize = new Vector2(16, 16);

    public int tileId;
    public Chunk chunk;
    public Vector2 tilePosition;
    public Integer lightIntensity = null; // Needs rendering
    public int tileOrientation = 0;
    public boolean needsTileOrientationRefreshing = true;
    public boolean overrideTileOrientation = false;

    public PointLight tileLight;

    public boolean hasTileBack = false;
    public boolean hasTileFront = true;

    public WorldTile(int tileId, Chunk chunk, Vector2 tilePosition, boolean hasTileFront, boolean hasTileBack) { // todo: TileFlags class
        // Tile information
        this.tileId = tileId;

        // Tile environment data
        this.hasTileFront = hasTileFront;
        this.hasTileBack = hasTileBack;

        // Tile position
        this.chunk = chunk;
        this.tilePosition = tilePosition;
        this.tileLight = new PointLight(BoxLighting.rayHandler, BoxLighting.tileLightRaysQuality, Color.WHITE,50,128,128);
        this.tileLight.setActive(false);
        this.tileLight.setSoft(false);
    }

    public static NeighbourTile getBlockNeighbourTile(WorldTile tile, Vector2 neighbourPosition) {
        NeighbourTile neighbourTile = new NeighbourTile(null,null);

        Vector2 targetPosition = tile.getTilePosition();
        Vector2 adjustedNeighbourPosition = MathUtilities.Vectors.addTwoVectors(targetPosition, neighbourPosition);
        Chunk tileChunk = tile.getChunk();
        Vector2 flippedPosition = new Vector2();

        // Not in same chunk
        boolean isNeighbourPositionLeft  = (adjustedNeighbourPosition.x < 0); // <-| East
        boolean isNeighbourPositionRight = (adjustedNeighbourPosition.x > (Chunk.chunkSize.x/WorldTile.tileSize.x-1)); // |-> // West
        boolean isNeighbourPositionTop   = (adjustedNeighbourPosition.y > (Chunk.chunkSize.y/WorldTile.tileSize.y-1)); // North
        boolean isNeighbourPositionBelow = (adjustedNeighbourPosition.y < 0); // South

        if (
                isNeighbourPositionLeft  ||
                isNeighbourPositionRight ||
                isNeighbourPositionTop   ||
                isNeighbourPositionBelow
        ) {
            Vector2 leftNeighbourChunkPosition  = MathUtilities.Vectors.addToVector(tile.getChunk().getChunkPosition(),-1,0);
            Vector2 rightNeighbourChunkPosition = MathUtilities.Vectors.addToVector(tile.getChunk().getChunkPosition(),1,0);
            Vector2 topNeighbourChunkPosition   = MathUtilities.Vectors.addToVector(tile.getChunk().getChunkPosition(),0,1);
            Vector2 belowNeighbourChunkPosition = MathUtilities.Vectors.addToVector(tile.getChunk().getChunkPosition(),0,-1);

            Chunk targetChunk = null;

            targetChunk = isNeighbourPositionLeft ? MapInformation.getLoadedMap().getChunks().get(leftNeighbourChunkPosition) : targetChunk;
            targetChunk = isNeighbourPositionRight ? MapInformation.getLoadedMap().getChunks().get(rightNeighbourChunkPosition) : targetChunk;
            targetChunk = isNeighbourPositionTop ? MapInformation.getLoadedMap().getChunks().get(topNeighbourChunkPosition) : targetChunk;
            targetChunk = isNeighbourPositionBelow ? MapInformation.getLoadedMap().getChunks().get(belowNeighbourChunkPosition) : targetChunk;

            if (targetChunk != null) {
                flippedPosition.set(adjustedNeighbourPosition.x % 16, adjustedNeighbourPosition.y % 16);

                flippedPosition.x = flippedPosition.x < 0 ? 16 + (flippedPosition.x) : flippedPosition.x;
                flippedPosition.y = flippedPosition.y < 0 ? 16 + (flippedPosition.y) : flippedPosition.y;

                neighbourTile.chunk = targetChunk;
                neighbourTile.tile = targetChunk.getTileFromChunk(flippedPosition);

                // return the tile if it exists or else it returns null
                return neighbourTile;
            } else { return new NeighbourTile(null, null); }
        }

        // In same chunk
        neighbourTile.chunk = tileChunk;
        neighbourTile.tile = tileChunk.getTileFromChunk(adjustedNeighbourPosition);

        return neighbourTile;
    }

    public int getTileId() {return tileId;}
    public Chunk getChunk() {return chunk;}
    public Vector2 getTilePosition() {return tilePosition;}
    public Integer getlightIntensity() {return lightIntensity;}
    public int getTileOrientation() {return tileOrientation;}
    public boolean needsTileOrientationRefreshing() {return needsTileOrientationRefreshing;}
    public boolean hasTileFront() {return hasTileFront;}
    public boolean hasTileBack() {return hasTileBack;}
    public PointLight getTileLight() {return tileLight;}
}
