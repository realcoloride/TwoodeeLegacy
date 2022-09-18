package org.coloride.twoodee.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class UIFont {
    private FreeTypeFontGenerator fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("font/arial.ttf")); // Fallback font
    public FreeTypeFontGenerator getFontGenerator() {
        return fontGenerator;
    }
}
