package com.practice.school.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "Payment")
public class Payment extends StudentAction {

    @Column
    private double amount;

    public Payment() {
    }

    public Payment(Long id) {
        super(id);
    }

    public Payment(Student student, LocalDate date, double amount) {
        super(student, date);
        this.amount = amount;
    }

    public Payment(long id, Student student, LocalDate date, double amount) {
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
