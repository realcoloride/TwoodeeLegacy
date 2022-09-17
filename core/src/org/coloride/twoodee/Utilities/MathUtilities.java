package org.coloride.twoodee.Utilities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;

public class MathUtilities {
    public static class Interpolation {
        public static float linearInterpolate(float from, float to, float weightSpeed, float delta) {
            return from + (to - from) * weightSpeed * delta;
        }
    }
    public static class Conversion {
        public static BoundingBox boundingBox2dTo3d(float x, float y, float width, float height) {
            return new BoundingBox(new Vector3(x,y,0), new Vector3(width, height,0));
        }
        public static Vector3 vector2dTo3d(Vector2 vector2) {
            return new Vector3(vector2.x, vector2.y, 0);
        }
    }
    public static class Vectors {
        public static Vector2 addTwoVectors(Vector2 vector2, Vector2 otherVector2) {
            return new Vector2(vector2.x + otherVector2.x, vector2.y + otherVector2.y);
        }
        public static Vector2 addToVector(Vector2 vector2, float x, float y) {
            return new Vector2(vector2.x + x, vector2.y + y);
        }
    }
}
