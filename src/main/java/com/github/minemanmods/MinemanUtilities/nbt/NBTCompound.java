package com.github.minemanmods.MinemanUtilities.nbt;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Logger;

import net.minecraft.server.v1_12_R1.NBTBase;
import net.minecraft.server.v1_12_R1.NBTTagByte;
import net.minecraft.server.v1_12_R1.NBTTagByteArray;
import net.minecraft.server.v1_12_R1.NBTTagCompound;
import net.minecraft.server.v1_12_R1.NBTTagDouble;
import net.minecraft.server.v1_12_R1.NBTTagFloat;
import net.minecraft.server.v1_12_R1.NBTTagInt;
import net.minecraft.server.v1_12_R1.NBTTagIntArray;
import net.minecraft.server.v1_12_R1.NBTTagList;
import net.minecraft.server.v1_12_R1.NBTTagLong;
import net.minecraft.server.v1_12_R1.NBTTagShort;
import net.minecraft.server.v1_12_R1.NBTTagString;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

public class NBTCompound {

    private static final Logger log = Bukkit.getLogger();

    private NBTTagCompound tag;

    public NBTCompound() {
        this.tag = new NBTTagCompound();
    }

    public NBTCompound(ItemStack item) {
        this(CraftItemStack.asNMSCopy(item).getTag());
    }

    public NBTCompound(NBTTagCompound tag) {
        this.tag = tag == null ? new NBTTagCompound() : tag;
    }

    /**
     * Gets a boolean value.
     * Returns false if the value does not exist on the tag.
     * Returns false if the value on the tag is not a boolean.
     * * Booleans are saved as a byte, either: 0x0 or 0x1
     * */
    public boolean getBoolean(String key) {
        return this.tag.getBoolean(key);
    }

    /**
     * Sets a boolean value to a key.
     * */
    public void setBoolean(String key, boolean value) {
        this.tag.setBoolean(key, value);
    }

    /**
     * Gets a boolean array.
     * Returns an empty array if the value does not exist on the tag.
     * Returns an empty array if the value on the tag is not a boolean array.
     * */
    public boolean[] getBooleanArray(String key) {
        byte[] cache = this.tag.getByteArray(key);
        boolean[] result = new boolean[cache.length];
        for (int i = 0; i < cache.length; i++) {
            result[i] = cache[i] != 0;
        }
        return result;
    }

    /**
     * Sets a boolean array to a key.
     * */
    public void setBooleanArray(String key, boolean[] values) {
        if (values != null) {
            byte[] cache = new byte[values.length];
            for (int i = 0; i < values.length; i++) {
                cache[i] = (byte) (values[i] ? 0x1 : 0x0);
            }
            this.tag.setByteArray(key, cache);
        }
    }

    /**
     * Gets a byte value.
     * Returns 0x00 if the value does not exist on the tag.
     * Returns 0x00 if the value on the tag is not a Byte.
     * */
    public byte getByte(String key) {
        return this.tag.getByte(key);
    }

    /**
     * Sets a byte to a key.
     * */
    public void setByte(String key, byte value) {
        this.tag.setByte(key, value);
    }

    /**
     * Gets a byte array.
     * Returns an empty array if the value does not exist on the tag.
     * Returns an empty array if the value on the tag is not a byte array.
     * */
    public byte[] getByteArray(String key) {
        return this.tag.getByteArray(key);
    }

    /**
     * Sets a byte array to a key.
     * */
    public void setByteArray(String key, byte[] values) {
        this.tag.setByteArray(key, values);
    }

    /**
     * Gets a short value.
     * Returns zero if the value does not exist on the tag.
     * Returns zero if the value on the tag is not a Short.
     * */
    public short getShort(String key) {
        return this.tag.getShort(key);
    }

    /**
     * Sets a short to a key.
     * */
    public void setShort(String key, short value) {
        this.tag.setShort(key, value);
    }

