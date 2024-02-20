package org.assertj.reflection;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.reflection.ReflectionAssertions.assertThat;

class ConstructorAssertModifiersTest {
    @Nested
    class IsPublic {
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
    }

    @Nested
    class IsPackagePrivate {
        @Test
        void packagePrivateConstructor() throws NoSuchMethodException {
            var constructor = Subject.class.getDeclaredConstructor(float.class);
            assertThat(constructor).isPackagePrivate();
        }

        @Test
        void publicConstructor() throws NoSuchMethodException {
            var constructor = Subject.class.getDeclaredConstructor();
            Assertions.assertThatExceptionOfType(AssertionError.class)
                    .isThrownBy(() -> assertThat(constructor).isPackagePrivate());
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
    }

    @Nested
    class IsPrivate {
        @Test
        void privateConstructor() throws NoSuchMethodException {
            var constructor = Subject.class.getDeclaredConstructor(boolean.class);
            assertThat(constructor).isPrivate();
        }

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
    }

    @Nested
    class IsProtected {
        @Test
        void protectedConstructor() throws NoSuchMethodException {
            var constructor = Subject.class.getDeclaredConstructor(int.class);
            assertThat(constructor).isProtected();
        }

        @Test
        void publicConstructor() throws NoSuchMethodException {
            var constructor = Subject.class.getDeclaredConstructor();
            Assertions.assertThatExceptionOfType(AssertionError.class)
                    .isThrownBy(() -> assertThat(constructor).isProtected());
        }

        @Test
        void packagePrivateConstructor() throws NoSuchMethodException {
            var constructor = Subject.class.getDeclaredConstructor(float.class);
            Assertions.assertThatExceptionOfType(AssertionError.class)
                    .isThrownBy(() -> assertThat(constructor).isProtected());
        }

        @Test
        void privateConstructor() throws NoSuchMethodException {
            var constructor = Subject.class.getDeclaredConstructor(boolean.class);
            Assertions.assertThatExceptionOfType(AssertionError.class)
                    .isThrownBy(() -> assertThat(constructor).isPublic());
        }
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
