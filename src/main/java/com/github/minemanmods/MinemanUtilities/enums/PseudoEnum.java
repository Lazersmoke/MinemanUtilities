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

    String getSlug();

//    private static final List<PseudoEnum> values = new ArrayList<PseudoEnum>();
//
//    // START OF ENUM ENTRIES
//
//    public static final PseudoEnum EXAMPLE = setValue(null);
//
//    // END OF ENUM ENTRIES
//
//    private static PseudoEnum setValue(PseudoEnum modifier) {
//        values.add(modifier);
//        return modifier;
//    }
//
//    public static PseudoEnum[] values() {
//        return values.toArray(new PseudoEnum[0]);
//    }
//
//    public static PseudoEnum getValue(String slug) {
//        if (slug != null && !slug.isEmpty()) {
//            for (PseudoEnum value : values) {
//                if (Objects.equals(slug, value.getSlug())) {
//                    return value;
//                }
//            }
//        }
//        return null;
//    }

}
