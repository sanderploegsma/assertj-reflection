package org.assertj.reflection;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.reflection.ReflectionAssertions.assertThat;

class ClassAssertModifiersTest {
    public class Class1 {
    }

    static final class Class2 {
    }

    protected static class Class3 {
    }

    private abstract class Class4 {
    }

    @Nested
    class IsPublic {
        @Test
        void publicClass() {
            assertThat(Class1.class).isPublic();
        }

        @Test
        void packagePrivateClass() {
            Assertions.assertThatExceptionOfType(AssertionError.class)
                    .isThrownBy(() -> assertThat(Class2.class).isPublic());
        }

        @Test
        void protectedClass() {
            Assertions.assertThatExceptionOfType(AssertionError.class)
                    .isThrownBy(() -> assertThat(Class3.class).isPublic());
        }

        @Test
        void privateClass() {
            Assertions.assertThatExceptionOfType(AssertionError.class)
                    .isThrownBy(() -> assertThat(Class4.class).isPublic());
        }
    }

    @Nested
    class IsPackagePrivate {
        @Test
        void packagePrivateClass() {
            assertThat(Class2.class).isPackagePrivate();
        }

        @Test
        void publicClass() {
            Assertions.assertThatExceptionOfType(AssertionError.class)
                    .isThrownBy(() -> assertThat(Class1.class).isPackagePrivate());
        }

        @Test
        void protectedClass() {
            Assertions.assertThatExceptionOfType(AssertionError.class)
                    .isThrownBy(() -> assertThat(Class3.class).isPackagePrivate());
        }

        @Test
        void privateClass() {
            Assertions.assertThatExceptionOfType(AssertionError.class)
                    .isThrownBy(() -> assertThat(Class4.class).isPackagePrivate());
        }
    }

    @Nested
    class IsPrivate {
        @Test
        void privateClass() {
            assertThat(Class4.class).isPrivate();
        }

        @Test
        void publicClass() {
            Assertions.assertThatExceptionOfType(AssertionError.class)
                    .isThrownBy(() -> assertThat(Class1.class).isPrivate());
        }

        @Test
        void packagePrivateClass() {
            Assertions.assertThatExceptionOfType(AssertionError.class)
                    .isThrownBy(() -> assertThat(Class2.class).isPrivate());
        }

        @Test
        void protectedClass() {
            Assertions.assertThatExceptionOfType(AssertionError.class)
                    .isThrownBy(() -> assertThat(Class3.class).isPrivate());
        }
    }

    @Nested
    class IsProtected {
        @Test
        void protectedClass() {
            assertThat(Class3.class).isProtected();
        }

        @Test
        void publicClass() {
            Assertions.assertThatExceptionOfType(AssertionError.class)
                    .isThrownBy(() -> assertThat(Class1.class).isProtected());
        }

        @Test
        void packagePrivateClass() {
            Assertions.assertThatExceptionOfType(AssertionError.class)
                    .isThrownBy(() -> assertThat(Class2.class).isProtected());
        }

        @Test
        void privateClass() {
            Assertions.assertThatExceptionOfType(AssertionError.class)
                    .isThrownBy(() -> assertThat(Class4.class).isPublic());
        }
    }

    @Nested
    class IsAbstract {
        @Test
        void abstractClass() {
            assertThat(Class4.class).isAbstract();
        }

        @ParameterizedTest
        @ValueSource(classes = {Class1.class, Class2.class, Class3.class})
        void nonAbstractClass(Class<?> clazz) {
            Assertions.assertThatExceptionOfType(AssertionError.class)
                    .isThrownBy(() -> assertThat(clazz).isAbstract());
        }
    }

    @Nested
    class IsStatic {
        @ParameterizedTest
        @ValueSource(classes = {Class2.class, Class3.class})
        void staticClass(Class<?> clazz) {
            assertThat(clazz).isStatic();
        }

        @ParameterizedTest
        @ValueSource(classes = {Class1.class, Class4.class})
        void nonStaticClass(Class<?> clazz) {
            Assertions.assertThatExceptionOfType(AssertionError.class)
                    .isThrownBy(() -> assertThat(clazz).isStatic());
        }
    }

    @Nested
    class IsFinal {
        @Test
        void finalClass() {
            assertThat(Class2.class).isFinal();
        }

        @ParameterizedTest
        @ValueSource(classes = {Class1.class, Class3.class, Class4.class})
        void nonFinalClass(Class<?> clazz) {
            Assertions.assertThatExceptionOfType(AssertionError.class)
                    .isThrownBy(() -> assertThat(clazz).isFinal());
        }
    }
}
