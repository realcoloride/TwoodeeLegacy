package org.coloride.twoodee.Content.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import org.coloride.twoodee.Rendering.BatchRenderer;
import org.coloride.twoodee.Rendering.Camera;
import org.coloride.twoodee.UI.Elements.Buttons.UIBaseButton;
import org.coloride.twoodee.UI.Elements.Buttons.UIBaseSlider;
import org.coloride.twoodee.UI.Elements.Buttons.UICheckbox;
import org.coloride.twoodee.UI.Elements.Buttons.UIClickableText;
import org.coloride.twoodee.UI.Elements.Enums.ButtonToggleType;
import org.coloride.twoodee.UI.Elements.Interfaces.BaseButtonEvent;
import org.coloride.twoodee.UI.Elements.UIImage;
import org.coloride.twoodee.UI.Elements.UIText;
import org.coloride.twoodee.UI.Fonts.VCRFont;

import java.awt.event.ActionListener;

public class DebugUI {
    private static UIText debugText = new UIText(BatchRenderer.debugUiBatch, "", new VCRFont(), 0, 64, 32, Color.WHITE);
    private static UIText zoomText = new UIText(BatchRenderer.debugUiBatch, "Zoom", new VCRFont(), 0, 200-16, 32, Color.WHITE);
    private static UIImage debugImage = new UIImage(BatchRenderer.debugUiBatch, Gdx.files.internal("img/ui/other/ui_image_fallback.png"), 0, 0, new Color(1,1,1,1), 512, 512);
    private static UIClickableText debugButton = new UIClickableText(BatchRenderer.debugUiBatch, ButtonToggleType.CLICKABLE, new UIText(BatchRenderer.debugUiBatch, "> click for tick <", new VCRFont(), 600, 128, 32, Color.WHITE), Color.YELLOW);
    private static UICheckbox debugCheckBox = new UICheckbox(BatchRenderer.debugUiBatch, 600, 168, 64, 64);
    private static UIBaseSlider slider = new UIBaseSlider(BatchRenderer.debugUiBatch, 10, 200, 0, .6f, 0.001f, 200, 10);

    public static void create() {
        debugCheckBox.hidden = true;
        slider.uiScaleX = 2;
        slider.uiScaleY = 2;
        debugButton.addEventListener(new BaseButtonEvent() {
            @Override
            public void onClick() {
                debugCheckBox.hidden = false;
                debugButton.uiText.setText("tick box!");
            }

            @Override
            public void onToggle() {

            }

            @Override
            public void onHover() {

            }
        });
        debugCheckBox.addEventListener(new BaseButtonEvent() {
            @Override
            public void onClick() {
            }

            @Override
            public void onToggle() {

            }

            @Override
            public void onHover() {

            }
        });
    }
    public static void process() {
        debugText.setText("TrueTypeFont - vcr.ttf!\nFPS:" + Gdx.graphics.getFramesPerSecond());
        slider.hint = slider.sliderValue + "/" + slider.sliderMaximum;
        zoomText.setText("Zoom: " + Camera.cameraZoomFactor + "x");
        debugCheckBox.hint = "im currently struggling with\nmaking the rendering better";
        Camera.cameraZoomFactor = Camera.cameraMaxZoomFactor-slider.sliderValue;

        //debugImage.process();
        debugText.process();
        zoomText.process();
        slider.process();
        debugButton.process();
        debugCheckBox.process();
    }
    public static void draw() {
        BatchRenderer.debugUiBatch.begin();
        //debugImage.draw();
        debugText.draw();
        zoomText.draw();
        slider.draw();
        debugButton.draw();
        debugCheckBox.draw();
        BatchRenderer.debugUiBatch.end();
    }
}
