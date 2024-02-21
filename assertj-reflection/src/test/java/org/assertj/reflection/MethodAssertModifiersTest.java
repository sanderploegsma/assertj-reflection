package org.assertj.reflection;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.ThrowableAssert;
import org.assertj.core.api.ThrowableAssertAlternative;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.lang.reflect.Method;

import static org.assertj.reflection.MemberModifierShouldBe.*;
import static org.assertj.reflection.ReflectionAssertions.assertThat;

class MethodAssertModifiersTest {
    private static class Subject {
        public static void method1() {
        }

        protected static void method2() {
        }

        private void method3() {
        }

        void method4() {
        }
    }

    @Nested
    class IsPublic {
        @Test
        void shouldFailIfActualIsNull() {
            Method actual = null;
            expectAssertionError(() -> assertThat(actual).isPublic());
        }

        @ParameterizedTest
        @ValueSource(strings = {"method2", "method3", "method4"})
        void shouldFailIfActualIsNotPublic(String methodName) throws NoSuchMethodException {
            Method actual = Subject.class.getDeclaredMethod(methodName);
            expectAssertionError(() -> assertThat(actual).isPublic())
                    .withMessage(shouldBePublic(actual).create());
        }

        @Test
        void shouldPassIfActualIsPublic() throws NoSuchMethodException {
            Method actual = Subject.class.getDeclaredMethod("method1");
            assertThat(actual).isPublic();
        }
    }

    @Nested
    class IsNotPublic {
        @Test
        void shouldFailIfActualIsNull() {
            Method actual = null;
            expectAssertionError(() -> assertThat(actual).isNotPublic());
        }

        @Test
        void shouldFailIfActualIsPublic() throws NoSuchMethodException {
            Method actual = Subject.class.getDeclaredMethod("method1");
            expectAssertionError(() -> assertThat(actual).isNotPublic())
                    .withMessage(shouldNotBePublic(actual).create());
        }

        @ParameterizedTest
        @ValueSource(strings = {"method2", "method3", "method4"})
        void shouldPassIfActualIsNotPublic(String methodName) throws NoSuchMethodException {
            Method actual = Subject.class.getDeclaredMethod(methodName);
            assertThat(actual).isNotPublic();
        }
    }

    @Nested
    class IsProtected {
        @Test
        void shouldFailIfActualIsNull() {
            Method actual = null;
            expectAssertionError(() -> assertThat(actual).isProtected());
        }

        @ParameterizedTest
        @ValueSource(strings = {"method1", "method3", "method4"})
        void shouldFailIfActualIsNotProtected(String methodName) throws NoSuchMethodException {
            Method actual = Subject.class.getDeclaredMethod(methodName);
            expectAssertionError(() -> assertThat(actual).isProtected())
                    .withMessage(shouldBeProtected(actual).create());
        }

        @Test
        void shouldPassIfActualIsProtected() throws NoSuchMethodException {
            Method actual = Subject.class.getDeclaredMethod("method2");
            assertThat(actual).isProtected();
        }
    }

    @Nested
    class IsNotProtected {
        @Test
        void shouldFailIfActualIsNull() {
            Method actual = null;
            expectAssertionError(() -> assertThat(actual).isNotProtected());
        }

        @Test
        void shouldFailIfActualIsProtected() throws NoSuchMethodException {
            Method actual = Subject.class.getDeclaredMethod("method2");
            expectAssertionError(() -> assertThat(actual).isNotProtected())
                    .withMessage(shouldNotBeProtected(actual).create());
        }

        @ParameterizedTest
        @ValueSource(strings = {"method1", "method3", "method4"})
        void shouldPassIfActualIsNotProtected(String methodName) throws NoSuchMethodException {
            Method actual = Subject.class.getDeclaredMethod(methodName);
            assertThat(actual).isNotProtected();
        }
    }

    @Nested
    class IsPrivate {
        @Test
        void shouldFailIfActualIsNull() {
            Method actual = null;
            expectAssertionError(() -> assertThat(actual).isPrivate());
        }

        @ParameterizedTest
        @ValueSource(strings = {"method1", "method2", "method4"})
        void shouldFailIfActualIsNotPrivate(String methodName) throws NoSuchMethodException {
            Method actual = Subject.class.getDeclaredMethod(methodName);
            expectAssertionError(() -> assertThat(actual).isPrivate())
                    .withMessage(shouldBePrivate(actual).create());
        }

        @Test
        void shouldPassIfActualIsPrivate() throws NoSuchMethodException {
            Method actual = Subject.class.getDeclaredMethod("method3");
            assertThat(actual).isPrivate();
        }
    }

