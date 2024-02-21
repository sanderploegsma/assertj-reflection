package org.assertj.reflection;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.ParseException;

import static org.assertj.reflection.ReflectionAssertions.assertThat;

class MethodAssertThrowsExceptionTest {
    private static class Subject {
        void throwsNothing() {}
        void throwsExceptions() throws FileNotFoundException, ArithmeticException {}
    }

    @Test
    void throwsExpectedException() throws NoSuchMethodException {
        assertThat(Subject.class.getDeclaredMethod("throwsExceptions"))
                .throwsException(FileNotFoundException.class)
                .throwsException(ArithmeticException.class);
    }

    @Test
    void throwsExceptionAssignableFromExpected() throws NoSuchMethodException {
        assertThat(Subject.class.getDeclaredMethod("throwsExceptions"))
                .throwsException(IOException.class);
    }

    @Test
    void throwsDifferentException() throws NoSuchMethodException {
        Method actual = Subject.class.getDeclaredMethod("throwsExceptions");
        Assertions.assertThatExceptionOfType(AssertionError.class)
                .isThrownBy(() -> assertThat(actual).throwsException(ParseException.class));
    }

    @Test
    void throwsNoException() throws NoSuchMethodException {
        Method actual = Subject.class.getDeclaredMethod("throwsNothing");
        Assertions.assertThatExceptionOfType(AssertionError.class)
                        .isThrownBy(() -> assertThat(actual).throwsException(IOException.class));
    }
}
