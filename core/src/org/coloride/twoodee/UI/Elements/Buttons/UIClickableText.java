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

    public void process() {
        uiText.process();

        uiBounds.width = uiText.glyphLayout.width * uiScaleX;
        uiBounds.height = uiText.glyphLayout.height * uiScaleY;
        uiBounds.x = uiText.uiPositionX;
        uiBounds.y = uiText.uiPositionY - uiBounds.height;

        uiText.bitmapFont.setColor(hovered ? hoveredColor : uiText.textColor);

        super.process();
    }

    public void draw() {
        if (!hidden)
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
