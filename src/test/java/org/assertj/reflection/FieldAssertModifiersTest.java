package org.assertj.reflection;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.ThrowableAssert;
import org.assertj.core.api.ThrowableAssertAlternative;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.lang.reflect.Field;

import static org.assertj.reflection.MemberModifierShouldBe.*;
import static org.assertj.reflection.ReflectionAssertions.assertThat;

class FieldAssertModifiersTest {
    private static class Subject {
        public final int field1 = 1;
        protected int field2 = 2;
        private static int field3 = 3;
        final int field4 = 4;
    }

    @Nested
    class IsPublic {
        @Test
        void shouldFailIfActualIsNull() {
            Field actual = null;
            expectAssertionError(() -> assertThat(actual).isPublic());
        }

        @ParameterizedTest
        @ValueSource(strings = {"field2", "field3", "field4"})
        void shouldFailIfActualIsNotPublic(String fieldName) throws NoSuchFieldException {
            Field actual = Subject.class.getDeclaredField(fieldName);
            expectAssertionError(() -> assertThat(actual).isPublic())
                    .withMessage(shouldBePublic(actual).create());
        }

        @Test
        void shouldPassIfActualIsPublic() throws NoSuchFieldException {
            Field actual = Subject.class.getDeclaredField("field1");
            assertThat(actual).isPublic();
        }
    }

    @Nested
    class IsNotPublic {
        @Test
        void shouldFailIfActualIsNull() {
            Field actual = null;
            expectAssertionError(() -> assertThat(actual).isNotPublic());
        }

        @Test
        void shouldFailIfActualIsPublic() throws NoSuchFieldException {
            Field actual = Subject.class.getDeclaredField("field1");
            expectAssertionError(() -> assertThat(actual).isNotPublic())
                    .withMessage(shouldNotBePublic(actual).create());
        }

        @ParameterizedTest
        @ValueSource(strings = {"field2", "field3", "field4"})
        void shouldPassIfActualIsNotPublic(String fieldName) throws NoSuchFieldException {
            Field actual = Subject.class.getDeclaredField(fieldName);
            assertThat(actual).isNotPublic();
        }
    }

    @Nested
    class IsProtected {
        @Test
        void shouldFailIfActualIsNull() {
            Field actual = null;
            expectAssertionError(() -> assertThat(actual).isProtected());
        }

        @ParameterizedTest
        @ValueSource(strings = {"field1", "field3", "field4"})
        void shouldFailIfActualIsNotProtected(String fieldName) throws NoSuchFieldException {
            Field actual = Subject.class.getDeclaredField(fieldName);
            expectAssertionError(() -> assertThat(actual).isProtected())
                    .withMessage(shouldBeProtected(actual).create());
        }

        @Test
        void shouldPassIfActualIsProtected() throws NoSuchFieldException {
            Field actual = Subject.class.getDeclaredField("field2");
            assertThat(actual).isProtected();
        }
    }

    @Nested
    class IsNotProtected {
        @Test
        void shouldFailIfActualIsNull() {
            Field actual = null;
            expectAssertionError(() -> assertThat(actual).isNotProtected());
        }

        @Test
        void shouldFailIfActualIsProtected() throws NoSuchFieldException {
            Field actual = Subject.class.getDeclaredField("field2");
            expectAssertionError(() -> assertThat(actual).isNotProtected())
                    .withMessage(shouldNotBeProtected(actual).create());
        }

        @ParameterizedTest
        @ValueSource(strings = {"field1", "field3", "field4"})
        void shouldPassIfActualIsNotProtected(String fieldName) throws NoSuchFieldException {
            Field actual = Subject.class.getDeclaredField(fieldName);
            assertThat(actual).isNotProtected();
        }
    }

    @Nested
    class IsPrivate {
        @Test
        void shouldFailIfActualIsNull() {
            Field actual = null;
            expectAssertionError(() -> assertThat(actual).isPrivate());
        }

        @ParameterizedTest
        @ValueSource(strings = {"field1", "field2", "field4"})
        void shouldFailIfActualIsNotPrivate(String fieldName) throws NoSuchFieldException {
            Field actual = Subject.class.getDeclaredField(fieldName);
            expectAssertionError(() -> assertThat(actual).isPrivate())
                    .withMessage(shouldBePrivate(actual).create());
        }

        @Test
        void shouldPassIfActualIsPrivate() throws NoSuchFieldException {
            Field actual = Subject.class.getDeclaredField("field3");
            assertThat(actual).isPrivate();
        }
    }

    @Nested
    class IsNotPrivate {
        @Test
        void shouldFailIfActualIsNull() {
            Field actual = null;
            expectAssertionError(() -> assertThat(actual).isNotPrivate());
        }

        @Test
        void shouldFailIfActualIsPrivate() throws NoSuchFieldException {
            Field actual = Subject.class.getDeclaredField("field3");
            expectAssertionError(() -> assertThat(actual).isNotPrivate())
                    .withMessage(shouldNotBePrivate(actual).create());
        }

        @ParameterizedTest
        @ValueSource(strings = {"field1", "field2", "field4"})
        void shouldPassIfActualIsNotPrivate(String fieldName) throws NoSuchFieldException {
            Field actual = Subject.class.getDeclaredField(fieldName);
            assertThat(actual).isNotPrivate();
        }
    }

