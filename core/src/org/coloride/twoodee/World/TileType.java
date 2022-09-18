package org.coloride.twoodee.World;

import com.badlogic.gdx.graphics.Color;
import org.coloride.twoodee.Utilities.ColorUtilities;

import java.util.HashMap;

public enum TileType {
    COUNTING_BLOCK(
            -2,
            "Counting block",
            false,
            15,
            ColorUtilities.Conversion.RGBtoraw(255,244,191,200)
    ),
    DEBUG_BLOCK(
            -1,
            "Debug block",
            false,
            3,
            ColorUtilities.Conversion.RGBtoraw(0,0,20,200)
    ),
    AIR(
            0,
            "Air",
            false,
            0,
            null
    );

    private int tileId;
    private String tileName;
    private boolean canCollide; // todo: make CollisionFlags class for platforms
    private int lightInfluence;
    private boolean backgroundTile;
    private Color lightColor;

    private TileType(int tileId, String tileName, boolean canCollide, int lightInfluence, Color lightColor) {
        this.tileId = tileId;
        this.tileName = tileName;
        this.canCollide = canCollide;
        this.lightInfluence = lightInfluence;
        this.lightColor = lightColor;
    }

    // Getters n' Setters
    public int getTileId() {return tileId;}
    public String getName() {return tileName;}
    public boolean isCollidable() {return canCollide;}
    public int getLightInfluence() {return lightInfluence;}
    public boolean isBackgroundTile() {return backgroundTile;}
    public Color getLightColor() {return lightColor;}

    public static HashMap<Integer, TileType> tilesTypes = new HashMap<>();

    static {
        for (TileType tileType : TileType.values()){
            tilesTypes.put(tileType.tileId, tileType);
        }
    }

    public static TileType getTileTypeById(int tileId) {
        return tilesTypes.get(tileId);
    }
}
