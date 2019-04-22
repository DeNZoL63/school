package com.practice.school.dao;

import org.springframework.boot.CommandLineRunner;

//@Component
//@ConditionalOnProperty(name = "app.db-init", havingValue = "true")
public class Dbinitializer implements CommandLineRunner {

//    private StudentRepository studentRepository;
//
//    public Dbinitializer(StudentRepository studentRepository) {
//        this.studentRepository = studentRepository;
//    }

    @Override
    public void run(String... args) throws Exception {
//        this.studentRepository.deleteAll();
    }

//    public static Connection connectToDB(){
//        Connection result = null;
//        try {
//            result = DriverManager.getConnection("jdbc:hsqldb:file:/db/schooldb", "SA", "");
//        }catch (Exception e){
//            Notification.show(e.getMessage());
//        }
//        return result;
//    }
}
