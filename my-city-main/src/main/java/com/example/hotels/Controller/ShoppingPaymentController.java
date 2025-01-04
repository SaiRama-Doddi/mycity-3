package com.example.hotels.Controller;


import com.example.hotels.Model.Product;
import com.example.hotels.Repository.ProductRepository;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.Utils;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


@RestController
@RequestMapping("/api/payment")
public class ShoppingPaymentController {

    @Value("${razorpay.key}")
    private String razorpayKey;

    @Value("${razorpay.secret}")
    private String razorpaySecret;

    @Autowired
    private ProductRepository productRepository;

    @PostMapping("/create-order/{productId}")
    public ResponseEntity<?> createOrder(@PathVariable Long productId) {
        try {
            // Fetch product by ID
            Product product = productRepository.findById(productId)
                    .orElseThrow(() -> new RuntimeException("Product not found with ID: " + productId));
            
            int amount = (int) (product.getPrice() * 100);

            RazorpayClient razorpayClient = new RazorpayClient(razorpayKey, razorpaySecret);

            JSONObject options = new JSONObject();
            options.put("amount", amount); // Amount in paise
            options.put("currency", "INR");
            options.put("receipt", "txn_123456");
            Order order = razorpayClient.orders.create(options);

            return ResponseEntity.ok(order.toJson());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

//     @PostMapping("/create-order/{productId}")
// public ResponseEntity<?> createOrder(@PathVariable Long productId) {
//     try {
//         // Fetch product by ID
//         Product product = productRepository.findById(productId)
//                 .orElseThrow(() -> new RuntimeException("Product not found with ID: " + productId));
        
//         // Convert price to paise (Razorpay requires amount in the smallest unit)
//         int amount = (int) (product.getPrice() * 100);

//         // Create Razorpay client
//         RazorpayClient razorpayClient = new RazorpayClient(razorpayKey, razorpaySecret);

//         // Create order options
//         JSONObject options = new JSONObject();
//         options.put("amount", amount); // Amount in paise
//         options.put("currency", "INR");
//         options.put("receipt", "txn_" + productId);

//         // Create Razorpay order
//         Order order = razorpayClient.orders.create(options);

//         // Return Razorpay order details as JSON
//         return ResponseEntity.ok(order.toJson());
//     } catch (Exception e) {
//         e.printStackTrace();
//         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
//     }
// }

    @PostMapping("/payment-callback")
    public ResponseEntity<String> paymentCallback(@RequestParam String razorpay_payment_id,
            @RequestParam String razorpay_order_id,
            @RequestParam String razorpay_signature) {
        try {
            JSONObject payload = new JSONObject();
            payload.put("razorpay_order_id", razorpay_order_id);
            payload.put("razorpay_payment_id", razorpay_payment_id);
            payload.put("razorpay_signature", razorpay_signature);

            Utils.verifyPaymentSignature(payload, razorpaySecret);

            return ResponseEntity.ok("Payment success");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Internal error");
        }
    }
}