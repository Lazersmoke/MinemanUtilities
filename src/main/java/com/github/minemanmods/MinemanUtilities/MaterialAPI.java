package com.github.minemanmods.MinemanUtilities;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class MaterialAPI {

    /**
     * Determines whether an item stack is a valid item.
     * Returns false if the item id is impossible.
     * Returns false if the item durability is impossible.
     * */
    @SuppressWarnings("deprecation")
    public static boolean isValidMaterial(final Material material, final short durability) {
        return material.getId() > 0 && durability >= 0;
    }

    /**
     * Determines whether two items have the same material.
     * Returns false if either or both items are null.
     * Returns false if the item ids do not match.
     * Returns false if the item durabilities do not match.
     * */
    @SuppressWarnings("deprecation")
    public static boolean isSameMaterial(final ItemStack item1, final ItemStack item2) {
        return item1 != null && item2 != null && isSameMaterial(item1.getTypeId(), item1.getDurability(), item2.getTypeId(), item2.getDurability());
    }

    /**
     * Determines whether two items have the same material.
     * Returns false if the item ids do not match.
     * Returns false if the item durabilities do not match.
     * */
    public static boolean isSameMaterial(final int id1, final short durability1, final int id2, final short durability2) {
        return id1 == id2 && durability1 == durability2;
    }

    /**
     * Determines whether two materials match.
     * Returns false if either material is null.
     * */
    public static boolean isSameMaterial(final Material material1, final Material material2) {
        return material1 != null && material1.equals(material2);
    }

    /**
     * Returns a Material from a stringified SLUG, such as: AIR, STONE, or DOUBLE_STEP
     * Returns Air if the material cannot be found.
     * */
    public static Material getMaterial(String slug) {
        Material material = Material.getMaterial(slug);
        return material == null ? Material.AIR : material;
    }

    /**
     * Returns a Material from an id.
     * Returns Air if the material cannot be found.
     * */
    @SuppressWarnings("deprecation")
    public static Material getMaterial(int id) {
        Material material = Material.getMaterial(id);
        return material == null ? Material.AIR : material;
    }

    /**
     * Returns the nice name for an item, converting STONE:2 or 1:2 into Polished Granite
     * Returns id:durability if a nice name cannot be found.
     * */
    public static String getMaterialName(ItemStack item) {
        return getMaterialName(item, false);
    }

    /**
     * Returns the nice name for an item, converting STONE:2 or 1:2 into Polished Granite
     * Returns the items display name if that is preferred and it exists.
     * Returns id:durability if a nice name cannot be found.
     * */
    public static String getMaterialName(ItemStack item, boolean preferDisplayName) {
        if (item == null) {
            return "Air";
        }
        if (preferDisplayName) {
            String name = ItemAPI.getName(item);
            if (name != null) {
                return name;
            }
        }
        return getMaterialName(item.getType(), item.getDurability());
    }

    /**
     * Returns the nice name for an item, converting STONE:2 into Polished Granite
     * Returns id:durability if a nice name cannot be found.
     * */
    @SuppressWarnings("deprecation")
    public static String getMaterialName(Material material, short durability) {
        if (material == null || durability < 0) {
            return "Air";
        }
        return getMaterialName(material.getId(), durability);
    }

    /**
     * Returns the nice name for an item, converting 1:2 into Polished Granite
     * Returns id:durability if a nice name cannot be found.
     * */
    public static String getMaterialName(int id, short durability) {
        if (id < 0 || durability < 0) {
            return "Air";
        }
        switch (id) {
            case 0:
                return "Air";
            case 1:
                switch (durability) {
                    case 0:
                        return "Stone";
                    case 1:
                        return "Granite";
                    case 2:
                        return "Polished Granite";
                    case 3:
                        return "Diorite";
                    case 4:
                        return "Polished Diorite";
                    case 5:
                        return "Andesite";
                    case 6:
                        return "Polished Andesite";
                }
            case 2:
                return "Grass";
            case 3:
                switch (durability) {
                    case 0:
                        return "Dirt";
                    case 1:
                        return "Coarse Dirt";
                    case 2:
                        return "Podzol";
                }
            case 4:
                return "Cobblestone";
            case 5:
                switch (durability) {
                    case 0:
                        return "Oak Wood Plank";
                    case 1:
                        return "Spruce Wood Plank";
                    case 2:
                        return "Birch Wood Plank";
                    case 3:
                        return "Jungle Wood Plank";
                    case 4:
                        return "Acacia Wood Plank";
                    case 5:
                        return "Dark Oak Wood Plank";
                }
            case 6:
                switch (durability) {
                    case 0:
                        return "Oak Sapling";
                    case 1:
                        return "Spruce Sapling";
                    case 2:
                        return "Birch Sapling";
                    case 3:
                        return "Jungle Sapling";
                    case 4:
                        return "Acacia Sapling";
                    case 5:
                        return "Dark Oak Sapling";
                }
            case 7:
                return "Bedrock";
            case 8:
            case 9:
                return "Water";
            case 10:
            case 11:
                return "Lava";
            case 12:
                switch (durability) {
                    case 0:
                        return "Sand";
                    case 1:
                        return "Red Sand";
                }
            case 13:
                return "Gravel";
            case 14:
                return "Gold Ore";
            case 15:
                return "Iron Ore";
            case 16:
                return "Coal Ore";
            case 17:
                switch (durability) {
                    case 0:
                        return "Oak Wood";
                    case 1:
                        return "Spruce Wood";
                    case 2:
                        return "Birch Wood";
                    case 3:
                        return "Jungle Wood";
                }
            case 18:
                switch (durability) {
                    case 0:
                        return "Oak Leaves";
                    case 1:
                        return "Spruce Leaves";
                    case 2:
                        return "Birch Leaves";
                    case 3:
                        return "Jungle Leaves";
                }
            case 19:
                switch (durability) {
                    case 0:
                        return "Sponge";
                    case 1:
                        return "Wet Sponge";
                }
            case 20:
                return "Glass";
            case 21:
                return "Lapis Lazuli Ore";
            case 22:
                return "Lapis Lazuli Block";
            case 23:
                return "Dispenser";
            case 24:
                switch (durability) {
                    case 0:
                        return "Sandstone";
                    case 1:
                        return "Chiseled Sandstone";
                    case 2:
                        return "Smooth Sandstone";
                }
            case 25:
                return "Note Block";
            case 26:
            case 355:
                return "Bed";
            case 27:
                return "Powered Rail";
            case 28:
                return "Detector Rail";
            case 29:
                return "Sticky Piston";
            case 30:
                return "Cobweb";
            case 31:
                switch (durability) {
                    case 0:
                        return "Dead Shrub";
                    case 1:
                        return "Grass";
                    case 2:
                        return "Fern";
                }
            case 32:
                return "Dead Bush";
            case 33:
            case 34:
            case 36:
                return "Piston";
            case 35:
                switch (durability) {
                    case 0:
                        return "White Wool";
                    case 1:
                        return "Orange Wool";
                    case 2:
                        return "Magenta Wool";
                    case 3:
                        return "Light Blue Wool";
                    case 4:
                        return "Yellow Wool";
                    case 5:
                        return "Lime Wool";
                    case 6:
                        return "Pink Wool";
                    case 7:
                        return "Gray Wool";
                    case 8:
                        return "Light Gray Wool";
                    case 9:
                        return "Cyan Wool";
                    case 10:
                        return "Purple Wool";
                    case 11:
                        return "Blue Wool";
                    case 12:
                        return "Brown Wool";
                    case 13:
                        return "Green Wool";
                    case 14:
                        return "Red Wool";
                    case 15:
                        return "Black Wool";
                }
            case 37:
                return "Dandelion";
            case 38:
                switch (durability) {
                    case 0:
                        return "Poppy";
                    case 1:
                        return "Blue Orchid";
                    case 2:
                        return "Allium";
                    case 3:
                        return "Azure Bluet";
                    case 4:
                        return "Red Tulip";
                    case 5:
                        return "Orange Tulip";
                    case 6:
                        return "White Tulip";
                    case 7:
                        return "Pink Tulip";
                    case 8:
                        return "Oxeye Daisy";
                }
            case 39:
                return "Brown Mushroom";
            case 40:
                return "Red Mushroom";
            case 41:
                return "Gold Block";
            case 42:
                return "Iron Block";
            case 43:
                switch (durability) {
                    case 0:
                        return "Double Stone Slab";
                    case 1:
                        return "Double Sandstone Slab";
                    case 2:
                        return "Double Wooden Slab";
                    case 3:
                        return "Double Cobblestone Slab";
                    case 4:
                        return "Double Brick Slab";
                    case 5:
                        return "Double Stone Brick Slab";
                    case 6:
                        return "Double Nether Brick Slab";
                    case 7:
                        return "Double Quartz Slab";
                }
            case 44:
                switch (durability) {
                    case 0:
                        return "Stone Slab";
                    case 1:
                        return "Sandstone Slab";
                    case 2:
                        return "Wooden Slab";
                    case 3:
                        return "Cobblestone Slab";
                    case 4:
                        return "Brick Slab";
                    case 5:
                        return "Stone Brick Slab";
                    case 6:
                        return "Nether Brick Slab";
                    case 7:
                        return "Quartz Slab";
                }
            case 45:
                return "Bricks";
            case 46:
                return "TNT";
            case 47:
                return "Bookshelf";
            case 48:
                return "Moss Stone";
            case 49:
                return "Obsidian";
            case 50:
                return "Torch";
            case 51:
                return "Fire";
            case 52:
                return "Monster Spawner";
            case 53:
                return "Oak Wood Stairs";
            case 54:
                return "Chest";
            case 55:
            case 331:
                return "Redstone";
            case 56:
                return "Diamond Ore";
            case 58:
                return "Crafting Table";
            case 59:
                return "Wheat Crops";
            case 60:
                return "Farmland";
            case 61:
            case 62:
                return "Furnace";
            case 63:
            case 68:
            case 323:
                return "Sign";
            case 64:
            case 324:
                return "Oak Door";
            case 65:
                return "Ladder";
            case 66:
                return "Rail";
            case 67:
                return "Cobblestone Stairs";
            case 69:
                return "Lever";
            case 70:
                return "Stone Pressure Plate";
            case 71:
            case 330:
                return "Iron Door";
            case 72:
                return "Wooden Pressure Plate";
            case 73:
            case 74:
                return "Redstone Ore";
            case 75:
            case 76:
                return "Redstone Torch";
            case 77:
                return "Stone Button";
            case 78:
                return "Snow";
            case 79:
                return "Ice";
            case 80:
                return "Snow Block";
            case 81:
                return "Cactus";
            case 82:
            case 337:
                return "Clay";
            case 83:
            case 338:
                return "Sugar Cane";
            case 84:
                return "Jukebox";
            case 85:
                return "Oak Fence";
            case 86:
                return "Pumpkin";
            case 87:
                return "Netherrack";
            case 88:
                return "Soul Sand";
            case 89:
                return "Glowstone";
            case 90:
                return "Nether Portal";
            case 91:
                return "Jack o'Lantern";
            case 92:
            case 354:
                return "Cake";
            case 93:
            case 94:
            case 356:
                return "Redstone Repeater";
            case 95:
                switch (durability) {
                    case 0:
                        return "White Stained Glass";
                    case 1:
                        return "Orange Stained Glass";
                    case 2:
                        return "Magenta Stained Glass";
                    case 3:
                        return "Light Blue Stained Glass";
                    case 4:
                        return "Yellow Stained Glass";
                    case 5:
                        return "Lime Stained Glass";
                    case 6:
                        return "Pink Stained Glass";
                    case 7:
                        return "Gray Stained Glass";
                    case 8:
                        return "Light Gray Stained Glass";
                    case 9:
                        return "Cyan Stained Glass";
                    case 10:
                        return "Purple Stained Glass";
                    case 11:
                        return "Blue Stained Glass";
                    case 12:
                        return "Brown Stained Glass";
                    case 13:
                        return "Green Stained Glass";
                    case 14:
                        return "Red Stained Glass";
                    case 15:
                        return "Black Stained Glass";
                }
            case 96:
                return "Wooden Trapdoor";
            case 97:
                switch (durability) {
                    case 0:
                        return "Stone Monster Egg";
                    case 1:
                        return "Cobblestone Monster Egg";
                    case 2:
                        return "Stone Brick Monster Egg";
                    case 3:
                        return "Mossy Stone Brick Monster Egg";
                    case 4:
                        return "Cracked Stone Brick Monster Egg";
                    case 5:
                        return "Chiseled Stone Brick Monster Egg";
                }
            case 98:
                switch (durability) {
                    case 0:
                        return "Stone Bricks";
                    case 1:
                        return "Mossy Stone Bricks";
                    case 2:
                        return "Cracked Stone Bricks";
                    case 5:
                        return "Chiseled Stone Bricks";
                }
            case 99:
                return "Brown Mushroom Block";
            case 100:
                return "Red Mushroom Block";
            case 101:
                return "Iron Bars";
            case 102:
                return "Glass Pane";
            case 103:
            case 360:
                return "Melon";
            case 104:
                return "Pumpkin Stem";
            case 105:
                return "Melon Stem";
            case 106:
                return "Vines";
            case 107:
                return "Oak Fence Gate";
            case 108:
                return "Brick Stairs";
            case 109:
                return "Stone Brick Stairs";
            case 110:
                return "Mycelium";
            case 111:
                return "Lily Pad";
            case 112:
                return "Nether Brick";
            case 113:
                return "Nether Brick Fence";
            case 114:
                return "Nether Brick Stairs";
            case 115:
            case 372:
                return "Nether Wart";
            case 116:
                return "Enchantment Table";
            case 117:
            case 379:
                return "Brewing Stand";
            case 118:
            case 380:
                return "Cauldron";
            case 119:
            case 120:
                return "End Portal";
            case 121:
                return "End Stone";
            case 122:
                return "Dragon Egg";
            case 123:
            case 124:
                return "Redstone Lamp";
            case 125:
                switch (durability) {
                    case 0:
                        return "Double Oak Wood Slab";
                    case 1:
                        return "Double Spruce Wood Slab";
                    case 2:
                        return "Double Birch Wood Slab";
                    case 3:
                        return "Double Jungle Wood Slab";
                    case 4:
                        return "Double Acacia Wood Slab";
                    case 5:
                        return "Double Dark Oak Wood Slab";
                }
            case 126:
                switch (durability) {
                    case 0:
                        return "Oak Wood Slab";
                    case 1:
                        return "Spruce Wood Slab";
                    case 2:
                        return "Birch Wood Slab";
                    case 3:
                        return "Jungle Wood Slab";
                    case 4:
                        return "Acacia Wood Slab";
                    case 5:
                        return "Dark Oak Wood Slab";
                }
            case 127:
                return "Cocoa";
            case 128:
                return "Sandstone Stairs";
            case 129:
                return "Emerald Ore";
            case 130:
                return "Ender Chest";
            case 131:
                return "Tripwire Hook";
            case 132:
                return "Tripwire";
            case 133:
                return "Emerald Block";
            case 134:
                return "Spruce Wood Stairs";
            case 135:
                return "Birch Wood Stairs";
            case 136:
                return "Jungle Wood Stairs";
            case 137:
                return "Command Block";
            case 138:
                return "Beacon";
            case 139:
                switch (durability) {
                    case 0:
                        return "Cobblestone Wall";
                    case 1:
                        return "Mossy Cobblestone Wall";
                }
            case 140:
            case 390:
                return "Flower Pot";
            case 141:
                return "Carrots";
            case 142:
                return "Potatoes";
            case 143:
                return "Wooden Button";
            case 144:
                return "Mob Head";
            case 145:
                return "Anvil";
            case 146:
                return "Trapped Chest";
            case 147:
            case 148:
                return "Weighted Pressure Plate";
            case 149:
            case 150:
            case 404:
                return "Redstone Comparator";
            case 151:
                return "Daylight Sensor";
            case 152:
                return "Redstone Block";
            case 153:
                return "Nether Quartz Ore";
            case 154:
                return "Hopper";
            case 155:
                switch (durability) {
                    case 0:
                        return "Quartz Block";
                    case 1:
                        return "Chiseled Quartz Block";
                    case 2:
                        return "Pillar Quartz Block";
                }
            case 156:
                return "Quartz Stairs";
            case 157:
                return "Activator Rail";
            case 158:
                return "Dropper";
            case 159:
                switch (durability) {
                    case 0:
                        return "White Terracotta";
                    case 1:
                        return "Orange Terracotta";
                    case 2:
                        return "Magenta Terracotta";
                    case 3:
                        return "Light Blue Terracotta";
                    case 4:
                        return "Yellow Terracotta";
                    case 5:
                        return "Lime Terracotta";
                    case 6:
                        return "Pink Terracotta";
                    case 7:
                        return "Gray Terracotta";
                    case 8:
                        return "Light Gray Terracotta";
                    case 9:
                        return "Cyan Terracotta";
                    case 10:
                        return "Purple Terracotta";
                    case 11:
                        return "Blue Terracotta";
                    case 12:
                        return "Brown Terracotta";
                    case 13:
                        return "Green Terracotta";
                    case 14:
                        return "Red Terracotta";
                    case 15:
                        return "Black Terracotta";
                }
            case 160:
                switch (durability) {
                    case 0:
                        return "White Stained Glass Pane";
                    case 1:
                        return "Orange Stained Glass Pane";
                    case 2:
                        return "Magenta Stained Glass Pane";
                    case 3:
                        return "Light Blue Stained Glass Pane";
                    case 4:
                        return "Yellow Stained Glass Pane";
                    case 5:
                        return "Lime Stained Glass Pane";
                    case 6:
                        return "Pink Stained Glass Pane";
                    case 7:
                        return "Gray Stained Glass Pane";
                    case 8:
                        return "Light Gray Stained Glass Pane";
                    case 9:
                        return "Cyan Stained Glass Pane";
                    case 10:
                        return "Purple Stained Glass Pane";
                    case 11:
                        return "Blue Stained Glass Pane";
                    case 12:
                        return "Brown Stained Glass Pane";
                    case 13:
                        return "Green Stained Glass Pane";
                    case 14:
                        return "Red Stained Glass Pane";
                    case 15:
                        return "Black Stained Glass Pane";
                }
            case 161:
                switch (durability) {
                    case 0:
                        return "Acacia Leaves";
                    case 1:
                        return "Dark Oak Leaves";
                }
            case 162:
                switch (durability) {
                    case 0:
                        return "Acacia Wood";
                    case 1:
                        return "Dark Oak Wood";
                }
            case 163:
                return "Acacia Wood Stairs";
            case 164:
                return "Dark Oak Wood Stairs";
            case 165:
                return "Slime Block";
            case 166:
                return "Barrier";
            case 167:
                return "Iron Trapdoor";
            case 168:
                switch (durability) {
                    case 0:
                        return "Prismarine";
                    case 1:
                        return "Prismarine Bricks";
                    case 2:
                        return "Dark Prismarine";
                }
            case 169:
                return "Sea Lantern";
            case 170:
                return "Hay Bale";
            case 171:
                switch (durability) {
                    case 0:
                        return "White Carpet";
                    case 1:
                        return "Orange Carpet";
                    case 2:
                        return "Magenta Carpet";
                    case 3:
                        return "Light Blue Carpet";
                    case 4:
                        return "Yellow Carpet";
                    case 5:
                        return "Lime Carpet";
                    case 6:
                        return "Pink Carpet";
                    case 7:
                        return "Gray Carpet";
                    case 8:
                        return "Light Gray Carpet";
                    case 9:
                        return "Cyan Carpet";
                    case 10:
                        return "Purple Carpet";
                    case 11:
                        return "Blue Carpet";
                    case 12:
                        return "Brown Carpet";
                    case 13:
                        return "Green Carpet";
                    case 14:
                        return "Red Carpet";
                    case 15:
                        return "Black Carpet";
                }
            case 172:
                return "Terracotta";
            case 173:
                return "Block of Coal";
            case 174:
                return "Packed Ice";
            case 175:
                switch (durability) {
                    case 0:
                        return "Sunflower";
                    case 1:
                        return "Lilac";
                    case 2:
                        return "Double Tallgrass";
                    case 3:
                        return "Large Fern";
                    case 4:
                        return "Rose Bush";
                    case 5:
                        return "Peony";
                }
            case 176:
            case 177:
            case 425:
                return "Banner";
            case 178:
                return "Inverted Daylight Sensor";
            case 179:
                switch (durability) {
                    case 0:
                        return "Red Sandstone";
                    case 1:
                        return "Chiseled Red Sandstone";
                    case 2:
                        return "Smooth Red Sandstone";
                }
            case 180:
                return "Red Sandstone Stairs";
            case 181:
                return "Double Red Sandstone Slab";
            case 182:
                return "Red Sandstone Slab";
            case 183:
                return "Spruce Fence Gate";
            case 184:
                return "Birch Fence Gate";
            case 185:
                return "Jungle Fence Gate";
            case 186:
                return "Dark Oak Fence Gate";
            case 187:
                return "Acacia Fence Gate";
            case 188:
                return "Spruce Fence";
            case 189:
                return "Birch Fence";
            case 190:
                return "Jungle Fence";
            case 191:
                return "Dark Oak Fence";
            case 192:
                return "Acacia Fence";
            case 193:
            case 427:
                return "Spruce Door";
            case 194:
            case 428:
                return "Birch Door";
            case 195:
            case 429:
                return "Jungle Door";
            case 196:
            case 430:
                return "Acacia Door";
            case 197:
            case 431:
                return "Dark Oak Door";
            case 198:
                return "End Rod";
            case 199:
                return "Chorus Plant";
            case 200:
                return "Chorus Flower";
            case 201:
                return "Purpur Block";
            case 202:
                return "Pupur Pillar";
            case 203:
                return "Purpur Stairs";
            case 204:
                return "Purpur Double Slab";
            case 205:
                return "Purpur Slab";
            case 206:
                return "End Stone Bricks";
            case 207:
            case 434:
                return "Beetroot";
            case 208:
                return "Grass Path";
            case 209:
                return "End Gateway";
            case 210:
                return "Repeating Command Block";
            case 211:
                return "Chain Command Block";
            case 212:
                return "Frosted Ice";
            case 213:
                return "Magma Block";
            case 214:
                return "Nether Wart Block";
            case 215:
                return "Red Nether Brick";
            case 216:
                return "Bone Block";
            case 217:
                return "Structure Void";
            case 218:
                return "Observer";
            case 219:
                return "White Shulker Box";
            case 220:
                return "Orange Shulker Box";
            case 221:
                return "Magenta Shulker Box";
            case 222:
                return "Light Blue Shulker Box";
            case 223:
                return "Yellow Shulker Box";
            case 224:
                return "Lime Shulker Box";
            case 225:
                return "Pink Shulker Box";
            case 226:
                return "Gray Shulker Box";
            case 227:
                return "Light Gray Shulker Box";
            case 228:
                return "Cyan Shulker Box";
            case 229:
                return "Purple Shulker Box";
            case 230:
                return "Blue Shulker Box";
            case 231:
                return "Brown Shulker Box";
            case 232:
                return "Green Shulker Box";
            case 233:
                return "Red Shulker Box";
            case 234:
                return "Black Shulker Box";
            case 235:
                return "White Glazed Terracotta";
            case 236:
                return "Orange Glazed Terracotta";
            case 237:
                return "Magenta Glazed Terracotta";
            case 238:
                return "Light Blue Glazed Terracotta";
            case 239:
                return "Yellow Glazed Terracotta";
            case 240:
                return "Lime Glazed Terracotta";
            case 241:
                return "Pink Glazed Terracotta";
            case 242:
                return "Gray Glazed Terracotta";
            case 243:
                return "Light Gray Glazed Terracotta";
            case 244:
                return "Cyan Glazed Terracotta";
            case 245:
                return "Purple Glazed Terracotta";
            case 246:
                return "Blue Glazed Terracotta";
            case 247:
                return "Brown Glazed Terracotta";
            case 248:
                return "Green Glazed Terracotta";
            case 249:
                return "Red Glazed Terracotta";
            case 250:
                return "Black Glazed Terracotta";
            case 251:
                switch (durability) {
                    case 0:
                        return "White Concrete";
                    case 1:
                        return "Orange Concrete";
                    case 2:
                        return "Magenta Concrete";
                    case 3:
                        return "Light Blue Concrete";
                    case 4:
                        return "Yellow Concrete";
                    case 5:
                        return "Lime Concrete";
                    case 6:
                        return "Pink Concrete";
                    case 7:
                        return "Gray Concrete";
                    case 8:
                        return "Light Gray Concrete";
                    case 9:
                        return "Cyan Concrete";
                    case 10:
                        return "Purple Concrete";
                    case 11:
                        return "Blue Concrete";
                    case 12:
                        return "Brown Concrete";
                    case 13:
                        return "Green Concrete";
                    case 14:
                        return "Red Concrete";
                    case 15:
                        return "Black Concrete";
                }
            case 252:
                switch (durability) {
                    case 0:
                        return "White Concrete Powder";
                    case 1:
                        return "Orange Concrete Powder";
                    case 2:
                        return "Magenta Concrete Powder";
                    case 3:
                        return "Light Blue Concrete Powder";
                    case 4:
                        return "Yellow Concrete Powder";
                    case 5:
                        return "Lime Concrete Powder";
                    case 6:
                        return "Pink Concrete Powder";
                    case 7:
                        return "Gray Concrete Powder";
                    case 8:
                        return "Light Gray Concrete Powder";
                    case 9:
                        return "Cyan Concrete Powder";
                    case 10:
                        return "Purple Concrete Powder";
                    case 11:
                        return "Blue Concrete Powder";
                    case 12:
                        return "Brown Concrete Powder";
                    case 13:
                        return "Green Concrete Powder";
                    case 14:
                        return "Red Concrete Powder";
                    case 15:
                        return "Black Concrete Powder";
                }
            case 255:
                return "Structure Block";
            case 256:
                return "Iron Shovel";
            case 257:
                return "Iron Pickaxe";
            case 258:
                return "Iron Axe";
            case 259:
                return "Flint and Steel";
            case 260:
                return "Apple";
            case 261:
                return "Bow";
            case 262:
                return "Arrow";
            case 263:
                switch (durability) {
                    case 0:
                        return "Coal";
                    case 1:
                        return "Charcoal";
                }
            case 264:
                return "Diamond";
            case 265:
                return "Iron Ingot";
            case 266:
                return "Gold Ingot";
            case 267:
                return "Iron Sword";
            case 268:
                return "Wooden Sword";
            case 269:
                return "Wooden Shovel";
            case 270:
                return "Wooden Pickaxe";
            case 271:
                return "Wooden Axe";
            case 272:
                return "Stone Sword";
            case 273:
                return "Stone Shovel";
            case 274:
                return "Stone Pickaxe";
            case 275:
                return "Stone Axe";
            case 276:
                return "Diamond Sword";
            case 277:
                return "Diamond Shovel";
            case 278:
                return "Diamond Pickaxe";
            case 279:
                return "Diamond Axe";
            case 280:
                return "Stick";
            case 281:
                return "Bowl";
            case 282:
                return "Mushroom Stew";
            case 283:
                return "Golden Sword";
            case 284:
                return "Golden Shovel";
            case 285:
                return "Golden Pickaxe";
            case 286:
                return "Golden Axe";
            case 287:
                return "String";
            case 288:
                return "Feather";
            case 289:
                return "Gunpowder";
            case 290:
                return "Wooden Hoe";
            case 291:
                return "Stone Hoe";
            case 292:
                return "Iron Hoe";
            case 293:
                return "Diamond Hoe";
            case 294:
                return "Golden Hoe";
            case 295:
                return "Wheat Seeds";
            case 296:
                return "Wheat";
            case 297:
                return "Bread";
            case 298:
                return "Leather Helmet";
            case 299:
                return "Leather Tunic";
            case 300:
                return "Leather Pants";
            case 301:
                return "Leather Boots";
            case 302:
                return "Chainmail Helmet";
            case 303:
                return "Chainmail Tunic";
            case 304:
                return "Chainmail Pants";
            case 305:
                return "Chainmail Boots";
            case 306:
                return "Iron Helmet";
            case 307:
                return "Iron Tunic";
            case 308:
                return "Iron Pants";
            case 309:
                return "Iron Boots";
            case 310:
                return "Diamond Helmet";
            case 311:
                return "Diamond Tunic";
            case 312:
                return "Diamond Pants";
            case 313:
                return "Diamond Boots";
            case 314:
                return "Golden Helmet";
            case 315:
                return "Golden Tunic";
            case 316:
                return "Golden Pants";
            case 317:
                return "Golden Boots";
            case 318:
                return "Flint";
            case 319:
                return "Raw Porkchop";
            case 320:
                return "Cooked Porkchop";
            case 321:
                return "Painting";
            case 322:
                switch (durability) {
                    case 0:
                        return "Golden Apple";
                    case 1:
                        return "Enchanted Golden Apple";
                }
            case 325:
                return "Bucket";
            case 326:
                return "Water Bucket";
            case 327:
                return "Lava Bucket";
            case 328:
                return "Minecart";
            case 329:
                return "Saddle";
            case 332:
                return "Snowball";
            case 333:
                return "Oak Boat";
            case 334:
                return "Leather";
            case 335:
                return "Milk Bucket";
            case 336:
                return "Brick";
            case 339:
                return "Paper";
            case 340:
                return "Book";
            case 341:
                return "Slimeball";
            case 342:
                return "Minecart with Chest";
            case 343:
                return "Minecart with Furnace";
            case 344:
                return "Egg";
            case 345:
                return "Compass";
            case 346:
                return "Fishing Rod";
            case 347:
                return "Clock";
            case 348:
                return "Glowstone Dust";
            case 349:
                switch (durability) {
                    case 0:
                        return "Raw Fish";
                    case 1:
                        return "Raw Salmon";
                    case 2:
                        return "Clownfish";
                    case 3:
                        return "Pufferfish";
                }
            case 350:
                switch (durability) {
                    case 0:
                        return "Cooked Fish";
                    case 1:
                        return "Cooked Salmon";
                }
            case 351:
                switch (durability) {
                    case 0:
                        return "Ink Sack";
                    case 1:
                        return "Rose Red";
                    case 2:
                        return "Cactus Green";
                    case 3:
                        return "Coco Beans";
                    case 4:
                        return "Lapis Lazuli";
                    case 5:
                        return "Purple Dye";
                    case 6:
                        return "Cyan Dye";
                    case 7:
                        return "Light Gray Dye";
                    case 8:
                        return "Gray Dye";
                    case 9:
                        return "Pink Dye";
                    case 10:
                        return "Lime Dye";
                    case 11:
                        return "Dandelion Yellow";
                    case 12:
                        return "Light Blue Dye";
                    case 13:
                        return "Magenta Dye";
                    case 14:
                        return "Orange Dye";
                    case 15:
                        return "Bone Meal";
                }
            case 352:
                return "Bone";
            case 353:
                return "Sugar";
            case 357:
                return "Cookie";
            case 358:
                return "Map";
            case 359:
                return "Shears";
            case 361:
                return "Pumpkin Seeds";
            case 362:
                return "Melon Seeds";
            case 363:
                return "Raw Beef";
            case 364:
                return "Steak";
            case 365:
                return "Raw Chicken";
            case 366:
                return "Cooked Chicken";
            case 367:
                return "Rotten Flesh";
            case 368:
                return "Ender Pearl";
            case 369:
                return "Blaze Rod";
            case 370:
                return "Ghast Tear";
            case 371:
                return "Gold Nugget";
            case 373:
                return "Potion";
            case 374:
                return "Glass Bottle";
            case 375:
                return "Spider Eye";
            case 376:
                return "Fermented Spider Eye";
            case 377:
                return "Blaze Powder";
            case 378:
                return "Magma Cream";
            case 381:
                return "Eye of Ender";
            case 382:
                return "Glistering Melon";
            case 383:
                switch (durability) {
                    case 4:
                        return "Spawn Elder Guardian";
                    case 5:
                        return "Spawn Wither Skeleton";
                    case 6:
                        return "Spawn Stray";
                    case 23:
                        return "Spawn Husk";
                    case 27:
                        return "Spawn Zombie Villager";
                    case 28:
                        return "Spawn Skeleton Horse";
                    case 29:
                        return "Spawn Zombie Horse";
                    case 31:
                        return "Spawn Donkey Horse";
                    case 32:
                        return "Spawn Mule";
                    case 34:
                        return "Spawn Evoker";
                    case 35:
                        return "Spawn Vex";
                    case 36:
                        return "Spawn Vindicator";
                    case 50:
                        return "Spawn Creeper";
                    case 51:
                        return "Spawn Skeleton";
                    case 52:
                        return "Spawn Spider";
                    case 54:
                        return "Spawn Zombie";
                    case 55:
                        return "Spawn Slime";
                    case 56:
                        return "Spawn Ghast";
                    case 57:
                        return "Spawn Zombie Pigman";
                    case 58:
                        return "Spawn Enderman";
                    case 59:
                        return "Spawn Cave Spider";
                    case 60:
                        return "Spawn Silverfish";
                    case 61:
                        return "Spawn Blaze";
                    case 62:
                        return "Spawn Magma Cube";
                    case 65:
                        return "Spawn Bat";
                    case 66:
                        return "Spawn Witch";
                    case 67:
                        return "Spawn Endermite";
                    case 68:
                        return "Spawn Guardian";
                    case 69:
                        return "Spawn Shulker";
                    case 90:
                        return "Spawn Pig";
                    case 91:
                        return "Spawn Sheep";
                    case 92:
                        return "Spawn Cow";
                    case 93:
                        return "Spawn Chicken";
                    case 94:
                        return "Spawn Squid";
                    case 95:
                        return "Spawn Wolf";
                    case 96:
                        return "Spawn Mooshroom";
                    case 98:
                        return "Spawn Ocelot";
                    case 100:
                        return "Spawn Horse";
                    case 101:
                        return "Spawn Rabbit";
                    case 102:
                        return "Spawn Polar Bear";
                    case 103:
                        return "Spawn Llama";
                    case 105:
                        return "Spawn Parrot";
                    case 120:
                        return "Spawn Villager";
                }
            case 384:
                return "Bottle o' Enchanting";
            case 385:
                return "Fire Charge";
            case 386:
                return "Book and Quill";
            case 387:
                return "Written Book";
            case 388:
                return "Emerald";
            case 389:
                return "Item Frame";
            case 391:
                return "Carrot";
            case 392:
                return "Potato";
            case 393:
                return "Baked Potato";
            case 394:
                return "Poisonous Potato";
            case 395:
                return "Empty Map";
            case 396:
                return "Golden Carrot";
            case 397:
                switch (durability) {
                    case 0:
                        return "Skeleton Head";
                    case 1:
                        return "Wither Skeleton Head";
                    case 2:
                        return "Zombie Head";
                    case 3:
                        return "Human Head";
                    case 4:
                        return "Creeper Head";
                    case 5:
                        return "Dragon head";
                }
            case 398:
                return "Carrot on a Stick";
            case 399:
                return "Nether Star";
            case 400:
                return "Pumpkin Pie";
            case 401:
                return "Firework Rocket";
            case 402:
                return "Firework Star";
            case 403:
                return "Enchanted Book";
            case 405:
                return "Nether Brick";
            case 406:
                return "Nether Quartz";
            case 407:
                return "Minecart with TNT";
            case 408:
                return "Minecart with Hopper";
            case 409:
                return "Prismarine Shard";
            case 410:
                return "Prismarine Crystals";
            case 411:
                return "Raw Rabbit";
            case 412:
                return "Cooked Rabbit";
            case 413:
                return "Rabbit Stew";
            case 414:
                return "Rabbit's Foot";
            case 415:
                return "Rabbit Hide";
            case 416:
                return "Armor Stand";
            case 417:
                return "Iron Horse Armor";
            case 418:
                return "Golden Horse Armor";
            case 419:
                return "Diamond Horse Armor";
            case 420:
                return "Lead";
            case 421:
                return "Name Tag";
            case 422:
                return "Minecart with Command Block";
            case 423:
                return "Raw Mutton";
            case 424:
                return "Cooked Mutton";
            case 426:
                return "End Crystal";
            case 432:
                return "Chorus Fruit";
            case 433:
                return "Popped Chorus Fruit";
            case 435:
                return "Beetroot Seeds";
            case 436:
                return "Beetroot Soup";
            case 437:
                return "Dragon's Breath";
            case 438:
                return "Splash Potion";
            case 439:
                return "Spectral Arrow";
            case 440:
                return "Tipped Arrow";
            case 441:
                return "Lingering Potion";
            case 442:
                return "Shield";
            case 443:
                return "Elytra";
            case 444:
                return "Spruce Boat";
            case 445:
                return "Birch Boat";
            case 446:
                return "Jungle Boat";
            case 447:
                return "Acacia Boat";
            case 448:
                return "Dark Oak Boat";
            case 449:
                return "Totem of Undying";
            case 450:
                return "Shulker Shell";
            case 452:
                return "Iron Nugget";
            case 453:
                return "Knowledge Book";
            case 2256:
                return "13 Disc";
            case 2257:
                return "Cat Disc";
            case 2258:
                return "Blocks Disc";
            case 2259:
                return "Chirp Disc";
            case 2260:
                return "Far Disc";
            case 2261:
                return "Mall Disc";
            case 2262:
                return "Mellohi Disc";
            case 2263:
                return "Stal Disc";
            case 2264:
                return "Strad Disc";
            case 2265:
                return "Ward Disc";
            case 2266:
                return "11 Disc";
            case 2267:
                return "Wait Disc";
            default:
                return id + ":" + durability;
        }
    }

    /**
     * Determines whether an item's or block's durability is used to differentiate between items.
     * Returns false if the item fails an MinemanUtilities.isValidItem() check.
     * */
    public static boolean durabilityIsDifferentiator(ItemStack item) {
        return ItemAPI.isValidItem(item) && durabilityIsDifferentiator(item.getType());
    }

    /**
     * Determines whether an item's or block's durability is used to differentiate between items.
     * Returns false if the material is null.
     * */
    @SuppressWarnings("deprecation")
    public static boolean durabilityIsDifferentiator(Material material) {
        return material != null && durabilityIsDifferentiator(material.getId());
    }

    /**
     * Determines whether an item's or block's durability is used to differentiate between items.
     * */
    public static boolean durabilityIsDifferentiator(int id) {
        switch (id) {
            case 1:
            case 3:
            case 5:
            case 6:
            case 12:
            case 17:
            case 19:
            case 24:
            case 31:
            case 35:
            case 38:
            case 43:
            case 44:
            case 95:
            case 97:
            case 98:
            case 125:
            case 126:
            case 139:
            case 155:
            case 159:
            case 160:
            case 161:
            case 162:
            case 168:
            case 171:
            case 175:
            case 179:
            case 251:
            case 252:
            case 263:
            case 322:
            case 349:
            case 350:
            case 351:
            case 383:
            case 397:
                return true;
            default:
                return false;
        }
    }

    /**
     * Determines whether an item's or block's durability equates to how damaged it is.
     * Returns false if the item fails an MinemanUtilities.isValidItem() check.
     * */
    public static boolean durabilityIsDurability(ItemStack item) {
        return ItemAPI.isValidItem(item) && durabilityIsDurability(item.getType());
    }

    /**
     * Determines whether an item's or block's durability equates to how damaged it is.
     * Returns false if the material is null.
     * */
    @SuppressWarnings("deprecation")
    public static boolean durabilityIsDurability(Material material) {
        return material != null && durabilityIsDurability(material.getId());
    }

    /**
     * Determines whether an item's or block's durability equates to how damaged it is.
     * */
    public static boolean durabilityIsDurability(int id) {
        switch (id) {
            case 256:
            case 257:
            case 258:
            case 259:
            case 261:
            case 267:
            case 268:
            case 269:
            case 270:
            case 271:
            case 272:
            case 273:
            case 274:
            case 275:
            case 276:
            case 277:
            case 278:
            case 279:
            case 283:
            case 284:
            case 285:
            case 286:
            case 290:
            case 291:
            case 292:
            case 293:
            case 294:
            case 298:
            case 299:
            case 300:
            case 301:
            case 302:
            case 303:
            case 304:
            case 305:
            case 306:
            case 307:
            case 308:
            case 309:
            case 310:
            case 311:
            case 312:
            case 313:
            case 314:
            case 315:
            case 316:
            case 317:
            case 346:
            case 359:
            case 398:
            case 442:
            case 443:
                return true;
            default:
                return false;
        }
    }

}
