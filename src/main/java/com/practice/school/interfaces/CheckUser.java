package com.practice.school.interfaces;

import com.practice.school.MainUI;

public interface CheckUser {

    static void checkUser(){
        if (!MainUI.isUser()) {
            //#TODO при старте системы создает 2 окна
//            UI.getCurrent().getNavigator().navigateTo("logon");
        }
    }
}
