package org.coloride.twoodee.World;

import com.badlogic.gdx.math.Vector2;
import org.coloride.twoodee.Content.Biomes.DebugBiome;

public class WorldGenerator {
    public static void generateWorld() {
        Vector2 chunkPosition = new Vector2();
        MapInformation.MapSize mapSize = MapInformation.getLoadedMap().getMapSize();

        for (int cx = 0; cx < MapInformation.mapSizes.get(mapSize).x; cx++) {
            for (int cy = 0; cy < MapInformation.mapSizes.get(mapSize).y; cy++) {
                // For each chunk
                chunkPosition = new Vector2(cx,cy);

                Chunk chunk = new Chunk(chunkPosition, new DebugBiome());
                chunk.generate();

                MapInformation.getLoadedMap().getChunks().put(chunkPosition, chunk);
                //MapInformation.loadedChunks.put(chunkPosition, chunk);
            }
        }
    };
}
