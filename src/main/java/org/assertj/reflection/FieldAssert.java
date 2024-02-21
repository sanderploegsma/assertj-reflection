package org.assertj.reflection;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;

import java.lang.reflect.AccessFlag;
import java.lang.reflect.Field;

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
        Assertions.assertThat(actual.accessFlags())
                .as("Expecting field %s to be public", actual.toString())
                .contains(AccessFlag.PUBLIC);
        return this;
    }

    /**
     * Verifies that the {@link Field} is <em>protected</em>.
     *
     * @return This {@link FieldAssert} instance.
     */
    public FieldAssert isProtected() {
        isNotNull();
        Assertions.assertThat(actual.accessFlags())
                .as("Expecting field %s to be protected", actual.toString())
                .contains(AccessFlag.PROTECTED);
        return this;
    }

    /**
     * Verifies that the {@link Field} is <em>private</em>.
     *
     * @return This {@link FieldAssert} instance.
     */
    public FieldAssert isPrivate() {
        isNotNull();
        Assertions.assertThat(actual.accessFlags())
                .as("Expecting field %s to be private", actual.toString())
                .contains(AccessFlag.PRIVATE);
        return this;
    }

    /**
     * Verifies that the {@link Field} is <em>package-private</em>.
     *
     * @return This {@link FieldAssert} instance.
     */
    public FieldAssert isPackagePrivate() {
        isNotNull();
        Assertions.assertThat(actual.accessFlags())
                .as("Expecting field %s to be package-private", actual.toString())
                .doesNotContain(AccessFlag.PUBLIC, AccessFlag.PROTECTED, AccessFlag.PRIVATE);
        return this;
    }

    /**
     * Verifies that the {@link Field} is <em>final</em>.
     *
     * @return This {@link FieldAssert} instance.
     */
    public FieldAssert isFinal() {
        isNotNull();
        Assertions.assertThat(actual.accessFlags())
                .as("Expecting field %s to be final", actual.toString())
                .contains(AccessFlag.FINAL);
        return this;
    }

    /**
     * Verifies that the {@link Field} is <em>static</em>.
     *
     * @return This {@link FieldAssert} instance.
     */
    public FieldAssert isStatic() {
        isNotNull();
        Assertions.assertThat(actual.accessFlags())
                .as("Expecting field %s to be static", actual.toString())
                .contains(AccessFlag.STATIC);
        return this;
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
