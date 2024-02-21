package org.assertj.reflection;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.reflection.ReflectionAssertions.assertThat;

class ClassAssertHasDeclaredConstructorTest {
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
    void declaredConstructors() {
        assertThat(Super.class)
                .hasDeclaredConstructor()
                .hasDeclaredConstructor(int.class);

        assertThat(Subject.class)
                .hasDeclaredConstructor(int.class)
                .hasDeclaredConstructor(int.class, boolean.class);
    }

    @Test
    void withConstructorAssertConsumer() {
        assertThat(Super.class)
                .hasDeclaredConstructor(ConstructorAssert::isNotNull)
                .hasDeclaredConstructor(int.class, ConstructorAssert::isNotNull);

        assertThat(Subject.class)
                .hasDeclaredConstructor(int.class, ConstructorAssert::isNotNull)
                .hasDeclaredConstructor(new Class[]{int.class, boolean.class}, ConstructorAssert::isNotNull);
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
}
