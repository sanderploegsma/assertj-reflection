package org.assertj.reflection;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.reflection.ReflectionAssertions.assertThat;

class ConstructorAssertIsPublicTest {
    @Test
    void publicConstructor() throws NoSuchMethodException {
        var constructor = Subject.class.getDeclaredConstructor();
        assertThat(constructor).isPublic();
    }

    @Test
    void packagePrivateConstructor() throws NoSuchMethodException {
        var constructor = Subject.class.getDeclaredConstructor(float.class);
        Assertions.assertThatExceptionOfType(AssertionError.class)
                .isThrownBy(() -> assertThat(constructor).isPublic());
    }

    @Test
    void protectedConstructor() throws NoSuchMethodException {
        var constructor = Subject.class.getDeclaredConstructor(int.class);
        Assertions.assertThatExceptionOfType(AssertionError.class)
                .isThrownBy(() -> assertThat(constructor).isPublic());
    }

    @Test
    void privateConstructor() throws NoSuchMethodException {
        var constructor = Subject.class.getDeclaredConstructor(boolean.class);
        Assertions.assertThatExceptionOfType(AssertionError.class)
                .isThrownBy(() -> assertThat(constructor).isPublic());
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
