package src.lib.test;

import java.lang.Thread;
import java.util.HashMap;
import java.util.Map;

public class Asserts {
    private static String assertEqualsFormatString = "\nExpected: '%s'\nActual: '%s'";
    private static String assertNotEqualsFormatString = "\nExpected: Not '%s'\nActual: %s'";

    private enum AssertType {
        EQUALS,
        NOTEQUALS
    }

    private static HashMap<AssertType, String> assertTypeToFormatString = new HashMap<>(
        Map.of(
            AssertType.EQUALS, assertEqualsFormatString,
            AssertType.NOTEQUALS, assertNotEqualsFormatString
        ));


    private static String getMethodName() {
        return Thread.currentThread().getStackTrace()[4].getMethodName();
    }

    private static String getFileName() {
        return Thread.currentThread().getStackTrace()[4].getFileName();
    }

    private static String getAssertErrorString(AssertType assertType) {
        return assertTypeToFormatString.get(assertType);
    }

    public static void assertEquals(long expected, long actual) {
        assert expected == actual : 
            String.format(
                getAssertErrorString(AssertType.EQUALS),
                expected,
                actual
            );
    }
    public static void assertEquals(Object expected, Object actual) {
        if (expected == null || actual == null) {
            assert expected == actual : 
                String.format(
                    getAssertErrorString(AssertType.EQUALS),
                    expected,
                    actual
                );
        }

        assert expected.equals(actual) :
            String.format(
                getAssertErrorString(AssertType.EQUALS),
                expected,
                actual
            );
    }

    public static void assertNotEquals(long expected, long actual) {
        assert expected != actual : 
            String.format(
                getAssertErrorString(AssertType.NOTEQUALS),
                expected,
                actual
            );
    }
    public static void assertNotEquals(Object expected, Object actual) {
        if (expected == null || actual == null) {
            assert expected != actual : 
            String.format(
                getAssertErrorString(AssertType.NOTEQUALS),
                expected,
                actual
            );
        }

        assert !expected.equals(actual) :
            String.format(
                getAssertErrorString(AssertType.NOTEQUALS),
                expected,
                actual
            );
    }
}
