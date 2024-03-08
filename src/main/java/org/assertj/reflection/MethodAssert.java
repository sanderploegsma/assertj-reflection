package org.assertj.reflection;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import static org.assertj.reflection.MemberModifierShouldBe.*;

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
        if (!hasPublicModifier()) {
            throw assertionError(shouldBePublic(actual));
        }
        return this;
    }

    /**
     * Verifies that the {@link Method} is not <em>public</em>.
     *
     * @return This {@link MethodAssert} instance.
     */
    public MethodAssert isNotPublic() {
        isNotNull();
        if (hasPublicModifier()) {
            throw assertionError(shouldNotBePublic(actual));
        }
        return this;
    }

    private boolean hasPublicModifier() {
        return Modifier.isPublic(actual.getModifiers());
    }

    /**
     * Verifies that the {@link Method} is <em>protected</em>.
     *
     * @return This {@link MethodAssert} instance.
     */
    public MethodAssert isProtected() {
        isNotNull();
        if (!hasProtectedModifier()) {
            throw assertionError(shouldBeProtected(actual));
        }
        return this;
    }

    /**
     * Verifies that the {@link Method} is not <em>protected</em>.
     *
     * @return This {@link MethodAssert} instance.
     */
    public MethodAssert isNotProtected() {
        isNotNull();
        if (hasProtectedModifier()) {
            throw assertionError(shouldNotBeProtected(actual));
        }
        return this;
    }

    private boolean hasProtectedModifier() {
        return Modifier.isProtected(actual.getModifiers());
    }

    /**
     * Verifies that the {@link Method} is <em>private</em>.
     *
     * @return This {@link MethodAssert} instance.
     */
    public MethodAssert isPrivate() {
        isNotNull();
        if (!hasPrivateModifier()) {
            throw assertionError(shouldBePrivate(actual));
        }
        return this;
    }

    /**
     * Verifies that the {@link Method} is not <em>private</em>.
     *
     * @return This {@link MethodAssert} instance.
     */
    public MethodAssert isNotPrivate() {
        isNotNull();
        if (hasPrivateModifier()) {
            throw assertionError(shouldNotBePrivate(actual));
        }
        return this;
    }

    private boolean hasPrivateModifier() {
        return Modifier.isPrivate(actual.getModifiers());
    }

    /**
     * Verifies that the {@link Method} is <em>package-private</em>.
     *
     * @return This {@link MethodAssert} instance.
     */
    public MethodAssert isPackagePrivate() {
        isNotNull();
        if (hasPublicModifier() || hasProtectedModifier() || hasPrivateModifier()) {
            throw assertionError(shouldBePackagePrivate(actual));
        }
        return this;
    }

    /**
     * Verifies that the {@link Method} is not <em>package-private</em>.
     *
     * @return This {@link MethodAssert} instance.
     */
    public MethodAssert isNotPackagePrivate() {
        isNotNull();
        if (!hasPublicModifier() && !hasProtectedModifier() && !hasPrivateModifier()) {
            throw assertionError(shouldNotBePackagePrivate(actual));
        }
        return this;
    }

    /**
     * Verifies that the {@link Method} is <em>static</em>.
     *
     * @return This {@link MethodAssert} instance.
     */
    public MethodAssert isStatic() {
        isNotNull();
        if (!hasStaticModifier()) {
            throw assertionError(shouldBeStatic(actual));
        }
        return this;
    }

    /**
     * Verifies that the {@link Method} is not <em>static</em>.
     *
     * @return This {@link MethodAssert} instance.
     */
    public MethodAssert isNotStatic() {
        isNotNull();
        if (hasStaticModifier()) {
            throw assertionError(shouldNotBeStatic(actual));
        }
        return this;
    }

    private boolean hasStaticModifier() {
        return Modifier.isStatic(actual.getModifiers());
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
        Assertions.assertThat(actual.getExceptionTypes()).anyMatch(expected::isAssignableFrom);
        return this;
    }
}
