package org.coloride.twoodee.UI.Elements.Buttons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.coloride.twoodee.UI.Elements.Enums.ButtonToggleType;
import org.coloride.twoodee.UI.Elements.UIImage;

public class UICheckbox extends UIBaseButton {
    final static Texture untickedTexture = new Texture(Gdx.files.internal("img/ui/checkbox/unticked.png"));
    final static Texture tickedTexture = new Texture(Gdx.files.internal("img/ui/checkbox/ticked.png"));
    final static Texture disableTexture = new Texture(Gdx.files.internal("img/ui/checkbox/disabled.png"));
    final static float defaultCheckboxSizeX = 32f;
    final static float defaultCheckboxSizeY = 32f;

    public UIImage checkboxImage;

    public void process() {
        checkboxImage.process();

        uiBounds.width = checkboxImage.imageWidth * uiScaleX;
        uiBounds.height = checkboxImage.imageHeight * uiScaleY;
        uiBounds.x = checkboxImage.uiPositionX;
        uiBounds.y = checkboxImage.uiPositionY;

        if (buttonToggleType == ButtonToggleType.TOGGLEABLE && !hidden) {
            if (toggled) {
                // avoid memory leaks
                if (checkboxImage.getTexture() != tickedTexture) {
                    checkboxImage.setImage(tickedTexture);
                }
            } else {
                if (checkboxImage.getTexture() != untickedTexture) {
                    checkboxImage.setImage(untickedTexture);
                }
            }
        }
        if (buttonToggleType == ButtonToggleType.DISABLED) {
            if (checkboxImage.getTexture() != disableTexture) {
                checkboxImage.setImage(disableTexture);
            }
        }

        super.process();
    }
    public void draw() {
        if (!hidden)
            checkboxImage.draw();

        super.draw();
    }

    public UICheckbox(SpriteBatch batch, float x, float y, float checkboxWidth, float checkboxHeight, boolean toggled, boolean disabled) {
        this.uiPositionX = x;
        this.uiPositionY = y;
        this.batch = batch;
        this.buttonToggleType = disabled ? ButtonToggleType.DISABLED : ButtonToggleType.TOGGLEABLE;
        this.checkboxImage = new UIImage(batch, disableTexture, x, y, new Color(1,1,1,1), checkboxWidth, checkboxHeight);
        this.toggled = toggled;
    }
    public UICheckbox(SpriteBatch batch, float x, float y, float checkboxWidth, float checkboxHeight, boolean toggled) {
        this(batch, x, y, checkboxWidth, checkboxHeight, toggled, false);
    }
    public UICheckbox(SpriteBatch batch, float x, float y, float checkboxWidth, float checkboxHeight) {
        this(batch, x, y, checkboxWidth, checkboxHeight, false);
    }
    public UICheckbox(SpriteBatch batch, float x, float y) {
        this(batch, x, y, defaultCheckboxSizeX, defaultCheckboxSizeY);
    }
}
