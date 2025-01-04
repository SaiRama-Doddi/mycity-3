package com.example.hotels.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PaybillsController {
    
    @GetMapping("/beauty")
    public String paybillsPage(){
        return "beauty";
    }

    @GetMapping("/paybills_service")
    public String paybillsServicePage(){
        return "homeServices";
    }

    @GetMapping("/waxing")
    public String waxingPage(){
        return "waxing";
    }

    @GetMapping("/paybillsCart")
    public String paybills_cart(){
        return "paybills-cart";
    }

    @GetMapping("/paybills_cart_payment")
    public String paybills_cart_payment(){
        return "paybillsCartPayment";
    }
}
