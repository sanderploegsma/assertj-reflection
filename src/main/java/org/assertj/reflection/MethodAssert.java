package org.assertj.reflection;

import org.assertj.core.api.AbstractAssert;

import java.lang.reflect.Method;

public class MethodAssert extends AbstractAssert<MethodAssert, Method> {
    protected MethodAssert(Method actual) {
        super(actual, MethodAssert.class);
    }

    public MethodAssert hasReturnType(Class<?> expected) {
        isNotNull();
        if (!actual.getReturnType().equals(expected)) {
            failWithActualExpectedAndMessage(actual.getReturnType(), expected, "");
        }
        return this;
    }
}
