package org.coloride.twoodee.World;

import com.badlogic.gdx.math.Vector2;

import java.util.HashMap;

public class Chunk {
    public static Vector2 chunkSize = new Vector2(WorldTile.tileSize.x * 16, WorldTile.tileSize.y * 16);

    private Vector2 chunkPosition;
    public HashMap<Vector2, WorldTile> chunkTiles;
    private Biome biome;
    public boolean requiresUpdate = true;

    public Chunk(Vector2 chunkPosition, Biome biome, HashMap<Vector2, WorldTile> chunkTiles) {
        this.chunkPosition = chunkPosition;
        this.biome = biome;
        this.chunkTiles = chunkTiles;
    }
    public Chunk(Vector2 chunkPosition, Biome biome) {
        this.chunkPosition = chunkPosition;
        this.biome = biome;
        this.chunkTiles = new HashMap();
    }

    public void generate() {
        biome.generateTiles(this);
    }

    public WorldTile getTileFromChunk(Vector2 tilePosition) {
        return chunkTiles.get(tilePosition);
    }

    public Vector2 getChunkPosition() {return chunkPosition;}
    public HashMap<Vector2, WorldTile> getChunkTiles() {return chunkTiles;}
    public Biome getChunkBiome() {return biome;}
    public boolean doesRequireUpdate() { return requiresUpdate; }
}
