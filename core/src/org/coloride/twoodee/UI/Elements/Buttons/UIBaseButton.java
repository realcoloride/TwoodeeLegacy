package org.coloride.twoodee.UI.Elements.Buttons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Rectangle;
import org.coloride.twoodee.UI.Elements.Enums.ButtonToggleType;
import org.coloride.twoodee.UI.Elements.Interfaces.BaseButtonEvent;
import org.coloride.twoodee.UI.Elements.UIElement;

import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;

public class UIBaseButton extends UIElement implements EventListener {
    // todo: support only left mouse/right mouse
    public List<BaseButtonEvent> buttonEventListeners = new ArrayList<>();
    public Rectangle buttonBounds = new Rectangle();
    public ButtonToggleType buttonToggleType;
    public boolean toggled;
    public boolean hovered = false;

    public UIBaseButton() {

    }

    public void addEventListener(BaseButtonEvent listener) {
        buttonEventListeners.add(listener);
    }

    public void onToggle() {
        for (BaseButtonEvent baseButtonEvent : buttonEventListeners) { baseButtonEvent.onToggle(); }
    }

    public void onClick() {
        /*if (buttonToggleType == ButtonToggleType.CLICKABLE) {

        }*/
        if (buttonToggleType == ButtonToggleType.TOGGABLE) {
            toggled = !toggled;
            onToggle();
        }

        for (BaseButtonEvent baseButtonEvent : buttonEventListeners) { baseButtonEvent.onClick(); }
    }

    public void onHover() {
        for (BaseButtonEvent baseButtonEvent : buttonEventListeners) { baseButtonEvent.onHover(); }
    }

    public void process() {
        boolean inBounds = buttonBounds.contains(Gdx.input.getX(), 720-Gdx.input.getY());

        if (inBounds) {
            if (!hovered && inBounds) { onHover(); }

            if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
                onClick();
            }
        }
        hovered = inBounds;
    }
}
