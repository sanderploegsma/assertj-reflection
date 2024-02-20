package org.assertj.reflection;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.reflection.ReflectionAssertions.assertThat;

class MethodAssertModifiersTest {
    private static class Subject {
        public static void method1() {}
        static void method2() {}
        protected void method3() {}
        private void method4() {}
    }

    @Nested
    class IsPublic {
        @Test
        void publicMethod() throws NoSuchMethodException {
            var method = Subject.class.getDeclaredMethod("method1");
            assertThat(method).isPublic();
        }

        @Test
        void packagePrivateMethod() throws NoSuchMethodException {
            var method = Subject.class.getDeclaredMethod("method2");
            Assertions.assertThatExceptionOfType(AssertionError.class)
                    .isThrownBy(() -> assertThat(method).isPublic());
        }

        @Test
        void protectedMethod() throws NoSuchMethodException {
            var method = Subject.class.getDeclaredMethod("method3");
            Assertions.assertThatExceptionOfType(AssertionError.class)
                    .isThrownBy(() -> assertThat(method).isPublic());
        }

        @Test
        void privateMethod() throws NoSuchMethodException {
            var method = Subject.class.getDeclaredMethod("method4");
            Assertions.assertThatExceptionOfType(AssertionError.class)
                    .isThrownBy(() -> assertThat(method).isPublic());
        }
    }

    @Nested
    class IsPackagePrivate {
        @Test
        void packagePrivateMethod() throws NoSuchMethodException {
            var method = Subject.class.getDeclaredMethod("method2");
            assertThat(method).isPackagePrivate();
        }

        @Test
        void publicMethod() throws NoSuchMethodException {
            var method = Subject.class.getDeclaredMethod("method1");
            Assertions.assertThatExceptionOfType(AssertionError.class)
                    .isThrownBy(() -> assertThat(method).isPackagePrivate());
        }

        @Test
        void protectedMethod() throws NoSuchMethodException {
            var method = Subject.class.getDeclaredMethod("method3");
            Assertions.assertThatExceptionOfType(AssertionError.class)
                    .isThrownBy(() -> assertThat(method).isPackagePrivate());
        }

        @Test
        void privateMethod() throws NoSuchMethodException {
            var method = Subject.class.getDeclaredMethod("method4");
            Assertions.assertThatExceptionOfType(AssertionError.class)
                    .isThrownBy(() -> assertThat(method).isPackagePrivate());
        }
    }

    @Nested
    class IsPrivate {
        @Test
        void privateMethod() throws NoSuchMethodException {
            var method = Subject.class.getDeclaredMethod("method4");
            assertThat(method).isPrivate();
        }

        @Test
        void publicMethod() throws NoSuchMethodException {
            var method = Subject.class.getDeclaredMethod("method1");
            Assertions.assertThatExceptionOfType(AssertionError.class)
                    .isThrownBy(() -> assertThat(method).isPrivate());
        }

        @Test
        void packagePrivateMethod() throws NoSuchMethodException {
            var method = Subject.class.getDeclaredMethod("method2");
            Assertions.assertThatExceptionOfType(AssertionError.class)
                    .isThrownBy(() -> assertThat(method).isPrivate());
        }

        @Test
        void protectedMethod() throws NoSuchMethodException {
            var method = Subject.class.getDeclaredMethod("method3");
            Assertions.assertThatExceptionOfType(AssertionError.class)
                    .isThrownBy(() -> assertThat(method).isPrivate());
        }
    }

    @Nested
    class IsProtected {
        @Test
        void protectedMethod() throws NoSuchMethodException {
            var method = Subject.class.getDeclaredMethod("method3");
            assertThat(method).isProtected();
        }

        @Test
        void publicMethod() throws NoSuchMethodException {
            var method = Subject.class.getDeclaredMethod("method1");
            Assertions.assertThatExceptionOfType(AssertionError.class)
                    .isThrownBy(() -> assertThat(method).isProtected());
        }

        @Test
        void packagePrivateMethod() throws NoSuchMethodException {
            var method = Subject.class.getDeclaredMethod("method2");
            Assertions.assertThatExceptionOfType(AssertionError.class)
                    .isThrownBy(() -> assertThat(method).isProtected());
        }

        @Test
        void privateMethod() throws NoSuchMethodException {
            var method = Subject.class.getDeclaredMethod("method4");
            Assertions.assertThatExceptionOfType(AssertionError.class)
                    .isThrownBy(() -> assertThat(method).isPublic());
        }
    }

    @Nested
    class IsStatic {
        @ParameterizedTest
        @ValueSource(strings = {"method1", "method2"})
        void staticMethod(String methodName) throws NoSuchMethodException {
            var method = Subject.class.getDeclaredMethod(methodName);
            assertThat(method).isStatic();
        }

        @ParameterizedTest
        @ValueSource(strings = {"method3", "method4"})
        void nonStaticMethod(String methodName) throws NoSuchMethodException {
            var method = Subject.class.getDeclaredMethod(methodName);
            Assertions.assertThatExceptionOfType(AssertionError.class)
                    .isThrownBy(() -> assertThat(method).isStatic());
        }
    }
}
