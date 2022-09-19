package org.coloride.twoodee.UI.Elements.Buttons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.coloride.twoodee.UI.Elements.Enums.ButtonToggleType;
import org.coloride.twoodee.UI.Elements.UIImage;

public class UICheckbox extends UIBaseButton {
    private static Texture untickedTexture = new Texture(Gdx.files.internal("img/ui/checkbox/unticked.png"));
    private static Texture tickedTexture = new Texture(Gdx.files.internal("img/ui/checkbox/ticked.png"));
    private static Texture disableTexture = new Texture(Gdx.files.internal("img/ui/checkbox/disabled.png"));
    private static float defaultCheckboxSizeX = 32f;
    private static float defaultCheckboxSizeY = 32f;

    public UIImage checkboxImage;

    public void process() {
        buttonBounds.width = checkboxImage.imageWidth * uiScaleX;
        buttonBounds.height = checkboxImage.imageHeight * uiScaleY;
        buttonBounds.x = checkboxImage.uiPositionX;
        buttonBounds.y = checkboxImage.uiPositionY;

        if (buttonToggleType == ButtonToggleType.TOGGLEABLE) {
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
        checkboxImage.process();
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
