package com.github.minemanmods.MinemanUtilities;

import com.github.minemanmods.MinemanUtilities.exceptions.FailedTransactionException;
import com.github.minemanmods.MinemanUtilities.exceptions.NotEnoughSpaceException;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.Map;

public class InventoryAPI {

    /**
     * Checks whether an inventory is valid.
     * Returns false if the inventory is null.
     * Returns false if the inventory has no slots.
     * */
    public static boolean isValidInventory(final Inventory inventory) {
        return inventory != null && inventory.getSize() > 0;
    }

    /**
     * Duplicates an inventory in array form via Inventory.getContents()
     * Items that do not fulfill the requirements of isValidItem() are replaced with null.
     * */
    public static ItemStack[] duplicateInventory(final ItemStack[] original) {
        if (original == null) {
            return new ItemStack[0];
        }
        else {
            ItemStack[] duplicate = new ItemStack[original.length];
            for (int i = 0; i < original.length; i++) {
                if (ItemAPI.isValidItem(original[i])) {
                    duplicate[i] = original[i].clone();
                }
            }
            return duplicate;
        }
    }

    /**
     * Swaps the contents of two inventories.
     * Throws if either inventory is null.
     * Throws if either inventory has no space for the other inventory's items.
     * Items that do not fulfill the requirements of isValidItem() are replaced with null.
     * */
    public static void swapInventoryContents(final Inventory inventory1, final Inventory inventory2) throws NullPointerException, NotEnoughSpaceException {
        if (inventory1 == null) {
            throw new NullPointerException("Cannot swap contents, inventory1 is null.");
        }
        if (inventory2 == null) {
            throw new NullPointerException("Cannot swap contents, inventory2 is null.");
        }
        ItemStack[] contents1 = Arrays.stream(inventory1.getContents()).filter(ItemAPI::isValidItem).toArray(ItemStack[]::new);
        ItemStack[] contents2 = Arrays.stream(inventory2.getContents()).filter(ItemAPI::isValidItem).toArray(ItemStack[]::new);
        if (contents1.length > inventory2.getSize()) {
            throw new NotEnoughSpaceException("Cannot swap contents, inventory2 doesn't have enough space for inventory1's items.");
        }
        if (contents2.length > inventory1.getSize()) {
            throw new NotEnoughSpaceException("Cannot swap contents, inventory1 doesn't have enough space for inventory2's items.");
        }
        inventory1.clear();
        inventory2.clear();
        inventory1.setContents(contents2);
        inventory2.setContents(contents1);
    }

