package org.assertj.reflection;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.reflection.ReflectionAssertions.assertThat;

class ClassAssertHasNoDeclaredConstructorTest {
    private static class Super {
        Super() {
        }

        Super(int arg) {
        }
    }

    private static class Subject extends Super {
        Subject(int arg) {
            this(arg, false);
        }

        Subject(int arg, boolean arg2) {
            super(arg);
        }
    }

    @Test
    void declaredConstructor() {
        Assertions.assertThatExceptionOfType(AssertionError.class)
                .isThrownBy(() -> assertThat(Subject.class).hasNoDeclaredConstructor(int.class))
                .withMessage("Expected org.assertj.reflection.ClassAssertHasNoDeclaredConstructorTest$Subject " +
                        "not to have declared constructor Subject(int) but found " +
                        "org.assertj.reflection.ClassAssertHasNoDeclaredConstructorTest$Subject(int)");
    }

    @Test
    void constructorInheritedFromSuper() {
        assertThat(Subject.class).hasNoDeclaredConstructor();
    }

    @Test
    void nonExistingConstructor() {
        assertThat(Subject.class).hasNoDeclaredConstructor(boolean.class);
    }
}
