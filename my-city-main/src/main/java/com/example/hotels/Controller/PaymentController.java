package com.example.hotels.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.hotels.Model.Payment;
import com.example.hotels.Service.PaymentService;

@RestController
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/payment")
    public Payment savePayment(@RequestBody Payment payment) {
        return paymentService.savePayment(payment);
    }

    @PutMapping("/payment")
    public Payment updatePayment(@RequestBody Payment payment) {
        return paymentService.updatePayment(payment);
    }

    @GetMapping("/payments")
    public List<Payment> getAllPayments() {
        return paymentService.getAllPayments();
    }

    @GetMapping("/payment/{paymentId}")
    public Payment getPaymentById(@PathVariable Long paymentId) {
        return paymentService.getPaymentById(paymentId);
    }

    @DeleteMapping("/payment/{paymentId}")
    public String deletePayment(@PathVariable Long paymentId) {
        paymentService.deletePayment(paymentId);
        return "Payment with id " + paymentId + " is deleted";
    }
}
