package org.assertj.reflection;

import org.assertj.core.error.BasicErrorMessageFactory;
import org.assertj.core.error.ErrorMessageFactory;

import java.lang.reflect.Member;
import java.lang.reflect.Modifier;

class MemberModifierShouldBe extends BasicErrorMessageFactory {
    private static final String PACKAGE_PRIVATE = "package-private";

    public MemberModifierShouldBe(Member actual, boolean positive, String modifier) {
        super("%nExpecting actual:%n  %s%n" + (positive ? "to" : "not to") + " be %s but was %s.",
                actual.toString(), modifier, modifiers(actual));
    }

    static ErrorMessageFactory shouldBePublic(Member actual) {
        return new MemberModifierShouldBe(actual, true, Modifier.toString(Modifier.PUBLIC));
    }

    static ErrorMessageFactory shouldNotBePublic(Member actual) {
        return new MemberModifierShouldBe(actual, false, Modifier.toString(Modifier.PUBLIC));
    }

    static ErrorMessageFactory shouldBeProtected(Member actual) {
        return new MemberModifierShouldBe(actual, true, Modifier.toString(Modifier.PROTECTED));
    }

    static ErrorMessageFactory shouldNotBeProtected(Member actual) {
        return new MemberModifierShouldBe(actual, false, Modifier.toString(Modifier.PROTECTED));
    }

    static ErrorMessageFactory shouldBePrivate(Member actual) {
        return new MemberModifierShouldBe(actual, true, Modifier.toString(Modifier.PRIVATE));
    }

    static ErrorMessageFactory shouldNotBePrivate(Member actual) {
        return new MemberModifierShouldBe(actual, false, Modifier.toString(Modifier.PRIVATE));
    }

    static ErrorMessageFactory shouldBePackagePrivate(Member actual) {
        return new MemberModifierShouldBe(actual, true, PACKAGE_PRIVATE);
    }

    static ErrorMessageFactory shouldNotBePackagePrivate(Member actual) {
        return new MemberModifierShouldBe(actual, false, PACKAGE_PRIVATE);
    }

    static ErrorMessageFactory shouldBeStatic(Member actual) {
        return new MemberModifierShouldBe(actual, true, Modifier.toString(Modifier.STATIC));
    }

    static ErrorMessageFactory shouldNotBeStatic(Member actual) {
        return new MemberModifierShouldBe(actual, false, Modifier.toString(Modifier.STATIC));
    }

    static ErrorMessageFactory shouldBeFinal(Member actual) {
        return new MemberModifierShouldBe(actual, true, Modifier.toString(Modifier.FINAL));
    }

    static ErrorMessageFactory shouldNotBeFinal(Member actual) {
        return new MemberModifierShouldBe(actual, false, Modifier.toString(Modifier.FINAL));
    }

    private static String modifiers(Member actual) {
        var modifiers = actual.getModifiers();
        if (!Modifier.isPublic(modifiers) && !Modifier.isProtected(modifiers) && !Modifier.isPrivate(modifiers)) {
            return PACKAGE_PRIVATE;
        }

        return Modifier.toString(modifiers);
    }
}
