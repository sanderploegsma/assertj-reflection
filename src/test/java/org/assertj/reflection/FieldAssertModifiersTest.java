package org.assertj.reflection;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.reflection.ReflectionAssertions.assertThat;

class FieldAssertModifiersTest {
    private static class Subject {
        public final int field1 = 1;
        int field2 = 2;
        protected static int field3 = 3;
        private final int field4 = 4;
    }

    @Nested
    class IsPublic {
        @Test
        void publicField() throws NoSuchFieldException {
            var field = Subject.class.getDeclaredField("field1");
            assertThat(field).isPublic();
        }

        @Test
        void packagePrivateField() throws NoSuchFieldException {
            var field = Subject.class.getDeclaredField("field2");
            Assertions.assertThatExceptionOfType(AssertionError.class)
                    .isThrownBy(() -> assertThat(field).isPublic());
        }

        @Test
        void protectedField() throws NoSuchFieldException {
            var field = Subject.class.getDeclaredField("field3");
            Assertions.assertThatExceptionOfType(AssertionError.class)
                    .isThrownBy(() -> assertThat(field).isPublic());
        }

        @Test
        void privateField() throws NoSuchFieldException {
            var field = Subject.class.getDeclaredField("field4");
            Assertions.assertThatExceptionOfType(AssertionError.class)
                    .isThrownBy(() -> assertThat(field).isPublic());
        }
    }

    @Nested
    class IsPackagePrivate {
        @Test
        void packagePrivateField() throws NoSuchFieldException {
            var field = Subject.class.getDeclaredField("field2");
            assertThat(field).isPackagePrivate();
        }

        @Test
        void publicField() throws NoSuchFieldException {
            var field = Subject.class.getDeclaredField("field1");
            Assertions.assertThatExceptionOfType(AssertionError.class)
                    .isThrownBy(() -> assertThat(field).isPackagePrivate());
        }

        @Test
        void protectedField() throws NoSuchFieldException {
            var field = Subject.class.getDeclaredField("field3");
            Assertions.assertThatExceptionOfType(AssertionError.class)
                    .isThrownBy(() -> assertThat(field).isPackagePrivate());
        }

        @Test
        void privateField() throws NoSuchFieldException {
            var field = Subject.class.getDeclaredField("field4");
            Assertions.assertThatExceptionOfType(AssertionError.class)
                    .isThrownBy(() -> assertThat(field).isPackagePrivate());
        }
    }

    @Nested
    class IsPrivate {
        @Test
        void privateField() throws NoSuchFieldException {
            var field = Subject.class.getDeclaredField("field4");
            assertThat(field).isPrivate();
        }

        @Test
        void publicField() throws NoSuchFieldException {
            var field = Subject.class.getDeclaredField("field1");
            Assertions.assertThatExceptionOfType(AssertionError.class)
                    .isThrownBy(() -> assertThat(field).isPrivate());
        }

        @Test
        void packagePrivateField() throws NoSuchFieldException {
            var field = Subject.class.getDeclaredField("field2");
            Assertions.assertThatExceptionOfType(AssertionError.class)
                    .isThrownBy(() -> assertThat(field).isPrivate());
        }

        @Test
        void protectedField() throws NoSuchFieldException {
            var field = Subject.class.getDeclaredField("field3");
            Assertions.assertThatExceptionOfType(AssertionError.class)
                    .isThrownBy(() -> assertThat(field).isPrivate());
        }
    }

    @Nested
    class IsProtected {
        @Test
        void protectedField() throws NoSuchFieldException {
            var field = Subject.class.getDeclaredField("field3");
            assertThat(field).isProtected();
        }

        @Test
        void publicField() throws NoSuchFieldException {
            var field = Subject.class.getDeclaredField("field1");
            Assertions.assertThatExceptionOfType(AssertionError.class)
                    .isThrownBy(() -> assertThat(field).isProtected());
        }

        @Test
        void packagePrivateField() throws NoSuchFieldException {
            var field = Subject.class.getDeclaredField("field2");
            Assertions.assertThatExceptionOfType(AssertionError.class)
                    .isThrownBy(() -> assertThat(field).isProtected());
        }

        @Test
        void privateField() throws NoSuchFieldException {
            var field = Subject.class.getDeclaredField("field4");
            Assertions.assertThatExceptionOfType(AssertionError.class)
                    .isThrownBy(() -> assertThat(field).isPublic());
        }
    }

    @Nested
    class IsFinal {
        @ParameterizedTest
        @ValueSource(strings = {"field1", "field4"})
        void finalField(String fieldName) throws NoSuchFieldException {
            var field = Subject.class.getDeclaredField(fieldName);
            assertThat(field).isFinal();
        }

        @ParameterizedTest
        @ValueSource(strings = {"field2", "field3"})
        void nonFinalField(String fieldName) throws NoSuchFieldException {
            var field = Subject.class.getDeclaredField(fieldName);
            Assertions.assertThatExceptionOfType(AssertionError.class)
                    .isThrownBy(() -> assertThat(field).isFinal());
        }
    }

    @Nested
    class IsStatic {
        @Test
        void staticField() throws NoSuchFieldException {
            var field = Subject.class.getDeclaredField("field3");
            assertThat(field).isStatic();
        }

        @ParameterizedTest
        @ValueSource(strings = {"field1", "field2", "field4"})
        void nonStaticField(String fieldName) throws NoSuchFieldException {
            var field = Subject.class.getDeclaredField(fieldName);
            Assertions.assertThatExceptionOfType(AssertionError.class)
                    .isThrownBy(() -> assertThat(field).isStatic());
        }
    }
}
