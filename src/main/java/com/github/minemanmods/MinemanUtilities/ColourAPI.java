package com.github.minemanmods.MinemanUtilities;

import org.bukkit.Color;

public class ColourAPI {

    public static int colourToRGB(Color colour) {
        return colour == null ? -1 : colour.asRGB();
    }

    public static Color rbgToColour(int rgb) {
        if (rgb < 0) {
            return null;
        }
        else {
            try {
                return Color.fromRGB(rgb);
            }
            catch (IllegalArgumentException exception) {
                return null;
            }
        }
    }

}
