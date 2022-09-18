package org.coloride.twoodee.UI.Elements.Buttons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import org.coloride.twoodee.UI.Elements.Enums.ButtonToggleType;
import org.coloride.twoodee.UI.Elements.UIElement;

public class UIBaseButton extends UIElement {
    // todo: support only left mouse/right mouse
    public Rectangle buttonBounds = new Rectangle();
    public ButtonToggleType buttonToggleType;
    public boolean toggled;
    public boolean hovered = false;

    public UIBaseButton() {

    }

    public void onToggle() {

    }

    public void onClick() {
        /*if (buttonToggleType == ButtonToggleType.CLICKABLE) {

        }*/
        if (buttonToggleType == ButtonToggleType.TOGGABLE) {
            toggled = !toggled;
            onToggle();
        }
    }

    public void onHover() {

    }

    public void process() {
        boolean inBounds = buttonBounds.contains(Gdx.input.getX(), 720-Gdx.input.getY());

        if (inBounds) {
            if (!hovered && inBounds) { onHover(); }

            if (Gdx.input.isTouched()) {
                onClick();
            }
        }
        hovered = inBounds;
    }
}
