package org.coloride.twoodee.World;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import org.coloride.twoodee.Rendering.Camera;
import org.coloride.twoodee.Utilities.MathUtilities;

import javax.rmi.ssl.SslRMIClientSocketFactory;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;

public class TileLighting extends Thread {
    public static ArrayList<Chunk> chunkRefreshBuffer = new ArrayList<>();
    public static boolean rendering = false;


    public void run() {
        try {
            rendering = true;
            for (int i = 0; i < chunkRefreshBuffer.size(); i++) {
                refreshChunkLightInfluence(chunkRefreshBuffer.get(i));
            }
            chunkRefreshBuffer.clear();
            rendering = false;
        } catch (Exception e) {
            System.out.println("Exception occured");
            System.out.println(e.getCause() + " - " + e.getMessage() + " - " + e.getClass() + " - " + e.getLocalizedMessage() + "");
        }
    }

    public static void bakeLighting() {
        for (Chunk chunk : MapInformation.getLoadedMap().getChunks().values()) {
            addChunkToRefreshLightBuffer(chunk);
        }
    }

    public static void addChunkToRefreshLightBuffer(Chunk chunk) { chunkRefreshBuffer.add(chunk); }
    public static void refreshChunkLightInfluence(Chunk chunk) {
        if (chunk.requiresUpdate) {
            Vector2 tilePosition;
            float cameraPositionX = (Camera.camera.position.x - Camera.camera.viewportWidth/2);
            float cameraPositionY = (Camera.camera.position.y - Camera.camera.viewportHeight/2);

            int minTileX = MathUtils.floor(cameraPositionX/WorldTile.tileSize.x); // drawing anchor point (x)
            int minTileY = MathUtils.floor(cameraPositionY/WorldTile.tileSize.y); // drawing anchor point (y)
            int maxTileX = MathUtils.ceil(Camera.camera.viewportWidth/WorldTile.tileSize.x); // tile render amount (x)
            int maxTileY = MathUtils.ceil(Camera.camera.viewportHeight/WorldTile.tileSize.y); // tile render amount (y)

            float tileIterationsX = MathUtils.clamp(minTileX + maxTileX, 0, Chunk.chunkSize.x / WorldTile.tileSize.x);
            float tileIterationsY = MathUtils.clamp(minTileY + maxTileY, 0, Chunk.chunkSize.y / WorldTile.tileSize.y);

            for (int x = 0; x < tileIterationsX; x++) {
                for (int y = 0; y < tileIterationsY; y++) {
                    tilePosition = new Vector2(x,y);
                    WorldTile tile = chunk.getTileFromChunk(tilePosition);

                    // Get the current light intensity, if the light intensity is null, get the light intensity by its influence
                    Integer lightIntensity = tile.getlightIntensity() == null ? TileType.getTileTypeById(tile.getTileId()).getLightInfluence() : tile.getlightIntensity();

                    // Get all neighbouring tiles
                    NeighbourTile neighbourNorthTile = WorldTile.getBlockNeighbourTile(tile, new Vector2(0, 1));
                    NeighbourTile neighbourEastTile  = WorldTile.getBlockNeighbourTile(tile, new Vector2(1,0));
                    NeighbourTile neighbourSouthTile = WorldTile.getBlockNeighbourTile(tile, new Vector2(0,-1));
                    NeighbourTile neighbourWestTile  = WorldTile.getBlockNeighbourTile(tile, new Vector2(-1,0));

                    // If the neighbours exists, add the influence

                    Integer leftTileInfluence  =  neighbourEastTile.getTile() != null ?  neighbourEastTile.getTile().getlightIntensity() : null;
                    Integer rightTileInfluence =  neighbourWestTile.getTile() != null ?  neighbourWestTile.getTile().getlightIntensity() : null;
                    Integer upTileInfluence    = neighbourNorthTile.getTile() != null ? neighbourNorthTile.getTile().getlightIntensity() : null;
                    Integer downTileInfluence  = neighbourSouthTile.getTile() != null ? neighbourSouthTile.getTile().getlightIntensity() : null;

                    int[] influences = new int[5];

                    if (leftTileInfluence != null) influences[0]=leftTileInfluence;
                    if (rightTileInfluence != null) influences[1]=rightTileInfluence;
                    if (upTileInfluence != null) influences[2]=upTileInfluence;
                    if (downTileInfluence != null) influences[3]=downTileInfluence;

                    int biggestInfluence = lightIntensity;

                    for (int i = 0; i < influences.length; i++) {
                        if (influences[i] > biggestInfluence) {
                            biggestInfluence = influences[i]-1;
                        }
                    }

                    lightIntensity = MathUtils.clamp(biggestInfluence, 0, 15);
                    tile.lightIntensity = lightIntensity;
                }
            }
        }
    }
}