package org.coloride.twoodee.UI.Elements.Buttons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import org.coloride.twoodee.UI.Elements.Enums.ButtonToggleType;
import org.coloride.twoodee.UI.Elements.UIText;

public class UIClickableText extends UIBaseButton {
    public UIText uiText;
    public Color hoveredColor;

    private static ShapeRenderer shapeRenderer = new ShapeRenderer();
    private Color lastColor;

    public void process() {
        buttonBounds.width = uiText.glyphLayout.width * uiScaleX;
        buttonBounds.height = uiText.glyphLayout.height * uiScaleY;
        buttonBounds.x = uiText.uiPositionX;
        buttonBounds.y = uiText.uiPositionY - buttonBounds.height;

        uiText.process();
        uiText.bitmapFont.setColor(hovered ? hoveredColor : uiText.textColor);

        super.process();
    }

    public void draw() {
        super.draw();
        uiText.draw();
    }

    public UIClickableText(SpriteBatch batch, ButtonToggleType buttonToggleType, UIText uiText, Color hoverColor) {
        this.batch = batch;
        this.buttonToggleType = buttonToggleType;
        this.hoveredColor = hoverColor;
        this.uiText = uiText;
    }
}
