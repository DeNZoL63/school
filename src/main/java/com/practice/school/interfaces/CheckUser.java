package com.practice.school.interfaces;

import com.practice.school.MainUI;
import com.vaadin.ui.UI;

public interface CheckUser {

    static void checkUser(){
        if (!MainUI.isUser()) {
            UI.getCurrent().getNavigator().navigateTo("logon");
        }
    }
}
