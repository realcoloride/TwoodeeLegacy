package org.coloride.twoodee.Utilities;

public class UIUtilities {
    public static class Text {
        public static float pointToPixel(float pointSize) {
            return pointSize*4f/3f;
        }
        public static float pixelToPoint(float pixelSize) {
            return pixelSize*3f/4f;
        }
    }
}
