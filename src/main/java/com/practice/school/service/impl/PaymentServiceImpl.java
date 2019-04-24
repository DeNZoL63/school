package com.practice.school.service.impl;

import com.practice.school.dao.PaymentRepository;
import com.practice.school.entity.Payment;
import com.practice.school.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    @Autowired
    public PaymentServiceImpl(PaymentRepository repository) {
        this.paymentRepository = repository;
    }

    @Override
    public Payment addPayment(Payment teacher) {
        return paymentRepository.saveAndFlush(teacher);
    }

    @Override
    public Payment editPayment(Payment teacher) {
        return paymentRepository.saveAndFlush(teacher);
    }

    @Override
    public List<Payment> getAll() {
        return paymentRepository.findAll();
    }

    @Override
    public void deleteByID(Long id) {
        boolean teacherExist = paymentRepository.existsById(id);

        if (teacherExist){
            paymentRepository.deleteById(id);
        }
    }

    @Override
    public Payment findById(Long id) {
        return paymentRepository.findById(id).orElse(null);
    }

}
