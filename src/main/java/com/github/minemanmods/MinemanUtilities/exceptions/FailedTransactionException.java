package com.github.minemanmods.MinemanUtilities.exceptions;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class FailedTransactionException extends Exception {

    public FailedTransactionException(@NotNull @NotEmpty String message) {
        super(message);
    }

}
