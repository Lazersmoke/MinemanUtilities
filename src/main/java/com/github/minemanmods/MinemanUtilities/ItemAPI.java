package com.github.minemanmods.MinemanUtilities;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ItemAPI {

    /**
     * Determines whether an item stack is a valid item.
     * Returns false if the item is null.
     * Returns false if the item id is impossible.
     * Returns false if the item durability is impossible.
     * Returns false if the item amount is impossible.
     * */
    @SuppressWarnings("deprecation")
    public static boolean isValidItem(final ItemStack item) {
        return item != null && isValidItem(item.getType(), item.getDurability(), item.getAmount());
    }

    /**
     * Determines whether an item stack is a valid item.
     * Returns false if the item id is impossible.
     * Returns false if the item durability is impossible.
     * Returns false if the item amount is impossible.
     * */
    public static boolean isValidItem(final Material material, final short durability, final int amount) {
        return MaterialAPI.isValidMaterial(material, durability) && amount > 0;
    }

    /**
     * Determines whether all items in a set are valid.
     * Returns false if the set is null or empty.
     * Returns false if any single item is invalid.
     * */
    public static boolean isValidItemSet(final ItemStack[] items) {
        if (items == null || items.length < 1) {
            return false;
        }
        for (ItemStack item : items) {
            if (!isValidItem(item)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks whether an item is identical.
     * Returns false if either item fail an isValidItem() check.
     * Returns false if both item fails the isSameMaterial() check.
     * Returns false if both items do not have the same amount.
     * Returns false if one item has meta and the other does not.
     * Returns false if the item metas do not match.
     * */
    public static boolean isSameItem(final ItemStack item1, final ItemStack item2) {
        return isSimilarItem(item1, item2) && item1.getAmount() == item2.getAmount();
    }

    /**
     * Checks whether an item is identical, except for in amount.
     * Returns false if either item fail an isValidItem() check.
     * Returns false if both item fails the isSameMaterial() check.
     * Returns false if one item has meta and the other does not.
     * Returns false if the item metas do not match.
     * */
    public static boolean isSimilarItem(final ItemStack item1, final ItemStack item2) {
        if (!isValidItem(item1) || !isValidItem(item2)) {
            return false;
        }
        if (!MaterialAPI.isSameMaterial(item1, item2)) {
            return false;
        }
        if (item1 == item2) {
            return true;
        }
        if (item1.hasItemMeta() != item2.hasItemMeta()) {
            return false;
        }
        ItemMeta meta1 = item1.getItemMeta();
        ItemMeta meta2 = item2.getItemMeta();
        return Bukkit.getItemFactory().equals(meta1, meta2);
    }

    /**
     * Duplicates an item.
     * If an item is null, it shall return a blank air item.
     * If an item has an impossible material, material is set to air.
     * If an item has an impossible durability, durability set to zero.
     * If an item has an impossible or no amount, amount set to one.
     * */
    @SuppressWarnings("deprecation")
    public static ItemStack duplicateItem(ItemStack item) {
        if (item == null) {
            return new ItemStack(Material.AIR, 0, (short)0);
        }
        ItemStack clone = item.clone();
        if (item.getTypeId() < 0) {
            clone.setType(Material.AIR);
        }
        if (item.getDurability() < 0) {
            clone.setDurability((short) 0);
        }
        if (item.getAmount() < 1) {
            clone.setAmount(1);
        }
        return clone;
    }

    /**
     * This will divide an item into stacks when given a size.
     * For example, 320 Iron Ingots will become 5 stacks.
     * The amount on the given item stack is ignored, please use the amount parameter.
     * Returns an empty array of item is invalid.
     * Returns an empty array if amount is zero or below.
     * */
    public static ItemStack[] stackifyItem(final ItemStack item, final int amount) {
        // If item or amount is not valid, return an empty array
        if (!isValidItem(item) || amount < 1) {
            return new ItemStack[0];
        }
        // If amount only requires one stack, return that stack
        else if (amount <= item.getMaxStackSize()) {
            ItemStack temp = item.clone();
            temp.setAmount(amount);
            return new ItemStack[] {temp};
        }
        // If amount requires multiple stacks, create them
        else {
            int allStacks = (int) Math.ceil((double) amount / (double) item.getMaxStackSize());
            int fullStacks = (int) Math.floor((double) amount / (double) item.getMaxStackSize());
            int remainder = amount % item.getMaxStackSize();
            ItemStack[] stacks = new ItemStack[allStacks];
            for (int i = 0; i < fullStacks; i++) {
                ItemStack temp = item.clone();
                temp.setAmount(item.getMaxStackSize());
                stacks[i] = temp;
            }
            if (remainder != 0) {
                ItemStack temp = item.clone();
                temp.setAmount(remainder);
                stacks[stacks.length - 1] = temp;
            }
            return stacks;
        }
    }

    /**
     * Gets the display name of an item.
     * Returns null if the item is null.
     * Returns null if the item has no meta.
     * Returns null if the item has no display name.
     * */
    public static String getName(final ItemStack item) {
        if (item != null && item.hasItemMeta()) {
            ItemMeta meta = item.getItemMeta();
            if (meta.hasDisplayName()) {
                return meta.getDisplayName();
            }
        }
        return null;
    }

    /**
     * Sets the display name of an item.
     * Does nothing if the item is null.
     * Does nothing if the name is null or empty.
     * Does nothing if the item has no meta.
     * @return True if the setting was successful.
     * */
    public static boolean setName(final ItemStack item, final String name) {
        if (item != null && item.hasItemMeta() && !Strings.isNullOrEmpty(name)) {
            item.getItemMeta().setDisplayName(name);
            return true;
        }
        return false;
    }

    /**
     * Gets the lore of an item.
     * Returns an empty list if the item is null.
     * Returns an empty list if the item has no meta.
     * Returns an empty list if the item has no lore.
     * */
    public static List<String> getLore(final ItemStack item) {
        if (item != null && item.hasItemMeta()) {
            List<String> lore = item.getItemMeta().getLore();
            if (lore != null) {
                return lore;
            }
        }
        return new ArrayList<>();
    }

    /**
     * Adds additional lore to an item.
     * Does nothing if the item is null.
     * Does nothing if there's no lore.
     * Does nothing if the item has no meta.
     * */
    public static boolean addLore(final ItemStack item, final String... lore) {
        return addLore(item, false, lore);
    }

    /**
     * Adds additional lore to an item.
     * Does nothing if the item is null.
     * Does nothing if there's no lore.
     * Does nothing if the item has no meta.
     * @return True if the setting was successful.
     * */
    public static boolean addLore(final ItemStack item, final boolean prepend, final String... lore) {
        if (item != null && lore != null && item.hasItemMeta()) {
            ItemMeta meta = item.getItemMeta();
            List<String> currentLore = meta.getLore();
            if (currentLore == null) {
                meta.setLore(Arrays.asList(lore));
                item.setItemMeta(meta);
                return true;
            }
            else {
                if (prepend) {
                    for (String line : Lists.reverse(Arrays.asList(lore))) {
                        currentLore.add(0, line);
                    }
                    return true;
                }
                else {
                    currentLore.addAll(Arrays.asList(lore));
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Sets an item's lore, overriding anything that my already be there.
     * Does nothing if the item is null.
     * Does nothing if there's no lore.
     * Does nothing if the item has no meta.
     * @return True if the setting was successful.
     * */
    public static boolean setLore(final ItemStack item, final String... lore) {
        if (item != null && lore != null && item.hasItemMeta()) {
            ItemMeta meta = item.getItemMeta();
            meta.setLore(Arrays.asList(lore));
            item.setItemMeta(meta);
            return true;
        }
        return false;
    }

}
