package org.coloride.twoodee.UI.Elements;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;

import java.util.ArrayList;

public class UIElement implements Disposable {
    public static ArrayList<UIElement> elementsList = new ArrayList<>();
    public float uiPositionX = 0;
    public float uiPositionY = 0;
    public float uiScaleX = 1;
    public float uiScaleY = 1;
    public boolean hidden = false;
    public String hint = "";
    public Rectangle uiBounds = new Rectangle();
    public SpriteBatch batch;

    public void create() {

    }

    public void process() {

    }

    public void draw() {

    }

    public void dispose() {

    }

    public UIElement() {
        elementsList.add(this);
    }

    public Vector2 getUiPosition() {return new Vector2(uiPositionX, uiPositionY);}
    public float getUiPositionX() {return uiPositionX;}
    public float getUiPositionY() {return uiPositionY;}
}
