package org.assertj.reflection;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;

import java.lang.reflect.AccessFlag;
import java.lang.reflect.Field;

public class FieldAssert extends AbstractAssert<FieldAssert, Field> {
    protected FieldAssert(Field actual) {
        super(actual, FieldAssert.class);
    }

    public FieldAssert isFinal() {
        isNotNull();
        Assertions.assertThat(actual.accessFlags())
                .as("Expecting field %s to be final", actual.toString())
                .contains(AccessFlag.FINAL);
        return this;
    }
}
