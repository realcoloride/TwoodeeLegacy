package org.coloride.twoodee.UI;

import org.coloride.twoodee.Content.UI.FrontHUD.HintUI;
import org.coloride.twoodee.Rendering.BatchRenderer;
import org.coloride.twoodee.UI.Elements.UIElement;

import java.util.ArrayList;

public class UIHandler {
    public static void process() {
        HintUI.process();
    }

    public static void draw() {
        BatchRenderer.frontHUDBatch.begin();

        HintUI.draw();

        BatchRenderer.frontHUDBatch.end();
    }
}
