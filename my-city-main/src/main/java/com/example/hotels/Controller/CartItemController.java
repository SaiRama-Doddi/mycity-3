package com.example.hotels.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.hotels.Service.CartItemService;


@Controller
@RequestMapping("/cart")
public class CartItemController {
    
    @Autowired
    private CartItemService cartService;

    @PostMapping("/add/{productId}")
    public String addToCart(@PathVariable Long productId) {
        cartService.addToCart(productId);
        return "redirect:/cart/view"; // Redirect to the cart view page
    }

    @GetMapping("/view")
    public String viewCart(Model model) {
        model.addAttribute("cartItems", cartService.getAllCartItems());
        model.addAttribute("totalAmount", cartService.calculateTotalAmount());
        return "cart"; // Thymeleaf template for cart page
    }

    @GetMapping("/delete/{cartItemId}")
    public String deleteCartItem(@PathVariable Long cartItemId){
        cartService.deleteCartItem(cartItemId);
        return "redirect:/cart/view";
    }

    @GetMapping("/increase/{cartItemId}")
    public String increaseQuantity(@PathVariable Long cartItemId) {
        cartService.increaseQuantity(cartItemId);
        return "redirect:/cart/view"; // Redirect back to the cart page
    }

    @GetMapping("/decrease/{cartItemId}")
    public String decreaseQuantity(@PathVariable Long cartItemId) {
        cartService.decreaseQuantity(cartItemId);
        return "redirect:/cart/view"; // Redirect back to the cart page
    }
}
