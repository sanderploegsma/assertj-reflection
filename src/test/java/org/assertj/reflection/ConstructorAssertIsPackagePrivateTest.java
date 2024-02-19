package org.assertj.reflection;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.reflection.ReflectionAssertions.assertThat;

class ConstructorAssertIsPackagePrivateTest {
    @Test
    void publicConstructor() throws NoSuchMethodException {
        var constructor = Subject.class.getDeclaredConstructor();
        Assertions.assertThatExceptionOfType(AssertionError.class)
                .isThrownBy(() -> assertThat(constructor).isPackagePrivate());
    }

    @Test
    void packagePrivateConstructor() throws NoSuchMethodException {
        var constructor = Subject.class.getDeclaredConstructor(float.class);
        assertThat(constructor).isPackagePrivate();
    }

    @Test
    void protectedConstructor() throws NoSuchMethodException {
        var constructor = Subject.class.getDeclaredConstructor(int.class);
        Assertions.assertThatExceptionOfType(AssertionError.class)
                .isThrownBy(() -> assertThat(constructor).isPackagePrivate());
    }

    @Test
    void privateConstructor() throws NoSuchMethodException {
        var constructor = Subject.class.getDeclaredConstructor(boolean.class);
        Assertions.assertThatExceptionOfType(AssertionError.class)
                .isThrownBy(() -> assertThat(constructor).isPackagePrivate());
    }

    private static class Subject {
        public Subject() {
        }

        Subject(float arg) {
        }

        protected Subject(int arg) {
        }

        private Subject(boolean arg) {
        }
    }
}
