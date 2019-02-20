package com.github.minemanmods.MinemanUtilities.enums;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public interface EnumType {

      @NotNull @NotEmpty String getSlug();

}
