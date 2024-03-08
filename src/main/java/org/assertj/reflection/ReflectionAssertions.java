package org.assertj.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Entry point for assertion methods for different reflection types.
 * Each method in this class is a static factory for a type-specific assertion object.
 */
public final class ReflectionAssertions {

    /**
     * Creates a new {@link ReflectionAssertions}.
     */
    private ReflectionAssertions() {
    }

    /**
     * Creates a new instance of {@link ClassAssert}.
     *
     * @param actual The actual value.
     * @return The created assertion object.
     */
    public static ClassAssert assertThat(Class<?> actual) {
        return new ClassAssert(actual);
    }

    /**
     * Creates a new instance of {@link ConstructorAssert}.
     *
     * @param actual The actual value.
     * @return The created assertion object.
     */
    public static ConstructorAssert assertThat(Constructor<?> actual) {
        return new ConstructorAssert(actual);
    }

    /**
     * Creates a new instance of {@link FieldAssert}.
     *
     * @param actual The actual value.
     * @return The created assertion object.
     */
    public static FieldAssert assertThat(Field actual) {
        return new FieldAssert(actual);
    }

    /**
     * Creates a new instance of {@link MethodAssert}.
     *
     * @param actual The actual value.
     * @return The created assertion object.
     */
    public static MethodAssert assertThat(Method actual) {
        return new MethodAssert(actual);
    }
}
