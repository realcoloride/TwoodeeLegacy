package org.coloride.twoodee.Content.Biomes;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import org.coloride.twoodee.World.*;

import java.util.HashMap;

public class DebugBiome extends Biome {
    String biomeName = "DebugBiome";
    @Override
    public void generateTiles(Chunk chunk) {
        Vector2 tilePosition;
        for (int x = 0; x < Chunk.chunkSize.x / WorldTile.tileSize.x; x++) {
            for (int y = 0; y < Chunk.chunkSize.y / WorldTile.tileSize.y; y++) {
                tilePosition = new Vector2(x,y);
                WorldTile tile = new WorldTile(0, chunk, tilePosition, true, false);

                if (y < 16) {
                    tile.tileId =  (MathUtils.random(1,2) == 1) ? -1 : tile.tileId;
                    tile.tileId = (MathUtils.random(1,10) == 1) ? -2 : tile.tileId;
                }

                chunk.chunkTiles.put(tilePosition, tile);
            }
        }
        //super.generateTiles(chunk);
    }
    @Override
    public String getBiomeName() {return biomeName;}
}
