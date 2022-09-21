package org.coloride.twoodee.Content.UI.FrontHUD;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import org.coloride.twoodee.Rendering.BatchRenderer;
import org.coloride.twoodee.Rendering.Camera;
import org.coloride.twoodee.UI.Elements.UIElement;
import org.coloride.twoodee.UI.Elements.UIText;
import org.coloride.twoodee.UI.Fonts.VCRFont;

public class HintUI {
    public static UIText uiText = new UIText(BatchRenderer.frontHUDBatch, "", new VCRFont(), 0,0,32, new Color(1,1,1,1));
    public static UIElement uiElement = null;

    public static void process() {
        uiElement = null;

        for (int i = 0; i < UIElement.elementsList.size(); i++) {
            if (UIElement.elementsList.get(i).uiBounds.contains(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY())) {
                uiElement = UIElement.elementsList.get(i);
                break;
            }
        }

        uiText.uiPositionX = 16 + Gdx.input.getX();
        uiText.uiPositionY = Gdx.graphics.getHeight() - Gdx.input.getY();
    }
    public static void draw() {
        if (uiElement != null) {
            if (!uiElement.hidden) {
                uiText.setText(uiElement.hint);
                uiText.draw();
            }
        }
    }
}
