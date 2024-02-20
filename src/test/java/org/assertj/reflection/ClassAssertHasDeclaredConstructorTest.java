package org.assertj.reflection;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.reflection.ReflectionAssertions.assertThat;

class ClassAssertHasDeclaredConstructorTest {
    @Test
    void declaredConstructors() {
        assertThat(Subject.class)
                .hasDeclaredConstructor(int.class)
                .hasDeclaredConstructor(new Class[]{int.class, boolean.class});
    }

    @Test
    void withConstructorAssertConsumer() {
        assertThat(Subject.class).hasDeclaredConstructor(int.class, ConstructorAssert::isNotNull);
    }

    @Test
    void constructorInheritedFromSuper() {
        Assertions.assertThatExceptionOfType(AssertionError.class)
                .isThrownBy(() -> assertThat(Subject.class).hasDeclaredConstructor())
                .withMessage("Expected org.assertj.reflection.ClassAssertHasDeclaredConstructorTest$Subject to have declared constructor Subject() but no such constructor exists");
    }

    @Test
    void nonExistingConstructor() {
        Assertions.assertThatExceptionOfType(AssertionError.class)
                .isThrownBy(() -> assertThat(Subject.class).hasDeclaredConstructor(boolean.class))
                .withMessage("Expected org.assertj.reflection.ClassAssertHasDeclaredConstructorTest$Subject to have declared constructor Subject(boolean) but no such constructor exists");
    }

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
}
