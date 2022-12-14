package org.coloride.twoodee.Rendering;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import org.coloride.twoodee.World.WorldRenderer;

public class CameraRenderer {
    enum CameraType {
        STATIC,
        DRAGGABLE,
        FOLLOW_STATIC,
        FOLLOW_INTERPOLATED
    };

    public static CameraType currentCameraType = CameraType.DRAGGABLE;
    public static Vector2 cameraPosition = new Vector2();

    public static void draw() {
        switch (currentCameraType) {
            case STATIC:
                Camera.camera.update();
            case DRAGGABLE:
                if (Gdx.input.isTouched()) {
                    Camera.camera.translate(-Gdx.input.getDeltaX()*Camera.cameraZoomFactor, Gdx.input.getDeltaY()*Camera.cameraZoomFactor);
                }
            case FOLLOW_STATIC:
                Camera.camera.translate(cameraPosition);
            //break;
        }
    }
}
