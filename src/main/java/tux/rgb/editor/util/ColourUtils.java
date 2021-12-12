package tux.rgb.editor.util;

import java.util.Random;

/**
 * @author Tuxiscool
 * @since 11/12/21
 */

public class ColourUtils {

    public static float getRed() {
        Random red = new Random();

        return red.nextFloat() * 255;
    }

    public static float getGreen() {
        Random green = new Random();

        return green.nextFloat() * 255;
    }

    public static float getBlue() {
        Random blue = new Random();

        return blue.nextFloat() * 255;
    }

}
