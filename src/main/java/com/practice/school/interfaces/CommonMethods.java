package com.practice.school.interfaces;

import com.practice.school.MainUI;
import com.practice.school.entity.BaseEntity;
import com.vaadin.ui.Notification;

import java.util.Set;

public interface CommonMethods {

    static void checkUser(){
        if (!MainUI.isUser()) {
            //#TODO включить после разработки
//            UI.getCurrent().getNavigator().navigateTo("logon");
        }
    }

    static <T extends BaseEntity> Long getSelectedtRow(Set<T> set) {
        final Long[] result = {Long.valueOf(0)};

        if (set.isEmpty()){
            Notification.show(MainUI.getResourceBundle().getString("SelectRowMessage"));
        }

        set.forEach(element -> result[0] = element.getId());
        return result[0];
    }
}
