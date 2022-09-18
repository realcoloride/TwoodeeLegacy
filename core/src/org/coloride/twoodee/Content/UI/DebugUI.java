package org.coloride.twoodee.Content.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import org.coloride.twoodee.Rendering.BatchRenderer;
import org.coloride.twoodee.UI.Elements.UIImage;
import org.coloride.twoodee.UI.Elements.UIText;
import org.coloride.twoodee.UI.Fonts.VCRFont;

public class DebugUI {
    private static UIText debugText = new UIText(BatchRenderer.debugUiBatch, "", new VCRFont(), 0, 64, 32, Color.WHITE);
    private static UIImage debugImage = new UIImage(BatchRenderer.debugUiBatch, Gdx.files.internal("img/ui/other/ui_image_fallback.png"), 0, 0, new Color(1,1,1,1), 512, 512);

    public static void create() {

    }
    public static void process() {
        debugText.text = "TrueTypeFont - vcr.ttf!\nFPS:" + Gdx.graphics.getFramesPerSecond();
        debugImage.process();
        debugText.process();
    }
    public static void draw() {
        BatchRenderer.debugUiBatch.begin();
        debugImage.draw();
        debugText.draw();
        BatchRenderer.debugUiBatch.end();
    }
}
