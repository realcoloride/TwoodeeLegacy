package org.coloride.twoodee.UI.Elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import org.coloride.twoodee.UI.Fonts.VCRFont;
import org.coloride.twoodee.UI.UIFont;

public class UIText extends UIElement {
    public String text;
    public UIFont uiFont;
    public BitmapFont bitmapFont;
    private Color textColor;
    private int textSize;
    private FreeTypeFontGenerator.FreeTypeFontParameter fontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
    private FreeTypeFontGenerator fontGenerator;

    public void process() {
        fontParameter.color = textColor;
        fontParameter.size = textSize;
        // todo: add those parameters
        /*fontParameter.shadowColor
        fontParameter.shadowOffsetX
        fontParameter.shadowOffsetY
        fontParameter.borderColor
        fontParameter.borderStraight
        fontParameter.padLeft
        fontParameter.padRight
        fontParameter.flip
        fontParameter.padBottom
        fontParameter.padTop*/
    }

    @Override
    public void draw() {
        bitmapFont.draw(batch, text, uiPositionX, uiPositionY);

        super.draw();
    }

    public void generateNewBitmapFont() {
        this.bitmapFont = fontGenerator.generateFont(fontParameter);
    }
    public UIText(SpriteBatch batch, String text, UIFont uiFont, float x, float y, int textSize, Color textColor) {
        this.batch = batch;

        this.text = text;
        this.uiFont = uiFont;
        this.uiPositionX = x;
        this.uiPositionY = y;
        this.textSize = textSize;
        this.textColor = textColor;

        this.fontParameter.size = textSize;
        this.fontParameter.color = textColor;
        this.fontGenerator = uiFont.getFontGenerator();
        this.generateNewBitmapFont();
    }

    public void setUiFont(UIFont uiFont) {
        this.fontGenerator = uiFont.getFontGenerator();
        this.generateNewBitmapFont();
    }
}
