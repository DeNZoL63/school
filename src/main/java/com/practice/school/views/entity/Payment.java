package com.practice.school.views.entity;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "Payments")
public class Payment extends StudentAction {

    @Column
    private double amount;

    public Payment() {
    }

    public Payment(Student student, Date date, double amount) {
        super(student, date);
        this.amount = amount;
    }

    public Payment(long id, Student student, Date date, double amount) {
        super(id, student, date);
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
