package com.github.minemanmods.MinemanUtilities.exceptions;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class NotEnoughSpaceException extends Exception {

    public NotEnoughSpaceException(@NotNull @NotEmpty String message) {
        super(message);
    }

}
