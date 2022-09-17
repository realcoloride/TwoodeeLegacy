package org.coloride.twoodee.World;

import com.badlogic.gdx.math.Vector2;

import java.util.HashMap;

public class MapInformation {
    public enum MapSize {
        TINY,
        SMALL,
        MEDIUM,
        LARGE,
    }


    public static MapData loadedMap = null;
    public static HashMap<MapSize, Vector2> mapSizes = new HashMap(){{
        put(MapSize.TINY, new Vector2(8,8));
        put(MapSize.SMALL, new Vector2(16,16));
        put(MapSize.MEDIUM, new Vector2(32,32));
        put(MapSize.LARGE, new Vector2(64,64));
    }};

    public static MapData getLoadedMap() {return loadedMap;}
}
