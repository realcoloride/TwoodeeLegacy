package org.coloride.twoodee;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import org.coloride.twoodee.Logic.GameLogic;
import org.coloride.twoodee.World.WorldRenderer;

import static org.coloride.twoodee.Rendering.Camera.camera;
import static org.coloride.twoodee.Rendering.Camera.viewport;

public class Game extends ApplicationAdapter {
	@Override
	public void create () {
		WorldRenderer.loadTilesTextures();
		GameLogic.onLoad();
		//Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
	}

	@Override
	public void render () {
		GameLogic.process();
		GameLogic.update();
	}

	@Override
	public void resize(int width, int height) {
		if (camera != null && viewport != null) {
			viewport.update(width, height);
			camera.update();
		}
	}
	
	@Override
	public void dispose () {
		GameLogic.dispose();
	}
}
