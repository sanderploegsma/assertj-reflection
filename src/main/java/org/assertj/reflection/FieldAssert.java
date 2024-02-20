package org.assertj.reflection;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;

import java.lang.reflect.AccessFlag;
import java.lang.reflect.Field;

public class FieldAssert extends AbstractAssert<FieldAssert, Field> {
    protected FieldAssert(Field actual) {
        super(actual, FieldAssert.class);
    }

    public FieldAssert isPublic() {
        isNotNull();
        Assertions.assertThat(actual.accessFlags())
                .as("Expecting field %s to be public", actual.toString())
                .contains(AccessFlag.PUBLIC);
        return this;
    }

    public FieldAssert isProtected() {
        isNotNull();
        Assertions.assertThat(actual.accessFlags())
                .as("Expecting field %s to be protected", actual.toString())
                .contains(AccessFlag.PROTECTED);
        return this;
    }

    public FieldAssert isPrivate() {
        isNotNull();
        Assertions.assertThat(actual.accessFlags())
                .as("Expecting field %s to be private", actual.toString())
                .contains(AccessFlag.PRIVATE);
        return this;
    }

    public FieldAssert isPackagePrivate() {
        isNotNull();
        Assertions.assertThat(actual.accessFlags())
                .as("Expecting field %s to be package-private", actual.toString())
                .doesNotContain(AccessFlag.PUBLIC, AccessFlag.PROTECTED, AccessFlag.PRIVATE);
        return this;
    }

    public FieldAssert isFinal() {
        isNotNull();
        Assertions.assertThat(actual.accessFlags())
                .as("Expecting field %s to be final", actual.toString())
                .contains(AccessFlag.FINAL);
        return this;
    }

    public FieldAssert isStatic() {
        isNotNull();
        Assertions.assertThat(actual.accessFlags())
                .as("Expecting field %s to be static", actual.toString())
                .contains(AccessFlag.STATIC);
        return this;
    }
}
