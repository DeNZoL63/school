package com.practice.school.entity;

import javax.persistence.*;
import java.util.Date;

@Table(name = "Payments")
public class Payment extends StudentAction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private long id;

    @Column
    private double amount;

    public Payment() {
    }

    public Payment(Student student, Date date, double amount) {
        super(student, date);
        this.amount = amount;
    }

    public Payment(long id, Student student, Date date, double amount) {
        super(student, date);
        this.id = id;
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
