package com.hanatour.hats.test.utils;

import org.springframework.data.util.Pair;

import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * 꼭 테스트 용도에서만 사용하세요
 */
@SuppressWarnings("unchecked")
public final class TestUtil {

    public static void changeValue(Object instance, String name, Object value) {
        try {
            Field field = instance.getClass().getDeclaredField(name);
            field.setAccessible(true);
            field.set(instance, value);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public static void changeValues(Object instance, Pair<String, Object>... nameAndValue) {
        Arrays.stream(nameAndValue)
                .forEach(pair -> changeValue(instance, pair.getFirst(), pair.getSecond()));
    }
}
