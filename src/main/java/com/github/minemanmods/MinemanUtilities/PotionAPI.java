package com.github.minemanmods.MinemanUtilities;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

public class PotionAPI {

    /**
     * Checks whether a potion is valid.
     * Returns false if the item is not valid.
     * Returns false if the item is not a potion.
     * Returns false if the item has no meta.
     * Returns false if the meta is not potion meta.
     * */
    @SuppressWarnings("deprecation")
    public static boolean isValidPotion(final ItemStack item) {
        if (!ItemAPI.isValidItem(item)) {
            return false;
        }
        if (item.getType().getId() != Material.POTION.getId()) {
            return false;
        }
        ItemMeta meta = item.getItemMeta();
        if (meta == null) {
            return false;
        }
        if (!(meta instanceof PotionMeta)) {
            return false;
        }
        return true;
    }

    /**
     * Gets the meta of an item.
     * Returns null if the item is not a valid potion.
     * */
    public static PotionMeta getPotionMeta(final ItemStack item) {
        if (isValidPotion(item)) {
            return getPotionMeta(item.getItemMeta());
        }
        return null;
    }

    /**
     * Casts an item meta to a potion meta, if possible
     * Returns null if the meta is not an instance of potion meta.
     * */
    public static PotionMeta getPotionMeta(final ItemMeta meta) {
        if (meta instanceof PotionMeta) {
            return (PotionMeta) meta;
        }
        return null;
    }

    /**
     * Returns a potion type based on a given potion type slug. (WATER, INSTANT_HEAL)
     * Returns null if the slug is null or empty.
     * Returns null if no match is found.
     * */
    public static PotionType getPotionType(final String slug) {
        if (slug == null || slug.isEmpty()) {
            return null;
        }
        for (PotionType type : PotionType.values()) {
            if (slug.equalsIgnoreCase(type.name())) {
                return type;
            }
        }
        return null;
    }

    /**
     * Gets the name of a vanilla potion.
     * Returns null is the item is null.
     * Returns null is the item is not a valid potion.
     * Returns null if the potion has no base data.
     * Returns null if no match has been found.
     * */
    public static String getPotionName(final ItemStack item) {
        if (isValidPotion(item)) {
            PotionMeta meta = getPotionMeta(item);
            if (meta != null) {
                return getPotionName(meta.getBasePotionData());
            }
        }
        return null;
    }

    /**
     * Gets the name of a vanilla potion.
     * Returns null if data is null.
     * Returns null if no match is found
     * */
    public static String getPotionName(final PotionData data) {
        return data == null ? null : getPotionName(data.getType(), data.isUpgraded());
    }

    /**
     * Gets the name of a vanilla potion.
     * Returns default unknown potion name if type doesn't match.
     * */
    public static String getPotionName(final PotionType type, final boolean upgraded) {
        switch (type) {
            case UNCRAFTABLE:
                return "Uncraftable Potion";
            case WATER:
                return "Water Bottle";
            case MUNDANE:
                return "Mundane Potion";
            case THICK:
                return "Thick Potion";
            case AWKWARD:
                return "Awkward Potion";
            case NIGHT_VISION:
                return "Potion of Night Vision";
            case INVISIBILITY:
                return "Potion of Invisibility";
            case JUMP:
                return upgraded ? "Potion of Leaping II" : "Potion of Leaping";
            case FIRE_RESISTANCE:
                return "Potion of Fire Resistance";
            case SPEED:
                return upgraded ? "Potion of Swiftness II" : "Potion of Swiftness";
            case SLOWNESS:
                return "Potion of Slowness";
            case WATER_BREATHING:
                return "Potion of Water Breathing";
            case INSTANT_HEAL:
                return upgraded ? "Potion of Healing II" : "Potion of Healing";
            case INSTANT_DAMAGE:
                return upgraded ? "Potion of Harming II" : "Potion of Harming";
            case POISON:
                return upgraded ? "Potion of Poison II" : "Potion of Poison";
            case REGEN:
                return upgraded ? "Potion of Regeneration II" : "Potion of Regeneration";
            case STRENGTH:
                return upgraded ? "Potion of Strength II" : "Potion of Strength";
            case WEAKNESS:
                return "Potion of Weakness";
            case LUCK:
                return "Potion of Luck";
            default:
                return null;
        }
    }

    /**
     * Gets the base effect of a potion.
     * Returns null if item is not a valid potion.
     * */
    public static PotionData getBasePotion(final ItemStack item) {
        PotionMeta meta = getPotionMeta(item);
        if (meta != null) {
            return meta.getBasePotionData();
        }
        return null;
    }

    /**
     * Sets the base effect of a potion.
     * Does nothing if the item is not a valid potion.
     * Does nothing if the data is null.
     * */
    public static boolean setBasePotion(final ItemStack item, final PotionData data) {
        if (data != null) {
            PotionMeta meta = getPotionMeta(item);
            if (meta != null) {
                meta.setBasePotionData(data);
                item.setItemMeta(meta);
                return true;
            }
        }
        return false;
    }

    /**
     * Gets the custom potion effects from an item.
     * Returns an empty array if the item is not a valid potion.
     * */
    public static PotionEffect[] getCustomEffects(final ItemStack item) {
        PotionMeta meta = getPotionMeta(item);
        if (meta != null && meta.hasCustomEffects()) {
            return meta.getCustomEffects().toArray(new PotionEffect[0]);
        }
        return new PotionEffect[0];
    }

    /**
     * Adds a custom effect to a potion, overiding if necessary.
     * Returns false if the item is not a valid potion.
     * Returns false if the effect is null.
     * Returns false if the addition failed.
     * */
    public static boolean addCustomEffect(final ItemStack item, final PotionEffect effect) {
        if (effect != null) {
            PotionMeta meta = getPotionMeta(item);
            if (meta != null) {
                return meta.addCustomEffect(effect, true);
            }
        }
        return false;
    }

    /**
     * Removes a custom effect type from a potion.
     * Returns false if the item is not a valid potion.
     * Returns false if the type is null.
     * Returns false if the removal failed.
     * */
    public static boolean removeCustomEffect(final ItemStack item, final PotionEffectType type) {
        if (type != null) {
            PotionMeta meta = getPotionMeta(item);
            if (meta != null && meta.hasCustomEffects()) {
                return meta.removeCustomEffect(type);
            }
        }
        return false;
    }

    /**
     * Clears a potion of all custom effects.
     * Returns false if the item is not a valid potion.
     * Returns false if the clearing failed.
     * */
    public static boolean clearCustomEffects(final ItemStack item) {
        PotionMeta meta = getPotionMeta(item);
        if (meta != null && meta.hasCustomEffects()) {
            return meta.clearCustomEffects();
        }
        return false;
    }

    /**
     * Gets the colour of a potion.
     * Returns null if the item is not a valid potion.
     * */
    public static Color getColour(final ItemStack item) {
        PotionMeta meta = getPotionMeta(item);
        if (meta != null) {
            return meta.getColor();
        }
        return null;
    }

    /**
     * Sets the colour of a potion.
     * Returns false if the item is not a valid potion.
     * */
    public static boolean setColour(final ItemStack item, final Color colour) {
        PotionMeta meta = getPotionMeta(item);
        if (meta != null) {
            meta.setColor(colour);
            return true;
        }
        return false;
    }

}
