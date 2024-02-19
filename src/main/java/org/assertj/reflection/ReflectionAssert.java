package org.assertj.reflection;

import java.lang.reflect.Method;

public class ReflectionAssert {
    public static ClassAssert assertThat(Class<?> actual) {
        return new ClassAssert(actual);
    }

    public static MethodAssert assertThat(Method method) {
        return new MethodAssert(method);
    }
}
