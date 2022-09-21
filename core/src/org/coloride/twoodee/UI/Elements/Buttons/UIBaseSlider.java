package org.coloride.twoodee.UI.Elements.Buttons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import org.coloride.twoodee.UI.Elements.Interfaces.BaseButtonEvent;
import org.coloride.twoodee.UI.Elements.Interfaces.BaseSliderEvent;
import org.coloride.twoodee.UI.Elements.UIElement;

import java.util.ArrayList;
import java.util.EventListener;

public class UIBaseSlider extends UIElement implements EventListener {
    final static Texture beginTexture  = new Texture(Gdx.files.internal("img/ui/slider/slider_begin.png"));
    final static Texture middleTexture = new Texture(Gdx.files.internal("img/ui/slider/slider_middle.png"));
    final static Texture endTexture    = new Texture(Gdx.files.internal("img/ui/slider/slider_end.png"));
    final static Texture sliderTexture = new Texture(Gdx.files.internal("img/ui/slider/slider.png"));
    public static Color disabledColor    = new Color(0.9f,0.9f,0.9f,1f);

    final static float defaultSliderWidth = 100f;
    final static float defaultSliderHeight = 16f;
    final static float defaultSliderStep   = 0.1f;

    public ArrayList<BaseSliderEvent> sliderEventListeners = new ArrayList();

    public Sprite beginSprite  = new Sprite(beginTexture);
    public Sprite middleSprite = new Sprite(middleTexture);
    public Sprite endSprite    = new Sprite(endTexture);
    public Sprite sliderSprite = new Sprite(sliderTexture);

    public float sliderWidth;
    public float sliderHeight;

    public float sliderMinimum;
    public float sliderMaximum;
    public float sliderStep;
    public float sliderValue;
    public boolean hovered;
    public boolean disabled;

    public void addEventListener(BaseSliderEvent listener) {
        sliderEventListeners.add(listener);
    }

    public void onValueChange() {
        for (BaseSliderEvent baseSliderEvent : sliderEventListeners) { baseSliderEvent.onValueChange(); }
    }

    public void onClick() {
        for (BaseSliderEvent baseSliderEvent : sliderEventListeners) { baseSliderEvent.onClick(); }
    }

    public void onHover() {
        for (BaseSliderEvent baseSliderEvent : sliderEventListeners) { baseSliderEvent.onHover(); }
    }

    public void process() {
        uiBounds.x = (endTexture.getWidth() * uiScaleX)/2;
        uiBounds.y = uiPositionY;
        uiBounds.width = (sliderWidth + (endTexture.getWidth() * uiScaleX)) * uiScaleX;
        uiBounds.height = sliderTexture.getHeight() * uiScaleY;

        boolean inBounds = uiBounds.contains(Gdx.input.getX(), Gdx.graphics.getHeight()-Gdx.input.getY());
        if (!hovered && inBounds) { onHover(); }

        if (inBounds) {
            if (!disabled && Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
                double value = Math.round(
                        (
                                (((Gdx.input.getX() - uiPositionX - sliderTexture.getWidth()/2) * uiScaleX) * (sliderMaximum - sliderMinimum)) * sliderMaximum / ((sliderMaximum - sliderMinimum) * uiScaleX) / (sliderWidth * uiScaleX)
                        ) / sliderStep
                ) * sliderStep;
                sliderValue = MathUtils.clamp((float)value, 0, sliderMaximum);
            }
        }

        hovered = inBounds;

        beginSprite.setPosition(uiPositionX - sliderTexture.getWidth()/2 * uiScaleX, uiPositionY);
        beginSprite.setSize(beginTexture.getWidth() * uiScaleX, beginTexture.getHeight() * uiScaleY);

        middleSprite.setPosition(uiPositionX - (sliderTexture.getWidth()*2 * uiScaleX) + (beginTexture.getWidth() + sliderTexture.getWidth()) * uiScaleX, uiPositionY);
        middleSprite.setSize((sliderWidth + sliderTexture.getWidth()) * uiScaleX, middleTexture.getHeight() * uiScaleY);

        endSprite.setPosition(uiPositionX + (sliderWidth + middleTexture.getWidth() + sliderTexture.getWidth()/2) * uiScaleX, uiPositionY);
        endSprite.setSize(endTexture.getWidth() * uiScaleX, endTexture.getHeight() * uiScaleY);

        sliderSprite.setPosition(uiPositionX + ((sliderValue * sliderWidth / sliderMaximum - sliderMinimum) * uiScaleX), uiPositionY);
        sliderSprite.setSize(sliderTexture.getWidth() * uiScaleX, sliderTexture.getHeight() * uiScaleY);

        super.process();
    }

    public void draw() {
        if (!hidden) {
            middleSprite.draw(batch);
            beginSprite.draw(batch);
            endSprite.draw(batch);
            sliderSprite.draw(batch);
        }

        super.draw();
    }

    public UIBaseSlider(SpriteBatch batch, float x, float y, float sliderMinimum, float sliderMaximum, float sliderStep, float sliderWidth, float sliderHeight) {
        super();
        this.batch = batch;
        this.uiPositionX = x;
        this.uiPositionY = y;

        this.sliderMinimum = sliderMinimum;
        this.sliderMaximum = sliderMaximum;
        this.sliderStep    = sliderStep;
        this.sliderWidth   = sliderWidth;
        this.sliderHeight  = sliderHeight;
    }
    public UIBaseSlider(SpriteBatch batch, float x, float y, float sliderMinimum, float sliderMaximum, float sliderStep) {
        this(batch, x, y, sliderMinimum, sliderMaximum, sliderStep, defaultSliderWidth, defaultSliderHeight);
    }
    public UIBaseSlider(SpriteBatch batch, float x, float y, float sliderMinimum, float sliderMaximum) {
        this(batch, x, y, sliderMinimum, sliderMaximum, defaultSliderStep);
    }
}