    @Nested
    class IsNotPrivate {
        @Test
        void shouldFailIfActualIsNull() {
            Method actual = null;
            expectAssertionError(() -> assertThat(actual).isNotPrivate());
        }

        @Test
        void shouldFailIfActualIsPrivate() throws NoSuchMethodException {
            Method actual = Subject.class.getDeclaredMethod("method3");
            expectAssertionError(() -> assertThat(actual).isNotPrivate())
                    .withMessage(shouldNotBePrivate(actual).create());
        }

        @ParameterizedTest
        @ValueSource(strings = {"method1", "method2", "method4"})
        void shouldPassIfActualIsNotPrivate(String methodName) throws NoSuchMethodException {
            Method actual = Subject.class.getDeclaredMethod(methodName);
            assertThat(actual).isNotPrivate();
        }
    }

    @Nested
    class IsPackagePrivate {
        @Test
        void shouldFailIfActualIsNull() {
            Method actual = null;
            expectAssertionError(() -> assertThat(actual).isPackagePrivate());
        }

        @ParameterizedTest
        @ValueSource(strings = {"method1", "method2", "method3"})
        void shouldFailIfActualIsNotPackagePrivate(String methodName) throws NoSuchMethodException {
            Method actual = Subject.class.getDeclaredMethod(methodName);
            expectAssertionError(() -> assertThat(actual).isPackagePrivate())
                    .withMessage(shouldBePackagePrivate(actual).create());
        }

        @Test
        void shouldPassIfActualIsPackagePrivate() throws NoSuchMethodException {
            Method actual = Subject.class.getDeclaredMethod("method4");
            assertThat(actual).isPackagePrivate();
        }
    }

    @Nested
    class IsNotPackagePrivate {
        @Test
        void shouldFailIfActualIsNull() {
            Method actual = null;
            expectAssertionError(() -> assertThat(actual).isNotPackagePrivate());
        }

        @Test
        void shouldFailIfActualIsPackagePrivate() throws NoSuchMethodException {
            Method actual = Subject.class.getDeclaredMethod("method4");
            expectAssertionError(() -> assertThat(actual).isNotPackagePrivate())
                    .withMessage(shouldNotBePackagePrivate(actual).create());
        }

        @ParameterizedTest
        @ValueSource(strings = {"method1", "method2", "method3"})
        void shouldPassIfActualIsNotPackagePrivate(String methodName) throws NoSuchMethodException {
            Method actual = Subject.class.getDeclaredMethod(methodName);
            assertThat(actual).isNotPackagePrivate();
        }
    }

    @Nested
    class IsStatic {
        @Test
        void shouldFailIfActualIsNull() {
            Method actual = null;
            expectAssertionError(() -> assertThat(actual).isStatic());
        }

        @ParameterizedTest
        @ValueSource(strings = {"method3", "method4"})
        void shouldFailIfActualIsNotStatic(String methodName) throws NoSuchMethodException {
            Method actual = Subject.class.getDeclaredMethod(methodName);
            expectAssertionError(() -> assertThat(actual).isStatic())
                    .withMessage(shouldBeStatic(actual).create());
        }

        @ParameterizedTest
        @ValueSource(strings = {"method1", "method2"})
        void shouldPassIfActualIsStatic(String methodName) throws NoSuchMethodException {
            Method actual = Subject.class.getDeclaredMethod(methodName);
            assertThat(actual).isStatic();
        }
    }

    @Nested
    class IsNotStatic {
        @Test
        void shouldFailIfActualIsNull() {
            Method actual = null;
            expectAssertionError(() -> assertThat(actual).isNotStatic());
        }

        @ParameterizedTest
        @ValueSource(strings = {"method1", "method2"})
        void shouldFailIfActualIsStatic(String methodName) throws NoSuchMethodException {
            Method actual = Subject.class.getDeclaredMethod(methodName);
            expectAssertionError(() -> assertThat(actual).isNotStatic())
                    .withMessage(shouldNotBeStatic(actual).create());
        }

        @ParameterizedTest
        @ValueSource(strings = {"method3", "method4"})
        void shouldPassIfActualIsNotStatic(String methodName) throws NoSuchMethodException {
            Method actual = Subject.class.getDeclaredMethod(methodName);
            assertThat(actual).isNotStatic();
        }
    }

    private static ThrowableAssertAlternative<AssertionError> expectAssertionError(ThrowableAssert.ThrowingCallable callable) {
        return Assertions.assertThatExceptionOfType(AssertionError.class)
                .isThrownBy(callable);
    }
}
