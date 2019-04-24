package com.practice.school.service;

import com.practice.school.entity.Payment;

import java.util.List;

public interface PaymentService {

    Payment addPayment(Payment payment);

    Payment editPayment(Payment payment);

    List<Payment> getAll();

    void deleteByID(Long id);

    Payment findById(Long id);
}
