package org.assertj.reflection;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import static org.assertj.reflection.MemberModifierShouldBe.*;

/**
 * Assertions for the {@link Field} type.
 */
public class FieldAssert extends AbstractAssert<FieldAssert, Field> {

    /**
     * Creates a new {@link FieldAssert}.
     *
     * @param actual The actual value.
     */
    protected FieldAssert(Field actual) {
        super(actual, FieldAssert.class);
    }

    /**
     * Verifies that the {@link Field} is <em>public</em>.
     *
     * @return This {@link FieldAssert} instance.
     */
    public FieldAssert isPublic() {
        isNotNull();
        if (!hasPublicModifier()) {
            throw assertionError(shouldBePublic(actual));
        }
        return this;
    }

    /**
     * Verifies that the {@link Field} is not <em>public</em>.
     *
     * @return This {@link FieldAssert} instance.
     */
    public FieldAssert isNotPublic() {
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
     * Verifies that the {@link Field} is <em>protected</em>.
     *
     * @return This {@link FieldAssert} instance.
     */
    public FieldAssert isProtected() {
        isNotNull();
        if (!hasProtectedModifier()) {
            throw assertionError(shouldBeProtected(actual));
        }
        return this;
    }

    /**
     * Verifies that the {@link Field} is not <em>protected</em>.
     *
     * @return This {@link FieldAssert} instance.
     */
    public FieldAssert isNotProtected() {
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
     * Verifies that the {@link Field} is <em>private</em>.
     *
     * @return This {@link FieldAssert} instance.
     */
    public FieldAssert isPrivate() {
        isNotNull();
        if (!hasPrivateModifier()) {
            throw assertionError(shouldBePrivate(actual));
        }
        return this;
    }

    /**
     * Verifies that the {@link Field} is not <em>private</em>.
     *
     * @return This {@link FieldAssert} instance.
     */
    public FieldAssert isNotPrivate() {
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
     * Verifies that the {@link Field} is <em>package-private</em>.
     *
     * @return This {@link FieldAssert} instance.
     */
    public FieldAssert isPackagePrivate() {
        isNotNull();
        if (hasPublicModifier() || hasProtectedModifier() || hasPrivateModifier()) {
            throw assertionError(shouldBePackagePrivate(actual));
        }
        return this;
    }

    /**
     * Verifies that the {@link Field} is not <em>package-private</em>.
     *
     * @return This {@link FieldAssert} instance.
     */
    public FieldAssert isNotPackagePrivate() {
        isNotNull();
        if (!hasPublicModifier() && !hasProtectedModifier() && !hasPrivateModifier()) {
            throw assertionError(shouldNotBePackagePrivate(actual));
        }
        return this;
    }

    /**
     * Verifies that the {@link Field} is <em>static</em>.
     *
     * @return This {@link FieldAssert} instance.
     */
    public FieldAssert isStatic() {
        isNotNull();
        if (!hasStaticModifier()) {
            throw assertionError(shouldBeStatic(actual));
        }
        return this;
    }

    /**
     * Verifies that the {@link Field} is not <em>static</em>.
     *
     * @return This {@link FieldAssert} instance.
     */
    public FieldAssert isNotStatic() {
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
     * Verifies that the {@link Field} is <em>final</em>.
     *
     * @return This {@link FieldAssert} instance.
     */
    public FieldAssert isFinal() {
        isNotNull();
        if (!hasFinalModifier()) {
            throw assertionError(shouldBeFinal(actual));
        }
        return this;
    }

    /**
     * Verifies that the {@link Field} is not <em>final</em>.
     *
     * @return This {@link FieldAssert} instance.
     */
    public FieldAssert isNotFinal() {
        isNotNull();
        if (hasFinalModifier()) {
            throw assertionError(shouldNotBeFinal(actual));
        }
        return this;
    }

    private boolean hasFinalModifier() {
        return Modifier.isFinal(actual.getModifiers());
    }

    /**
     * Verifies that the {@link Field} has the expected type.
     *
     * @param expected The expected type.
     * @return this {@link FieldAssert} instance.
     */
    public FieldAssert hasType(Class<?> expected) {
        isNotNull();
        Assertions.assertThat(actual.getType()).isEqualTo(expected);
        return this;
    }
}
