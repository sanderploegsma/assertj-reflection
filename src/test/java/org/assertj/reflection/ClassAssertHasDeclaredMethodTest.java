package org.assertj.reflection;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.reflection.ReflectionAssert.assertThat;

class ClassAssertHasDeclaredMethodTest {
    @Test
    void declaredMethods() {
        assertThat(Subject.class).hasDeclaredMethod("methodOnSubject");
        assertThat(Subject.class).hasDeclaredMethod("methodOnSubject", int.class);
        assertThat(Subject.class).hasDeclaredMethod("methodOnBoth");
        assertThat(Subject.class).hasDeclaredMethod("toString");
    }

    @Test
    void methodInheritedFromSuper() {
        Assertions.assertThatExceptionOfType(AssertionError.class)
                .isThrownBy(() -> assertThat(Subject.class).hasDeclaredMethod("methodOnSuper"))
                .withMessage("Expected org.assertj.reflection.ClassAssertHasDeclaredMethodTest$Subject to have declared method methodOnSuper() but no such method exists");

        Assertions.assertThatExceptionOfType(AssertionError.class)
                .isThrownBy(() -> assertThat(Subject.class).hasDeclaredMethod("equals", Object.class))
                .withMessage("Expected org.assertj.reflection.ClassAssertHasDeclaredMethodTest$Subject to have declared method equals(java.lang.Object) but no such method exists");
    }

    @Test
    void nonExistingMethod() {
        Assertions.assertThatExceptionOfType(AssertionError.class)
                .isThrownBy(() -> assertThat(Subject.class).hasDeclaredMethod("methodOnSubject", boolean.class))
                .withMessage("Expected org.assertj.reflection.ClassAssertHasDeclaredMethodTest$Subject to have declared method methodOnSubject(boolean) but no such method exists");
    }

    private static class Super {
        void methodOnSuper() {
        }

        void methodOnBoth() {
        }
    }

    private static class Subject extends Super {
        void methodOnSubject() {
        }

        void methodOnSubject(int arg) {
        }

        @Override
        void methodOnBoth() {
            super.methodOnBoth();
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }
}
