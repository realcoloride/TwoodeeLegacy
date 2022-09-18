package org.coloride.twoodee.Rendering;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;
import com.badlogic.gdx.utils.viewport.*;
import org.coloride.twoodee.Utilities.MathUtilities;

public class Camera {
    public static float cameraZoomFactor = .8f;
    public static float cameraMinZoomFactor = .2f;
    public static float cameraMaxZoomFactor = .8f;

    public static OrthographicCamera camera;
    public static Viewport viewport;

    public static float viewportRenderDistance = 0.1f;

    public static void setCameraType(CameraRenderer.CameraType cameraType) {
        CameraRenderer.currentCameraType = cameraType;
    }

    public static Vector2 getCursorPositionInSpace() {
        return new Vector2(
            Camera.camera.position.x - Camera.camera.viewportWidth/2 + Gdx.input.getX(),
            Camera.camera.position.y + Camera.camera.viewportHeight/2 - Gdx.input.getY()
        );
    }

    public static void create() {
        camera = new OrthographicCamera();

        camera.setToOrtho(false, 1280, 720);
        camera.update();
    }

    public static void draw() {
        camera.zoom = MathUtils.clamp(cameraZoomFactor,
                cameraMinZoomFactor, cameraMaxZoomFactor
        );

        CameraRenderer.draw();
        camera.update();
    }
}
