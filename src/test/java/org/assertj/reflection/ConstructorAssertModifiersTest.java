package org.assertj.reflection;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.ThrowableAssert;
import org.assertj.core.api.ThrowableAssertAlternative;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.lang.reflect.Constructor;

import static org.assertj.reflection.MemberModifierShouldBe.*;
import static org.assertj.reflection.MemberModifierShouldBe.shouldNotBePackagePrivate;
import static org.assertj.reflection.ReflectionAssertions.assertThat;

class ConstructorAssertModifiersTest {
    static class Class1 {
        public Class1() {}
    }

    static class Class2 {
        protected Class2() {}
    }

    static class Class3 {
        private Class3() {}
    }

    static class Class4 {
        Class4() {}
    }

    @Nested
    class IsPublic {
        @Test
        void shouldFailIfActualIsNull() {
            Constructor<?> actual = null;
            expectAssertionError(() -> assertThat(actual).isPublic());
        }

        @ParameterizedTest
        @ValueSource(classes = {Class2.class, Class3.class, Class4.class})
        void shouldFailIfActualIsNotPublic(Class<?> clazz) throws NoSuchMethodException {
            Constructor<?> actual = clazz.getDeclaredConstructor();
            expectAssertionError(() -> assertThat(actual).isPublic())
                    .withMessage(shouldBePublic(actual).create());
        }

        @Test
        void shouldPassIfActualIsPublic() throws NoSuchMethodException {
            Constructor<?> actual = Class1.class.getDeclaredConstructor();
            assertThat(actual).isPublic();
        }
    }

    @Nested
    class IsNotPublic {
        @Test
        void shouldFailIfActualIsNull() {
            Constructor<?> actual = null;
            expectAssertionError(() -> assertThat(actual).isNotPublic());
        }

        @Test
        void shouldFailIfActualIsPublic() throws NoSuchMethodException {
            Constructor<?> actual = Class1.class.getDeclaredConstructor();
            expectAssertionError(() -> assertThat(actual).isNotPublic())
                    .withMessage(shouldNotBePublic(actual).create());
        }

        @ParameterizedTest
        @ValueSource(classes = {Class2.class, Class3.class, Class4.class})
        void shouldPassIfActualIsNotPublic(Class<?> clazz) throws NoSuchMethodException {
            Constructor<?> actual = clazz.getDeclaredConstructor();
            assertThat(actual).isNotPublic();
        }
    }

    @Nested
    class IsProtected {
        @Test
        void shouldFailIfActualIsNull() {
            Constructor<?> actual = null;
            expectAssertionError(() -> assertThat(actual).isProtected());
        }

        @ParameterizedTest
        @ValueSource(classes = {Class1.class, Class3.class, Class4.class})
        void shouldFailIfActualIsNotProtected(Class<?> clazz) throws NoSuchMethodException {
            Constructor<?> actual = clazz.getDeclaredConstructor();
            expectAssertionError(() -> assertThat(actual).isProtected())
                    .withMessage(shouldBeProtected(actual).create());
        }

        @Test
        void shouldPassIfActualIsProtected() throws NoSuchMethodException {
            Constructor<?> actual = Class2.class.getDeclaredConstructor();
            assertThat(actual).isProtected();
        }
    }

    @Nested
    class IsNotProtected {
        @Test
        void shouldFailIfActualIsNull() {
            Constructor<?> actual = null;
            expectAssertionError(() -> assertThat(actual).isNotProtected());
        }

        @Test
        void shouldFailIfActualIsProtected() throws NoSuchMethodException {
            Constructor<?> actual = Class2.class.getDeclaredConstructor();
            expectAssertionError(() -> assertThat(actual).isNotProtected())
                    .withMessage(shouldNotBeProtected(actual).create());
        }

        @ParameterizedTest
        @ValueSource(classes = {Class1.class, Class3.class, Class4.class})
        void shouldPassIfActualIsNotProtected(Class<?> clazz) throws NoSuchMethodException {
            Constructor<?> actual = clazz.getDeclaredConstructor();
            assertThat(actual).isNotProtected();
        }
    }

