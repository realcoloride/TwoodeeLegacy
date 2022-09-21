package org.coloride.twoodee.UI.Elements.Buttons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import org.coloride.twoodee.UI.Elements.Enums.ButtonToggleType;
import org.coloride.twoodee.UI.Elements.Interfaces.BaseButtonEvent;
import org.coloride.twoodee.UI.Elements.UIElement;

import java.util.ArrayList;
import java.util.EventListener;

public class UIBaseButton extends UIElement implements EventListener {
    // todo: support only left mouse/right mouse
    public ArrayList<BaseButtonEvent> buttonEventListeners = new ArrayList<>();
    public ButtonToggleType buttonToggleType;
    public boolean toggled;
    public boolean useRightClick = false;
    public boolean hovered = false;

    public UIBaseButton() {
        super();
    }

    public void addEventListener(BaseButtonEvent listener) {
        buttonEventListeners.add(listener);
    }

    public void onToggle() {
        for (BaseButtonEvent baseButtonEvent : buttonEventListeners) { baseButtonEvent.onToggle(); }
    }

    public void onClick() {
        for (BaseButtonEvent baseButtonEvent : buttonEventListeners) { baseButtonEvent.onClick(); }
    }

    public void onHover() {
        for (BaseButtonEvent baseButtonEvent : buttonEventListeners) { baseButtonEvent.onHover(); }
    }

    public void process() {
        boolean inBounds = uiBounds.contains(Gdx.input.getX(), Gdx.graphics.getHeight()-Gdx.input.getY());

        if (inBounds && !hidden) {
            if (!hovered && inBounds) { onHover(); }
            int button = useRightClick ? Input.Buttons.RIGHT : Input.Buttons.LEFT;

            if (Gdx.input.isButtonJustPressed(button)) {
                if (buttonToggleType == ButtonToggleType.TOGGLEABLE) {
                    toggled = !toggled;
                    onToggle();
                }
                onClick();
            }
        }

        hovered = inBounds;
    }
}
