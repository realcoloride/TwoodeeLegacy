package org.coloride.twoodee.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import org.coloride.twoodee.Rendering.BatchRenderer;
import org.coloride.twoodee.Rendering.Camera;
import org.coloride.twoodee.Rendering.RenderInformation;
import org.coloride.twoodee.World.*;

import static org.coloride.twoodee.World.WorldRenderer.getRegionFromTexture;
import static org.coloride.twoodee.World.WorldRenderer.getTileTexture;

public class DebugUI {
    private static BitmapFont debugFont = new BitmapFont();
    private static GlyphLayout debugGlyphLayout = new GlyphLayout();
    private static ShapeRenderer shapeRenderer = new ShapeRenderer();
    private static ShapeRenderer terrainShapeRenderer = new ShapeRenderer();
    private static SpriteBatch debugUiBatch = BatchRenderer.debugUiBatch;
    private static WorldTile pointTile;
    public static int workload = 0;

    public static void process() {

    }

    public static void draw() {
        Chunk chunk = WorldRenderer.getChunkFromSpacePosition(Camera.getCursorPositionInSpace());
        pointTile = (WorldRenderer.getTileFromSpacePosition(Camera.getCursorPositionInSpace()) != null) ? WorldRenderer.getTileFromSpacePosition(Camera.getCursorPositionInSpace()) : pointTile;
        NeighbourTile neighbourNorthTile = WorldTile.getBlockNeighbourTile(pointTile, new Vector2(0, 1));
        NeighbourTile neighbourEastTile  = WorldTile.getBlockNeighbourTile(pointTile, new Vector2(1,0));
        NeighbourTile neighbourSouthTile = WorldTile.getBlockNeighbourTile(pointTile, new Vector2(0,-1));
        NeighbourTile neighbourWestTile  = WorldTile.getBlockNeighbourTile(pointTile, new Vector2(-1,0));

        String debugText = String.join("",
                "Mouse coordinates: " + Gdx.input.getX() + "," + Gdx.input.getY() + " - Space: " + Camera.getCursorPositionInSpace().x + "," + Camera.getCursorPositionInSpace().y + "\n",
                "Hovered chunk: " + ((chunk != null) ? (chunk.getChunkPosition().toString()+" - Biome: "+ chunk.getChunkBiome().getBiomeName()) : "No chunk") + "\n",
                "Hovered tile: " + ((pointTile != null && chunk != null) ? (
                        "Tile: " + pointTile.getTilePosition().toString() + " - Id: " + pointTile.getTileId() + " - Orientation: " + pointTile.getTileOrientation() + " - Light Intensity: " + pointTile.getlightIntensity() + " (I:" + TileType.getTileTypeById(pointTile.getTileId()).getLightInfluence() + ")" + "\n" +
                        "Tile Neighbours:\n" + (
                                (
                                    ((neighbourEastTile.getTile() != null)) ? "East: " + neighbourEastTile.getTile().getTilePosition().toString() + " - Id: " + neighbourEastTile.getTile().getTileId() + " - Orientation: " + neighbourNorthTile.getTile().getTileOrientation() + " - Light Intensity: " + neighbourEastTile.getTile().getlightIntensity() + " (I:" + TileType.getTileTypeById(neighbourEastTile.getTile().getTileId()).getLightInfluence() + ")" + " Chunk Position: " + neighbourEastTile.getChunk().getChunkPosition() + "\n" : "") +
                                    ((neighbourWestTile.getTile() != null) ? "West: " + neighbourWestTile.getTile().getTilePosition().toString() + " - Id: " + neighbourWestTile.getTile().getTileId() + " - Orientation: " + neighbourWestTile.getTile().getTileOrientation() + " - Light Intensity: " + neighbourWestTile.getTile().getlightIntensity() + " (I:" + TileType.getTileTypeById(neighbourWestTile.getTile().getTileId()).getLightInfluence() + ")" + " Chunk Position: " + neighbourWestTile.getChunk().getChunkPosition() + "\n" : "") +
                                    ((neighbourNorthTile.getTile() != null) ? "North: " + neighbourNorthTile.getTile().getTilePosition().toString() + " - Id: " + neighbourNorthTile.getTile().getTileId() + " - Orientation: " + neighbourNorthTile.getTile().getTileOrientation() + " - Light Intensity: " + neighbourNorthTile.getTile().getlightIntensity() + " (I:" + TileType.getTileTypeById(neighbourNorthTile.getTile().getTileId()).getLightInfluence() + ")" + " Chunk Position: " + neighbourNorthTile.getChunk().getChunkPosition() + "\n" : "") +
                                    ((neighbourSouthTile.getTile() != null) ? "South: " + neighbourSouthTile.getTile().getTilePosition().toString() + " - Id: " + neighbourSouthTile.getTile().getTileId() + " - Orientation: " + neighbourSouthTile.getTile().getTileOrientation() + " - Light Intensity: " + neighbourSouthTile.getTile().getlightIntensity() + " (I:" + TileType.getTileTypeById(neighbourSouthTile.getTile().getTileId()).getLightInfluence() + ")" +  " Chunk Position: " + neighbourSouthTile.getChunk().getChunkPosition()  + "\n" : "")
                                )
                ) : "No tile") + "\n" +
                "Needs tile processing: " + WorldRenderer.needsTileProcessing + "\n"
                );

        if (chunk != null) {
            terrainShapeRenderer.setProjectionMatrix(Camera.camera.combined);
            terrainShapeRenderer.begin(ShapeRenderer.ShapeType.Line);
            terrainShapeRenderer.setColor(1,0,1,.75f);
            terrainShapeRenderer.rect(chunk.getChunkPosition().x*Chunk.chunkSize.x,chunk.getChunkPosition().y*Chunk.chunkSize.y,Chunk.chunkSize.x,Chunk.chunkSize.y);
            terrainShapeRenderer.end();
        }
        if (pointTile != null && chunk != null) {
            terrainShapeRenderer.setProjectionMatrix(Camera.camera.combined);
            terrainShapeRenderer.begin(ShapeRenderer.ShapeType.Line);
            terrainShapeRenderer.setColor(0,0,0.25f,0);
            terrainShapeRenderer.rect(
                    chunk.getChunkPosition().x * Chunk.chunkSize.x + pointTile.getTilePosition().x * WorldTile.tileSize.x,
                    chunk.getChunkPosition().y * Chunk.chunkSize.y + pointTile.getTilePosition().y * WorldTile.tileSize.y,
                    WorldTile.tileSize.x,
                    WorldTile.tileSize.y
            );
            terrainShapeRenderer.setColor(1,0,0,0);
            if (neighbourEastTile.getTile() != null) {
                chunk = neighbourEastTile.getTile().chunk;
                WorldTile tile = neighbourEastTile.getTile();
                terrainShapeRenderer.rect(
                        chunk.getChunkPosition().x * Chunk.chunkSize.x + tile.getTilePosition().x * WorldTile.tileSize.x,
                        chunk.getChunkPosition().y * Chunk.chunkSize.y + tile.getTilePosition().y * WorldTile.tileSize.y,
                        WorldTile.tileSize.x,
                        WorldTile.tileSize.y
                );
            }
            terrainShapeRenderer.setColor(0,1,0,0);
            if (neighbourWestTile.getTile() != null) {
                chunk = neighbourWestTile.getTile().chunk;
                WorldTile tile = neighbourWestTile.getTile();
                terrainShapeRenderer.rect(
                        chunk.getChunkPosition().x * Chunk.chunkSize.x + tile.getTilePosition().x * WorldTile.tileSize.x,
                        chunk.getChunkPosition().y * Chunk.chunkSize.y + tile.getTilePosition().y * WorldTile.tileSize.y,
                        WorldTile.tileSize.x,
                        WorldTile.tileSize.y
                );
            }
            terrainShapeRenderer.setColor(0,0,1,0);
            if (neighbourNorthTile.getTile() != null) {
                chunk = neighbourNorthTile.getTile().chunk;
                WorldTile tile = neighbourNorthTile.getTile();
                terrainShapeRenderer.rect(
                        chunk.getChunkPosition().x * Chunk.chunkSize.x + tile.getTilePosition().x * WorldTile.tileSize.x,
                        chunk.getChunkPosition().y * Chunk.chunkSize.y + tile.getTilePosition().y * WorldTile.tileSize.y,
                        WorldTile.tileSize.x,
                        WorldTile.tileSize.y
                );
            }

            terrainShapeRenderer.setColor(0,1,1,0);
            if (neighbourSouthTile.getTile() != null) {
                chunk = neighbourSouthTile.getTile().chunk;
                WorldTile tile = neighbourSouthTile.getTile();
                terrainShapeRenderer.rect(
                        neighbourSouthTile.getTile().chunk.getChunkPosition().x * Chunk.chunkSize.x + neighbourSouthTile.getTile().getTilePosition().x * WorldTile.tileSize.x,
                        neighbourSouthTile.getTile().chunk.getChunkPosition().y * Chunk.chunkSize.y + tile.getTilePosition().y * WorldTile.tileSize.y,
                        WorldTile.tileSize.x,
                        WorldTile.tileSize.y
                );
            }

            terrainShapeRenderer.end();
        }

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(0,0,0,.75f);
        shapeRenderer.rect(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY() - debugGlyphLayout.height-10f, debugGlyphLayout.width + 5, debugGlyphLayout.height + 15f);
        shapeRenderer.setColor(1,1,1,1);
        shapeRenderer.rect(Gdx.input.getX()+14, Gdx.graphics.getHeight() - Gdx.input.getY() - 15,18,18);
        shapeRenderer.end();

        debugUiBatch.begin();

        if (pointTile != null) {
            TextureRegion[][] textureRegion = getRegionFromTexture(getTileTexture(pointTile.getTileId()));
            Sprite sprite = new Sprite(AutoTiling.getTextureRegionFromWorldTile(textureRegion, pointTile));
            sprite.setPosition(Gdx.input.getX()+15, Gdx.graphics.getHeight() - Gdx.input.getY() - 14);
            sprite.draw(debugUiBatch);
        }

        debugGlyphLayout.setText(debugFont, debugText);
        float w = debugGlyphLayout.width;
        debugFont.draw(debugUiBatch, debugText, Gdx.input.getX()+1.5f, Gdx.graphics.getHeight() - Gdx.input.getY() - 20-2.5f);
        String fpsText = "RCPS: " + BatchRenderer.tilesBatch.renderCalls + " - FPS: " + Gdx.graphics.getFramesPerSecond();

        debugFont.draw(debugUiBatch, fpsText, Gdx.input.getX()+w-1.5f-new GlyphLayout(debugFont, fpsText).width, Gdx.graphics.getHeight() - Gdx.input.getY() - 2.5f);

        debugUiBatch.end();

    }
}
