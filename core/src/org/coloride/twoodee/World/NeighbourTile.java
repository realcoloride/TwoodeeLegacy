package org.coloride.twoodee.World;

public class NeighbourTile {
    public Chunk chunk;
    public WorldTile tile;

    public NeighbourTile(Chunk chunk, WorldTile tile) {
        this.chunk = chunk;
        this.tile = tile;
    }

    public static boolean doesNeighbourTileExist(NeighbourTile neighbourTile, boolean mustHaveTile) {
        // If the instance exists
        if (neighbourTile != null) {
            // If tile exists, check if we want to know if there is a tile (which means neighbour was found)
            return neighbourTile.getTile() != null ? mustHaveTile : false;
        } else {
            return false;
        }
    };

    public boolean isFromChunk(Chunk targetChunk) { return (targetChunk == chunk); }

    public Chunk getChunk() {return chunk;}
    public WorldTile getTile() {return tile;}
}
