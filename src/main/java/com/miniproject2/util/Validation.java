package com.miniproject2.util;

public class Validation {
    public static boolean notBlank(String s) { return s != null && !s.isBlank(); }
    public static boolean emailLike(String s) { return s != null && s.matches(".+@.+\\..+"); }
    public static boolean phoneLike(String s) { return s != null && s.matches("\\d{6,15}"); }
    public static boolean positiveInt(int n) { return n > 0; }
    public static boolean nonNegativeInt(int n) { return n >= 0; }
    public static boolean positiveDouble(double d) { return d > 0; }
}
