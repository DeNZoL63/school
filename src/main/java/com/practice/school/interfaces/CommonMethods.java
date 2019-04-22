package com.practice.school.interfaces;

import com.practice.school.MainUI;

public interface CommonMethods {

    static void checkUser(){
        if (!MainUI.isUser()) {
            //#TODO включить после разработки
//            UI.getCurrent().getNavigator().navigateTo("logon");
        }
    }

    static boolean checkLengthMoreTwo(String s) {
        return s.length() > 2;
    }

    static boolean checkLengthNotEmpty(String s) {
        return s.length() > 0;
    }
}
