package org.assertj.reflection;

import org.assertj.core.api.AbstractAssert;

import java.util.Arrays;
import java.util.stream.Collectors;

public class ClassAssert extends AbstractAssert<ClassAssert, Class<?>> {
    protected ClassAssert(Class<?> actual) {
        super(actual, ClassAssert.class);
    }

    public MethodAssert hasDeclaredMethod(String methodName, Class<?>... parameterTypes) {
        isNotNull();
        try {
            var method = actual.getDeclaredMethod(methodName, parameterTypes);
            return new MethodAssert(method);
        } catch (NoSuchMethodException e) {
            var parameterDescriptor = Arrays.stream(parameterTypes)
                    .map(Class::getName)
                    .collect(Collectors.joining(","));

            throw failure("Expected %s to have declared method %s(%s) but no such method exists",
                    actual.getName(), methodName, parameterDescriptor);
        }
    }

    public ClassAssert hasNoDeclaredMethod(String methodName, Class<?>... parameterTypes) {
        isNotNull();
        try {
            var method = actual.getDeclaredMethod(methodName, parameterTypes);
            var parameterDescriptor = Arrays.stream(parameterTypes)
                    .map(Class::getName)
                    .collect(Collectors.joining(","));

            failWithMessage("Expected %s not to have declared method %s(%s) but found %s",
                    actual.getName(), methodName, parameterDescriptor, method.toString());
            return this;
        } catch (NoSuchMethodException e) {
            return this;
        }
    }
}
