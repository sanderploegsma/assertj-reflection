package org.assertj.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class ReflectionAssertions {
    public static ClassAssert assertThat(Class<?> actual) {
        return new ClassAssert(actual);
    }

    public static ConstructorAssert assertThat(Constructor<?> actual) {
        return new ConstructorAssert(actual);
    }

    public static MethodAssert assertThat(Method method) {
        return new MethodAssert(method);
    }
}
