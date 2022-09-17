package org.coloride.twoodee.World;

import com.badlogic.gdx.math.Vector2;

import java.util.HashMap;

public class MapData {
    public HashMap<Vector2, Chunk> chunks;
    public MapInformation.MapSize mapSize;
    public float mapTime = 0; // 0 - 24 hours

    // todo: entities

    public MapData(MapInformation.MapSize mapSize, HashMap<Vector2, Chunk> chunks) {
        this.mapSize = mapSize;
        this.chunks = chunks;
    }

    public void saveToFile() {

    }

    public void loadToFile() {

    }

    public HashMap<Vector2, Chunk> getChunks() {return chunks;}
    public MapInformation.MapSize getMapSize() {return mapSize;}
    public float getMapTime() {return mapTime;}
}