    @Nested
    class IsPackagePrivate {
        @Test
        void shouldFailIfActualIsNull() {
            Field actual = null;
            expectAssertionError(() -> assertThat(actual).isPackagePrivate());
        }

        @ParameterizedTest
        @ValueSource(strings = {"field1", "field2", "field3"})
        void shouldFailIfActualIsNotPackagePrivate(String fieldName) throws NoSuchFieldException {
            Field actual = Subject.class.getDeclaredField(fieldName);
            expectAssertionError(() -> assertThat(actual).isPackagePrivate())
                    .withMessage(shouldBePackagePrivate(actual).create());
        }

        @Test
        void shouldPassIfActualIsPackagePrivate() throws NoSuchFieldException {
            Field actual = Subject.class.getDeclaredField("field4");
            assertThat(actual).isPackagePrivate();
        }
    }

    @Nested
    class IsNotPackagePrivate {
        @Test
        void shouldFailIfActualIsNull() {
            Field actual = null;
            expectAssertionError(() -> assertThat(actual).isNotPackagePrivate());
        }

        @Test
        void shouldFailIfActualIsPackagePrivate() throws NoSuchFieldException {
            Field actual = Subject.class.getDeclaredField("field4");
            expectAssertionError(() -> assertThat(actual).isNotPackagePrivate())
                    .withMessage(shouldNotBePackagePrivate(actual).create());
        }

        @ParameterizedTest
        @ValueSource(strings = {"field1", "field2", "field3"})
        void shouldPassIfActualIsNotPackagePrivate(String fieldName) throws NoSuchFieldException {
            Field actual = Subject.class.getDeclaredField(fieldName);
            assertThat(actual).isNotPackagePrivate();
        }
    }

    @Nested
    class IsFinal {
        @Test
        void shouldFailIfActualIsNull() {
            Field actual = null;
            expectAssertionError(() -> assertThat(actual).isFinal());
        }

        @ParameterizedTest
        @ValueSource(strings = {"field2", "field3"})
        void shouldFailIfActualIsNotFinal(String fieldName) throws NoSuchFieldException {
            Field actual = Subject.class.getDeclaredField(fieldName);
            expectAssertionError(() -> assertThat(actual).isFinal())
                    .withMessage(shouldBeFinal(actual).create());
        }

        @ParameterizedTest
        @ValueSource(strings = {"field1", "field4"})
        void shouldPassIfActualIsFinal(String fieldName) throws NoSuchFieldException {
            Field actual = Subject.class.getDeclaredField(fieldName);
            assertThat(actual).isFinal();
        }
    }

    @Nested
    class IsNotFinal {
        @Test
        void shouldFailIfActualIsNull() {
            Field actual = null;
            expectAssertionError(() -> assertThat(actual).isNotFinal());
        }

        @ParameterizedTest
        @ValueSource(strings = {"field1", "field4"})
        void shouldFailIfActualIsFinal(String fieldName) throws NoSuchFieldException {
            Field actual = Subject.class.getDeclaredField(fieldName);
            expectAssertionError(() -> assertThat(actual).isNotFinal())
                    .withMessage(shouldNotBeFinal(actual).create());
        }

        @ParameterizedTest
        @ValueSource(strings = {"field2", "field3"})
        void shouldPassIfActualIsNotFinal(String fieldName) throws NoSuchFieldException {
            Field actual = Subject.class.getDeclaredField(fieldName);
            assertThat(actual).isNotFinal();
        }
    }

    @Nested
    class IsStatic {
        @Test
        void shouldFailIfActualIsNull() {
            Field actual = null;
            expectAssertionError(() -> assertThat(actual).isStatic());
        }

        @ParameterizedTest
        @ValueSource(strings = {"field1", "field2", "field4"})
        void shouldFailIfActualIsNotStatic(String fieldName) throws NoSuchFieldException {
            Field actual = Subject.class.getDeclaredField(fieldName);
            expectAssertionError(() -> assertThat(actual).isStatic())
                    .withMessage(shouldBeStatic(actual).create());
        }

        @Test
        void shouldPassIfActualIsStatic() throws NoSuchFieldException {
            assertThat(Subject.class.getDeclaredField("field3")).isStatic();
        }
    }

    @Nested
    class IsNotStatic {
        @Test
        void shouldFailIfActualIsNull() {
            Field actual = null;
            expectAssertionError(() -> assertThat(actual).isNotStatic());
        }

        @Test
        void shouldFailIfActualIsStatic() throws NoSuchFieldException {
            Field actual = Subject.class.getDeclaredField("field3");
            expectAssertionError(() -> assertThat(actual).isNotStatic())
                    .withMessage(shouldNotBeStatic(actual).create());
        }

        @ParameterizedTest
        @ValueSource(strings = {"field1", "field2", "field4"})
        void shouldPassIfActualIsNotStatic(String fieldName) throws NoSuchFieldException {
            Field actual = Subject.class.getDeclaredField(fieldName);
            assertThat(actual).isNotStatic();
        }
    }

    private static ThrowableAssertAlternative<AssertionError> expectAssertionError(ThrowableAssert.ThrowingCallable callable) {
        return Assertions.assertThatExceptionOfType(AssertionError.class)
                .isThrownBy(callable);
    }
}
