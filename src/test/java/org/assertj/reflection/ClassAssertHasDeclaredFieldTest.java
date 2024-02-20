package org.assertj.reflection;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.reflection.ReflectionAssertions.assertThat;

class ClassAssertHasDeclaredFieldTest {
    private static class Super {
        public int field1 = 1;
    }

    private static class Subject extends Super {
        public int field2 = 2;
        public static int field3 = 3;
    }

    @Test
    void declaredFields() {
        assertThat(Subject.class)
                .hasDeclaredField("field2")
                .hasDeclaredField("field3");
    }

    @Test
    void withFieldAssertConsumer() {
        assertThat(Subject.class).hasDeclaredField("field2", FieldAssert::isNotNull);
    }

    @Test
    void fieldInheritedFromSuper() {
        Assertions.assertThatExceptionOfType(AssertionError.class)
                .isThrownBy(() -> assertThat(Subject.class).hasDeclaredField("field1"))
                .withMessage("Expected org.assertj.reflection.ClassAssertHasDeclaredFieldTest$Subject to have declared field field1 but no such field exists");
    }

    @Test
    void nonExistingField() {
        Assertions.assertThatExceptionOfType(AssertionError.class)
                .isThrownBy(() -> assertThat(Subject.class).hasDeclaredField("field4"))
                .withMessage("Expected org.assertj.reflection.ClassAssertHasDeclaredFieldTest$Subject to have declared field field4 but no such field exists");
    }
}
