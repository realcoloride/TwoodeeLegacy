package org.coloride.twoodee.Utilities;

import com.badlogic.gdx.graphics.Color;

public class ColorUtilities {
    public static class Conversion {
        public static Color RGBtoraw(float red, float green, float blue, float alpha) {
            return new Color(red / 255f, green / 255f, blue / 255f, alpha / 255f);
        }
        public static Color rawToRGB(int red, int green, int blue, int alpha) {
            return new Color(red * 255f, green * 255f, blue * 255f, alpha * 255f);
        }
    }
}
