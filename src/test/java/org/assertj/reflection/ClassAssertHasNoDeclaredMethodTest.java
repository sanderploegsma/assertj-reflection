package org.assertj.reflection;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.reflection.ReflectionAssertions.assertThat;

class ClassAssertHasNoDeclaredMethodTest {
    @Test
    void declaredMethod() {
        Assertions.assertThatExceptionOfType(AssertionError.class)
                .isThrownBy(() -> assertThat(Subject.class).hasNoDeclaredMethod("methodOnSubject"))
                .withMessage("Expected org.assertj.reflection.ClassAssertHasNoDeclaredMethodTest$Subject " +
                        "not to have declared method methodOnSubject() but found void " +
                        "org.assertj.reflection.ClassAssertHasNoDeclaredMethodTest$Subject.methodOnSubject()");
    }

    @Test
    void methodInheritedFromSuper() {
        assertThat(Subject.class)
                .hasNoDeclaredMethod("methodOnSuper")
                .hasNoDeclaredMethod("equals", new Class[]{Object.class});
    }

    @Test
    void nonExistingMethod() {
        assertThat(Subject.class).hasNoDeclaredMethod("methodOnSubject", new Class[]{boolean.class});
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
