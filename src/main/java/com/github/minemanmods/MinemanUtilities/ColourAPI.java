package com.github.minemanmods.MinemanUtilities;

import com.github.minemanmods.MinemanUtilities.colour.Colour;
import org.bukkit.ChatColor;

public class ColourAPI {

    public static String removeAllFormatting(String string) {
        if (Validate.isValid(string)) {
            string = ChatColor.stripColor(string);
            for (Colour colour : Colour.values()) {
                string = string.replaceAll(colour.getCommandColour(), "");
            }
            return string;
        }
        else {
            return null;
        }
    }

}
