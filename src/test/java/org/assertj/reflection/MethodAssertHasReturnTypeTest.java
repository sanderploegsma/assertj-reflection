package org.assertj.reflection;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.reflection.ReflectionAssert.assertThat;

class MethodAssertHasReturnTypeTest {
    @Test
    void methodReturningSimpleType() throws NoSuchMethodException {
        var method = Subject.class.getDeclaredMethod("simple", String.class);
        assertThat(method).hasReturnType(int.class);
    }

    @Test
    void methodReturningComplexType() throws NoSuchMethodException {
        var method = Subject.class.getDeclaredMethod("complex");
        assertThat(method).hasReturnType(List.class);
    }

    @Test
    void methodReturningNothing() throws NoSuchMethodException {
        var method = Subject.class.getDeclaredMethod("nothing");
        assertThat(method).hasReturnType(void.class);
    }

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
}
