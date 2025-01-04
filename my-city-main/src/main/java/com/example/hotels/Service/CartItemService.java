package com.example.hotels.Service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hotels.Model.CartItem;
import com.example.hotels.Model.Product;
import com.example.hotels.Repository.CartItemRepository;
import com.example.hotels.Repository.ProductRepository;



@Service
public class CartItemService {
    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ProductRepository productRepository;

    public void addToCart(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid product ID: " + productId));

        List<CartItem> existingCartItems = cartItemRepository.findByProduct(product);
        
        if (!existingCartItems.isEmpty()) {
            // If the product already exists in the cart, increase the quantity
            CartItem cartItem = existingCartItems.get(0);
            cartItem.setQuantity(cartItem.getQuantity() + 1);
            cartItemRepository.save(cartItem);
        } else {
            // Add a new product to the cart
            CartItem cartItem = new CartItem();
            cartItem.setProduct(product);
            cartItem.setQuantity(1);
            cartItemRepository.save(cartItem);
        }
    }

    public List<CartItem> getAllCartItems() {
        return cartItemRepository.findAll();
    }

    public void deleteCartItem(Long cartItemId){
        cartItemRepository.deleteById(cartItemId);
    }

    public double calculateTotalAmount() {
        List<CartItem> cartItems = cartItemRepository.findAll();
        return cartItems.stream()
                        .mapToDouble(item -> item.getQuantity() * item.getProduct().getPrice())
                        .sum();
    }
    

    public void increaseQuantity(Long cartItemId) {
        CartItem cartItem = cartItemRepository.findById(cartItemId).orElseThrow(() -> 
                new RuntimeException("Cart item not found"));
        cartItem.setQuantity(cartItem.getQuantity() + 1);
        cartItemRepository.save(cartItem);
    }

    public void decreaseQuantity(Long cartItemId) {
        CartItem cartItem = cartItemRepository.findById(cartItemId).orElseThrow(() -> 
                new RuntimeException("Cart item not found"));
        if (cartItem.getQuantity() > 1) {
            cartItem.setQuantity(cartItem.getQuantity() - 1);
            cartItemRepository.save(cartItem);
        }
    }
}
