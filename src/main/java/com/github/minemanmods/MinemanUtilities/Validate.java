package com.github.minemanmods.MinemanUtilities;

import com.github.minemanmods.MinemanUtilities.interfaces.Validation;

import java.util.Collection;
import java.util.Map;

public class Validate {

    public static boolean isValid(final String string) {
        return string != null && !string.isEmpty();
    }

    public static <T> boolean isValid(final T[] array) {
        return array != null && array.length > 0;
    }

    public static boolean isValid(final boolean[] array) {
        return array != null && array.length > 0;
    }

    public static boolean isValid(final byte[] array) {
        return array != null && array.length > 0;
    }

    public static boolean isValid(final char[] array) {
        return array != null && array.length > 0;
    }

    public static boolean isValid(final short[] array) {
        return array != null && array.length > 0;
    }

    public static boolean isValid(final int[] array) {
        return array != null && array.length > 0;
    }

    public static boolean isValid(final long[] array) {
        return array != null && array.length > 0;
    }

    public static boolean isValid(final float[] array) {
        return array != null && array.length > 0;
    }

    public static boolean isValid(final double[] array) {
        return array != null && array.length > 0;
    }

    public static <T> boolean isValid(final Collection<T> list) {
        return list != null && !list.isEmpty();
    }

    public static <T, K> boolean isValid(final Map<T, K> map) {
        return map != null && !map.isEmpty();
    }

    public static boolean isValid(final Validation valid) {
        return valid != null && valid.isValid();
    }

}