    /**
     * Gets a short array.
     * Returns an empty array if the value does not exist on the tag.
     * Returns an empty array if the value on the tag is not a short array.
     * */
    public short[] getShortArray(String key) {
        NBTTagList list = this.tag.getList(key, 2);
        short[] result = new short[list.size()];
        for (int i = 0; i < result.length; i++) {
            NBTBase base = list.i(i);
            if (base.getTypeId() != 2) {
                result[i] = 0;
            }
            else if (!(base instanceof NBTTagShort)) {
                result[i] = 0;
            }
            else {
                result[i] = ((NBTTagShort) base).f();
            }
        }
        return result;
    }

    /**
     * Sets a short array to a key.
     * */
    public void setShortArray(String key, short[] values) {
        if (values != null) {
            NBTTagList list = new NBTTagList();
            for (short value : values) {
                list.add(new NBTTagShort(value));
            }
            this.tag.set(key, list);
        }
    }

    /**
     * Gets an int value.
     * Returns zero if the value does not exist on the tag.
     * Returns zero if the value on the tag is not an Integer.
     * */
    public int getInt(String key) {
        return this.tag.getInt(key);
    }

    /**
     * Sets an int to a key.
     * */
    public void setInt(String key, int value) {
        this.tag.setInt(key, value);
    }

    /**
     * Gets an int array.
     * Returns an empty array if the value does not exist on the tag.
     * Returns an empty array if the value on the tag is not an int array.
     * */
    public int[] getIntArray(String key) {
        return this.tag.getIntArray(key);
    }

    /**
     * Sets an int array to a key.
     * */
    public void setIntArray(String key, int[] values) {
        this.tag.setIntArray(key, values);
    }

    /**
     * Gets a long value.
     * Returns zero if the value does not exist on the tag.
     * Returns zero if the value on the tag is not a Long.
     * */
    public long getLong(String key) {
        return this.tag.getLong(key);
    }

    /**
     * Sets a long to a key.
     * */
    public void setLong(String key, long value) {
        this.tag.setLong(key, value);
    }

    /**
     * Gets a long array.
     * Returns an empty array if the value does not exist on the tag.
     * Returns an empty array if the value on the tag is not a long array.
     * */
    public long[] getLongArray(String key) {
        NBTTagList list = this.tag.getList(key, 4);
        long[] result = new long[list.size()];
        for (int i = 0; i < result.length; i++) {
            NBTBase base = list.i(i);
            if (base.getTypeId() != 4) {
                result[i] = 0;
            }
            else if (!(base instanceof NBTTagLong)) {
                result[i] = 0;
            }
            else {
                result[i] = ((NBTTagLong) base).d();
            }
        }
        return result;
    }

    /**
     * Sets a long array to a key.
     * */
    public void setLongArray(String key, long[] values) {
        if (values != null) {
            NBTTagList list = new NBTTagList();
            for (long value : values) {
                list.add(new NBTTagLong(value));
            }
            this.tag.set(key, list);
        }
    }

    /**
     * Gets a float value.
     * Returns 0.0f if the value does not exist on the tag.
     * Returns 0.0f if the value on the tag is not a Float.
     * */
    public float getFloat(String key) {
        return this.tag.getFloat(key);
    }

    /**
     * Sets a float to a key.
     * */
    public void setFloat(String key, float value) {
        this.tag.setFloat(key, value);
    }

    /**
     * Gets a float array.
     * Returns an empty array if the value does not exist on the tag.
     * Returns an empty array if the value on the tag is not a float array.
     * */
    public float[] getFloatArray(String key) {
        NBTTagList list = this.tag.getList(key, 5);
        float[] result = new float[list.size()];
        for (int i = 0; i < result.length; i++) {
            NBTBase base = list.i(i);
            if (base.getTypeId() != 5) {
                result[i] = 0.0f;
            }
            else if (!(base instanceof NBTTagFloat)) {
                result[i] = 0.0f;
            }
            else {
                result[i] = ((NBTTagFloat) base).i();
            }
        }
        return result;
    }

    /**
     * Sets a float array to a key.
     * */
    public void setFloatArray(String key, float[] values) {
        if (values != null) {
            NBTTagList list = new NBTTagList();
            for (float value : values) {
                list.add(new NBTTagFloat(value));
            }
            this.tag.set(key, list);
        }
    }

