package org.assertj.reflection;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.util.List;

import static org.assertj.reflection.ReflectionAssertions.assertThat;

class MethodAssertHasReturnTypeTest {
    private static class Subject {
        int simple(String parameter) {
            return -1;
        }

        List<Integer> complex() {
            return null;
        }

        void nothing() {
        }
    }

    @Test
    void methodReturningSimpleType() throws NoSuchMethodException {
        Method actual = Subject.class.getDeclaredMethod("simple", String.class);
        assertThat(actual).hasReturnType(int.class);
    }

    @Test
    void methodReturningComplexType() throws NoSuchMethodException {
        Method actual = Subject.class.getDeclaredMethod("complex");
        assertThat(actual).hasReturnType(List.class);
    }

    @Test
    void methodReturningNothing() throws NoSuchMethodException {
        Method actual = Subject.class.getDeclaredMethod("nothing");
        assertThat(actual).hasReturnType(void.class);
    }
}
