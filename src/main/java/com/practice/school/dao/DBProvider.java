package com.practice.school.dao;

import com.vaadin.ui.Notification;

import java.sql.Connection;
import java.sql.DriverManager;

//@Component
public class DBProvider {

    public static Connection connectToDB(){
        Connection result = null;
        try {
            result = DriverManager.getConnection("jdbc:hsqldb:file:/db/schooldb", "SA", "");
        }catch (Exception e){
            Notification.show(e.getMessage());
        }
        return result;
    }
}
