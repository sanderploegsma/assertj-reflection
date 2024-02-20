package org.assertj.reflection;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;

import java.lang.reflect.AccessFlag;
import java.lang.reflect.Constructor;

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
        Assertions.assertThat(actual.accessFlags())
                .as("Expecting constructor %s to be public", actual.toString())
                .contains(AccessFlag.PUBLIC);
        return this;
    }

    /**
     * Verifies that the {@link Constructor} is <em>protected</em>.
     *
     * @return This {@link ConstructorAssert} instance.
     */
    public ConstructorAssert isProtected() {
        isNotNull();
        Assertions.assertThat(actual.accessFlags())
                .as("Expecting constructor %s to be protected", actual.toString())
                .contains(AccessFlag.PROTECTED);
        return this;
    }

    /**
     * Verifies that the {@link Constructor} is <em>private</em>.
     *
     * @return This {@link ConstructorAssert} instance.
     */
    public ConstructorAssert isPrivate() {
        isNotNull();
        Assertions.assertThat(actual.accessFlags())
                .as("Expecting constructor %s to be private", actual.toString())
                .contains(AccessFlag.PRIVATE);
        return this;
    }

    /**
     * Verifies that the {@link Constructor} is <em>package-private</em>.
     *
     * @return This {@link ConstructorAssert} instance.
     */
    public ConstructorAssert isPackagePrivate() {
        isNotNull();
        Assertions.assertThat(actual.accessFlags())
                .as("Expecting constructor %s to be package-private", actual.toString())
                .doesNotContain(AccessFlag.PUBLIC, AccessFlag.PROTECTED, AccessFlag.PRIVATE);
        return this;
    }
}
