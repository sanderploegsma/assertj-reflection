package org.assertj.reflection;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.reflection.ReflectionAssertions.assertThat;

class FieldAssertHasTypeTest {
    private static class Subject {
        int primitive = 1;
        Integer boxed = 2;
        List<Integer> generic = new ArrayList<>();
    }

    @Test
    void fieldWithPrimitiveType() throws NoSuchFieldException {
        assertThat(Subject.class.getDeclaredField("primitive")).hasType(int.class);
    }

    @Test
    void fieldWithBoxedType() throws NoSuchFieldException {
        assertThat(Subject.class.getDeclaredField("boxed")).hasType(Integer.class);
    }

    @Test
    void fieldWithGenericType() throws NoSuchFieldException {
        assertThat(Subject.class.getDeclaredField("generic")).hasType(List.class);
    }
}
