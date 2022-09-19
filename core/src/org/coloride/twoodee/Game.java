package org.coloride.twoodee;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import org.coloride.twoodee.Content.UI.Cursor.UICursor;
import org.coloride.twoodee.Logic.GameLogic;
import org.coloride.twoodee.World.WorldRenderer;

public class Game extends ApplicationAdapter {
	@Override
	public void create () {
		WorldRenderer.loadTilesTextures();
		GameLogic.onLoad();
		UICursor.create();
		//Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
	}

	@Override
	public void render () {
		GameLogic.process();
		GameLogic.update();
	}
	
	@Override
	public void dispose () {
		GameLogic.dispose();
	}
}
