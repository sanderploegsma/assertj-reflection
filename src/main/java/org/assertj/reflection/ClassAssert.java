package org.assertj.reflection;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;

import java.lang.reflect.AccessFlag;
import java.util.Arrays;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * Assertions for the {@link Class} type.
 */
public class ClassAssert extends AbstractAssert<ClassAssert, Class<?>> {

    /**
     * Creates a new {@link ClassAssert}.
     *
     * @param actual The actual value.
     */
    protected ClassAssert(Class<?> actual) {
        super(actual, ClassAssert.class);
    }

    /**
     * Verifies that the {@link Class} is <em>public</em>.
     *
     * @return This {@link ClassAssert} instance.
     */
    public ClassAssert isPublic() {
        isNotNull();
        Assertions.assertThat(actual.accessFlags())
                .as("Expecting class %s to be public", actual.getName())
                .contains(AccessFlag.PUBLIC);
        return this;
    }

    /**
     * Verifies that the {@link Class} is <em>protected</em>.
     *
     * @return This {@link ClassAssert} instance.
     */
    public ClassAssert isProtected() {
        isNotNull();
        Assertions.assertThat(actual.accessFlags())
                .as("Expecting class %s to be protected", actual.getName())
                .contains(AccessFlag.PROTECTED);
        return this;
    }

    /**
     * Verifies that the {@link Class} is <em>private</em>.
     *
     * @return This {@link ClassAssert} instance.
     */
    public ClassAssert isPrivate() {
        isNotNull();
        Assertions.assertThat(actual.accessFlags())
                .as("Expecting class %s to be private", actual.getName())
                .contains(AccessFlag.PRIVATE);
        return this;
    }

    /**
     * Verifies that the {@link Class} is <em>package-private</em>.
     *
     * @return This {@link ClassAssert} instance.
     */
    public ClassAssert isPackagePrivate() {
        isNotNull();
        Assertions.assertThat(actual.accessFlags())
                .as("Expecting class %s to be package-private", actual.getName())
                .doesNotContain(AccessFlag.PUBLIC, AccessFlag.PROTECTED, AccessFlag.PRIVATE);
        return this;
    }

    /**
     * Verifies that the {@link Class} is <em>abstract</em>.
     *
     * @return This {@link ClassAssert} instance.
     */
    public ClassAssert isAbstract() {
        isNotNull();
        Assertions.assertThat(actual.accessFlags())
                .as("Expecting class %s to be abstract", actual.getName())
                .contains(AccessFlag.ABSTRACT);
        return this;
    }

    /**
     * Verifies that the {@link Class} is <em>final</em>.
     *
     * @return This {@link ClassAssert} instance.
     */
    public ClassAssert isFinal() {
        isNotNull();
        Assertions.assertThat(actual.accessFlags())
                .as("Expecting class %s to be final", actual.getName())
                .contains(AccessFlag.FINAL);
        return this;
    }

    /**
     * Verifies that the {@link Class} is <em>static</em>.
     *
     * @return This {@link ClassAssert} instance.
     */
    public ClassAssert isStatic() {
        isNotNull();
        Assertions.assertThat(actual.accessFlags())
                .as("Expecting class %s to be static", actual.getName())
                .contains(AccessFlag.STATIC);
        return this;
    }

    /**
     * Verifies that the {@link Class} has a declared constructor with no arguments.
     *
     * @return this {@link ClassAssert} instance.
     */
    public ClassAssert hasDeclaredConstructor() {
        return hasDeclaredConstructor(new Class[0], null);
    }

    /**
     * Verifies that the {@link Class} has a declared constructor with a single argument.
     *
     * @param parameterType The type of the constructor argument.
     * @return this {@link ClassAssert} instance.
     */
    public ClassAssert hasDeclaredConstructor(Class<?> parameterType) {
        return hasDeclaredConstructor(new Class[]{parameterType}, null);
    }

