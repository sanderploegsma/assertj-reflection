package org.assertj.reflection;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.reflection.ReflectionAssertions.assertThat;

class ConstructorAssertIsPrivateTest {
    @Test
    void publicConstructor() throws NoSuchMethodException {
        var constructor = Subject.class.getDeclaredConstructor();
        Assertions.assertThatExceptionOfType(AssertionError.class)
                .isThrownBy(() -> assertThat(constructor).isPrivate());
    }

    @Test
    void packagePrivateConstructor() throws NoSuchMethodException {
        var constructor = Subject.class.getDeclaredConstructor(float.class);
        Assertions.assertThatExceptionOfType(AssertionError.class)
                .isThrownBy(() -> assertThat(constructor).isPrivate());
    }

    @Test
    void protectedConstructor() throws NoSuchMethodException {
        var constructor = Subject.class.getDeclaredConstructor(int.class);
        Assertions.assertThatExceptionOfType(AssertionError.class)
                .isThrownBy(() -> assertThat(constructor).isPrivate());
    }

    @Test
    void privateConstructor() throws NoSuchMethodException {
        var constructor = Subject.class.getDeclaredConstructor(boolean.class);
        assertThat(constructor).isPrivate();
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
