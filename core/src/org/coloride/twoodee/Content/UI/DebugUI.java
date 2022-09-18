package org.coloride.twoodee.Content.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import org.coloride.twoodee.Rendering.BatchRenderer;
import org.coloride.twoodee.UI.Elements.Buttons.UIClickableText;
import org.coloride.twoodee.UI.Elements.Enums.ButtonToggleType;
import org.coloride.twoodee.UI.Elements.UIImage;
import org.coloride.twoodee.UI.Elements.UIText;
import org.coloride.twoodee.UI.Fonts.VCRFont;

public class DebugUI {
    private static UIText debugText = new UIText(BatchRenderer.debugUiBatch, "", new VCRFont(), 0, 64, 32, Color.WHITE);
    private static UIImage debugImage = new UIImage(BatchRenderer.debugUiBatch, Gdx.files.internal("img/ui/other/ui_image_fallback.png"), 0, 0, new Color(1,1,1,1), 512, 512);
    private static UIClickableText debugButton = new UIClickableText(BatchRenderer.debugUiBatch, ButtonToggleType.CLICKABLE, new UIText(BatchRenderer.debugUiBatch, "> hover me <", new VCRFont(), 600, 128, 32, Color.WHITE), Color.YELLOW);

    public static void create() {

    }
    public static void process() {
        debugText.setText("TrueTypeFont - vcr.ttf!\nFPS:" + Gdx.graphics.getFramesPerSecond());
        debugImage.uiPositionX = Gdx.input.getX();
        debugImage.uiPositionY = Gdx.graphics.getHeight()-Gdx.input.getY()-512;

        debugButton.onClick();

        debugImage.process();
        debugText.process();
        debugButton.process();
    }
    public static void draw() {
        BatchRenderer.debugUiBatch.begin();
        debugImage.draw();
        debugText.draw();
        debugButton.draw();
        BatchRenderer.debugUiBatch.end();
    }
}
