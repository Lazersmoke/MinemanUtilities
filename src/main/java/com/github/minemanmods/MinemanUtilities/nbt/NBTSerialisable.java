package com.github.minemanmods.MinemanUtilities.nbt;

public interface NBTSerialisable {

    NBTCompound serialise(NBTCompound nbt);
    void deserialise(NBTCompound nbt);

}
