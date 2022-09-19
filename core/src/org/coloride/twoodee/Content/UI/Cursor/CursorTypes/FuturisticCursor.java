package org.coloride.twoodee.Content.UI.Cursor.CursorTypes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.Pixmap;
import org.coloride.twoodee.Content.UI.Cursor.CursorType;

public class FuturisticCursor extends CursorType {
    private String cursorName = "Futuristic";
    private Pixmap pixmap = new Pixmap(Gdx.files.internal("img/ui/cursors/futuristic.png"));
    private int xHotspot = 0;
    private int yHotspot = 0;
    private Cursor cursor = Gdx.graphics.newCursor(pixmap, xHotspot, yHotspot);

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
