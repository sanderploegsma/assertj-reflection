import org.assertj.reflection.ConstructorAssert;
import org.junit.jupiter.api.Test;

import static org.assertj.reflection.ReflectionAssertions.assertThat;

class SampleTest {
    @Test
    void generatedClassIsPublic() {
        assertThat(Sample.class).isPublic();
    }

    @Test
    void generatedClassHasPublicDefaultConstructor() {
        assertThat(Sample.class).hasDeclaredConstructor(ConstructorAssert::isPublic);
    }

    @Test
    void generatedClassHasPublicGetters() {
        assertThat(Sample.class)
                .hasDeclaredMethod("getFoo", method -> method.isPublic().hasReturnType(String.class))
                .hasDeclaredMethod("getBar", method -> method.isPublic().hasReturnType(Integer.class))
                .hasDeclaredMethod("getBaz", method -> method.isPublic().hasReturnType(Boolean.class));
    }

    @Test
    void generatedClassHasPublicSetters() {
        assertThat(Sample.class)
                .hasDeclaredMethod("setFoo", String.class, method -> method.isPublic().hasReturnType(void.class))
                .hasDeclaredMethod("setBar", Integer.class, method -> method.isPublic().hasReturnType(void.class))
                .hasDeclaredMethod("setBaz", Boolean.class, method -> method.isPublic().hasReturnType(void.class));
    }

    @Test
    void generatedClassHasEqualsMethod() {
        assertThat(Sample.class)
                .hasDeclaredMethod("equals", Object.class, method -> method.isPublic().hasReturnType(boolean.class));
    }

    @Test
    void generatedClassHasHashCodeMethod() {
        assertThat(Sample.class)
                .hasDeclaredMethod("hashCode", method -> method.isPublic().hasReturnType(int.class));
    }

    @Test
    void generatedClassHasToStringMethod() {
        assertThat(Sample.class)
                .hasDeclaredMethod("toString", method -> method.isPublic().hasReturnType(String.class));
    }
}
