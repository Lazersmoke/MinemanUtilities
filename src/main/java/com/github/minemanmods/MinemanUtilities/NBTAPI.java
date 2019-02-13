package com.github.minemanmods.MinemanUtilities;

import com.github.minemanmods.MinemanUtilities.nbt.NBTCompound;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

public class NBTAPI {

    /**
     * Gets a copy of the NBT data on an item.
     * * Note that changes to the returned item will NOT be reflected, you must set it manually.
     * */
    public NBTCompound getNBT(ItemStack item, String key) {
        if (item != null && key != null && !key.isEmpty()) {
            net.minecraft.server.v1_12_R1.ItemStack copy = CraftItemStack.asNMSCopy(item);
            NBTCompound raw = new NBTCompound(copy.getTag());
            return raw.getCompound(key);
        }
        return new NBTCompound();
    }

    /**
     * Applies an NBT Compound to an item.
     * Returns null if the item is null.
     * Returns null if the key is null or empty.
     * Returns null if the nbt compound is null.
     * * Note that NBT will not be applied to the passed item, only the returned item.
     * */
    public ItemStack setNBT(ItemStack item, String key, NBTCompound nbt) {
        if (item != null && key != null && !key.isEmpty() && nbt != null) {
            net.minecraft.server.v1_12_R1.ItemStack copy = CraftItemStack.asNMSCopy(item);
            NBTCompound raw = new NBTCompound(copy.getTag());
            raw.setCompound(key, nbt);
            copy.setTag(raw.getRAW());
            return CraftItemStack.asBukkitCopy(copy);
        }
        return null;
    }

}
