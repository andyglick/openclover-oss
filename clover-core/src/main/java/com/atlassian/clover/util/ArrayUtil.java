package com.atlassian.clover.util;

import clover.com.google.common.base.Function;
import clover.com.google.common.base.Functions;
import clover.com.google.common.collect.Iterables;

import java.util.Arrays;
import java.util.Locale;

/**
 * Helper class to transform raw arrays.
 */
public class ArrayUtil {

    private ArrayUtil() {

    }

    /**
     * Convert <code>input</code> array into an array of strings by calling {@link Object#toString()} and {@link
     * String#toLowerCase()} methods on each array element.
     *
     * @param input input array
     * @return String[] output by calling toString().toLowerCase
     */
    public static String[] toLowerCaseStringArray(Object[] input) {
        return transformArray(input, new Function<Object, String>() {
            @Override
            public String apply(java.lang.Object o) {
                return o.toString().toLowerCase(Locale.ENGLISH);
            }
        }, String.class);
    }

    /**
     * Convert <code>input</code> array into an array of strings by calling {@link Object#toString()} method on each
     * array element.
     *
     * @param input input array
     * @return String[] output by calling toString() on every element
     */
    public static String[] toStringArray(Object[] input) {
        return transformArray(input, Functions.toStringFunction(), String.class);
    }

    /**
     * Convert <code>input</code> array of type F into an output array of type T using the <code>transformer</code>
     * function. It's a wrapper for {@link Iterables#transform(Iterable, clover.com.google.common.base.Function)}} which
     * allows to work on array.
     *
     * @param input       input array
     * @param transformer conversion
     * @param targetClass type of the target array
     * @param <F>         from type
     * @param <T>         to type
     * @return T[] an array of type targetClass
     */
    public static <F, T> T[] transformArray(F[] input, Function<F, T> transformer, Class<T> targetClass) {
        return Iterables.toArray(Iterables.transform(Arrays.asList(input), transformer), targetClass);
    }
}
