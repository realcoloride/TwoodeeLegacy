package org.coloride.twoodee.UI.Elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class UIImage extends UIElement {
    public SpriteBatch batch;
    private Sprite image;
    public float imageWidth;
    public float imageHeight;
    public Color imageColor;

    public void process() {
        uiBounds.x = uiPositionX;
        uiBounds.y = uiPositionY;
        uiBounds.width = imageWidth * uiScaleX;
        uiBounds.height = imageHeight * uiScaleY;

        image.setColor(imageColor);
    }

    public void draw() {
        if (!hidden)
            batch.draw(image, uiPositionX, uiPositionY, imageWidth * uiScaleX, imageHeight * uiScaleY);

        super.draw();
    }

    public UIImage(SpriteBatch batch, Texture texture, float x, float y, Color imageColor, float imageWidth, float imageHeight) {
        this.batch = batch;
        this.image = new Sprite(texture);
        this.uiPositionX = x;
        this.uiPositionY = y;
        this.imageColor = imageColor;
        this.imageWidth = imageWidth;
        this.imageHeight = imageHeight;
    }
    public UIImage(SpriteBatch batch, FileHandle imagePath, float x, float y, Color imageColor, float imageWidth, float imageHeight) {
        this(batch, new Texture(imagePath), x, y, imageColor, imageWidth, imageHeight);
    }
    public UIImage(SpriteBatch batch, Texture texture, float x, float y, Color imageColor) {
        this(batch, texture, x, y, imageColor, texture.getWidth(), texture.getHeight());
    }
    public UIImage(SpriteBatch batch, FileHandle imagePath, float x, float y, Color imageColor) {
        this(batch, new Texture(imagePath), x, y, imageColor, new Texture(imagePath).getWidth(), new Texture(imagePath).getHeight());
    }

    public UIImage(SpriteBatch batch, FileHandle imagePath, float x, float y) {
        this(batch, imagePath, x, y, new Color(1,1,1,1));
    }
    public UIImage(SpriteBatch batch, Texture texture, float x, float y) {
        this(batch, texture, x, y, new Color(1,1,1,1));
    }

    public UIImage(SpriteBatch batch, float x, float y) {
        this(batch,new Texture(Gdx.files.internal("img/ui/other/ui_image_fallback.png")),x,y);
    }

    public Texture getTexture() {
        return this.image.getTexture();
    }
    public Sprite getSprite() {
        return this.image;
    }
    // todo: set image with a forced width/height
    public void setImage(Texture texture, float imageWidth, float imageHeight) {
        this.imageWidth = imageWidth;
        this.imageHeight = imageHeight;
        image.set(new Sprite(texture));
    }
    public void setImage(Texture texture) {
        this.setImage(texture, imageWidth, imageHeight);
    }
}
