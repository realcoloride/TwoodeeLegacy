package org.coloride.twoodee.UI.Elements;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import org.coloride.twoodee.UI.UIFont;

public class UIText extends UIElement {
    public String text;
    public UIFont uiFont;
    public BitmapFont bitmapFont;
    public Color textColor;
    public int textSize;

    private FreeTypeFontGenerator.FreeTypeFontParameter fontParameter;

    public void process() {
        fontParameter.color = this.textColor;
        fontParameter.size = this.textSize;
    }

    @Override
    public void draw() {
        BitmapFont font = uiFont.getFont(fontParameter);

        font.draw(batch, text, uiPositionX, uiPositionY);
        super.draw();
    }

    public UIText(SpriteBatch batch, String text, UIFont uiFont, float x, float y, int textSize, Color textColor) {
        this.batch = batch;

        this.text = text;
        this.uiFont = uiFont;
        this.uiPositionX = x;
        this.uiPositionY = y;
        this.textSize = textSize;
        this.textColor = textColor;

        this.fontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
    }

    public String getText() {return text;}
}
