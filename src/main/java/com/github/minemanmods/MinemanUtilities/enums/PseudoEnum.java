package com.github.minemanmods.MinemanUtilities.enums;

/**
 * Sometimes you need something like an enum, but can be extended post compilation.
 * Minecraft's Enchantment class does this, whereby it has all its vanilla enchants
 * saved onto the class statically, like an enum, but can be extended. It does this
 * by having the definitions call a static function that adds the entry to a list,
 * imitating enum's values()
 *
 * As these methods are static, they can't be inherited, so this class exists merely
 * as an indicator, as well as providing a link within IDE's to this very the methods
 * commented below.
 * */
public interface PseudoEnum {

//    private static final List<EnumType> values = new ArrayList<EnumType>();
//
//    // START OF ENUM ENTRIES
//
//    public static final EnumType EXAMPLE = registerValue(null);
//
//    // END OF ENUM ENTRIES
//
//    public static EnumType registerValue(EnumType modifier) {
//        values.add(modifier);
//        return modifier;
//    }
//
//    public static EnumType[] values() {
//        return values.toArray(new EnumType[0]);
//    }
//
//    public static EnumType getValue(String slug) {
//        if (slug != null && !slug.isEmpty()) {
//            for (EnumType value : values) {
//                if (Objects.equals(slug, value.getSlug())) {
//                    return value;
//                }
//            }
//        }
//        return null;
//    }

}
