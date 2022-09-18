package org.coloride.twoodee.UI.Fonts;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import org.coloride.twoodee.UI.UIFont;

public class VCRFont extends UIFont {
    private FreeTypeFontGenerator fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("font/vcr.ttf"));
}