    /**
     * Verifies that the {@link Class} has a declared constructor with multiple arguments.
     *
     * @param parameterTypes The types of the constructor arguments.
     * @return this {@link ClassAssert} instance.
     */
    public ClassAssert hasDeclaredConstructor(Class<?>[] parameterTypes) {
        return hasDeclaredConstructor(parameterTypes, null);
    }

    /**
     * Verifies that the {@link Class} has a declared constructor with no arguments.
     *
     * @param constructorAssertConsumer Consumer that is invoked with an instance of {@link ConstructorAssert},
     *                                  to perform additional assertions on the matched constructor.
     * @return this {@link ClassAssert} instance.
     */
    public ClassAssert hasDeclaredConstructor(Consumer<ConstructorAssert> constructorAssertConsumer) {
        return hasDeclaredConstructor(new Class[0], constructorAssertConsumer);
    }

    /**
     * Verifies that the {@link Class} has a declared constructor with a single argument.
     *
     * @param parameterType             The type of the constructor argument.
     * @param constructorAssertConsumer Consumer that is invoked with an instance of {@link ConstructorAssert},
     *                                  to perform additional assertions on the matched constructor.
     * @return this {@link ClassAssert} instance.
     */
    public ClassAssert hasDeclaredConstructor(Class<?> parameterType, Consumer<ConstructorAssert> constructorAssertConsumer) {
        return hasDeclaredConstructor(new Class[]{parameterType}, constructorAssertConsumer);
    }

    /**
     * Verifies that the {@link Class} has a declared constructor with multiple arguments.
     *
     * @param parameterTypes            The types of the constructor arguments.
     * @param constructorAssertConsumer Consumer that is invoked with an instance of {@link ConstructorAssert},
     *                                  to perform additional assertions on the matched constructor.
     * @return this {@link ClassAssert} instance.
     */
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

    /**
     * Verifies that the {@link Class} does not have a declared constructor with no arguments.
     *
     * @return this {@link ClassAssert} instance.
     */
    public ClassAssert hasNoDeclaredConstructor() {
        return hasNoDeclaredConstructor(new Class[0]);
    }

    /**
     * Verifies that the {@link Class} does not have a declared constructor with a single argument of the given type.
     *
     * @param parameterType The type of the constructor argument.
     * @return this {@link ClassAssert} instance.
     */
    public ClassAssert hasNoDeclaredConstructor(Class<?> parameterType) {
        return hasNoDeclaredConstructor(new Class[]{parameterType});
    }

    /**
     * Verifies that the {@link Class} does not have a declared constructor with multiple arguments.
     *
     * @param parameterTypes The types of the constructor arguments.
     * @return this {@link ClassAssert} instance.
     */
    public ClassAssert hasNoDeclaredConstructor(Class<?>[] parameterTypes) {
        isNotNull();
        try {
            var constructor = actual.getDeclaredConstructor(parameterTypes);
            var parameterDescriptor = Arrays.stream(parameterTypes)
                    .map(Class::getName)
                    .collect(Collectors.joining(","));

            throw failure("Expected %s not to have declared constructor %s(%s) but found %s",
                    actual.getName(), actual.getSimpleName(), parameterDescriptor, constructor.toString());
        } catch (NoSuchMethodException e) {
            return this;
        }
    }

    /**
     * Verifies that the {@link Class} has a declared field with the given name.
     *
     * @param fieldName The name of the declared field.
     * @return this {@link ClassAssert} instance.
     */
    public ClassAssert hasDeclaredField(String fieldName) {
        return hasDeclaredField(fieldName, null);
    }

    /**
     * Verifies that the {@link Class} has a declared field with the given name.
     *
     * @param fieldName           The name of the declared field.
     * @param fieldAssertConsumer Consumer that is invoked with an instance of {@link FieldAssert},
     *                            to perform additional assertions on the matched field.
     * @return this {@link ClassAssert} instance.
     */
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

