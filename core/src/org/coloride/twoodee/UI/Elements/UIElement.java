package org.coloride.twoodee.UI.Elements;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import org.coloride.twoodee.Rendering.CameraRenderer;
import org.coloride.twoodee.UI.UIHandler;

public class UIElement {
    public float uiPositionX = 0;
    public float uiPositionY = 0;
    public SpriteBatch batch;

    public UIElement() {
        UIHandler.uiElements.add(this);
    }

    public void process() {

    }

    public void draw() {

    }

    public void remove() {
        UIHandler.uiElements.remove(this);
    }

    public Vector2 getUiPosition() {return new Vector2(uiPositionX, uiPositionY);}
    public float getUiPositionX() {return uiPositionX;}
    public float getUiPositionY() {return uiPositionY;}
}