    /**
     * Checks whether an inventory has the required amount of a specific item.
     * The amount on the given item stack is ignored, please use the amount parameter.
     * Returns false if the inventory not valid.
     * Returns false if the item is not valid.
     * Returns false if the amount is zero or below.
     * */
    @SuppressWarnings("deprecation")
    public static boolean hasRequiredItem(final Inventory inventory, final ItemStack item, final int amount) {
        if (!isValidInventory(inventory)) {
            return false;
        }
        if (item == null) {
            return false;
        }
        if (item.getTypeId() < 1) {
            return false;
        }
        if (item.getDurability() < 0) {
            return false;
        }
        if (amount < 1) {
            return false;
        }
        int count = 0;
        for (ItemStack currentItem : inventory.getContents()) {
            if (ItemAPI.isSimilarItem(currentItem, item)) {
                count += currentItem.getAmount();
                if (count >= amount) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Safely removes an item stack from an inventory.
     * It is recommended that this function be called from a synchronous scheduler.
     * The inventory will be restored to its previous state if an error occurs.
     * Throws if the inventory is not valid.
     * Throws if the item is not valid.
     * Throws if the inventory does not have the required amount of the item.
     * Throws if was unable to remove the required amount of the item.
     * */
    public static void removeItemFromInventory(final Inventory inventory, final ItemStack item) throws NullPointerException, FailedTransactionException {
        if (!isValidInventory(inventory)) {
            throw new NullPointerException("Cannot remove item from an invalid inventory.");
        }
        if (!ItemAPI.isValidItem(item)) {
            throw new NullPointerException("Cannot remove an invalid item from an inventory.");
        }
        if (!hasRequiredItem(inventory, item, item.getAmount())) {
            throw new FailedTransactionException("That inventory does not have the amount of times to remove.");
        }
        ItemStack[] savedInventory = duplicateInventory(inventory.getContents());
        try {
            int remaining = item.getAmount();
            for (int i = 0; i < inventory.getSize(); i++) {
                ItemStack currentItem = inventory.getItem(i);
                if (ItemAPI.isSimilarItem(currentItem, item)) {
                    int currentItemAmount = currentItem.getAmount();
                    // If this item can satisfy the remaining amount with some left over
                    // then lower the amount of that item and break out of the loop
                    if (currentItemAmount > remaining) {
                        currentItem.setAmount(currentItemAmount - remaining);
                        remaining = 0;
                        break;
                    }
                    // If this item can satisfy the remaining amount exactly
                    // then remove the item from the inventory and break out of the loop
                    else if (currentItemAmount == remaining) {
                        inventory.setItem(i, null);
                        remaining = 0;
                        break;
                    }
                    // Otherwise this item still leaves some remaining, so
                    // remove the item from the inventory and reduce the remaining
                    else {
                        inventory.setItem(i, null);
                        remaining -= currentItemAmount;
                    }
                }
            }
            if (remaining != 0) {
                throw new FailedTransactionException("Was not able to remove the exact amount of that item!");
            }
        }
        catch (FailedTransactionException exception) {
            // Restore the inventory to its previous state
            inventory.setContents(savedInventory);
            throw exception;
        }
    }

    /**
     * Safely adds an item to an inventory.
     * It is recommended that this function be called from a synchronous scheduler.
     * The inventory will be restored to its previous state if an error occurs.
     * Throws if the inventory is not valid.
     * Throws if the item is not valid.
     * Throws if there isn't enough room to add the item in its entirety.
     * */
    public static void addItemToInventory(final Inventory inventory, final ItemStack item) throws FailedTransactionException {
        if (!isValidInventory(inventory)) {
            throw new NullPointerException("Cannot remove item from an invalid inventory.");
        }
        if (!ItemAPI.isValidItem(item)) {
            throw new NullPointerException("Cannot remove an invalid item from an inventory.");
        }
        ItemStack[] savedInventory = duplicateInventory(inventory.getContents());
        Map<Integer, ItemStack> remaining = inventory.addItem(item);
        if (!remaining.isEmpty()) {
            // Restore the inventory to its previous state
            inventory.setContents(savedInventory);
            throw new FailedTransactionException("Was unable to put that item in that inventory.");
        }
    }

    /**
     * Performs a transaction between two inventories.
     * It is recommended that this function be called from a synchronous scheduler.
     * Both inventories will be restored to their previous states if an error occurs.
     * Items in item1 will be moved from inventory1 to inventory2.
     * Items in item2 will be moved from inventory2 to inventory1.
     * Throws if either inventory is invalid.
     * Throws if either items array is null.
     * Invalid items are ignored.
     * */
    public static void inventoryTransaction(final Inventory inventory1, final ItemStack[] items1, final Inventory inventory2, final ItemStack[] items2) throws NullPointerException, FailedTransactionException {
        if (!isValidInventory(inventory1)) {
            throw new NullPointerException("Cannot perform transaction, inventory1 is invalid.");
        }
        if (items1 == null) {
            throw new NullPointerException("Cannot perform transaction, items1 is null.");
        }
        if (!isValidInventory(inventory2)) {
            throw new NullPointerException("Cannot perform transaction, inventory2 is invalid.");
        }
        if (items2 == null) {
            throw new NullPointerException("Cannot perform transaction, items2 is null.");
        }
        if (!ItemAPI.isValidItemSet(items1)) {
            throw new NullPointerException("Cannot perform transaction, items1 contains an invalid item!");
        }
        if (!ItemAPI.isValidItemSet(items2)) {
            throw new NullPointerException("Cannot perform transaction, items2 contains an invalid item!");
        }
        ItemStack[] savedInventory1 = duplicateInventory(inventory1.getContents());
        ItemStack[] savedInventory2 = duplicateInventory(inventory2.getContents());
        try {
            // Remove items1 from inventory1
            for (ItemStack item : items1) {
                removeItemFromInventory(inventory1, item);
            }
            // Remove items2 from inventory2
            for (ItemStack item : items2) {
                removeItemFromInventory(inventory2, item);
            }
            // Add items2 to inventory1
            for (ItemStack item : items2) {
                addItemToInventory(inventory1, item);
            }
            // Add items1 to inventory2
            for (ItemStack item : items1) {
                addItemToInventory(inventory2, item);
            }
        }
        catch (FailedTransactionException exception) {
            // Restore the inventory to its previous state
            inventory1.setContents(savedInventory1);
            inventory2.setContents(savedInventory2);
            throw exception;
        }
    }

}