    /**
     * Verifies that the {@link Class} does not have a declared field with the given name.
     *
     * @param fieldName The name of the declared field.
     * @return this {@link ClassAssert} instance.
     */
    public ClassAssert hasNoDeclaredField(String fieldName) {
        isNotNull();
        try {
            var field = actual.getDeclaredField(fieldName);
            throw failure("Expected %s not to have declared field %s but found %s",
                    actual.getName(), fieldName, field.toString());
        } catch (NoSuchFieldException e) {
            return this;
        }
    }

    /**
     * Verifies that the {@link Class} has a declared method with the given name and no arguments.
     *
     * @param methodName The name of the declared method.
     * @return this {@link ClassAssert} instance.
     */
    public ClassAssert hasDeclaredMethod(String methodName) {
        return hasDeclaredMethod(methodName, new Class[0], null);
    }

    /**
     * Verifies that the {@link Class} has a declared method with the given name and a single argument.
     *
     * @param methodName    The name of the declared method.
     * @param parameterType The type of the method argument.
     * @return this {@link ClassAssert} instance.
     */
    public ClassAssert hasDeclaredMethod(String methodName, Class<?> parameterType) {
        return hasDeclaredMethod(methodName, new Class[]{parameterType}, null);
    }

    /**
     * Verifies that the {@link Class} has a declared method with the given name and multiple arguments.
     *
     * @param methodName     The name of the declared method.
     * @param parameterTypes The types of the method arguments.
     * @return this {@link ClassAssert} instance.
     */
    public ClassAssert hasDeclaredMethod(String methodName, Class<?>[] parameterTypes) {
        return hasDeclaredMethod(methodName, parameterTypes, null);
    }

    /**
     * Verifies that the {@link Class} has a declared method with the given name and no arguments.
     *
     * @param methodName           The name of the declared method.
     * @param methodAssertConsumer Consumer that is invoked with an instance of {@link MethodAssert},
     *                             to perform additional assertions on the matched method.
     * @return this {@link ClassAssert} instance.
     */
    public ClassAssert hasDeclaredMethod(String methodName, Consumer<MethodAssert> methodAssertConsumer) {
        return hasDeclaredMethod(methodName, new Class[0], methodAssertConsumer);
    }

    /**
     * Verifies that the {@link Class} has a declared method with the given name and a single argument.
     *
     * @param methodName           The name of the declared method.
     * @param parameterType        The type of the method argument.
     * @param methodAssertConsumer Consumer that is invoked with an instance of {@link MethodAssert},
     *                             to perform additional assertions on the matched method.
     * @return this {@link ClassAssert} instance.
     */
    public ClassAssert hasDeclaredMethod(String methodName, Class<?> parameterType, Consumer<MethodAssert> methodAssertConsumer) {
        return hasDeclaredMethod(methodName, new Class[]{parameterType}, methodAssertConsumer);
    }

    /**
     * Verifies that the {@link Class} has a declared method with the given name and multiple arguments.
     *
     * @param methodName           The name of the declared method.
     * @param parameterTypes       The types of the method arguments.
     * @param methodAssertConsumer Consumer that is invoked with an instance of {@link MethodAssert},
     *                             to perform additional assertions on the matched method.
     * @return this {@link ClassAssert} instance.
     */
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

    /**
     * Verifies that the {@link Class} does not have a declared method with the given name and no arguments.
     *
     * @param methodName The name of the declared method.
     * @return this {@link ClassAssert} instance.
     */
    public ClassAssert hasNoDeclaredMethod(String methodName) {
        return hasNoDeclaredMethod(methodName, new Class[0]);
    }

    /**
     * Verifies that the {@link Class} does not have a declared method with the given name and a single argument.
     *
     * @param methodName    The name of the declared method.
     * @param parameterType The type of the method argument.
     * @return this {@link ClassAssert} instance.
     */
    public ClassAssert hasNoDeclaredMethod(String methodName, Class<?> parameterType) {
        return hasNoDeclaredMethod(methodName, new Class[]{parameterType});
    }

    /**
     * Verifies that the {@link Class} has a declared method with the given name and multiple arguments.
     *
     * @param methodName     The name of the declared method.
     * @param parameterTypes The types of the method arguments.
     * @return this {@link ClassAssert} instance.
     */
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
