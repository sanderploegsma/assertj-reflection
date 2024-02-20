package org.assertj.reflection;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;

import java.lang.reflect.AccessFlag;
import java.lang.reflect.Method;

public class MethodAssert extends AbstractAssert<MethodAssert, Method> {
    protected MethodAssert(Method actual) {
        super(actual, MethodAssert.class);
    }

    public MethodAssert isPublic() {
        isNotNull();
        Assertions.assertThat(actual.accessFlags())
                .as("Expecting method %s to be public", actual.toString())
                .contains(AccessFlag.PUBLIC);
        return this;
    }

    public MethodAssert isProtected() {
        isNotNull();
        Assertions.assertThat(actual.accessFlags())
                .as("Expecting method %s to be protected", actual.toString())
                .contains(AccessFlag.PROTECTED);
        return this;
    }

    public MethodAssert isPrivate() {
        isNotNull();
        Assertions.assertThat(actual.accessFlags())
                .as("Expecting method %s to be private", actual.toString())
                .contains(AccessFlag.PRIVATE);
        return this;
    }

    public MethodAssert isPackagePrivate() {
        isNotNull();
        Assertions.assertThat(actual.accessFlags())
                .as("Expecting method %s to be package-private", actual.toString())
                .doesNotContain(AccessFlag.PUBLIC, AccessFlag.PROTECTED, AccessFlag.PRIVATE);
        return this;
    }

    public MethodAssert isStatic() {
        isNotNull();
        Assertions.assertThat(actual.accessFlags())
                .as("Expecting method %s to be static", actual.toString())
                .contains(AccessFlag.STATIC);
        return this;
    }

    public MethodAssert hasReturnType(Class<?> expected) {
        isNotNull();
        Assertions.assertThat(actual.getReturnType()).isEqualTo(expected);
        return this;
    }
}
