package org.coloride.twoodee.UI.Elements.Buttons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.coloride.twoodee.UI.Elements.UIElement;

public class UISlider extends UIElement {
    private static Texture beginTexture  = new Texture(Gdx.files.internal("img/ui/slider/slider_begin.png"));
    private static Texture middleTexture = new Texture(Gdx.files.internal("img/ui/checkbox/slider_middle.png"));
    private static Texture endTexture    = new Texture(Gdx.files.internal("img/ui/checkbox/slider_end.png"));
    public static Color disabledColor    = new Color(0.9f,0.9f,0.9f,1f);

    private static float defaultSliderWidth = 100f;
    private static float getDefaultSliderHeight = 16f;

    public float sliderWidth;
    public float sliderHeight;

    public float sliderMinimum = 0f;
    public float sliderMaximum = 1f;
    public float sliderStep    = 0.1f;

    public void process() {

    }

    public void draw() {

    }

    public UISlider(SpriteBatch batch, float x, float y) {
        this.batch = batch;
        this.uiPositionX = x;
        this.uiPositionY = y;
    }
}