    @Nested
    class IsPrivate {
        @Test
        void shouldFailIfActualIsNull() {
            Constructor<?> actual = null;
            expectAssertionError(() -> assertThat(actual).isPrivate());
        }

        @ParameterizedTest
        @ValueSource(classes = {Class1.class, Class2.class, Class4.class})
        void shouldFailIfActualIsNotPrivate(Class<?> clazz) throws NoSuchMethodException {
            Constructor<?> actual = clazz.getDeclaredConstructor();
            expectAssertionError(() -> assertThat(actual).isPrivate())
                    .withMessage(shouldBePrivate(actual).create());
        }

        @Test
        void shouldPassIfActualIsPrivate() throws NoSuchMethodException {
            Constructor<?> actual = Class3.class.getDeclaredConstructor();
            assertThat(actual).isPrivate();
        }
    }

    @Nested
    class IsNotPrivate {
        @Test
        void shouldFailIfActualIsNull() {
            Constructor<?> actual = null;
            expectAssertionError(() -> assertThat(actual).isNotPrivate());
        }

        @Test
        void shouldFailIfActualIsPrivate() throws NoSuchMethodException {
            Constructor<?> actual = Class3.class.getDeclaredConstructor();
            expectAssertionError(() -> assertThat(actual).isNotPrivate())
                    .withMessage(shouldNotBePrivate(actual).create());
        }

        @ParameterizedTest
        @ValueSource(classes = {Class1.class, Class2.class, Class4.class})
        void shouldPassIfActualIsNotPrivate(Class<?> clazz) throws NoSuchMethodException {
            Constructor<?> actual = clazz.getDeclaredConstructor();
            assertThat(actual).isNotPrivate();
        }
    }

    @Nested
    class IsPackagePrivate {
        @Test
        void shouldFailIfActualIsNull() {
            Constructor<?> actual = null;
            expectAssertionError(() -> assertThat(actual).isPackagePrivate());
        }

        @ParameterizedTest
        @ValueSource(classes = {Class1.class, Class2.class, Class3.class})
        void shouldFailIfActualIsNotPackagePrivate(Class<?> clazz) throws NoSuchMethodException {
            Constructor<?> actual = clazz.getDeclaredConstructor();
            expectAssertionError(() -> assertThat(actual).isPackagePrivate())
                    .withMessage(shouldBePackagePrivate(actual).create());
        }

        @Test
        void shouldPassIfActualIsPackagePrivate() throws NoSuchMethodException {
            Constructor<?> actual = Class4.class.getDeclaredConstructor();
            assertThat(actual).isPackagePrivate();
        }
    }

    @Nested
    class IsNotPackagePrivate {
        @Test
        void shouldFailIfActualIsNull() {
            Constructor<?> actual = null;
            expectAssertionError(() -> assertThat(actual).isNotPackagePrivate());
        }

        @Test
        void shouldFailIfActualIsPackagePrivate() throws NoSuchMethodException {
            Constructor<?> actual = Class4.class.getDeclaredConstructor();
            expectAssertionError(() -> assertThat(actual).isNotPackagePrivate())
                    .withMessage(shouldNotBePackagePrivate(actual).create());
        }

        @ParameterizedTest
        @ValueSource(classes = {Class1.class, Class2.class, Class3.class})
        void shouldPassIfActualIsNotPackagePrivate(Class<?> clazz) throws NoSuchMethodException {
            Constructor<?> actual = clazz.getDeclaredConstructor();
            assertThat(actual).isNotPackagePrivate();
        }
    }

    private static ThrowableAssertAlternative<AssertionError> expectAssertionError(ThrowableAssert.ThrowingCallable callable) {
        return Assertions.assertThatExceptionOfType(AssertionError.class)
                .isThrownBy(callable);
    }
}
