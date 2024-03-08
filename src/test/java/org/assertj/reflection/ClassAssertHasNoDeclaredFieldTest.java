package org.assertj.reflection;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.reflection.ReflectionAssertions.assertThat;

class ClassAssertHasNoDeclaredFieldTest {
    private static class Super {
        public int field1 = 1;
    }

    private static class Subject extends Super {
        public int field2 = 2;
        public static int field3 = 3;
    }

    @Test
    void declaredField() {
        Assertions.assertThatExceptionOfType(AssertionError.class)
                .isThrownBy(() -> assertThat(Subject.class).hasNoDeclaredField("field2"))
                .withMessage("Expected org.assertj.reflection.ClassAssertHasNoDeclaredFieldTest$Subject " +
                        "not to have declared field field2 but found public int " +
                        "org.assertj.reflection.ClassAssertHasNoDeclaredFieldTest$Subject.field2");
    }

    @Test
    void fieldInheritedFromSuper() {
        assertThat(Subject.class).hasNoDeclaredField("field1");
    }

    @Test
    void nonExistingField() {
        assertThat(Subject.class).hasNoDeclaredField("field4");
    }
}
