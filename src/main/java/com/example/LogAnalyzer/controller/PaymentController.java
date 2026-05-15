package com.example.LogAnalyzer.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService = new PaymentService();

    @GetMapping("/process")
    public String processPayment(@RequestParam String id) {
        return paymentService.execute(id);
    }
}

// Simple service for the controller to use
class PaymentService {

    public String execute(String id) {
        return "Payment " + id + " processed successfully!";
    }

}