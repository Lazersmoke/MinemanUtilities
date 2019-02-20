package com.github.minemanmods.MinemanUtilities;

import com.github.minemanmods.MinemanUtilities.interfaces.Serialisable;
import com.github.minemanmods.MinemanUtilities.interfaces.Validation;
import com.github.minemanmods.MinemanUtilities.nbt.NBTCompound;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class NBTAPI {

    /**
     * Gets a copy of the NBT data on an item.
     * * Note that changes to the returned item will NOT be reflected, you must set it manually.
     * */
    public static NBTCompound getNBT(ItemStack item, String key) {
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
     * * Note that NBT will not be applied to the passed item, only to the returned item.
     * */
    public static ItemStack setNBT(ItemStack item, String key, NBTCompound nbt) {
        if (item != null && Validate.isValid(key) && nbt != null) {
            net.minecraft.server.v1_12_R1.ItemStack copy = CraftItemStack.asNMSCopy(item);
            NBTCompound raw = new NBTCompound(copy.getTag());
            raw.setCompound(key, nbt);
            copy.setTag(raw.getRAW());
            return CraftItemStack.asBukkitCopy(copy);
        }
        return null;
    }

    /**
     * Maps a list of serialisable items into a list of compounds by going through and serialising each item.
     * Returns an empty list if the given list is null or empty;
     * */
    public static <T extends Serialisable<NBTCompound> & Validation> List<NBTCompound> serialiseList(List<T> list) {
        if (Validate.isValid(list)) {
            List<NBTCompound> compounds = new ArrayList<>();
            for (T item : list) {
                if (Validate.isValid(item)) {
                    compounds.add(item.serialise(new NBTCompound()));
                }
            }
            return compounds;
        }
        else {
            return new ArrayList<>();
        }
    }

    /**
     * Maps a list of serialisable items into a list of compounds by going through and serialising each item.
     * @param adder Adds information that the item itself may not necessarily do itself.
     * Returns an empty list if the given list is null or empty;
     * Returns an empty list if the given adder is null.
     * */
    public static <T extends Serialisable<NBTCompound> & Validation> List<NBTCompound> serialiseListPre(List<T> list, BiConsumer<NBTCompound, T> adder) {
        if (adder != null && Validate.isValid(list)) {
            List<NBTCompound> compounds = new ArrayList<>();
            for (T item : list) {
                if (Validate.isValid(item)) {
                    NBTCompound compound = new NBTCompound();
                    adder.accept(compound, item);
                    item.serialise(compound);
                    compounds.add(compound);
                }
            }
            return compounds;
        }
        else {
            return new ArrayList<>();
        }
    }

    /**
     * Deserialises a list of NBTCompounds into a class.
     * @param constructor The class constructor (or other) for the data to be deserialised into.
     * Returns an empty list if the given list is null or empty.
     * Returns an empty list if the given constructor is null.
     * */
    public static <T extends Serialisable<NBTCompound> & Validation> List<T> deserialiseListNew(List<NBTCompound> list, Supplier<T> constructor) {
        if (constructor != null && Validate.isValid(list)) {
            List<T> items = new ArrayList<>();
            for (NBTCompound compound : list) {
                T item = constructor.get();
                if (item != null) {
                    item.deserialise(compound);
                }
                if (Validate.isValid(item)) {
                    items.add(item);
                }
            }
            return items;
        }
        else {
            return new ArrayList<>();
        }
    }


    /**
     * Deserialises a list of NBTCompounds into a class determined by the compound itself.
     * @param determiner The function that supplies a class based on the compound data.
     * Returns an empty list if the given list is null or empty.
     * Returns an empty list if the given determiner is null.
     * */
    public static <T extends Serialisable<NBTCompound> & Validation> List<T> deserialiseListPre(List<NBTCompound> list, Function<NBTCompound, T> determiner) {
        if (determiner != null && Validate.isValid(list)) {
            List<T> items = new ArrayList<>();
            for (NBTCompound compound : list) {
                T item = determiner.apply(compound);
                if (item != null) {
                    item.deserialise(compound);
                }
                if (Validate.isValid(item)) {
                    items.add(item);
                }
            }
            return items;
        }
        else {
            return new ArrayList<>();
        }
    }

    /**
     * Converts a list of compounds to an array
     * */
    public static NBTCompound[] toArray(List<NBTCompound> list) {
        if (Validate.isValid(list)) {
            return list.toArray(new NBTCompound[0]);
        }
        else {
            return new NBTCompound[0];
        }
    }

}
