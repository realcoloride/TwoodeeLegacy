package org.coloride.twoodee.UI.Elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import org.coloride.twoodee.UI.Fonts.VCRFont;
import org.coloride.twoodee.UI.UIFont;

public class UIText extends UIElement {
    private String text;
    public UIFont uiFont;
    public BitmapFont bitmapFont;
    public Color textColor;
    private int textSize;
    private FreeTypeFontGenerator.FreeTypeFontParameter fontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
    private FreeTypeFontGenerator fontGenerator;
    public GlyphLayout glyphLayout;

    public void process() {
        bitmapFont.setColor(textColor);
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

        this.uiFont = uiFont;
        this.uiPositionX = x;
        this.uiPositionY = y;
        this.textSize = textSize;
        this.textColor = textColor;

        this.fontParameter.size = textSize;
        this.fontParameter.color = textColor;
        this.fontGenerator = uiFont.getFontGenerator();
        this.generateNewBitmapFont();

        this.setText(text);
    }

    public void setUiFont(UIFont uiFont) {
        this.fontGenerator = uiFont.getFontGenerator();
        this.generateNewBitmapFont();
    }
    public void setTextSize(int textSize) {
        this.textSize = textSize;
        this.generateNewBitmapFont();
    }
    public void setText(String text) {
        this.text = text;
        this.glyphLayout = new GlyphLayout(this.bitmapFont, text);
    }
    public int getTextSize(int textSize) { return this.textSize; }
}