    /**
     * Gets a double value.
     * Returns 0.0 if the value does not exist on the tag.
     * Returns 0.0 if the value on the tag is not a Double.
     * */
    public double getDouble(String key) {
        return this.tag.getDouble(key);
    }

    /**
     * Sets a double to a key.
     * */
    public void setDouble(String key, double value) {
        this.tag.setDouble(key, value);
    }

    /**
     * Gets a double array.
     * Returns an empty array if the value does not exist on the tag.
     * Returns an empty array if the value on the tag is not a double array.
     * */
    public double[] getDoubleArray(String key) {
        NBTTagList list = this.tag.getList(key, 6);
        double[] result = new double[list.size()];
        for (int i = 0; i < result.length; i++) {
            NBTBase base = list.i(i);
            if (base.getTypeId() != 6) {
                result[i] = 0.0d;
            }
            else if (!(base instanceof NBTTagDouble)) {
                result[i] = 0.0d;
            }
            else {
                result[i] = ((NBTTagDouble) base).asDouble();
            }
        }
        return result;
    }

    /**
     * Sets a double array to a key.
     * */
    public void setDoubleArray(String key, double[] values) {
        if (values != null) {
            NBTTagList list = new NBTTagList();
            for (double value : values) {
                list.add(new NBTTagDouble(value));
            }
            this.tag.set(key, list);
        }
    }

    /**
     * Gets a String value.
     * Returns an empty String if the value does not exist on the tag.
     * Returns an empty String if the value on the tag is not a String.
     * */
    public String getString(String key) {
        return this.tag.getString(key);
    }

    /**
     * Sets a String value to a key.
     * */
    public void setString(String key, String value) {
        this.tag.setString(key, value);
    }

    /**
     * Gets a String array.
     * Returns an empty array if the value does not exist on the tag.
     * Returns an empty array if the value on the tag is not a String array.
     * */
    public String[] getStringArray(String key) {
        NBTTagList list = this.tag.getList(key, 8);
        String[] result = new String[list.size()];
        for (int i = 0; i < result.length; i++) {
            NBTBase base = list.i(i);
            if (base.getTypeId() != 8) {
                result[i] = "";
            }
            else if (!(base instanceof NBTTagString)) {
                result[i] = "";
            }
            else {
                result[i] = ((NBTTagString) base).c_();
            }
        }
        return result;
    }

    /**
     * Sets a String array to a key.
     * */
    public void setStringArray(String key, String[] values) {
        if (values != null) {
            NBTTagList list = new NBTTagList();
            for (String value : values) {
                list.add(new NBTTagString(value));
            }
            this.tag.set(key, list);
        }
    }

    /**
     * Gets an NBT Compound.
     * */
    public NBTCompound getCompound(String key) {
        return new NBTCompound(this.tag.getCompound(key));
    }

    /**
     * Sets an NBT Compound to a key.
     * */
    public void setCompound(String key, NBTCompound value) {
        if (value != null) {
            this.tag.set(key, value.tag);
        }
    }

    /**
     * Gets a Compound array.
     * Returns an empty array if the value does not exist on the tag.
     * Returns an empty array if the value on the tag is not a Compound array.
     * */
    public NBTCompound[] getCompoundArray(String key) {
        NBTTagList list = this.tag.getList(key, 10);
        NBTCompound[] result = new NBTCompound[list.size()];
        for (int i = 0; i < result.length; i++) {
            NBTBase base = list.i(i);
            if (base.getTypeId() != 10) {
                result[i] = new NBTCompound();
            }
            else if (!(base instanceof NBTTagCompound)) {
                result[i] = new NBTCompound();
            }
            else {
                result[i] = new NBTCompound((NBTTagCompound) base);
            }
        }
        return result;
    }

    /**
     * Sets an NBT Compound array to a key.
     * */
    public void setCompoundArray(String key, NBTCompound[] values) {
        if (values != null) {
            NBTTagList list = new NBTTagList();
            for (NBTCompound value : values) {
                list.add(value.tag);
            }
            this.tag.set(key, list);
        }
    }

    /**
     * Gets the raw compound.
     * */
    public NBTTagCompound getRAW() {
        return this.tag;
    }

}