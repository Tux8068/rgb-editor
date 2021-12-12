package tux.rgb.editor.util;

import java.util.Random;

/**
 * @author Tuxiscool
 * @since 11/12/21
 */

public class ColourUtils {

    public static float getred() {
        Random red = new Random();

        return  red.nextFloat(255);
    }

    public static float getgreen() {
        Random green = new Random();

        return green.nextFloat(255);
    }

    public static float getblue() {
        Random blue = new Random();

        return blue.nextFloat(255);
    }

}
