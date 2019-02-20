package com.github.minemanmods.MinemanUtilities;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;
import java.util.Map;

public class EnchantmentAPI {

    /**
     * Checks whether an enchantment is valid.
     * Returns false if the enchantment is null.
     * */
    public static boolean isValidEnchantment(final Enchantment enchantment) {
        return enchantment != null;
    }

    /**
     * Checks whether an enchantment is considered safe.
     * Returns false if the enchantment is not valid.
     * Returns false if the level is below the starting level.
     * Returns false if the level is above the max level.
     * */
    public static boolean isSafeEnchantment(final Enchantment enchantment, final int level) {
        return isValidEnchantment(enchantment) && level >= enchantment.getStartLevel() && level <= enchantment.getMaxLevel();
    }

    /**
     * Gets the slug of an enchantment.
     * Returns null if the enchantment is null.
     * */
    public static String getEnchantmentSlug(final Enchantment enchantment) {
        return enchantment == null ? null : enchantment.getName();
    }

    /**
     * Gets the name for a vanilla enchantment.
     * Returns null if the enchantment is invalid.
     * Returns null if the enchantment isn't vanilla.
     * */
    public static String getEnchantmentName(final Enchantment enchantment) {
        if (!isValidEnchantment(enchantment)) {
            return null;
        }
        if (Enchantment.ARROW_DAMAGE.equals(enchantment)) {
            return "Power";
        }
        if (Enchantment.ARROW_FIRE.equals(enchantment)) {
            return "Flame";
        }
        if (Enchantment.ARROW_INFINITE.equals(enchantment)) {
            return "Infinite";
        }
        if (Enchantment.ARROW_KNOCKBACK.equals(enchantment)) {
            return "Punch";
        }
        if (Enchantment.BINDING_CURSE.equals(enchantment)) {
            return "Curse of Binding";
        }
        if (Enchantment.DAMAGE_ALL.equals(enchantment)) {
            return "Sharpness";
        }
        if (Enchantment.DAMAGE_ARTHROPODS.equals(enchantment)) {
            return "Bane of Athropods";
        }
        if (Enchantment.DAMAGE_UNDEAD.equals(enchantment)) {
            return "Smite";
        }
        if (Enchantment.DEPTH_STRIDER.equals(enchantment)) {
            return "Depth Strider";
        }
        if (Enchantment.DIG_SPEED.equals(enchantment)) {
            return "Efficiency";
        }
        if (Enchantment.DURABILITY.equals(enchantment)) {
            return "Unbreaking";
        }
        if (Enchantment.FIRE_ASPECT.equals(enchantment)) {
            return "Fire Aspect";
        }
        if (Enchantment.FROST_WALKER.equals(enchantment)) {
            return "Frost Walker";
        }
        if (Enchantment.KNOCKBACK.equals(enchantment)) {
            return "Knockback";
        }
        if (Enchantment.LOOT_BONUS_BLOCKS.equals(enchantment)) {
            return "Fortune";
        }
        if (Enchantment.LOOT_BONUS_MOBS.equals(enchantment)) {
            return "Looting";
        }
        if (Enchantment.LUCK.equals(enchantment)) {
            return "Luck of the Sea";
        }
        if (Enchantment.LURE.equals(enchantment)) {
            return "Lure";
        }
        if (Enchantment.MENDING.equals(enchantment)) {
            return "Mending";
        }
        if (Enchantment.OXYGEN.equals(enchantment)) {
            return "Respiration";
        }
        if (Enchantment.PROTECTION_ENVIRONMENTAL.equals(enchantment)) {
            return "Protection";
        }
        if (Enchantment.PROTECTION_EXPLOSIONS.equals(enchantment)) {
            return "Blast Protection";
        }
        if (Enchantment.PROTECTION_FALL.equals(enchantment)) {
            return "Feather Falling";
        }
        if (Enchantment.PROTECTION_FIRE.equals(enchantment)) {
            return "Fire Protection";
        }
        if (Enchantment.PROTECTION_PROJECTILE.equals(enchantment)) {
            return "Projectile Protection";
        }
        if (Enchantment.SILK_TOUCH.equals(enchantment)) {
            return "Silk Touch";
        }
        if (Enchantment.THORNS.equals(enchantment)) {
            return "Thorns";
        }
        if (Enchantment.VANISHING_CURSE.equals(enchantment)) {
            return "Curse of Vanishing";
        }
        if (Enchantment.WATER_WORKER.equals(enchantment)) {
            return "Aqua Affinity";
        }
        return null;
    }

    /**
     * Gets the enchantments on an item.
     * Returns an empty array if the item has no meta.
     * */
    public static Map<Enchantment, Integer> getEnchantments(final ItemStack item) {
        if (item != null && item.hasItemMeta()) {
            ItemMeta meta = item.getItemMeta();
            if (meta.hasEnchants()) {
                return meta.getEnchants();
            }
        }
        return new HashMap<>();
    }

    /**
     * Adds a single enchantment (unsafely) to an item.
     * Does nothing if the item is null.
     * Does nothing if the enchantment is null.
     * Does nothing if the enchantment level is less than 1.
     * Does nothing if the item does not have any meta.
     * Does nothing if adding the enchantment failed.
     * */
    public static boolean addEnchantment(final ItemStack item, final Enchantment enchantment, final int level) {
        ItemMeta meta = ItemAPI.getItemMeta(item);
        if (item != null && enchantment != null && level > 0 && meta != null) {
            if (!meta.addEnchant(enchantment, level, true)) {
                return false;
            }
            item.setItemMeta(meta);
            return true;
        }
        return false;
    }

    /**
     * Removes a single enchantment from an item.
     * Does nothing if the item is null.
     * Does nothing if the enchantment is null.
     * Does nothing if the item does not have any meta.
     * Does nothing if removing the enchantment failed.
     * */
    public static boolean removeEnchantment(final ItemStack item, final Enchantment enchantment) {
        if (item != null && enchantment != null && item.hasItemMeta()) {
            ItemMeta meta = item.getItemMeta();
            if (!meta.removeEnchant(enchantment)) {
                return false;
            }
            item.setItemMeta(meta);
            return true;
        }
        return false;
    }

    /**
     * Clears the item of all enchantments.
     * Does nothing if the item is null.
     * Does nothing if the item does not have any meta.
     * Does nothing if any of the removals fail.
     * */
    public static boolean clearEnchantments(final ItemStack item) {
        if (item != null && item.hasItemMeta()) {
            ItemMeta meta = item.getItemMeta();
            if (meta.hasEnchants()) {
                for (Enchantment enchant : meta.getEnchants().keySet()) {
                    if (!meta.removeEnchant(enchant)) {
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }

}
