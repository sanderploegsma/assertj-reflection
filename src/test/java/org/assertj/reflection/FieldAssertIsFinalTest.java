package org.assertj.reflection;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.reflection.ReflectionAssertions.assertThat;

class FieldAssertIsFinalTest {
    @Test
    void finalField() throws NoSuchFieldException {
        var field = Subject.class.getField("field1");
        assertThat(field).isFinal();
    }

    @Test
    void nonFinalField() throws NoSuchFieldException {
        var field = Subject.class.getField("field2");
        Assertions.assertThatExceptionOfType(AssertionError.class)
                .isThrownBy(() -> assertThat(field).isFinal());
    }

    private static class Subject {
        public final int field1 = 1;
        public int field2 = 2;
    }
}
