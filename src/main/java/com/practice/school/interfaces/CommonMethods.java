package com.practice.school.interfaces;

public interface CommonMethods {

    static boolean checkLengthMoreTwo(String s) {
        return s.length() > 2;
    }

    static boolean checkLengthNotEmpty(String s) {
        return s.length() > 0;
    }
}
