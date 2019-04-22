package com.practice.school.interfaces;

import com.practice.school.MainUI;

public interface CommonMethods {

    static void checkUser(){
        if (!MainUI.isUser()) {
            //#TODO включить после разработки
//            UI.getCurrent().getNavigator().navigateTo("logon");
        }
    }
}
