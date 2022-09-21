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

    public static void setCameraType(CameraRenderer.CameraType cameraType) {
        CameraRenderer.currentCameraType = cameraType;
    }

    public static Vector2 getCursorPositionInSpace() {
        Vector3 unprojectedCoordinates = Camera.camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
        return new Vector2(unprojectedCoordinates.x, unprojectedCoordinates.y);
    }

    public static void create() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1280, 720);
        camera.update();
    }

    public static void draw() {
        cameraZoomFactor = MathUtils.clamp(cameraZoomFactor,
                cameraMinZoomFactor, cameraMaxZoomFactor
        );

        camera.zoom = cameraZoomFactor;

        CameraRenderer.draw();
        camera.update();
    }
}
