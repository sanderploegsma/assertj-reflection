package org.assertj.reflection;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;

import java.lang.reflect.Method;

public class MethodAssert extends AbstractAssert<MethodAssert, Method> {
    protected MethodAssert(Method actual) {
        super(actual, MethodAssert.class);
    }

    public MethodAssert hasReturnType(Class<?> expected) {
        isNotNull();
        Assertions.assertThat(actual.getReturnType()).isEqualTo(expected);
        return this;
    }
}
