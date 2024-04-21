package com.example.payment.repository;

import com.example.payment.entity.PayBase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<PayBase, String> {
}
