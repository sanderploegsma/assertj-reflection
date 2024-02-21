package org.assertj.reflection;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;

import java.lang.reflect.AccessFlag;
import java.lang.reflect.Method;

/**
 * Assertions for the {@link Method} type.
 */
public class MethodAssert extends AbstractAssert<MethodAssert, Method> {

    /**
     * Creates a new {@link MethodAssert}.
     *
     * @param actual The actual value.
     */
    protected MethodAssert(Method actual) {
        super(actual, MethodAssert.class);
    }

    /**
     * Verifies that the {@link Method} is <em>public</em>.
     *
     * @return This {@link MethodAssert} instance.
     */
    public MethodAssert isPublic() {
        isNotNull();
        Assertions.assertThat(actual.accessFlags())
                .as("Expecting method %s to be public", actual.toString())
                .contains(AccessFlag.PUBLIC);
        return this;
    }

    /**
     * Verifies that the {@link Method} is <em>protected</em>.
     *
     * @return This {@link MethodAssert} instance.
     */
    public MethodAssert isProtected() {
        isNotNull();
        Assertions.assertThat(actual.accessFlags())
                .as("Expecting method %s to be protected", actual.toString())
                .contains(AccessFlag.PROTECTED);
        return this;
    }

    /**
     * Verifies that the {@link Method} is <em>private</em>.
     *
     * @return This {@link MethodAssert} instance.
     */
    public MethodAssert isPrivate() {
        isNotNull();
        Assertions.assertThat(actual.accessFlags())
                .as("Expecting method %s to be private", actual.toString())
                .contains(AccessFlag.PRIVATE);
        return this;
    }

    /**
     * Verifies that the {@link Method} is <em>package-private</em>.
     *
     * @return This {@link MethodAssert} instance.
     */
    public MethodAssert isPackagePrivate() {
        isNotNull();
        Assertions.assertThat(actual.accessFlags())
                .as("Expecting method %s to be package-private", actual.toString())
                .doesNotContain(AccessFlag.PUBLIC, AccessFlag.PROTECTED, AccessFlag.PRIVATE);
        return this;
    }

    /**
     * Verifies that the {@link Method} is <em>static</em>.
     *
     * @return This {@link MethodAssert} instance.
     */
    public MethodAssert isStatic() {
        isNotNull();
        Assertions.assertThat(actual.accessFlags())
                .as("Expecting method %s to be static", actual.toString())
                .contains(AccessFlag.STATIC);
        return this;
    }

    /**
     * Verifies that the {@link Method} has the expected return type.
     *
     * @param expected The expected return type of the {@link Method}.
     * @return This {@link MethodAssert} instance.
     */
    public MethodAssert hasReturnType(Class<?> expected) {
        isNotNull();
        Assertions.assertThat(actual.getReturnType()).isEqualTo(expected);
        return this;
    }

    /**
     * Verifies that the {@link Method} declares the given exception to be thrown.
     * If a declared exception is assignable to the expected exception, this assertion passes.
     *
     * @param expected The expected exception to be thrown.
     * @return This {@link MethodAssert} instance.
     */
    public MethodAssert throwsException(Class<? extends Exception> expected) {
        isNotNull();
        var exceptions = actual.getExceptionTypes();
        Assertions.assertThat(exceptions).anyMatch(expected::isAssignableFrom);
        return this;
    }
}
