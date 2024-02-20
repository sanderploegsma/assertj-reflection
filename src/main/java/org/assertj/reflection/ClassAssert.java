package org.assertj.reflection;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;

import java.lang.reflect.AccessFlag;
import java.util.Arrays;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class ClassAssert extends AbstractAssert<ClassAssert, Class<?>> {

    protected ClassAssert(Class<?> actual) {
        super(actual, ClassAssert.class);
    }

    public ClassAssert isPublic() {
        isNotNull();
        Assertions.assertThat(actual.accessFlags())
                .as("Expecting class %s to be public", actual.getName())
                .contains(AccessFlag.PUBLIC);
        return this;
    }

    public ClassAssert isProtected() {
        isNotNull();
        Assertions.assertThat(actual.accessFlags())
                .as("Expecting class %s to be protected", actual.getName())
                .contains(AccessFlag.PROTECTED);
        return this;
    }

    public ClassAssert isPrivate() {
        isNotNull();
        Assertions.assertThat(actual.accessFlags())
                .as("Expecting class %s to be private", actual.getName())
                .contains(AccessFlag.PRIVATE);
        return this;
    }

    public ClassAssert isPackagePrivate() {
        isNotNull();
        Assertions.assertThat(actual.accessFlags())
                .as("Expecting class %s to be package-private", actual.getName())
                .doesNotContain(AccessFlag.PUBLIC, AccessFlag.PROTECTED, AccessFlag.PRIVATE);
        return this;
    }

    public ClassAssert isAbstract() {
        isNotNull();
        Assertions.assertThat(actual.accessFlags())
                .as("Expecting class %s to be abstract", actual.getName())
                .contains(AccessFlag.ABSTRACT);
        return this;
    }

    public ClassAssert isFinal() {
        isNotNull();
        Assertions.assertThat(actual.accessFlags())
                .as("Expecting class %s to be final", actual.getName())
                .contains(AccessFlag.FINAL);
        return this;
    }

    public ClassAssert isStatic() {
        isNotNull();
        Assertions.assertThat(actual.accessFlags())
                .as("Expecting class %s to be static", actual.getName())
                .contains(AccessFlag.STATIC);
        return this;
    }

    public ClassAssert hasDeclaredConstructor() {
        return hasDeclaredConstructor(new Class[0], null);
    }

    public ClassAssert hasDeclaredConstructor(Class<?> parameterType) {
        return hasDeclaredConstructor(new Class[]{parameterType}, null);
    }

    public ClassAssert hasDeclaredConstructor(Class<?>[] parameterTypes) {
        return hasDeclaredConstructor(parameterTypes, null);
    }

    public ClassAssert hasDeclaredConstructor(Consumer<ConstructorAssert> constructorAssertConsumer) {
        return hasDeclaredConstructor(new Class[0], constructorAssertConsumer);
    }

    public ClassAssert hasDeclaredConstructor(Class<?> parameterType, Consumer<ConstructorAssert> constructorAssertConsumer) {
        return hasDeclaredConstructor(new Class[]{parameterType}, constructorAssertConsumer);
    }

    public ClassAssert hasDeclaredConstructor(Class<?>[] parameterTypes, Consumer<ConstructorAssert> constructorAssertConsumer) {
        isNotNull();
        try {
            var constructor = actual.getDeclaredConstructor(parameterTypes);
            if (constructorAssertConsumer != null) {
                constructorAssertConsumer.accept(new ConstructorAssert(constructor));
            }
            return this;
        } catch (NoSuchMethodException e) {
            var parameterDescriptor = Arrays.stream(parameterTypes)
                    .map(Class::getName)
                    .collect(Collectors.joining(","));

            throw failure("Expected %s to have declared constructor %s(%s) but no such constructor exists",
                    actual.getName(), actual.getSimpleName(), parameterDescriptor);
        }
    }

    public ClassAssert hasDeclaredField(String fieldName) {
        return hasDeclaredField(fieldName, null);
    }

    public ClassAssert hasDeclaredField(String fieldName, Consumer<FieldAssert> fieldAssertConsumer) {
        isNotNull();
        try {
            var field = actual.getDeclaredField(fieldName);
            if (fieldAssertConsumer != null) {
                fieldAssertConsumer.accept(new FieldAssert(field));
            }
            return this;
        } catch (NoSuchFieldException e) {
            throw failure("Expected %s to have declared field %s but no such field exists",
                    actual.getName(), fieldName);
        }
    }

    public ClassAssert hasDeclaredMethod(String methodName) {
        return hasDeclaredMethod(methodName, new Class[0], null);
    }

    public ClassAssert hasDeclaredMethod(String methodName, Class<?> parameterType) {
        return hasDeclaredMethod(methodName, new Class[]{parameterType}, null);
    }

    public ClassAssert hasDeclaredMethod(String methodName, Class<?>[] parameterTypes) {
        return hasDeclaredMethod(methodName, parameterTypes, null);
    }

    public ClassAssert hasDeclaredMethod(String methodName, Consumer<MethodAssert> methodAssertConsumer) {
        return hasDeclaredMethod(methodName, new Class[0], methodAssertConsumer);
    }

    public ClassAssert hasDeclaredMethod(String methodName, Class<?> parameterType, Consumer<MethodAssert> methodAssertConsumer) {
        return hasDeclaredMethod(methodName, new Class[]{parameterType}, methodAssertConsumer);
    }

    public ClassAssert hasDeclaredMethod(String methodName, Class<?>[] parameterTypes, Consumer<MethodAssert> methodAssertConsumer) {
        isNotNull();
        try {
            var method = actual.getDeclaredMethod(methodName, parameterTypes);
            if (methodAssertConsumer != null) {
                methodAssertConsumer.accept(new MethodAssert(method));
            }
            return this;
        } catch (NoSuchMethodException e) {
            var parameterDescriptor = Arrays.stream(parameterTypes)
                    .map(Class::getName)
                    .collect(Collectors.joining(","));

            throw failure("Expected %s to have declared method %s(%s) but no such method exists",
                    actual.getName(), methodName, parameterDescriptor);
        }
    }

    public ClassAssert hasNoDeclaredMethod(String methodName) {
        return hasNoDeclaredMethod(methodName, new Class[0]);
    }

    public ClassAssert hasNoDeclaredMethod(String methodName, Class<?> parameterType) {
        return hasNoDeclaredMethod(methodName, new Class[]{parameterType});
    }

    public ClassAssert hasNoDeclaredMethod(String methodName, Class<?>[] parameterTypes) {
        isNotNull();
        try {
            var method = actual.getDeclaredMethod(methodName, parameterTypes);
            var parameterDescriptor = Arrays.stream(parameterTypes)
                    .map(Class::getName)
                    .collect(Collectors.joining(","));

            throw failure("Expected %s not to have declared method %s(%s) but found %s",
                    actual.getName(), methodName, parameterDescriptor, method.toString());
        } catch (NoSuchMethodException e) {
            return this;
        }
    }
}
