package com.example.hotels.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.hotels.Model.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    
}
