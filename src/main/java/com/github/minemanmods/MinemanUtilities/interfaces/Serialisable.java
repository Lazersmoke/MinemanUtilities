package com.github.minemanmods.MinemanUtilities.interfaces;

import javax.validation.constraints.NotNull;

public interface Serialisable<T> {

    @NotNull T serialise(@NotNull T nbt);
    void deserialise(@NotNull T nbt);

}
