package org.coloride.twoodee.UI;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class UIFont {
    private FreeTypeFontGenerator fontGenerator;

    public BitmapFont getFont(FreeTypeFontGenerator.FreeTypeFontParameter fontParameter) {
        return fontGenerator.generateFont(fontParameter);
    }
}
