package com.github.minemanmods.MinemanUtilities.structs;

import org.bukkit.Material;

public class MaterialAndDurability {

    private Material material;
    private short durability;

    public MaterialAndDurability() {
        this.material = Material.AIR;
        this.durability = 0;
    }

    public MaterialAndDurability(Material material) {
        this.material = material;
        this.durability = 0;
    }

    public MaterialAndDurability(Material material, short durability) {
        this.material = material;
        this.durability = durability;
    }

    public Material getMaterial() {
        return this.material;
    }

    public short getDurability() {
        return this.durability;
    }

}
