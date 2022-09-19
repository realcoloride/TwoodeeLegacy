package org.coloride.twoodee.Content.UI.Cursor;

import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.Pixmap;

public class CursorType {
    private String cursorName;
    private Pixmap pixmap;
    private int xHotspot = 0;
    private int yHotspot = 0;
    private Cursor cursor;

    public String getCursorName() {
        return cursorName;
    }

    public Pixmap getPixmap() {
        return pixmap;
    }

    public int getHotspotX() {
        return xHotspot;
    }

    public int getHotspotY() {
        return yHotspot;
    }

    public Cursor getCursor() {
        return cursor;
    }
}
