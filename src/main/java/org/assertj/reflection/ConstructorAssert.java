package org.assertj.reflection;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;

import java.lang.reflect.AccessFlag;
import java.lang.reflect.Constructor;

public class ConstructorAssert extends AbstractAssert<ConstructorAssert, Constructor<?>> {
    protected ConstructorAssert(Constructor<?> actual) {
        super(actual, ConstructorAssert.class);
    }

    public ConstructorAssert isPublic() {
        isNotNull();
        Assertions.assertThat(actual.accessFlags())
                .as("Expecting constructor %s to be public", actual.toString())
                .contains(AccessFlag.PUBLIC);
        return this;
    }

    public ConstructorAssert isProtected() {
        isNotNull();
        Assertions.assertThat(actual.accessFlags())
                .as("Expecting constructor %s to be protected", actual.toString())
                .contains(AccessFlag.PROTECTED);
        return this;
    }

    public ConstructorAssert isPrivate() {
        isNotNull();
        Assertions.assertThat(actual.accessFlags())
                .as("Expecting constructor %s to be private", actual.toString())
                .contains(AccessFlag.PRIVATE);
        return this;
    }

    public ConstructorAssert isPackagePrivate() {
        isNotNull();
        Assertions.assertThat(actual.accessFlags())
                .as("Expecting constructor %s to be package-private", actual.toString())
                .doesNotContain(AccessFlag.PUBLIC, AccessFlag.PROTECTED, AccessFlag.PRIVATE);
        return this;
    }
}
