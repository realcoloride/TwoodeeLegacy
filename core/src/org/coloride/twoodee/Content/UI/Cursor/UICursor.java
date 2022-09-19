package org.coloride.twoodee.Content.UI.Cursor;

import com.badlogic.gdx.Gdx;
import org.coloride.twoodee.Content.UI.Cursor.CursorTypes.FuturisticCursor;

public class UICursor {
    public CursorType cursorType;

    public static void create() {
        changeCursor(new FuturisticCursor());
    }

    public static void changeCursor(CursorType cursorType) {
        Gdx.graphics.setCursor(cursorType.getCursor());
    }
}
