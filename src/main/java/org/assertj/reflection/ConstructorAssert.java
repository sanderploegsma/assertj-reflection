package org.assertj.reflection;

import org.assertj.core.api.AbstractAssert;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

import static org.assertj.reflection.MemberModifierShouldBe.*;

/**
 * Assertions for the {@link Constructor} type.
 */
public class ConstructorAssert extends AbstractAssert<ConstructorAssert, Constructor<?>> {

    /**
     * Creates a new {@link ConstructorAssert}.
     *
     * @param actual The actual value.
     */
    protected ConstructorAssert(Constructor<?> actual) {
        super(actual, ConstructorAssert.class);
    }

    /**
     * Verifies that the {@link Constructor} is <em>public</em>.
     *
     * @return This {@link ConstructorAssert} instance.
     */
    public ConstructorAssert isPublic() {
        isNotNull();
        if (!hasPublicModifier()) {
            throw assertionError(shouldBePublic(actual));
        }
        return this;
    }

    /**
     * Verifies that the {@link Constructor} is not <em>public</em>.
     *
     * @return This {@link ConstructorAssert} instance.
     */
    public ConstructorAssert isNotPublic() {
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
     * Verifies that the {@link Constructor} is <em>protected</em>.
     *
     * @return This {@link ConstructorAssert} instance.
     */
    public ConstructorAssert isProtected() {
        isNotNull();
        if (!hasProtectedModifier()) {
            throw assertionError(shouldBeProtected(actual));
        }
        return this;
    }

    /**
     * Verifies that the {@link Constructor} is not <em>protected</em>.
     *
     * @return This {@link ConstructorAssert} instance.
     */
    public ConstructorAssert isNotProtected() {
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
     * Verifies that the {@link Constructor} is <em>private</em>.
     *
     * @return This {@link ConstructorAssert} instance.
     */
    public ConstructorAssert isPrivate() {
        isNotNull();
        if (!hasPrivateModifier()) {
            throw assertionError(shouldBePrivate(actual));
        }
        return this;
    }

    /**
     * Verifies that the {@link Constructor} is not <em>private</em>.
     *
     * @return This {@link ConstructorAssert} instance.
     */
    public ConstructorAssert isNotPrivate() {
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
     * Verifies that the {@link Constructor} is <em>package-private</em>.
     *
     * @return This {@link ConstructorAssert} instance.
     */
    public ConstructorAssert isPackagePrivate() {
        isNotNull();
        if (hasPublicModifier() || hasProtectedModifier() || hasPrivateModifier()) {
            throw assertionError(shouldBePackagePrivate(actual));
        }
        return this;
    }

    /**
     * Verifies that the {@link Constructor} is not <em>package-private</em>.
     *
     * @return This {@link ConstructorAssert} instance.
     */
    public ConstructorAssert isNotPackagePrivate() {
        isNotNull();
        if (!hasPublicModifier() && !hasProtectedModifier() && !hasPrivateModifier()) {
            throw assertionError(shouldNotBePackagePrivate(actual));
        }
        return this;
    }
}
