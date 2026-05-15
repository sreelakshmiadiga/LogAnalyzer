package com.example.LogAnalyzer.controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    // BUG: Missing @Autowired! This field will be null.
    private PaymentService paymentService;

    @GetMapping("/process")
    public String processPayment(@RequestParam String id) {
        // This line will throw a NullPointerException
        return paymentService.execute(id);
    }
}

// Simple service for the controller to use
@Service
class PaymentService {
    public String execute(String id) {
        return "Payment " + id + " processed successfully!";
    }

}